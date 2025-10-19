/**
 * RST Document and Builder library.
 * Copyright (C) 2023-2025 Vladimir Ivanov <ivvlev@devives.com>.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.devives.rst.document.table;

import com.devives.rst.Rst;
import com.devives.rst.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridTable extends Table {

    @Override
    protected String serializeElements() {

        if (getChildren().isEmpty()) {
            return NL;
        }

        int tableNumColumns = getChildren().stream().mapToInt(row -> row.getChildren().stream().mapToInt(TableCell::getColspan).sum()).max().orElse(0);

        for (TableRow row : getChildren()) {
            int rowNumColumns = row.getChildren().stream().mapToInt(TableCell::getColspan).sum();

            if (rowNumColumns < tableNumColumns) {
                String cellType = row.getChildren().isEmpty() ? "td" : row.getChildren().get(row.getChildren().size() - 1).getType();
                row.getChildren().add(Rst.elements().tableCell(cellType, 1, tableNumColumns - rowNumColumns));
            }
        }

        int[] col_widths = new int[tableNumColumns];
        int[] row_heights = new int[getChildren().size()];


        for (int i = 0; i < getChildren().size(); i++) {
            int j = 0;
            for (TableCell cell : getChildren().get(i).getChildren()) {
                int current_w = 0;
                for (int k = j; k < j + cell.getColspan(); k++) {
                    current_w += col_widths[k];
                }
                int required_w = 0;
                String[] contents = cell.serialize().split(NL);
                for (String l : contents) {
                    required_w = Math.max(required_w, l.length());
                }

                if (required_w > current_w) {
                    int additional = required_w - current_w;
                    col_widths[j] = col_widths[j] + additional - (cell.getColspan() - 1) * (additional / cell.getColspan());
                    for (int jj = j + 1; jj < j + cell.getColspan(); jj++) {
                        col_widths[jj] = col_widths[jj] + (additional / cell.getColspan());
                    }
                }

                int current_h = row_heights[i];
                int required_h = contents.length;

                if (required_h > current_h) {
                    row_heights[i] = required_h;
                }

                j += cell.getColspan();
            }
        }

        String row_sep = "+" + String.join("+", Arrays.stream(col_widths).boxed().map(i -> StringUtils.buildString('-', i + 2)).toArray(String[]::new)) + "+";
        String header_sep = row_sep.replaceAll("-", "=");
        List<String> lines = new ArrayList<>();
        lines.add(row_sep);

        for (int i = 0; i < getChildren().size(); i++) {
            for (int y = 0; y < row_heights[i]; y++) {
                List<String> line = new ArrayList<>();
                int j = 0;
                for (TableCell c : getChildren().get(i).getChildren()) {
                    int w = 0;
                    for (int n = j; n < j + c.getColspan(); n++) {
                        w += col_widths[n] + 3;
                    }
                    w -= 2;
                    int h = row_heights[i];

                    line.add("| ");
                    String[] cell_lines = c.serialize().split(NL);
                    String content = y < cell_lines.length ? cell_lines[y] : "";
                    line.add(String.format("%-" + w + "s", content));

                    j += c.getColspan();
                }

                line.add("|");
                lines.add(String.join("", line));
            }

            if (i == 0 && getChildren().get(i).getChildren().stream().allMatch(c -> c.getType().equals("th"))) {
                lines.add(header_sep);
            } else {
                lines.add(row_sep);
            }
        }
        return String.join(NL, lines) + NL;
    }
}

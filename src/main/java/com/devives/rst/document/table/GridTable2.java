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

import com.devives.rst.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GridTable2 extends Table {

//    private String serializeRow(TableRow row) throws RstSerializationException {
//        StringBuilder sb = new StringBuilder();
//        for (Object item : row.getElements()) {
//            if (item == null) {
//                throw new RstSerializationException("Column is null");
//            }
//            if (item.toString().contains(Table.SEPARATOR)) {
//                throw new RstSerializationException("Column contains seperator char \"" + Table.SEPARATOR + "\"");
//            }
//            sb.append(Table.SEPARATOR);
//            sb.append(StringUtil.surroundValueWith(item.toString(), " "));
//            if (getElements().indexOf(item) == getElements().size() - 1) {
//                sb.append(Table.SEPARATOR);
//            }
//        }
//        return sb.toString();
//    }

    @Override
    public String serialize() {
        Map<Integer, Integer> columnWidths = getColumnWidths(getChildren(), minimumColumnWidth);

        StringBuilder sb = new StringBuilder();

        String rowSeparator = generateRowSeparator(columnWidths, alignments, "+", "-");
        sb.append(rowSeparator).append(NL);
        boolean headerSeperatorAdded = false;
        for (TableRow row : getChildren()) {

            sb.append(SEPARATOR);
            for (int columnIndex = 0; columnIndex < columnWidths.size(); columnIndex++) {

                String value = "";
                if (row.getChildren().size() > columnIndex) {
                    TableCell cell = row.getChildren().get(columnIndex);
                    if (cell != null) {
                        value = cell.serialize();
                    }
                }
                if (value.equals(trimmingIndicator)) {
                    value = fillUpLeftAligned(value, trimmingIndicator, columnWidths.get(columnIndex));
                    value = StringUtils.surroundValueWith(value, WHITESPACE);
                } else {
                    int alignment = getAlignment(alignments, columnIndex);
                    value = StringUtils.surroundValueWith(value, WHITESPACE);
                    value = fillUpAligned(value, WHITESPACE, columnWidths.get(columnIndex) + 2, alignment);
                }
                sb.append(value);
//                if (getElements().indexOf(row) < getElements().size() - 1 || getElements().size() == 1) {
//                    sb.append(NL);
//                }
                sb.append(SEPARATOR);
            }
            sb.append(NL);

//            if (getElements().indexOf(row) < getElements().size() - 1 || getElements().size() == 1) {
//                sb.append(NL);
//            }

            if (!headerSeperatorAdded && firstRowIsHeader) {
                headerSeperatorAdded = true;
                String headerSeparator = generateRowSeparator(columnWidths, alignments, "+", "=");
                sb.append(headerSeparator).append(NL);
            } else {
                sb.append(rowSeparator).append(NL);
            }
        }
        return sb.toString();
    }


    public static String generateRowSeparator(Map<Integer, Integer> columnWidths, List<Integer> alignments, String corner, String delim) {
        StringBuilder sb = new StringBuilder();
        for (int columnIndex = 0; columnIndex < columnWidths.entrySet().size(); columnIndex++) {
            sb.append(corner);

            String value = fillUpLeftAligned("", delim, columnWidths.get(columnIndex));

            int alignment = getAlignment(alignments, columnIndex);
            switch (alignment) {
                case ALIGN_RIGHT: {
                    value = WHITESPACE + value + ":";
                    break;
                }
                case ALIGN_CENTER: {
                    value = ":" + value + ":";
                    break;
                }
                default: {
                    value = StringUtils.surroundValueWith(value, delim);
                    break;
                }
            }

            sb.append(value);
            if (columnIndex == columnWidths.entrySet().size() - 1) {
                sb.append(corner);
            }
        }
        return sb.toString();
    }

    public static Map<Integer, Integer> getColumnWidths(List<TableRow> rows, int minimumColumnWidth) {
        Map<Integer, Integer> columnWidths = new HashMap<Integer, Integer>();
        if (rows.isEmpty()) {
            return columnWidths;
        }
        for (int columnIndex = 0; columnIndex < rows.get(0).getChildren().size(); columnIndex++) {
            columnWidths.put(columnIndex, getMaximumItemLength(rows, columnIndex, minimumColumnWidth));
        }
        return columnWidths;
    }

    public static int getMaximumItemLength(List<TableRow> rows, int columnIndex, int minimumColumnWidth) {
        int maximum = minimumColumnWidth;
        for (TableRow row : rows) {
            if (row.getChildren().size() < columnIndex + 1) {
                continue;
            }
            TableCell cell = row.getChildren().get(columnIndex);
            if (cell == null) {
                continue;
            }
            maximum = Math.max(cell.serialize().length(), maximum);
        }
        return maximum;
    }

    public static String fillUpAligned(String value, String fill, int length, int alignment) {
        switch (alignment) {
            case Table.ALIGN_RIGHT: {
                return fillUpRightAligned(value, fill, length);
            }
            case Table.ALIGN_CENTER: {
                return fillUpCenterAligned(value, fill, length);
            }
            default: {
                return fillUpLeftAligned(value, fill, length);
            }
        }
    }


    public static String fillUpLeftAligned(String value, String fill, int length) {
        if (value.length() >= length) {
            return value;
        }
        while (value.length() < length) {
            value += fill;
        }
        return value;
    }

    public static String fillUpRightAligned(String value, String fill, int length) {
        if (value.length() >= length) {
            return value;
        }
        while (value.length() < length) {
            value = fill + value;
        }
        return value;
    }

    public static String fillUpCenterAligned(String value, String fill, int length) {
        if (value.length() >= length) {
            return value;
        }
        boolean left = true;
        while (value.length() < length) {
            if (left) {
                value = fillUpLeftAligned(value, fill, value.length() + 1);
            } else {
                value = fillUpRightAligned(value, fill, value.length() + 1);
            }
            left = !left;
        }
        return value;
    }

}

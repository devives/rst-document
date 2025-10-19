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


import com.devives.rst.document.RstNodeAbst;

import java.util.ArrayList;
import java.util.List;

public class Table extends RstNodeAbst<TableRow> {

    public static final String SEPARATOR = "|";
    public static final String WHITESPACE = " ";
    public static final String DEFAULT_TRIMMING_INDICATOR = "~";
    public static final int DEFAULT_MINIMUM_COLUMN_WIDTH = 2;

    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 2;
    public static final int ALIGN_RIGHT = 3;

    private int headerRowCount_ = 0;

    protected List<Integer> alignments;
    protected boolean firstRowIsHeader;
    protected int minimumColumnWidth = DEFAULT_MINIMUM_COLUMN_WIDTH;
    protected String trimmingIndicator = DEFAULT_TRIMMING_INDICATOR;

    public Table() {
        this.alignments = new ArrayList<>();
        firstRowIsHeader = true;
    }

    public Table(List<TableRow> rows) {
        this();
        rows.forEach(this.getChildren()::add);
    }

    public Table(List<TableRow> rows, List<Integer> alignments) {
        this(rows);
        this.alignments = alignments;
    }

//
//    /**
//     * Removes {@link TableRow}s from the center of this table until only the requested amount of
//     * rows is left.
//     *
//     * @param rowsToKeep Amount of {@link TableRow}s that should not be removed
//     * @return the trimmed table
//     */
//    public Table trim(int rowsToKeep) {
//        rstElements = trim(this, rowsToKeep, trimmingIndicator).getRows();
//        return this;
//    }
//
//    /**
//     * Removes {@link TableRow}s from the center of the specified table until only the requested
//     * amount of rows is left.
//     *
//     * @param table             Table to remove {@link TableRow}s from
//     * @param rowsToKeep        Amount of {@link TableRow}s that should not be removed
//     * @param trimmingIndicator The content that trimmed cells should be filled with
//     * @return The trimmed table
//     */
//    public static Table trim(Table table, int rowsToKeep, String trimmingIndicator) {
//        if (table.getRows().size() <= rowsToKeep) {
//            return table;
//        }
//
//        int trimmedEntriesCount = table.getRows().size() - (table.getRows().size() - rowsToKeep);
//        int trimmingStartIndex = Math.round(trimmedEntriesCount / 2) + 1;
//        int trimmingStopIndex = table.getRows().size() - trimmingStartIndex;
//
//        List<TableRow> trimmedRows = new ArrayList<>();
//        for (int i = trimmingStartIndex; i <= trimmingStopIndex; i++) {
//            trimmedRows.add(table.getRows().get(i));
//        }
//
//        table.getRows().removeAll(trimmedRows);
//
//        TableRow trimmingIndicatorRow = new TableRow();
//        for (int columnIndex = 0; columnIndex < table.getRows().get(0).getColumns().size(); columnIndex++) {
//            trimmingIndicatorRow.getColumns().add(trimmingIndicator);
//        }
//        table.getRows().add(trimmingStartIndex, trimmingIndicatorRow);
//
//        return table;
//    }

    public static int getAlignment(List<Integer> alignments, int columnIndex) {
        if (alignments.isEmpty()) {
            return ALIGN_LEFT;
        }
        if (columnIndex >= alignments.size()) {
            columnIndex = alignments.size() - 1;
        }
        return alignments.get(columnIndex);
    }

    public List<Integer> getAlignments() {
        return alignments;
    }

    public void setAlignments(List<Integer> alignments) {
        this.alignments = alignments;

    }

    public boolean isFirstRowHeader() {
        return firstRowIsHeader;
    }

    public void useFirstRowAsHeader(boolean firstRowIsHeader) {
        this.firstRowIsHeader = firstRowIsHeader;

    }

    public int getMinimumColumnWidth() {
        return minimumColumnWidth;
    }

    public void setMinimumColumnWidth(int minimumColumnWidth) {
        this.minimumColumnWidth = minimumColumnWidth;

    }

    public String getTrimmingIndicator() {
        return trimmingIndicator;
    }

    public void setTrimmingIndicator(String trimmingIndicator) {
        this.trimmingIndicator = trimmingIndicator;
    }

    public int getHeaderRowCount() {
        return headerRowCount_;
    }

    public void setHeadRowCount(int headerRowCount) {
        headerRowCount_ = headerRowCount;
    }

    public boolean isFirstRowIsHeader() {
        return firstRowIsHeader;
    }

    public void setFirstRowIsHeader(boolean firstRowIsHeader) {
        this.firstRowIsHeader = firstRowIsHeader;
    }
}

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

import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNodeAbst;
import com.devives.rst.util.RstValidationException;

public class TableCell extends RstNodeAbst<RstElement> {
    private String type;
    private int rowspan;
    private int colspan;

    public TableCell() {
        this("td", 1, 1);
    }

    public TableCell(String type) {
        this(type, 1, 1);
    }

    public TableCell(String type, int rowspan, int colspan) {
        validateType(type);
        this.type = type;
        this.rowspan = rowspan;
        this.colspan = colspan;
    }

    private void validateType(String value) {
        if (!("th".equalsIgnoreCase(value) || "td".equalsIgnoreCase(value))) {
            throw new RstValidationException(String.format("Unexpected cell type value: '%s'. Only 'th' or 'td' are allowed.", value));
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    @Override
    protected void onCollectChildText(StringBuilder stringBuilder, String itemText) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(NL);
        }
        super.onCollectChildText(stringBuilder, itemText);
    }

}

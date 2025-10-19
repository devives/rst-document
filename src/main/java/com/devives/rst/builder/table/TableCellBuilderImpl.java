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
package com.devives.rst.builder.table;

import com.devives.rst.Rst;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.table.TableCell;

/**
 * RstElementBuilder of {@link TableCell} element.
 */
public class TableCellBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, RstElement, TableCell, TableCellBuilder<PARENT>>
        implements TableCellBuilder<PARENT> {

    @Override
    protected TableCell createRstElement() {
        return Rst.elements().tableCell();
    }

    public TableCellBuilder<PARENT> setColspan(int value) {
        getRstElement().setColspan(value);
        return this;
    }

    public TableCellBuilder<PARENT> setRowspan(int value) {
        getRstElement().setRowspan(value);
        return this;
    }

    public TableCellBuilder<PARENT> setCellType(String value) {
        getRstElement().setType(value);
        return this;
    }
}

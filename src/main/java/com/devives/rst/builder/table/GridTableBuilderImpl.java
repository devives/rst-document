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
import com.devives.rst.document.table.GridTable;
import com.devives.rst.document.table.TableRow;

import java.util.function.Consumer;

/**
 * *
 */
public class GridTableBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, TableRow, GridTable, GridTableBuilder<PARENT>>
        implements GridTableBuilder<PARENT> {

    @Override
    protected GridTable createRstElement() {
        return Rst.elements().gridTable();
    }

    @Override
    public TableRowBuilder<GridTableBuilder<PARENT>> beginRow() {
        return new TableRowBuilderImpl<GridTableBuilder<PARENT>>().setParentBuilder(this);
    }

    public GridTableBuilder<PARENT> row(Consumer<TableRowBuilder<GridTableBuilder<PARENT>>> consumer) {
        TableRowBuilder<GridTableBuilder<PARENT>> rowBuilder = beginRow();
        consumer.accept(rowBuilder);
        return rowBuilder.end();
    }

    public GridTableBuilder<PARENT> setHeadRowCount(int count) {
        getRstElement().setHeadRowCount(count);
        return this;
    }
}

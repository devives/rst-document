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
import com.devives.rst.document.table.TableCell;
import com.devives.rst.document.table.TableRow;

import java.util.function.Consumer;

/**
 * *
 */
public class TableRowBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, TableCell, TableRow, TableRowBuilder<PARENT>>
        implements TableRowBuilder<PARENT> {

    @Override
    protected TableRow createRstElement() {
        return Rst.elements().tableRow();
    }

    @Override
    public TableCellBuilder<TableRowBuilder<PARENT>> beginCell() {
        return new TableCellBuilderImpl<TableRowBuilder<PARENT>>().setParentBuilder(this);
    }

    @Override
    public TableCellBuilder<TableRowBuilder<PARENT>> beginHead() {
        return new TableCellBuilderImpl<TableRowBuilder<PARENT>>().setParentBuilder(this).setCellType("th");
    }

    @Override
    public TableRowBuilder<PARENT> cell(String text) {
        return cell(cb -> cb.paragraph(text));
    }

    @Override
    public TableRowBuilder<PARENT> cell(Consumer<TableCellBuilder<TableRowBuilder<PARENT>>> consumer) {
        TableCellBuilder<TableRowBuilder<PARENT>> cellBuilder = beginCell();
        consumer.accept(cellBuilder);
        return cellBuilder.end();
    }

    @Override
    public TableRowBuilder<PARENT> head(String text) {
        return head(hb -> hb.paragraph(text));
    }

    @Override
    public TableRowBuilder<PARENT> head(Consumer<TableCellBuilder<TableRowBuilder<PARENT>>> consumer) {
        TableCellBuilder<TableRowBuilder<PARENT>> cellBuilder = beginHead();
        consumer.accept(cellBuilder);
        return cellBuilder.end();
    }
}

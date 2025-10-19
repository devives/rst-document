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


import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.table.TableCell;
import com.devives.rst.document.table.TableRow;

import java.util.function.Consumer;

/**
 * *
 */
public interface TableRowBuilder<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, TableCell, TableRow, TableRowBuilder<PARENT>> {

    TableCellBuilder<TableRowBuilder<PARENT>> beginCell();

    TableCellBuilder<TableRowBuilder<PARENT>> beginHead();

    TableRowBuilder<PARENT> cell(Consumer<TableCellBuilder<TableRowBuilder<PARENT>>> consumer);

    TableRowBuilder<PARENT> cell(String text);

    TableRowBuilder<PARENT> head(Consumer<TableCellBuilder<TableRowBuilder<PARENT>>> consumer);

    TableRowBuilder<PARENT> head(String text);
}

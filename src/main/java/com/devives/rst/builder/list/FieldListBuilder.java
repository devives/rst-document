/**
 * RST Document and Builder library.
 * Copyright (C) 2023-2024 Vladimir Ivanov <ivvlev@devives.com>.
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
package com.devives.rst.builder.list;


import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.list.FieldList;
import com.devives.rst.document.list.FieldListItem;

import java.util.function.Consumer;

public interface FieldListBuilder<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, FieldListItem, FieldList, FieldListBuilder<PARENT>> {

    FieldListItemBuilder<FieldListBuilder<PARENT>> beginItem(String name);

    FieldListBuilder<PARENT> item(String name, Consumer<FieldListItemBuilder<FieldListBuilder<PARENT>>> consumer);

    FieldListBuilder<PARENT> item(String name, String value);

    FieldListBuilder<PARENT> item(String name, boolean value);

    FieldListBuilder<PARENT> item(String name, long value);

    FieldListBuilder<PARENT> item(String name, double value);

}

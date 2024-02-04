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

import com.devives.rst.Rst;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.list.FieldList;
import com.devives.rst.document.list.FieldListItem;

import java.util.function.Consumer;

public class FieldListBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, FieldListItem, FieldList, FieldListBuilder<PARENT>>
        implements FieldListBuilder<PARENT> {

    @Override
    protected FieldList createRstElement() {
        return Rst.elements().fieldList();
    }

    public FieldListBuilder<PARENT> item(String name, Consumer<FieldListItemBuilder<FieldListBuilder<PARENT>>> consumer) {
        FieldListItemBuilder<FieldListBuilder<PARENT>> itemBuilder = beginItem(name);
        consumer.accept(itemBuilder);
        return itemBuilder.end();
    }

    public FieldListItemBuilder<FieldListBuilder<PARENT>> beginItem(String name) {
        return new FieldListItemBuilderImpl<FieldListBuilder<PARENT>>(name).setParentBuilder(this);
    }

    @Override
    public FieldListBuilder<PARENT> item(String name, String value) {
        return item(name, itm -> itm.paragraph(value));
    }

    @Override
    public FieldListBuilder<PARENT> item(String name, boolean value) {
        return item(name, Boolean.toString(value));
    }

    @Override
    public FieldListBuilder<PARENT> item(String name, long value) {
        return item(name, Long.toString(value));
    }

    @Override
    public FieldListBuilder<PARENT> item(String name, double value) {
        return item(name, Double.toString(value));
    }
}

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
package com.devives.rst.builder.list;

import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;

import java.util.function.Consumer;

public abstract class ListBuilderAbst<PARENT extends RstNodeBuilder<?, ?, ?, ?>, E extends RstElement, T extends RstNode<E>, SELF extends RstNodeBuilder<PARENT, E, T, SELF>>
        extends RstNodeBuilderAbst<PARENT, E, T, SELF>
        implements ListBuilder<PARENT, E, T, SELF> {

    protected final char marker;

    public ListBuilderAbst(char marker) {
        this.marker = marker;
    }

    public ListItemBuilder<SELF> beginItem() {
        return new ListItemBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    public SELF item(Consumer<ListItemBuilder<SELF>> consumer) {
        ListItemBuilder<SELF> itemBuilder = beginItem();
        consumer.accept(itemBuilder);
        return itemBuilder.end();
    }

    @Override
    public SELF item(String text) {
        return item(b -> b.paragraph(text));
    }

}

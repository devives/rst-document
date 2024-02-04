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
import com.devives.rst.document.list.LineBlock;
import com.devives.rst.document.list.LineBlockItem;

import java.util.function.Consumer;


public class LineBlockBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, LineBlockItem, LineBlock, LineBlockBuilder<PARENT>>
        implements LineBlockBuilder<PARENT> {
    public LineBlockBuilderImpl() {
    }

    @Override
    protected LineBlock createRstElement() {
        return Rst.elements().lineBlock();
    }

    public LineBlockItemBuilder<LineBlockBuilder<PARENT>> beginItem() {
        return new LineBlockItemBuilderImpl<LineBlockBuilder<PARENT>>().setParentBuilder(this);
    }

    public LineBlockBuilder<PARENT> item(Consumer<LineBlockItemBuilder<LineBlockBuilder<PARENT>>> consumer) {
        LineBlockItemBuilder<LineBlockBuilder<PARENT>> itemBuilder = beginItem();
        consumer.accept(itemBuilder);
        return itemBuilder.end();
    }

    @Override
    public LineBlockBuilder<PARENT> item(String text) {
        return item(b -> b.text(text));
    }
}

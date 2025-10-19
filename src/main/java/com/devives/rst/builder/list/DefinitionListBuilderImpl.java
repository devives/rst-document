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

import com.devives.rst.Rst;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.list.DefinitionList;
import com.devives.rst.document.list.DefinitionListItem;

import java.util.Objects;
import java.util.function.Consumer;

public class DefinitionListBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, DefinitionListItem, DefinitionList, DefinitionListBuilder<PARENT>>
        implements DefinitionListBuilder<PARENT> {

    @Override
    protected DefinitionList createRstElement() {
        return Rst.elements().definitionList();
    }

    public DefinitionListBuilder<PARENT> item(String name, Consumer<DefinitionListItemBuilder<DefinitionListBuilder<PARENT>>> consumer) {
        DefinitionListItemBuilder<DefinitionListBuilder<PARENT>> itemBuilder = beginItem(name);
        consumer.accept(itemBuilder);
        return itemBuilder.end();
    }

    public DefinitionListItemBuilder<DefinitionListBuilder<PARENT>> beginItem(String name) {
        Objects.requireNonNull(name, "name");
        return new DefinitionListItemBuilderImpl<DefinitionListBuilder<PARENT>>(name).setParentBuilder(this);
    }

}

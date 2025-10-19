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
package com.devives.rst.builder.directive;

import com.devives.rst.Rst;
import com.devives.rst.builder.ParagraphBuilder;
import com.devives.rst.builder.ParagraphBuilderImpl;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.list.FieldListBuilder;
import com.devives.rst.builder.list.FieldListBuilderImpl;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.list.FieldList;
import com.devives.rst.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DirectiveBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends CompoundDirectiveBuilderAbst<PARENT, Directive, DirectiveBuilder<PARENT>>
        implements DirectiveBuilder<PARENT> {

    public DirectiveBuilderImpl(Directive.Type type) {
        super(type);
    }

    @Override
    protected Directive createRstElement() {
        return Rst.elements().directive(type_);
    }

    @Override
    public DirectiveBuilder<PARENT> setArguments(String value) {
        StringUtils.requireNotNullOrEmpty(value);
        getRstElement().setArguments(Collections.singletonList(Rst.elements().text(value)));
        return this;
    }

    @Override
    public ParagraphBuilder<DirectiveBuilder<PARENT>> beginArguments() {
        return new ParagraphBuilderImpl<DirectiveBuilder<PARENT>>() {
            @Override
            public DirectiveBuilder<PARENT> end() {
                DirectiveBuilderImpl.this.getRstElement().setArguments(this.build().getChildren());
                return getParentBuilder();
            }
        };
    }

    @Override
    public DirectiveBuilder<PARENT> arguments(Consumer<ParagraphBuilder<?>> consumer) {
        ParagraphBuilder<DirectiveBuilder<PARENT>> builder = beginArguments();
        consumer.accept(builder);
        return builder.end();
    }

    @Override
    public FieldListBuilder<DirectiveBuilder<PARENT>> beginOptions() {
        return new FieldListBuilderImpl<DirectiveBuilder<PARENT>>() {
            @Override
            public DirectiveBuilder<PARENT> end() {
                Map<String, Object> map = new HashMap<>();
                FieldList fieldList = this.build();
                fieldList.getChildren().forEach(it -> map.put(it.getName(), StringUtils.trim(it.getChildren().stream().map(RstElement::serialize).collect(Collectors.joining()))));
                DirectiveBuilderImpl.this.getRstElement().setOptions(map);
                return getParentBuilder();
            }
        }.setParentBuilder(this);
    }

    @Override
    public DirectiveBuilder<PARENT> options(Consumer<FieldListBuilder<?>> consumer) {
        FieldListBuilder<DirectiveBuilder<PARENT>> builder = beginOptions();
        consumer.accept(builder);
        return builder.end();
    }
}

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
package com.devives.rst.builder.directive;

import com.devives.rst.Rst;
import com.devives.rst.builder.ParagraphBuilder;
import com.devives.rst.builder.ParagraphBuilderImpl;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.directive.Directives;
import com.devives.rst.document.inline.InlineElement;

import java.util.List;
import java.util.function.Consumer;

/**
 * *
 */
public class TopicBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, RstElement, Directive, TopicBuilder<PARENT>>
        implements TopicBuilder<PARENT> {

    @Override
    protected Directive createRstElement() {
        return Rst.elements().directive(Directives.Topic);
    }

    @Override
    public TopicBuilder<PARENT> setTitle(String title) {
        List<InlineElement> arguments = getRstElement().getArguments();
        arguments.clear();
        arguments.add(Rst.elements().text(title));
        return this;
    }

    @Override
    public ParagraphBuilder<TopicBuilder<PARENT>> beginTitle() {
        return new ParagraphBuilderImpl<TopicBuilder<PARENT>>() {
            @Override
            public TopicBuilder<PARENT> end() {
                TopicBuilderImpl.this.getRstElement().setArguments(this.build().getChildren());
                return getParentBuilder();
            }
        };
    }

    @Override
    public TopicBuilder<PARENT> title(Consumer<ParagraphBuilder<?>> consumer) {
        ParagraphBuilder<TopicBuilder<PARENT>> builder = beginTitle();
        consumer.accept(builder);
        return builder.end();
    }

}

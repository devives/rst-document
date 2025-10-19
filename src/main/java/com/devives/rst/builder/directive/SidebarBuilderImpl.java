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
public class SidebarBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, RstElement, Directive, SidebarBuilder<PARENT>>
        implements SidebarBuilder<PARENT> {

    @Override
    protected Directive createRstElement() {
        return Rst.elements().directive(Directives.Sidebar);
    }

    @Override
    public SidebarBuilder<PARENT> setTitle(String title) {
        List<InlineElement> arguments = getRstElement().getArguments();
        arguments.clear();
        arguments.add(Rst.elements().text(title));
        return this;
    }

    @Override
    public ParagraphBuilder<SidebarBuilder<PARENT>> beginTitle() {
        return new ParagraphBuilderImpl<SidebarBuilder<PARENT>>() {
            @Override
            public SidebarBuilder<PARENT> end() {
                SidebarBuilderImpl.this.getRstElement().setArguments(this.build().getChildren());
                return getParentBuilder();
            }
        };
    }

    @Override
    public SidebarBuilder<PARENT> title(Consumer<ParagraphBuilder<?>> consumer) {
        ParagraphBuilder<SidebarBuilder<PARENT>> builder = beginTitle();
        consumer.accept(builder);
        return builder.end();
    }

    @Override
    public SidebarBuilder<PARENT> setSubTitle(String subTitle) {
        getRstElement().getOptions().put("subtitle", subTitle);
        return this;
    }
}

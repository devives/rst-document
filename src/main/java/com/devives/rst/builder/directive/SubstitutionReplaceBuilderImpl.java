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
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.directive.SubstitutionDefinitions;

import java.util.function.Consumer;


public class SubstitutionReplaceBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends SubstitutionDefinitionBuilderAbst<PARENT, SubstitutionReplaceBuilder<PARENT>>
        implements SubstitutionReplaceBuilder<PARENT> {

    public SubstitutionReplaceBuilderImpl(String target) {
        super(SubstitutionDefinitions.Replace, target);
    }

    @Override
    public SubstitutionReplaceBuilder<PARENT> replacement(Consumer<ParagraphBuilder<?>> consumer) {
        ParagraphBuilder<?> builder = Rst.builders().paragraph();
        consumer.accept(builder);
        getRstElement().setArguments(builder.build().getChildren());
        return this;
    }

    @Override
    public SubstitutionReplaceBuilder<PARENT> replacement(String text) {
        return replacement(p -> p.text(text));
    }
}

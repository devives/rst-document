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
import com.devives.rst.document.directive.Directives;
import com.devives.rst.document.directive.Figure;
import com.devives.rst.util.StringUtils;

import java.util.Collections;
import java.util.function.Consumer;


public class FigureBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends CompoundDirectiveBuilderAbst<PARENT, Figure, FigureBuilder<PARENT>>
        implements FigureBuilder<PARENT> {

    public FigureBuilderImpl(String imageUri) {
        super(Directives.Figure);
        setImageUri(imageUri);
    }

    @Override
    protected Figure createRstElement() {
        return Rst.elements().figure();
    }

    @Override
    public FigureBuilder<PARENT> setImageUri(String imageUri) {
        StringUtils.requireNotNullOrEmpty(imageUri);
        getRstElement().setArguments(Collections.singletonList(Rst.elements().text(imageUri)));
        return this;
    }

    public FigureBuilder<PARENT> setAlign(String align) {
        getRstElement().getOptions().put("align", align);
        return this;
    }

    public FigureBuilder<PARENT> setAlt(String alt) {
        getRstElement().getOptions().put("alt", alt);
        return this;
    }

    public FigureBuilder<PARENT> setHeight(String height) {
        getRstElement().getOptions().put("height", height);
        return this;
    }

    public FigureBuilder<PARENT> setWidth(String width) {
        getRstElement().getOptions().put("width", width);
        return this;
    }

    public FigureBuilder<PARENT> setScale(String scale) {
        getRstElement().getOptions().put("scale", scale);
        return this;
    }

    public FigureBuilder<PARENT> setLoading(String loading) {
        getRstElement().getOptions().put("loading", loading);
        return this;
    }

    @Override
    public FigureBuilder<PARENT> setFigClass(String figClasses) {
        getRstElement().getOptions().put("figclass", figClasses);
        return this;
    }

    @Override
    public ParagraphBuilder<PARENT> beginCaption() {
        return new ParagraphBuilderImpl<>();
    }

    @Override
    public FigureBuilder<PARENT> caption(Consumer<ParagraphBuilder<?>> consumer) {
        ParagraphBuilder<PARENT> builder = beginCaption();
        consumer.accept(builder);
        getRstElement().setCaptions(builder.build().getChildren());
        return this;
    }

    @Override
    public FigureBuilder<PARENT> caption(String caption) {
        caption(c -> c.text(caption));
        return this;
    }
}

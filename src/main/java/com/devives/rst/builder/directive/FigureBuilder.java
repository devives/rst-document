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


import com.devives.rst.builder.BodyBuilders;
import com.devives.rst.builder.ParagraphBuilder;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.directive.Figure;

import java.util.function.Consumer;

/**
 * @see Figure
 */
public interface FigureBuilder<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, RstElement, Figure, FigureBuilder<PARENT>>,
        BodyBuilders<PARENT, RstElement, Figure, FigureBuilder<PARENT>> {

    FigureBuilder<PARENT> setImageUri(String uri);

    /**
     * The horizontal alignment of the figure, allowing the image to float and have the text flow around it.
     * The specific behaviour depends upon the browser or rendering software used.
     *
     * @param align "left", "center", or "right"
     * @return this instance
     */
    FigureBuilder<PARENT> setAlign(String align);

    FigureBuilder<PARENT> setAlt(String alt);

    FigureBuilder<PARENT> setHeight(String height);

    FigureBuilder<PARENT> setWidth(String width);

    FigureBuilder<PARENT> setScale(String scale);

    FigureBuilder<PARENT> setLoading(String loading);

    /**
     * Set a classes attribute value on the figure element.
     *
     * @param figClasses space separated list of class names
     * @return this instance
     */
    FigureBuilder<PARENT> setFigClass(String figClasses);

    ParagraphBuilder<PARENT> beginCaption();

    FigureBuilder<PARENT> caption(Consumer<ParagraphBuilder<?>> consumer);

    FigureBuilder<PARENT> caption(String caption);
}

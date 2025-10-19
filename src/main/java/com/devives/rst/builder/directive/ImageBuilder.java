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


import com.devives.rst.builder.RstElementBuilder;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.directive.Directive;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#image">Image</a>
 */
public interface ImageBuilder<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstElementBuilder<PARENT, Directive, ImageBuilder<PARENT>> {

    ImageBuilder<PARENT> setImageUri(String uri);

    /**
     * The alignment of the image, equivalent to the HTML &lt;img&gt; tag's deprecated "align" attribute or the corresponding
     * "vertical-align" and "text-align" CSS properties. The values "top", "middle", and "bottom" control an image's
     * vertical alignment (relative to the text baseline); they are only useful for inline images (substitutions).
     * The values "left", "center", and "right" control an image's horizontal alignment, allowing the image to float
     * and have the text flow around it. The specific behaviour depends upon the browser or rendering software used.
     *
     * @param align "top", "middle", "bottom", "left", "center", or "right"
     * @return this instance
     */
    ImageBuilder<PARENT> setAlign(String align);

    ImageBuilder<PARENT> setAlt(String alt);

    ImageBuilder<PARENT> setHeight(String height);

    ImageBuilder<PARENT> setWidth(String width);

    ImageBuilder<PARENT> setScale(String scale);

    ImageBuilder<PARENT> setLoading(String loading);
}

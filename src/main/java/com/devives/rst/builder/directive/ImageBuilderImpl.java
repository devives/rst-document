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
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.directive.Directives;
import com.devives.rst.util.StringUtils;

import java.util.Collections;


public class ImageBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends DirectiveBuilderAbst<PARENT, Directive, ImageBuilder<PARENT>>
        implements ImageBuilder<PARENT> {

    public ImageBuilderImpl(String imageUri) {
        super(Directives.Image);
        setImageUri(imageUri);
    }

    @Override
    public ImageBuilder<PARENT> setImageUri(String imageUri) {
        StringUtils.requireNotNullOrEmpty(imageUri);
        getRstElement().setArguments(Collections.singletonList(Rst.elements().text(imageUri)));
        return this;
    }

    public ImageBuilder<PARENT> setAlign(String align) {
        getRstElement().getOptions().put("align", align);
        return this;
    }

    public ImageBuilder<PARENT> setAlt(String alt) {
        getRstElement().getOptions().put("alt", alt);
        return this;
    }

    public ImageBuilder<PARENT> setHeight(String height) {
        getRstElement().getOptions().put("height", height);
        return this;
    }

    public ImageBuilder<PARENT> setWidth(String width) {
        getRstElement().getOptions().put("width", width);
        return this;
    }

    public ImageBuilder<PARENT> setScale(String scale) {
        getRstElement().getOptions().put("scale", scale);
        return this;
    }

    public ImageBuilder<PARENT> setLoading(String loading) {
        getRstElement().getOptions().put("loading", loading);
        return this;
    }
}

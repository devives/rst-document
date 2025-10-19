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
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.directive.Directives;

import java.util.Collections;


public class CodeBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends CompoundDirectiveBuilderAbst<PARENT, Directive, CodeBuilder<PARENT>>
        implements CodeBuilder<PARENT> {

    public CodeBuilderImpl() {
        this(LANGUAGE_UNKNOWN);
    }

    public CodeBuilderImpl(String language) {
        super(Directives.Code);
        getRstElement().getArguments().add(Rst.elements().text(language));
    }

    @Override
    public CodeBuilder<PARENT> setNumberLines(boolean numberLines) {
        if (numberLines)
            getRstElement().getOptions().put("number-lines", null);
        else
            getRstElement().getOptions().remove("number-lines");
        return this;
    }

    public CodeBuilder<PARENT> setLanguage(String language) {
        getRstElement().setArguments(Collections.singletonList(Rst.elements().text(language)));
        return this;
    }
//
//    @Override
//    public QuoteBuilder beginQuote() {
//        return new QuoteBuilder(this, 0);
//    }
}

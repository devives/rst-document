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
import com.devives.rst.builder.RstElementBuilder;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.directive.SubstitutionDefinition;
import com.devives.rst.util.StringUtils;


public class SubstitutionDefinitionBuilderAbst<PARENT extends RstNodeBuilder<?, ?, ?, ?>, SELF extends RstElementBuilder<PARENT, SubstitutionDefinition, SELF>>
        extends DirectiveBuilderAbst<PARENT, SubstitutionDefinition, SELF> {

    private final String substitutionText_;

    public SubstitutionDefinitionBuilderAbst(SubstitutionDefinition.Type type, String substitutionText) {
        super(type);
        substitutionText_ = StringUtils.requireNotNullOrEmpty(substitutionText);
    }

    @Override
    public SubstitutionDefinition.Type getType() {
        return (SubstitutionDefinition.Type) super.getType();
    }

    @Override
    protected SubstitutionDefinition createRstElement() {
        return Rst.elements().substitutionDefinition(getType(), substitutionText_);
    }
}

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
package com.devives.rst.document.directive;

import com.devives.rst.document.RstElement;

import java.util.Objects;

public class SubstitutionDefinition extends DirectiveAbst<RstElement> {

    private final String substitutionText_;
    private final Type type_;

    public SubstitutionDefinition(Type type, String substitutionText) {
        type_ = Objects.requireNonNull(type);
        substitutionText_ = Objects.requireNonNull(substitutionText);
    }

    public String getSubstitutionText() {
        return substitutionText_;
    }

    @Override
    protected String getName() {
        return type_.getName();
    }

    @Override
    protected String serializeDefinition() {
        return ".. |" + getSubstitutionText() + "| " + getName() + "::";
    }

    public static class Type extends Directive.Type {
        public Type(String name) {
            super(name);
        }
    }


}

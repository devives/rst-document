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
package com.devives.rst.document.directive;

import com.devives.rst.document.RstElement;
import com.devives.rst.document.inline.InlineElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html">ReStructuredText Directives</a>
 */
public class Directive extends DirectiveAbst<RstElement> {

    private Type type_;

    public Directive(Type type) {
        this.type_ = Objects.requireNonNull(type);
    }

    protected String getName() {
        return type_.getName();
    }

    public Type getType() {
        return type_;
    }

    public void setType(Type type) {
        this.type_ = type;
    }

    @Override
    public Arguments getArguments() {
        return super.getArguments();
    }

    @Override
    public void setArguments(List<InlineElement> arguments) {
        super.setArguments(arguments);
    }

    @Override
    public Options getOptions() {
        return super.getOptions();
    }

    @Override
    public void setOptions(Map<String, Object> options) {
        super.setOptions(options);
    }

    public static class Type {
        private final String name_;

        public Type(String name) {
            this.name_ = name;
        }

        public String getName() {
            return name_;
        }
    }

}

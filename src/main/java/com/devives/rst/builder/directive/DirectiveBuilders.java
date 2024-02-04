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

import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.directive.Directive;

import java.util.function.Consumer;

public interface DirectiveBuilders<PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        E extends RstElement,
        T extends RstNode<E>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, E, T, SELF> {

    /**
     * Creates a new {@link DirectiveBuilder} instance and sets the language.
     *
     * @param type the type of directive
     * @return a new child builder instance
     */
    default DirectiveBuilder<SELF> beginDirective(Directive.Type type) {
        return new DirectiveBuilderImpl<SELF>(type).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link DirectiveBuilder} instance, and pass to the consumer.
     *
     * @param type     the type of directive
     * @param consumer builder
     * @return this instance
     */
    default SELF directive(Directive.Type type, Consumer<DirectiveBuilder<SELF>> consumer) {
        DirectiveBuilder<SELF> builder = beginDirective(type);
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Create new instance of {@link Directive} and append it to parent builder's list of children.
     *
     * @param type     the type of directive
     * @param argument the text argument of directive
     * @return this instance
     */
    default SELF directive(Directive.Type type, String argument) {
        DirectiveBuilder<SELF> builder = beginDirective(type);
        ((DirectiveBuilderImpl<?>) builder).addArgument(argument);
        return builder.end();
    }

}

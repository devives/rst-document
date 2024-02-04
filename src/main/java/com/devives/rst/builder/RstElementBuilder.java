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
package com.devives.rst.builder;

import com.devives.rst.document.RstElement;

import java.util.function.Consumer;

/**
 * Base interface that every Rst builder implements. Basically capable of
 * appending stuff to a parent {@link RstNodeBuilder}.
 */
public interface RstElementBuilder<
        PARENT extends RstNodeBuilder,
        RESULT extends RstElement,
        SELF extends RstElementBuilder<?, ?, ?>> {

    /**
     * Returns the built {@link RstElement}'s tree.
     *
     * @return root {@link RstElement}.
     */
    RESULT build();

    /**
     * Return parent builder instance.
     *
     * @return parent builder
     */
    PARENT getParentBuilder();

    /**
     * Method set parent builder instance.
     *
     * @param parentBuilder parent builder.
     * @return this instance.
     */
    SELF setParentBuilder(PARENT parentBuilder);

    /**
     * Method build {@link RstElement} and append it to parent builder's list of children.
     *
     * @return parent builder
     */
    default PARENT end() {
        PARENT parent = getParentBuilder();
        if (parent != null) {
            parent.addChild(this.build());
        }
        return parent;
    }

    default SELF ifTrue(boolean condition, Consumer<SELF> consumer) {
        if (condition) {
            consumer.accept((SELF) this);
        }
        return (SELF) this;
    }

    default SELF ifTrue(boolean condition, Runnable runnable) {
        if (condition) {
            runnable.run();
        }
        return (SELF) this;
    }

}

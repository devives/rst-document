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
import com.devives.rst.document.RstNode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Base class that every {@link RstNode} builder.
 */
public interface RstNodeBuilder<
        PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstElementBuilder<?, ?, ?>>
        extends RstElementBuilder<PARENT, RESULT, SELF> {

    /**
     * Method add the element to the children list.
     *
     * @param element element
     * @return this instance
     */
    default SELF addChild(CHILD element) {
        return addChildren(Collections.singleton(element));
    }

    /**
     * Method add the element to the children list.
     *
     * @param element element
     * @return this instance
     */
    default SELF addChild(int index, CHILD element) {
        return addChildren(index, Collections.singleton(element));
    }

    /**
     * Method add the elements collection to the children list.
     *
     * @param elements array of elements
     * @return this instance
     */
    default SELF addChildren(CHILD[] elements) {
        return addChildren(Arrays.asList(elements));
    }

    /**
     * Method add the elements collection to the children list.
     *
     * @param elements collection of elements
     * @return this instance
     */
    SELF addChildren(Collection<CHILD> elements);

    /**
     * Method add the elements collection to the children list at passed index.
     *
     * @param index    index at which to insert the first element from the
     *                 specified collection
     * @param elements collection containing elements to be added to this list
     * @return this instance
     */
    SELF addChildren(int index, Collection<CHILD> elements);

    /**
     * Method check the existence of children elements in the builder.
     *
     * @return {@code true}, if no child element was added to builder, else {@code false}.
     */
    boolean isEmpty();
//
//    default <TB extends RstElement, BUILDER extends CompletableRstElementBuilder<TB, BUILDER, SELF>> BUILDER beginBuilder(BUILDER builder) {
//        return builder;
//    }
//
//    default <TB extends RstElement, BUILDER extends RstElementBuilder<TB, BUILDER>> SELF builder(BUILDER builder, Consumer<BUILDER> consumer) {
//        consumer.accept(builder);
//        return (SELF) builder.end();
//    }

}

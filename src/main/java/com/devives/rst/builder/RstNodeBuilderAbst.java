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

import java.util.Collection;

/**
 * The  abstract builder of {@link RstNode}.
 *
 * @param <PARENT> The type of parent builder.
 * @param <CHILD>  The type of child RstElements.
 * @param <RESULT> The type of resul of building.
 * @param <SELF>   Self type/
 */
public abstract class RstNodeBuilderAbst<
        PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstElementBuilder<?, ?, ?>>
        extends RstElementBuilderAbst<PARENT, RESULT, SELF>
        implements RstNodeBuilder<PARENT, CHILD, RESULT, SELF> {

    public SELF addChildren(Collection<CHILD> elements) {
        getRstElement().getChildren().addAll(elements);
        return (SELF) this;
    }

    @Override
    public SELF addChildren(int index, Collection<CHILD> elements) {
        getRstElement().getChildren().addAll(index, elements);
        return (SELF) this;
    }

    @Override
    public boolean isEmpty() {
        return getRstElement().getChildren().isEmpty();
    }

}

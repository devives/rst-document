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
package com.devives.rst.builder;

import com.devives.rst.document.RstElement;

/**
 * *
 */
public abstract class RstElementBuilderAbst<PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        T extends RstElement,
        SELF extends RstElementBuilder<?, ?, ?>>
        implements RstElementBuilder<PARENT, T, SELF> {

    /**
     * The root element that content will be appended too.
     */
    private T rstElement;

    private PARENT parentBuilder;

    protected T getRstElement() {
        if (rstElement == null) {
            rstElement = createRstElement();
        }
        return rstElement;
    }

    /**
     * Creates the root element. Typically without any content.
     *
     * @return the root Rst element
     */
    protected abstract T createRstElement();

    /**
     * Returns the root {@link RstElementBuilderAbst#rstElement}
     *
     * @return {@link RstElementBuilderAbst#rstElement}
     */
    public final T build() {
        T element = getRstElement();
        preBuild(element);
        onBuild(element);
        postBuild(element);
        return element;
    }

    protected void preBuild(T element) {

    }

    protected void onBuild(T element) {

    }

    protected void postBuild(T element) {

    }

    public SELF setParentBuilder(PARENT parentBuilder) {
        this.parentBuilder = parentBuilder;
        return (SELF) this;
    }

    public PARENT getParentBuilder() {
        return parentBuilder;
    }

}

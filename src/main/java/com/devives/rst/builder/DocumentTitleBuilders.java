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

import com.devives.rst.Rst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.Title;

public interface DocumentTitleBuilders<PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends TitleBuilders<PARENT, CHILD, RESULT, SELF> {

    /**
     * Appends a {@link Title} element with level 1 to the parent {@link RstNodeBuilder}.
     *
     * @param text     value for the new element
     * @param overline overline flag value
     * @return the builder instance
     * @see Title#Title(String, int)
     */
    default SELF title(String text, boolean overline) {
        Title title = Rst.elements().title(text, 1, overline);
        addChild((CHILD) title);
        return (SELF) this;
    }

    /**
     * Appends a {@link Title} element with level 2 to the parent {@link RstNodeBuilder}.
     *
     * @param text     value for the new element
     * @param overline overline flag value
     * @return the builder instance
     * @see Title#Title(String, int)
     */
    default SELF subTitle(String text, boolean overline) {
        Title title = Rst.elements().title(text, 2, overline);
        addChild((CHILD) title);
        return (SELF) this;
    }

}

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
package com.devives.rst.builder.inline;

import com.devives.rst.Rst;
import com.devives.rst.builder.RstElementBuilder;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.inline.Text;

/**
 * *
 */
public interface TextBuilders<PARENT extends RstNodeBuilder<?, ?, ?, ?>, E extends RstElement, T extends RstNode<E>, SELF extends TextBuilders<PARENT, E, T, SELF>>
        extends RstNodeBuilder<PARENT, E, T, SELF> {

    /**
     * Appends a normal {@link Text} element to the root {@link RstElementBuilder}.
     *
     * @param value value for the new element
     * @return the builder instance
     * @see Text#Text(String)
     */
    default SELF text(String value) {
        return addChild((E) Rst.elements().text(value));
    }

    /**
     * Appends a new line to the existing root {@link RstElementBuilder}.
     *
     * @return the builder instance
     */
    default SELF lineBreak() {
        return lineBreaks(1);
    }

    /**
     * Appends a new line to the existing root {@link RstElementBuilder}
     * if it not already ends with a new line.
     *
     * @return the builder instance
     */
    default SELF lineBreaks(int count) {
        for (int i = 0; i < count; i++) {
            addChild((E) Rst.elements().text(System.lineSeparator()));
        }
        return (SELF) this;
    }

//    default B lineBlock(Consumer<LineBlockBuilder> consumer) {
//        LineBlockBuilder lineBlockBuilder = new LineBlockBuilder(this);
//        consumer.accept(lineBlockBuilder);
//        return append((E) lineBlockBuilder.build());
//    }
//
//    /**
//     * Creates a new {@link LineBlockBuilder} instance.
//     *
//     * @return a new child builder instance
//     * @see LineBlockBuilder#LineBlockBuilder()
//     */
//    default LineBlockBuilder beginLineBlock() {
//        return new LineBlockBuilder(this);
//    }

//    /**
//     * Creates a new shifted {@link ParagraphBuilder} instance.
//     *
//     * @return a new child builder instance
//     * @see ParagraphBuilder#ParagraphBuilder(RstNodeBuilder)
//     * @see ParagraphBuilder#ParagraphBuilder(RstNodeBuilder)
//     */
//    default B quote(Consumer<QuoteBuilder> consumer) {
//        QuoteBuilder quoteBuilder = new QuoteBuilder(this, 3);
//        consumer.accept(quoteBuilder);
//        return append((E) quoteBuilder.build());
//    }
//
//    default QuoteBuilder beginQuote() {
//        return new QuoteBuilder(this, 3);
//    }


}

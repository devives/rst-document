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

/**
 *
 */
public interface RstBuilderFactory {

    /**
     * Return the new instance of {@link RstDocumentBuilder}.
     *
     * @return {@link RstDocumentBuilder} instance
     */
    default <PARENT extends RstNodeBuilder<?, ?, ?, ?>> RstDocumentBuilder<PARENT> document() {
        return new RstDocumentBuilderImpl<PARENT>();
    }

    /**
     * Return the new instance of {@link ParagraphBuilder}.
     *
     * @return {@link ParagraphBuilder} instance
     */
    default <PARENT extends RstNodeBuilder<?, ?, ?, ?>> ParagraphBuilder<PARENT> paragraph() {
        return new ParagraphBuilderImpl<PARENT>();
    }

    /**
     * Return the new instance of {@link SectionBuilder}.
     *
     * @return {@link SectionBuilder} instance
     */
    default <PARENT extends RstNodeBuilder<?, ?, ?, ?>> SectionBuilder<PARENT> section() {
        return new SectionBuilderImpl<PARENT>();
    }
}

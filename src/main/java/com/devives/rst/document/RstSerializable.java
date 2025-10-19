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
package com.devives.rst.document;

import com.devives.rst.util.RstSerializationException;

/**
 * Interface declare the common methods of Objects, serializable to ReStructuredText.
 */
public interface RstSerializable {

    /**
     * Attempts to generate a String representing this Rst element.
     *
     * @return Rst as String
     * @throws RstSerializationException If unable to generate a Rst String.
     */
    String serialize() throws RstSerializationException;

    /**
     * Returns the result of {@link RstElement#serialize()} or the specified fallback if a
     * {@link RstSerializationException} occurred.
     *
     * @param fallback String to return if serialization fails
     * @return Rst as String or specified fallback
     */
    default String getSerialized(String fallback) {
        try {
            return serialize();
        } catch (RstSerializationException e) {
            return fallback;
        }
    }
}

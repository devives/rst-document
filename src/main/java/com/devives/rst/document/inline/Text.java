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
package com.devives.rst.document.inline;

import com.devives.rst.util.RstSerializationException;

/**
 * The class of plain text RST element.
 */
public class Text implements InlineElement {

    protected String value;

    public Text(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Text setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String serialize() throws RstSerializationException {
        return String.valueOf(value);
    }
}

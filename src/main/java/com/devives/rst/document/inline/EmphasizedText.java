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

import com.devives.rst.document.RstElement;
import com.devives.rst.util.RstSerializationException;

import java.util.Objects;

/**
 * {@link RstElement}s that can be surrounded by prefix and suffix should
 * implement this interface.
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#character-level-inline-markup-1">Character-Level Inline Markup</a>
 */
public class EmphasizedText extends Text {

    private final String emphasisChars_;

    public EmphasizedText(String emphasisChars, String value) {
        super(value);
        emphasisChars_ = Objects.requireNonNull(emphasisChars, "emphasisChars");
    }

    /**
     * @return the string that should be added before the value
     */
    public String getPrefix() {
        return emphasisChars_;
    }

    /**
     * @return the string that should be added after the value
     */
    public String getSuffix() {
        return getPrefix();
    }

    protected String formatValue(String value) {
        return value;
    }

    @Override
    public String serialize() throws RstSerializationException {
        return getPrefix() + formatValue(super.serialize()) + getSuffix();
    }

}

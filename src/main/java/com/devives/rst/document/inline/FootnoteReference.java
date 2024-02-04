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
 * The result is substituted in from the substitution definition. It could be text, an image, a hyperlink, or a combination of these and others.
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#footnote-references">Footnote References</a>
 */
public class FootnoteReference extends Text {

    public FootnoteReference(String value) {
        super(value);
    }

    /**
     * @return the string that should be added before the value
     */
    public String getPrefix() {
        return "[";
    }

    /**
     * @return the string that should be added after the value
     */
    public String getSuffix() {
        return "]_";
    }

    protected String formatValue(String value) {
        return value;
    }

    @Override
    public String serialize() throws RstSerializationException {
        return getPrefix() + formatValue(super.serialize()) + getSuffix();
    }
}

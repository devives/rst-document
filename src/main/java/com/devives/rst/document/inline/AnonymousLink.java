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
package com.devives.rst.document.inline;

import com.devives.rst.util.RstSerializationException;

/**
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#anonymous-hyperlinks">Anonymous Hyperlinks</a>
 */
public class AnonymousLink extends Link {

    public AnonymousLink(String target) {
        super(target);
    }

    public AnonymousLink(String target, String text) {
        super(target, text);
    }

    @Override
    public String serialize() throws RstSerializationException {
        if (getText() == null || getText().isEmpty()) {
            return "`" + getTarget() + "`__";
        } else {
            return "`" + getText() + " <" + getTarget() + ">" + "`__";
        }

    }

}

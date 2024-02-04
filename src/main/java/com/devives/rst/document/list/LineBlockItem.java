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
package com.devives.rst.document.list;

import com.devives.rst.document.RstNodeAbst;
import com.devives.rst.document.inline.InlineElement;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

public class LineBlockItem extends RstNodeAbst<InlineElement> {

    @Override
    public String serialize() throws RstSerializationException {
        String text = super.serialize();
        text = StringUtils.shiftLeftEdge(text, 2);
        if (text.length() >= 2) {
            text = "| " + text.substring(2);
        }
        return StringUtils.endWithNL(text);
    }
}

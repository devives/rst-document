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
package com.devives.rst.document;

import com.devives.rst.document.inline.InlineElement;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

/**
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#literal-blocks">Literal Blocks</a>
 */
public class IndentedLiteralBlock extends RstNodeAbst<InlineElement> {

    @Override
    public String serialize() throws RstSerializationException {
        String text = super.serialize();
        String shiftedText = StringUtils.shiftLeftEdge(text, 3);
        return StringUtils.endWithNL(shiftedText);
    }
}

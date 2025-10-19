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
import com.devives.rst.util.StringUtils;

/**
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#block-quotes">Block Quotes</a>
 */
public class BlockQuote extends RstNodeAbst<RstElement> {

    private int indent = 3;

    public BlockQuote() {
    }

    public BlockQuote(int indent) {
        this.indent = indent;
    }

    /**
     * Method return the indent of the text block.
     *
     * @return indent value
     */
    public int getIndent() {
        return indent;
    }

    /**
     * Method set {@link #getIndent()} value.
     *
     * @param indent value
     */
    public void setIndent(int indent) {
        this.indent = indent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCollectChildText(StringBuilder stringBuilder, String itemText) {
        // Add line break between items.
        if (stringBuilder.length() > 0) {
            stringBuilder.append(NL);
        }
        super.onCollectChildText(stringBuilder, itemText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize() throws RstSerializationException {
        String definition = ".." + NL + NL;
        String content = super.serialize();

        StringBuilder sb = new StringBuilder(definition);
        if (StringUtils.notNullOrEmpty(content)) {
            sb.append(StringUtils.shiftLeftEdge(content, indent));
        }
        return StringUtils.endWithNL(sb.toString());
    }
}

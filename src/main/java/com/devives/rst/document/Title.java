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

import com.devives.rst.Rst;
import com.devives.rst.document.inline.Text;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/doctree.html#title">The Docutils Document Tree: Title</a>
 */
public class Title extends RstNodeAbst<Text> implements StructuralSubElement<Text> {

    public static final int MINIMUM_LEVEL = 1;
    public static final int MAXIMUM_LEVEL = 6;

    /**
     * Array contains all allowed characters declared in <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#sections"></a>
     */
    public static final char[] UNDERLINE_CHARS = {'=', '-', '^', '~', '*', '+', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', ',', '.', '/', ':', ';', '<', '>', '?', '@', '[', '\\', ']', '_', '`', '{', '|', '}'};

    private int level;
    private boolean overline;

    public Title(String value) {
        this.level = MINIMUM_LEVEL;
        getChildren().add(Rst.elements().text(value));
    }

    public Title(String value, int level) {
        super();
        this.level = trimLevel(level);
        getChildren().add(Rst.elements().text(value));
    }

    public Title(String value, int level, boolean overline) {
        super();
        this.level = trimLevel(level);
        this.overline = overline;
        getChildren().add(Rst.elements().text(value));
    }

    @Override
    protected String serializeElements() {
        String text = super.serializeElements();
        char chr = UNDERLINE_CHARS[level - 1];
        int length = text != null ? text.length() : 0;
        String underline = StringUtils.buildString(chr, length);
        return (overline)
                ? underline + NL + text + NL + underline
                : text + NL + underline;
    }

    @Override
    public String serialize() throws RstSerializationException {
        return super.serialize() + NL;
    }

    protected int trimLevel(int level) {
//        if (level < MINIMUM_LEVEL || MAXIMUM_LEVEL < level) {
//            throw new RstBuildingException('Invalid title level: ' + level + '. Value must be between ' + MINIMUM_LEVEL + ' and ' + MAXIMUM_LEVEL);
//        }
        return Math.min(MAXIMUM_LEVEL, Math.max(MINIMUM_LEVEL, level));
    }

    /**
     * Returns the title level.
     * <p>
     * Primary title has level "1".
     *
     * @return title level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the {@link #level} property.
     *
     * @param level new value: 1 .. 6
     */
    public void setLevel(int level) {
        this.level = trimLevel(level);
    }

    /**
     * Returns the value of the flag that controls the overlining of title text.
     * <p>
     * The underlined text:
     * <pre>
     * Header text
     * ===========
     * </pre>
     * The overlined and underlined text:
     * <pre>
     * ===========
     * Header text
     * ===========
     * </pre>
     *
     * @return title level
     */
    public boolean isOverline() {
        return overline;
    }

    /**
     * Sets the {@link #isOverline()} property.
     *
     * @param overline new value
     */
    public void setOverline(boolean overline) {
        this.overline = overline;
    }
}

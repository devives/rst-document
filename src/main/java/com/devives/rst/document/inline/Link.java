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

import com.devives.rst.document.Target;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

import java.util.Objects;

/**
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#hyperlink-references">Hyperlink References</a>
 */
public class Link implements InlineElement {

    private String text_;
    private String target_;

    /**
     * @param target Name of internal document {@link Target} or {@link InlineAnchor}.
     */
    public Link(String target) {
        target_ = target;
    }

    /**
     * @param target Name of internal document {@link Target} or {@link InlineAnchor} or URL to external resource.
     * @param text   Display text.
     */
    public Link(String target, String text) {
        this.text_ = text;
        this.target_ = Objects.requireNonNull(target, "target");
    }

    protected String getText() {
        return text_;
    }

    protected void setText(String text) {
        this.text_ = text;
    }

    public String getTarget() {
        return target_;
    }

    public void setTarget(String target) {
        this.target_ = Objects.requireNonNull(target, "target");
    }

    @Override
    public String serialize() throws RstSerializationException {
        String text = getText();
        if (StringUtils.notNullOrEmpty(text)) {
            return "`" + text + " <" + getTarget() + ">" + "`_";
        } else {
            return "`" + getTarget() + "`_";
        }
    }

}

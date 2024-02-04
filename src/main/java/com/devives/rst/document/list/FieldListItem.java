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

import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNodeAbst;
import com.devives.rst.util.StringUtils;

import static com.devives.rst.util.StringUtils.stripStart;

public class FieldListItem extends RstNodeAbst<RstElement> {
    private String name_;

    public FieldListItem(String name) {
        name_ = StringUtils.requireNotNullOrEmpty(name);
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        name_ = StringUtils.requireNotNullOrEmpty(name);
    }

    @Override
    protected void onCollectChildText(StringBuilder stringBuilder, String itemText) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(NL);
        }
        super.onCollectChildText(stringBuilder, itemText);
    }

    @Override
    protected String serializeElements() {
        String elementsText = super.serializeElements();
        //elementsText = StringUtils.endWithNL(elementsText);
        // Shift `3` is - ":: ".length()
        return ":" + name_ + ": " + stripStart(StringUtils.shiftLeftEdge(elementsText, name_.length() + 3), SPACE);
    }

}

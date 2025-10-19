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

import java.util.Objects;

public class Role implements InlineElement {
    private String name_;
    private String target_;
    private String title_;

    public Role(String name, String target) {
        this(name, target, null);
    }

    public Role(String name, String target, String title) {
        name_ = Objects.requireNonNull(name);
        target_ = Objects.requireNonNull(target);
        title_ = title;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        name_ = Objects.requireNonNull(name);
    }

    public String getTarget() {
        return target_;
    }

    public void setTarget(String target) {
        target_ = Objects.requireNonNull(target);
    }

    public String getTitle() {
        return title_;
    }

    public void setTitle(String title) {
        title_ = title;
    }

    @Override
    public String serialize() throws RstSerializationException {
        if (title_ != null) {
            return ":" + name_ + ":`" + title_ + " <" + target_ + ">`";
        } else {
            return ":" + name_ + ":`" + target_ + "`";
        }
    }
}

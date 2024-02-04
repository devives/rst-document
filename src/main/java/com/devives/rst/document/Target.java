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

import com.devives.rst.util.StringUtils;

import java.util.Objects;

/**
 * A cross-reference target within text.
 * These are also called explicit hyperlink targets.
 * <a href="https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#hyperlink-targets">Hyperlink Targets</a>
 */
public class Target extends RstNodeAbst<RstElement> {

    private String target_;
    private String name_;

    public Target(String target) {
        this.target_ = Objects.requireNonNull(target);
    }

    public Target(String target, String name) {
        target_ = Objects.requireNonNull(target);
        name_ = Objects.requireNonNull(name);
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = Objects.requireNonNull(name);
    }

    public String getTarget() {
        return target_;
    }

    public void setTarget(String target) {
        target_ = target;
    }

    @Override
    protected String serializeElements() {
        String result;
        String target = target_.contains(" ") || target_.contains("\n")
                ? "`" + target_ + "`"
                : target_;
        if (name_ == null || name_.isEmpty()) {
            result = ".. _" + target_ + ":";
        } else {
            result = ".. _" + name_ + ": " + target;
        }
        return StringUtils.endWithNL(result);
    }

}

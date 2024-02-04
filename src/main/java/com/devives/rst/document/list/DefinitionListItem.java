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

import com.devives.rst.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefinitionListItem extends ListItem {
    private String name_;
    private String[] classifiers_;

    public DefinitionListItem(String name, String... classifiers) {
        name_ = Objects.requireNonNull(name, "name");
        classifiers_ = classifiers;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        name_ = Objects.requireNonNull(name, "name");
    }

    public String[] getClassifiers() {
        return classifiers_;
    }

    public void setClassifiers(String[] classifiers) {
        classifiers_ = classifiers;
    }

    @Override
    protected String serializeElements() {
        String name = name_.startsWith("-") ? "\\" + name_ : name_;
        String classifiersText = classifiers_ != null
                ? Arrays.stream(classifiers_).map(s -> " : " + s).collect(Collectors.joining())
                : "";
        String elementsText = super.serializeElements();
        elementsText = StringUtils.endWithNL(elementsText);

        return name + classifiersText + NL
                + StringUtils.shiftLeftEdge(elementsText, 3);
    }

}

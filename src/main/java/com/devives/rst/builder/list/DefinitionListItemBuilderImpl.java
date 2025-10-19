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
package com.devives.rst.builder.list;

import com.devives.rst.Rst;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.builder.RstNodeBuilderAbst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.list.DefinitionListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefinitionListItemBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilderAbst<PARENT, RstElement, DefinitionListItem, DefinitionListItemBuilder<PARENT>>
        implements DefinitionListItemBuilder<PARENT> {

    private String name_;
    private String[] classifiers_;

    public DefinitionListItemBuilderImpl(String name) {
        this(name, new String[0]);
    }

    public DefinitionListItemBuilderImpl(String name, String[] classifiers) {
        name_ = name;
        classifiers_ = classifiers;
    }

    @Override
    protected DefinitionListItem createRstElement() {
        return Rst.elements().definitionListItem(name_, classifiers_);
    }

    public void setName(String name) {
        name_ = name;
    }

    public DefinitionListItemBuilder<PARENT> setClassifiers(String[] classifiers) {
        classifiers_ = classifiers;
        return this;
    }

    public DefinitionListItemBuilder<PARENT> addClassifier(String classifier) {
        List<String> list = new ArrayList<>(Arrays.asList(classifiers_ != null ? classifiers_ : new String[0]));
        list.add(classifier);
        classifiers_ = list.toArray(new String[0]);
        return this;
    }
}
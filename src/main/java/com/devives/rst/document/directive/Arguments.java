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
package com.devives.rst.document.directive;

import com.devives.rst.Rst;
import com.devives.rst.document.inline.InlineElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Arguments extends ArrayList<InlineElement> {

    Arguments() {
    }

    Arguments(Collection<? extends InlineElement> c) {
        super(c);
    }

    public void add(String text) {
        add(Rst.elements().text(text));
    }

    public void addAll(String... texts) {
        addAll(Arrays.stream(texts).map(Rst.elements()::text).collect(Collectors.toList()));
    }

}

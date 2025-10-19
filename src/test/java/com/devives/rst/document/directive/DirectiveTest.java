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
package com.devives.rst.document.directive;

import com.devives.rst.AbstractTest;
import com.devives.rst.builder.directive.DirectiveBuilderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectiveTest extends AbstractTest {

    @Test
    public void serialize_expectedString() throws Exception {
        Directive directive = new DirectiveBuilderImpl<>(Directives.Contents)
                .setArguments("Overview")
                .options(opts -> opts.item("depth", 2).item("some-option", "some-value"))
                .build();

        String result = directive.serialize();
        System.out.print(result);
        String expected = ".. contents:: Overview" + NL +
                "   :depth: 2" + NL +
                "   :some-option: some-value" + NL;
        Assertions.assertEquals(expected, result);
    }

}

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
package com.devives.rst.builder;

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SectionBuilderTest extends AbstractTest {

    @Test
    public void title() {
        String result = Rst.builders().section().title("RstDocument title").build().serialize();
        System.out.print(result);
        String expected =
                "RstDocument title" + NL +
                        "=================" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void section() {
        String result = Rst.builders()
                .section()
                .title("Section")
                .section(sectionBuilder -> {
                    sectionBuilder.title("Nested Section 1", 1);
                })
                .beginSection()
                .title("Nested Section 2", 2)
                .end()
                .build().serialize();
        System.out.print(result);
        String expected = EMPTY +
                "Section" + NL +
                "=======" + NL +
                NL +
                "Nested Section 1" + NL +
                "================" + NL +
                NL +
                "Nested Section 2" + NL +
                "----------------" + NL;
        Assertions.assertEquals(expected, result);
    }

}

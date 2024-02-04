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

public class ParagraphBuilderTest extends AbstractTest {

    @Test
    public void serialize_spaceBetweenTwoTexts_equalsExpected() throws Exception {
        String result =  Rst.builders().paragraph()
                .text("Fist")
                .text(SPACE)
                .text("Second")
                .build()
                .serialize();
        System.out.print(result);
        String expected = "Fist Second" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_twoParagraphs_equalsExpected() throws Exception {
        String result = Rst.builders().document()
                .paragraph("")
                .paragraph(pb -> {
                })
                .build().serialize();
        System.out.print(result);
        String expected = NL + NL + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void beginCodeBlock() throws Exception {
        String result = Rst.builders().document()
                .paragraph(paragraphBuilder -> paragraphBuilder.text("Code sample."))
                .beginCode("java")
                .text(JAVA_CODE_SAMPLE)
                .end()
                .build().serialize();
        System.out.print(result);
        String expected = "Code sample." + NL +
                NL +
                ".. code:: java" + NL +
                NL +
                "   for (int i = 0; i < 10; i++) {" + NL +
                "       System.out.println(i);" + NL +
                "   }" + NL;
        Assertions.assertEquals(expected, result);
    }
}

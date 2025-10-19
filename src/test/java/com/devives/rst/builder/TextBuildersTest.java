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
package com.devives.rst.builder;

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import com.devives.rst.document.Paragraph;
import com.devives.rst.document.inline.EmphasizedText;
import com.devives.rst.document.inline.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextBuildersTest extends AbstractTest {

    @Test
    public void text__instanceOfText() throws Exception {
        Paragraph paragraph = Rst.builders().paragraph().text("").build();
        Assertions.assertInstanceOf(Text.class, paragraph.getChildren().get(0));
    }

    @Test
    public void bold__instanceOfEmphasizedText() throws Exception {
        Paragraph paragraph = Rst.builders().paragraph().bold("").build();
        Assertions.assertInstanceOf(EmphasizedText.class, paragraph.getChildren().get(0));
    }

    @Test
    public void italic__instanceOfEmphasizedText() throws Exception {
        Paragraph paragraph = Rst.builders().paragraph().italic("").build();
        Assertions.assertInstanceOf(EmphasizedText.class, paragraph.getChildren().get(0));
    }

    @Test
    public void literal__instanceOfEmphasizedText() throws Exception {
        Paragraph paragraph = Rst.builders().paragraph().literal("").build();
        Assertions.assertInstanceOf(EmphasizedText.class, paragraph.getChildren().get(0));
    }

    @Test
    public void interpreted__instanceOfEmphasizedText() throws Exception {
        Paragraph paragraph = Rst.builders().paragraph().interpreted("").build();
        Assertions.assertInstanceOf(EmphasizedText.class, paragraph.getChildren().get(0));
    }

    @Test
    public void lineBreaks_betweenTexts_expected() throws Exception {
        String result = Rst.builders().paragraph()
                .text("foo")
                .lineBreaks(2)
                .text("bar")
                .build().serialize();
        System.out.print(result);
        String expected = "foo" + NL + NL + "bar" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void lineBreaks_atBeginning_expected() throws Exception {
        String result = Rst.builders().paragraph()
                .lineBreaks(2)
                .text("foobar")
                .build().serialize();
        System.out.print(result);
        String expected = NL + NL + "foobar" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void literal__equalsExpected() throws Exception {
        String result = Rst.builders().paragraph()
                .literal("foobar")
                .build().serialize();
        System.out.print(result);
        String expected = "``foobar``" + NL;
        Assertions.assertEquals(expected, result);
    }


}

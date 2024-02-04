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
import com.devives.rst.document.Paragraph;
import com.devives.rst.document.inline.InlineElement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class RstNodeBuilderTest extends AbstractTest {

    private static class LocalParagraph extends Paragraph {
        @Override
        protected String serializeElements() {
            String sb = "----" + super.serializeElements() +
                    "----";
            return sb;
        }
    }

    private static class LocalBuilderImpl<PARENT extends RstNodeBuilder<?, ?, ?, ?>>
            extends RstNodeBuilderAbst<PARENT, InlineElement, Paragraph, ParagraphBuilder<PARENT>>
            implements ParagraphBuilder<PARENT> {

        @Override
        protected Paragraph createRstElement() {
            return Rst.elements().paragraph();
        }
    }

    @Test
    public void beginBuilder() throws Exception {
//        String result = Rst.builders().paragraph()
//                .beginBuilder(new LocalBuilderImpl<>())
//                .text("Text")
//                .end()
//                .build()
//                .serialize();
//        System.out.print(result);
//        String expected = "Text" + NL;
//        Assertions.assertEquals(expected, result);
    }

    @Test
    public void builder() throws Exception {
//        String result = Rst.builders().document().substitutionImage("banana", builder -> builder.setUri("images/banana.png"))
//                .build()
//                .serialize();
//        System.out.print(result);
//        String expected = ".. |banana| image:: images/banana.png" + NL;
//        Assertions.assertEquals(expected, result);
    }


}

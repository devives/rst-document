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

public class RstDocumentBuilderTest extends AbstractTest {

    @Test
    public void title() {
        String result = Rst.builders().document().title("RstDocument title").build().serialize();
        System.out.print(result);
        String expected = EMPTY +
                "RstDocument title" + NL +
                "=================" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void section() {
        String result = Rst.builders().document().section(sectionBuilder -> {
            sectionBuilder.title("Section title", 1);
        }).build().serialize();
        System.out.print(result);
        String expected = EMPTY +
                "Section title" + NL +
                "=============" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void topic() {
        String result = Rst.builders().document().topic(topicBuilder -> topicBuilder
                .setTitle("Topic title")
                .paragraph("Topic content.")
        ).build().serialize();
        System.out.print(result);
        String expected = EMPTY +
                ".. topic:: Topic title" + NL +
                NL +
                "   Topic content." + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_TwoParagraphs_NewLineBetweenThem() {
        String result = Rst.builders().document()
                .paragraph(pb -> pb.text("Paragraph 1."))
                .paragraph(pb -> pb.text("Paragraph 2."))
                .build().serialize();

        System.out.print(result);
        String expected = EMPTY +
                "Paragraph 1." + NL +
                NL +
                "Paragraph 2." + NL;
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void serialize_ParagraphAfterTable_NewLineBetweenThem() {
        String result = Rst.builders().document()
                .gridTable(table -> table
                        .row(header -> header
                                .head("Aa")
                                .head(cell -> cell.paragraph("Bb"))
                        )
                        .row(row -> row
                                .cell(cell -> cell.paragraph("Cc"))
                                .cell("Dd")
                        )
                )
                .paragraph("Paragraph 1.")
                .paragraph(pb -> pb.text("Paragraph 2."))
                .build().serialize();

        System.out.print(result);
        String expected = "+----+----+" + NL +
                "| Aa | Bb |" + NL +
                "+====+====+" + NL +
                "| Cc | Dd |" + NL +
                "+----+----+" + NL +
                NL +
                "Paragraph 1." + NL +
                NL +
                "Paragraph 2." + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void title_1_AtDocumentBeginning() throws Exception {
        String result = Rst.builders().document().title("foobar").build().serialize();
        System.out.print(result);
        String expected = "foobar" + NL + "======" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void title_2_AtDocumentBeginning() throws Exception {
        String result = Rst.builders().document().title("foobar", 2).build().serialize();
        System.out.print(result);
        Assertions.assertEquals("foobar" + NL + "------" + NL, result);
    }

    @Test
    public void newParagraphIfRequired_AfterEmptyText() throws Exception {
        String result = Rst.builders().document()
                .paragraph(paragraphBuilder -> paragraphBuilder.text(""))
                .paragraph(pb -> {
                })
                .build().serialize();
        System.out.print(result);
        Assertions.assertEquals(NL + NL + NL, result);
    }

    @Test
    public void title_afterParagraph_NLasSeparator() throws Exception {
        String result = Rst.builders().document()
                .paragraph(paragraphBuilder -> {
                    paragraphBuilder.text("foo");
                })
                .title("bar")
                .build().serialize();
        System.out.print(result);
        String expected = EMPTY +
                "foo" + NL +
                NL +
                "bar" + NL +
                "===" + NL;
        Assertions.assertEquals(expected, result);
    }

}

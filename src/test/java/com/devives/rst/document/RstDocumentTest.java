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
package com.devives.rst.document;

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RstDocumentTest extends AbstractTest {

    @Test
    public void serialize_TwoParagraphs_NewLineBetweenThem() {
        String result = Rst.builders().document()
                .paragraph("Paragraph 1.")
                .paragraph("Paragraph 2.")
                .build().serialize();

        System.out.print(result);
        String expected = "Paragraph 1." + NL +
                NL +
                "Paragraph 2." + NL;
        Assertions.assertEquals(expected, result);
    }


    @Test
    public void serialize_TwoParagraphsWithNLs_NewLineBetweenThem() throws Exception {
        String result = Rst.builders().document()
                .paragraph(paragraphBuilder -> paragraphBuilder
                        .text("Paragraph")
                        .lineBreak()
                        .lineBreak())
                .paragraph("Paragraph 2")
                .build().serialize();
        System.out.print(result);
        String expected = "Paragraph" + NL +
                NL +
                NL +
                NL +
                "Paragraph 2" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_ParagraphAfterTable_NewLineBetweenThem() {
        String result = Rst.builders().document()
                .gridTable(table -> table
                        .row(header -> header
                                .head(cell -> cell.paragraph("Aa"))
                                .head(cell -> cell.paragraph("Bb"))
                        )
                        .row(row -> row
                                .cell(cell -> cell.paragraph("Cc"))
                                .cell(cell -> cell.paragraph("Dd"))
                        )
                )
                .paragraph(pb -> pb.text("Paragraph 1."))
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

}

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
import com.devives.rst.builder.directive.AdmonitionBuilder;
import com.devives.rst.builder.directive.FigureBuilder;
import com.devives.rst.builder.directive.ImageBuilder;
import com.devives.rst.document.BlockQuote;
import com.devives.rst.document.RstDocument;
import com.devives.rst.document.directive.Admonition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BodyBuildersTest extends AbstractTest {

    @Test
    public void beginBlockQuote__instanceOfBlockQuoteBuilder() throws Exception {
        Assertions.assertInstanceOf(BlockQuoteBuilder.class, Rst.builders().document().beginBlockQuote());
    }

    @Test
    public void blockQuote__instanceOfBlockQuote() throws Exception {
        RstDocument rstDocument = Rst.builders().document().blockQuote(b -> b.toString()).build();
        Assertions.assertInstanceOf(BlockQuote.class, rstDocument.getChildren().get(0));
    }

    @Test
    public void beginFigure__instanceOfFigureBuilder() throws Exception {
        Assertions.assertInstanceOf(FigureBuilder.class, Rst.builders().document()
                .beginFigure("image.png")
                .caption(c -> c.text("Image caption"))
                .caption("Image caption")
                .beginGridTable()
                .beginRow()
                .beginCell()
                .paragraph("Cell text.")
                .end()
                .end()
                .end()
        );
    }

    @Test
    public void beginImage__instanceOfExpectedClass() throws Exception {
        Assertions.assertInstanceOf(ImageBuilder.class, Rst.builders().document().beginImage("image.png"));
    }

    @Test
    public void beginNote__instanceOfAdmonitionBuilder() throws Exception {
        Assertions.assertInstanceOf(AdmonitionBuilder.class, Rst.builders().document().beginNote());
    }

    @Test
    public void beginNote__instanceOfAdmonition() throws Exception {
        RstDocument rstDocument = Rst.builders().document()
                .beginNote().paragraph("Text").end()
                .build();
        Assertions.assertInstanceOf(Admonition.class, rstDocument.getChildren().get(0));
    }

    @Test
    public void substitutionImage_uri_expected() throws Exception {
        String result = Rst.builders().document()
                .substitutionImage("banana", "images/banana.png")
                .build().serialize();
        System.out.print(result);
        String expected = ".. |banana| image:: images/banana.png" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void substitutionImage_consumer_expected() throws Exception {
        String result = Rst.builders().document()
                .substitutionImage("banana", builder -> builder.setUri("images/banana.png"))
                .build().serialize();
        System.out.print(result);
        String expected = ".. |banana| image:: images/banana.png" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void substitutionReplace_text_expected() throws Exception {
        String result = Rst.builders().document()
                .substitutionReplace("banana", "Replace text")
                .build().serialize();
        System.out.print(result);
        String expected = ".. |banana| replace:: Replace text" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void substitutionReplace_consumer_expected() throws Exception {
        String result = Rst.builders().document()
                .substitutionReplace("banana", builder -> builder.replacement("Replace text"))
                .build().serialize();
        System.out.print(result);
        String expected = ".. |banana| replace:: Replace text" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void blockQuote_betweenParagraphs_equalsExpected() throws Exception {
        String result = Rst.builders().document()
                .paragraph("Paragraph before block quote.")
                .blockQuote(qb -> {
                    qb.paragraph(pb -> {
                        pb.text("First block quote line.")
                                .lineBreak()
                                .text("Second block quote line.");
                    });
                })
                .paragraph("Paragraph after block quote.")
                .build().serialize();
        System.out.print(result);
        String expected = "Paragraph before block quote." + NL +
                NL +
                ".." + NL +
                NL +
                "   First block quote line." + NL +
                "   Second block quote line." + NL +
                NL +
                "Paragraph after block quote." + NL;
        Assertions.assertEquals(expected, result);
    }


    @Test
    public void blockQuote_nested_equalsExpected() throws Exception {
        String result = Rst.builders().document()
                .blockQuote(qb1 -> qb1
                        .paragraph(pb -> pb.text("Block quote."))
                        .blockQuote(qb2 -> qb2
                                .paragraph(pb -> pb.text("Nested lock quote."))
                        )
                )
                .build().serialize();
        System.out.print(result);
        String expected = ".." + NL +
                NL +
                "   Block quote." + NL +
                NL +
                "   .." + NL +
                NL +
                "      Nested lock quote." + NL;
        Assertions.assertEquals(expected, result);
    }


}

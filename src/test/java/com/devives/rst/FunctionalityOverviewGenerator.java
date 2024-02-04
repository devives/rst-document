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
package com.devives.rst;

import com.devives.rst.document.RstDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FunctionalityOverviewGenerator extends AbstractTest {

    private final Path rootPath = Paths.get("").toAbsolutePath().endsWith("RstProjectGroup")
            ? Paths.get("rst-document")
            : Paths.get("");
    private final Path resultPath = rootPath
            .resolve("build/tmp/test")
            .resolve(getClass().getCanonicalName().replace(".", "/"))
            .resolve("result.rst").toAbsolutePath();

    public static void main(String[] args) throws IOException {
        new FunctionalityOverviewGenerator().run();
    }

    private void run() throws IOException {

        RstDocument document = Rst.builders().document()
                .title("RstElementBuilder functionality review")
                .paragraph("Based on:")
                .bulletList(blb -> {
                    blb.item(ib -> ib.paragraph(pb -> pb.link("https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html", "reStructuredText Markup Specification")));
                    blb.item(ib -> ib.paragraph(pb -> pb.link("https://docutils.sourceforge.io/docs/ref/doctree.html", "The Docutils Document Tree")));
                })
                .title("Transitions", 2)
                .paragraph("This is the paragraph before transition.")
                .transition()
                .paragraph("This is the paragraph after transition.")
                .target("Paragraphs")
                .title("Paragraphs", 2)
                .paragraph("This is the first paragraph.")
                .paragraph("This is the second paragraph.")
                .target("Bullet Lists")
                .title("Bullet Lists", 2)
                .bulletList(blb -> blb
                        .item("Item 1")
                        .item("Item 2")
                )
                .target("Enumerated Lists")
                .title("Enumerated Lists", 2)
                .numberedList(blb -> blb
                        .item("Item 1")
                        .item("Item 2")
                )
                .target("Definition Lists")
                .title("Definition Lists", 2)
                .definitionList(dlb -> dlb
                        .item("term-1", ib -> ib
                                .paragraph("Term 1 description.")
                                .paragraph("Term 1 description, second paragraph."))
                        .item("-term-2-with-classifier", ib -> ib
                                .addClassifier("class1")
                                .paragraph("Term 2 description."))
                )
                .target("Field Lists")
                .title("Field Lists", 2)
                .fieldList(flb -> flb
                        .item("field1", ib -> ib
                                .paragraph("Field list item 1.")
                                .paragraph("Field list item 1, second paragraph."))
                        .item("field2", ib -> ib
                                .paragraph("Field list item 2."))
                )
                .title("Bibliographic Fields", 3)
                .paragraph("Not implemented yet.")
                .title("RCS Keywords", 3)
                .paragraph("Not implemented yet.")
                .target("Option Lists")
                .title("Option Lists", 2)
                .paragraph(p -> p.link("https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#option-lists", "Option Lists"))
                .paragraph("Not implemented yet.")
                .target("Literal Blocks")
                .title("Literal Blocks", 2)
                .paragraph(p -> p.link("https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#literal-blocks", "Literal Blocks"))
                .literalBlock("for a in [5,4,3,2,1]:   # this is *program code*, shown as-is\n" +
                        "    print a\n" +
                        "print \"it's...\"\n" +
                        "# a literal block continues until the indentation ends"
                )
                .target("Indented Literal Blocks")
                .title("Indented Literal Blocks", 3)
                .paragraph(p -> p.link("https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#indented-literal-blocks", "Indented Literal Blocks"))
                .indentedLiteralBlock("for a in [5,4,3,2,1]:   # this is *program code*, shown as-is\n" +
                        "    print a\n" +
                        "print \"it's...\"\n" +
                        "# a literal block continues until the indentation ends"
                )
                .target("Quoted Literal Blocks")
                .title("Quoted Literal Blocks", 3)
                .paragraph("Not implemented yet.")
                .target("Line Blocks")
                .title("Line Blocks", 2)
                .paragraph(p -> p.link("https://docutils.sourceforge.io/docs/ref/rst/restructuredtext.html#line-blocks", "Line Blocks"))
                .lineBlock(lbb -> lbb
                        .item(ib -> ib.text("Lend us a couple of bob till ").bold("Thursday").text("."))
                        .item(ib -> ib.text("I’m absolutely skint."))
                        .item(ib -> ib.text("But I’m expecting a postal order and I can pay you back as soon as it comes."))
                        .item(ib -> ib.text("Love, Ewan."))
                )
                .lineBlock(lbb -> lbb
                        .item(ib -> ib.text("A one, two, a one two three four\n"))
                        .item(ib -> ib.text("\n"))
                        .item(ib -> ib.text("Half a bee, philosophically,\n"))
                        .item(ib -> ib.text("   must, ipso facto, half not be.\n"))
                        .item(ib -> ib.text("But half the bee has got to be,\n"))
                        .item(ib -> ib.text("   vis a vis its entity. D’you see?\n"))
                        .item(ib -> ib.text("\n"))
                        .item(ib -> ib.text("But can a bee be said to be\n"))
                        .item(ib -> ib.text("   or not to be an entire bee,\n"))
                        .item(ib -> ib.text("       when half the bee is not a bee,\n"))
                        .item(ib -> ib.text("           due to some ancient injury?\n"))
                        .item(ib -> ib.text("\n"))
                        .item(ib -> ib.text("Singing…"))
                )
                .target("Block Quotes")
                .title("Block Quotes", 2)
                .paragraph("Not implemented yet.")
                .target("Doctest Blocks")
                .title("Doctest Blocks", 2)
                .paragraph("Not implemented yet.")
                .target("Tables")
                .title("Tables", 2)
                .target("Grid Tables")
                .title("Grid Tables", 3)
                .gridTable(table -> table
                        .setHeadRowCount(1)
                        .row(row -> row
                                .cell(cell -> cell.paragraph("First header"))
                                .cell(cell -> cell.paragraph("Second header"))
                        )
                        .row(row -> row
                                .cell(cell -> cell.paragraph("Aa"))
                                .cell(cell -> cell.paragraph("Bb"))
                        )
                        .row(row -> row
                                .cell(cell -> cell.paragraph("Cccc"))
                                .cell(cell -> cell.paragraph("Ddd"))
                        )
                )
                .target("Simple Tables")
                .title("Simple Tables", 3)
                .paragraph("Not implemented yet.")
                .target("Explicit Markup Blocks")
                .title("Explicit Markup Blocks", 2)
                .target("Footnotes")
                .title("Footnotes", 2)
                .paragraph("Not implemented yet.")
                .target("Citations")
                .title("Citations", 2)
                .paragraph("Not implemented yet.")
                .target("Directives")
                .title("Directives", 2)
                .paragraph(p -> p
                        .text("Directives are an extension mechanism for reStructuredText, a way of adding ")
                        .lineBreak()
                        .text("support for new constructs without adding new primary syntax (directives may support additional ")
                        .lineBreak()
                        .text("syntax locally). All standard directives (those implemented and registered in the reference ")
                        .lineBreak()
                        .text("reStructuredText parser) are described in the ")
                        .link("https://docutils.sourceforge.io/docs/ref/rst/directives.html", "reStructuredText Directives document")
                        .text(", and are ").lineBreak()
                        .text("always available. Any other directives are domain-specific, and may require special action ")
                        .lineBreak()
                        .text("to make them available when processing the document.")
                )
                .title("Parsed Literal Block", 3)
                .paragraph(p -> p
                        .link("https://docutils.sourceforge.io/docs/ref/rst/directives.html#parsed-literal-block", "Parsed Literal Block")
                )
                .parsedLiteral(d -> d
                        .setName("Some Name")
                        .text("for a in [5,4,3,2,1]:   # this is *program code*, shown as-is\n" +
                                "    print a\n" +
                                "print \"it's...\"\n" +
                                "# a literal block continues until the indentation ends")
                        .lineBreak()
                        .bold("This is bold text.")
                        .lineBreak()
                        .role("ref", "directives", "Reference to 'Directives' section.")
                        .lineBreak()
                        .anonymousLink("#directives", "Anonymous link to 'Directives' section.")
                )
                .target("Roles")
                .title("Roles", 2)
                .paragraph(p -> p.link("https://docutils.sourceforge.io/docs/ref/rst/roles.html", "reStructuredText Interpreted Text Roles"))
                .target("Inline Markup")
                .title("Inline Markup", 2)
                .target("Emphasis")
                .title("Emphasis", 3)
                .paragraph(p -> p.text("Italic Emphasis: ").italic("Italic").lineBreak())
                .paragraph(p -> p.text("Bold Emphasis: ").bold("Bold"))
                .paragraph(p -> p.text("Inline Literals: ").literal("Literal"))
                .paragraph(p -> p.text("Interpreted Text: ").interpreted("Interpreted"))
                .target("HyperLinks")
                .title("HyperLinks", 3)
                .paragraph(p -> p
                        .text("Link to ").link("Paragraphs").text(" section.")
                        .lineBreak()
                        .text("Link to external resource:").link("https://www.sphinx-doc.org/", "Sphinx").text(".")
                        .lineBreak()
                )
                .section(sb -> {
                    sb.section(sb2 -> {
                        // Sections can be nested.
                    });
                })
                .build();
        String rstText = document.serialize();
        Files.createDirectories(resultPath.getParent());
        Files.write(resultPath, Arrays.asList(rstText.split(NL)), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}

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
package com.devives.rst.document;

import com.devives.rst.document.directive.*;
import com.devives.rst.document.inline.*;
import com.devives.rst.document.list.*;
import com.devives.rst.document.table.GridTable;
import com.devives.rst.document.table.TableCell;
import com.devives.rst.document.table.TableRow;

/**
 * Interface of {@link RstElement} instances factory.
 */
public interface RstElementFactory {

    RstDocument document();

    BlockQuote blockQuote();

    Title title(String value, int level, boolean overline);

    Title title(String value, int level);

    Title title(String value);

    Paragraph paragraph();

    Section section();

    Sidebar sidebar();

    Transition transition();

    LiteralBlock literalBlock();

    IndentedLiteralBlock indentedLiteralBlock();

    Target target(String target);

    Target target(String name, String target);

    // Inlines

    Text text(String value);

    EmphasizedText bold(String value);

    EmphasizedText italic(String value);

    EmphasizedText inlineLiteral(String value);

    EmphasizedText interpretedText(String value);

    FootnoteReference footnoteReference(String value);

    Link link(String target, String text);

    Link link(String target);

    InlineAnchor inlineAnchor(String name);

    AnonymousLink anonymousLink(String target, String text);

    AnonymousLink anonymousLink(String target);

    Role role(String name, String target);

    Role role(String name, String target, String text);

    SubstitutionReference substitutionReference(String target);

    SubstitutionDefinition substitutionDefinition(SubstitutionDefinition.Type type, String target);

    // Directive

    Admonition admonition(Directive.Type type);

    Figure figure();

    Directive directive(Directive.Type type);

    ParsedLiteralBlock parsedLiteralBlock();

    // List

    BulletList bulletList(char marker);

    ListItem listItem();

    DefinitionList definitionList();

    DefinitionListItem definitionListItem(String name, String... classifiers);

    EnumeratedList enumeratedList(char marker, char start);

    FieldList fieldList();

    FieldListItem fieldListItem(String name);

    LineBlock lineBlock();

    LineBlockItem lineBlockItem();

    // Tables

    GridTable gridTable();

    TableRow tableRow();

    TableCell tableCell();

    TableCell tableCell(String type);

    TableCell tableCell(String type, int rowspan, int colspan);

}

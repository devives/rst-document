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
 * Default implementation of {@link RstElement} instances factory.
 */
public class DefaultRstElementFactoryImpl implements RstElementFactory {

    public RstDocument document() {
        return new RstDocument();
    }

    public BlockQuote blockQuote() {
        return new BlockQuote();
    }

    @Override
    public Title title(String value, int level, boolean overline) {
        return new Title(value, level, overline);
    }

    public Title title(String value, int level) {
        return new Title(value, level);
    }

    public Title title(String value) {
        return title(value, 1);
    }

    public Paragraph paragraph() {
        return new Paragraph();
    }

    @Override
    public Section section() {
        return new Section();
    }

    @Override
    public Sidebar sidebar() {
        return new Sidebar();
    }

    @Override
    public Transition transition() {
        return new Transition();
    }

    public LiteralBlock literalBlock() {
        return new LiteralBlock();
    }

    @Override
    public IndentedLiteralBlock indentedLiteralBlock() {
        return new IndentedLiteralBlock();
    }

    public Target target(String target) {
        return new Target(target);
    }

    public Target target(String target, String name) {
        return new Target(target, name);
    }

    public Text text(String value) {
        return new Text(value);
    }

    public EmphasizedText bold(String value) {
        return new EmphasizedText("**", value);
    }

    public EmphasizedText italic(String value) {
        return new EmphasizedText("*", value);
    }

    public EmphasizedText inlineLiteral(String value) {
        return new EmphasizedText("``", value);
    }

    public EmphasizedText interpretedText(String value) {
        return new EmphasizedText("`", value);
    }

    public Link link(String target, String text) {
        return new Link(target, text);
    }

    public Link link(String target) {
        return new Link(target);
    }

    public InlineAnchor inlineAnchor(String name) {
        return new InlineAnchor(name);
    }

    public AnonymousLink anonymousLink(String target, String text) {
        return new AnonymousLink(target, text);
    }

    public AnonymousLink anonymousLink(String target) {
        return new AnonymousLink(target);
    }

    @Override
    public FootnoteReference footnoteReference(String value) {
        return new FootnoteReference(value);
    }

    @Override
    public Role role(String name, String target) {
        return new Role(name, target);
    }

    @Override
    public Role role(String name, String target, String text) {
        return new Role(name, target, text);
    }

    @Override
    public SubstitutionReference substitutionReference(String substitutionText) {
        return new SubstitutionReference(substitutionText);
    }

    @Override
    public SubstitutionDefinition substitutionDefinition(SubstitutionDefinition.Type type, String substitutionText) {
        return new SubstitutionDefinition(type, substitutionText);
    }

    @Override
    public Admonition admonition(Directive.Type type) {
        return new Admonition(type);
    }

    @Override
    public Figure figure() {
        return new Figure();
    }

    @Override
    public Directive directive(Directive.Type type) {
        return new Directive(type);
    }

    @Override
    public ParsedLiteralBlock parsedLiteralBlock() {
        return new ParsedLiteralBlock();
    }

    @Override
    public BulletList bulletList(char marker) {
        return new BulletList(marker);
    }

    @Override
    public ListItem listItem() {
        return new ListItem();
    }

    @Override
    public DefinitionList definitionList() {
        return new DefinitionList();
    }

    @Override
    public DefinitionListItem definitionListItem(String name, String... classifiers) {
        return new DefinitionListItem(name, classifiers);
    }

    @Override
    public EnumeratedList enumeratedList(char marker, char start) {
        return new EnumeratedList(marker, start);
    }

    @Override
    public FieldList fieldList() {
        return new FieldList();
    }

    @Override
    public FieldListItem fieldListItem(String name) {
        return new FieldListItem(name);
    }

    @Override
    public LineBlock lineBlock() {
        return new LineBlock();
    }

    @Override
    public LineBlockItem lineBlockItem() {
        return new LineBlockItem();
    }

    @Override
    public GridTable gridTable() {
        return new GridTable();
    }

    @Override
    public TableRow tableRow() {
        return new TableRow();
    }

    @Override
    public TableCell tableCell() {
        return new TableCell();
    }

    @Override
    public TableCell tableCell(String type) {
        return new TableCell(type);
    }

    @Override
    public TableCell tableCell(String type, int rowspan, int colspan) {
        return new TableCell(type, rowspan, colspan);
    }
}

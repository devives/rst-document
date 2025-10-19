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

import com.devives.rst.Rst;
import com.devives.rst.builder.directive.*;
import com.devives.rst.builder.list.*;
import com.devives.rst.builder.table.GridTableBuilder;
import com.devives.rst.builder.table.GridTableBuilderImpl;
import com.devives.rst.document.*;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.directive.Directives;
import com.devives.rst.document.directive.ParsedLiteralBlock;
import com.devives.rst.document.directive.SubstitutionDefinition;
import com.devives.rst.document.inline.Role;
import com.devives.rst.util.StringUtils;

import java.util.function.Consumer;

/**
 * *
 */
public interface BodyBuilders<
        PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, CHILD, RESULT, SELF>,
        DirectiveBuilders<PARENT, CHILD, RESULT, SELF>,
        AdmonitionBuilders<PARENT, CHILD, RESULT, SELF> {

    /**
     * Creates a new {@link BlockQuoteBuilder} instance.
     *
     * @return a new child builder instance
     */
    default BlockQuoteBuilder<SELF> beginBlockQuote() {
        return new BlockQuoteBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link BlockQuote} instance with passed text and append it to the parent builder.
     *
     * @param text block quote text
     * @return this instance
     */
    default SELF blockQuote(String text) {
        BlockQuoteBuilder<SELF> builder = beginBlockQuote();
        builder.paragraph(text);
        return builder.end();
    }

    /**
     * Creates a new shifted {@link BlockQuoteBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF blockQuote(Consumer<BlockQuoteBuilder<SELF>> consumer) {
        BlockQuoteBuilder<SELF> builder = beginBlockQuote();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link ParagraphBuilder} instance.
     *
     * @return a new child builder instance
     */
    default ParagraphBuilder<SELF> beginParagraph() {
        return new ParagraphBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link ParagraphBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF paragraph(Consumer<ParagraphBuilder<SELF>> consumer) {
        ParagraphBuilder<SELF> builder = beginParagraph();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Append a new {@link Paragraph} instance, with passed text, to the list of children elements.
     *
     * @param text text of the paragraph
     * @return this instance
     */
    default SELF paragraph(String text) {
        return paragraph(p -> p.text(text));
    }

    /**
     * Creates a new {@link GridTableBuilder} instance.
     *
     * @return a new child builder instance
     */
    default GridTableBuilder<SELF> beginGridTable() {
        return new GridTableBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link GridTableBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF gridTable(Consumer<GridTableBuilder<SELF>> consumer) {
        GridTableBuilder<SELF> builder = beginGridTable();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link LiteralBlockBuilder} instance.
     *
     * @return a new child builder instance
     */
    default LiteralBlockBuilder<SELF> beginLiteralBlock() {
        return new LiteralBlockBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Create and append {@link LiteralBlock} to the parent builder.
     *
     * @param text text
     * @return this instance
     */
    default SELF literalBlock(String text) {
        LiteralBlockBuilder<SELF> builder = beginLiteralBlock();
        builder.text(text);
        return builder.end();
    }

    /**
     * Creates a new {@link LiteralBlockBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF literalBlock(Consumer<LiteralBlockBuilder<SELF>> consumer) {
        LiteralBlockBuilder<SELF> builder = beginLiteralBlock();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link IndentedLiteralBlockBuilder} instance.
     *
     * @return a new child builder instance
     */
    default IndentedLiteralBlockBuilder<SELF> beginIndentedLiteralBlock() {
        return new IndentedLiteralBlockBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Create and append {@link IndentedLiteralBlock} to the parent builder.
     *
     * @param text text
     * @return this instance
     */
    default SELF indentedLiteralBlock(String text) {
        IndentedLiteralBlockBuilder<SELF> builder = beginIndentedLiteralBlock();
        builder.text(text);
        return builder.end();
    }

    /**
     * Creates a new {@link IndentedLiteralBlockBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF indentedLiteralBlock(Consumer<IndentedLiteralBlockBuilder<SELF>> consumer) {
        IndentedLiteralBlockBuilder<SELF> builder = beginIndentedLiteralBlock();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Create and append {@link Target} to the parent builder.
     *
     * @param target
     * @return this instance
     */
    default SELF target(String target) {
        addChild((CHILD) Rst.elements().target(target));
        return (SELF) this;
    }

    /**
     * Create and append {@link Target} to the parent builder.
     *
     * @param target
     * @param name
     * @return this instance
     */
    default SELF target(String target, String name) {
        addChild((CHILD) Rst.elements().target(target, name));
        return (SELF) this;
    }

    /**
     * Creates a new {@link BulletListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default BulletListBuilder<SELF> beginBulletList() {
        return new BulletListBuilderImpl<SELF>('*').setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link BulletListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF bulletList(Consumer<BulletListBuilder<SELF>> consumer) {
        BulletListBuilder<SELF> builder = beginBulletList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default EnumeratedListBuilder<SELF> beginNumberedList() {
        return new EnumeratedListBuilderImpl<SELF>('#', '1').setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF numberedList(Consumer<EnumeratedListBuilder<SELF>> consumer) {
        EnumeratedListBuilder<SELF> builder = beginNumberedList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default EnumeratedListBuilder<SELF> beginRomanNumeralList() {
        return new EnumeratedListBuilderImpl<SELF>('#', 'I').setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF romanNumeralList(Consumer<EnumeratedListBuilder<SELF>> consumer) {
        EnumeratedListBuilder<SELF> builder = beginRomanNumeralList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default EnumeratedListBuilder<SELF> beginAlphabeticList() {
        return new EnumeratedListBuilderImpl<SELF>('#', 'a').setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link EnumeratedListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF alphabeticList(Consumer<EnumeratedListBuilder<SELF>> consumer) {
        EnumeratedListBuilder<SELF> builder = beginAlphabeticList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link DefinitionListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default DefinitionListBuilder<SELF> beginDefinitionList() {
        return new DefinitionListBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link DefinitionListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF definitionList(Consumer<DefinitionListBuilder<SELF>> consumer) {
        DefinitionListBuilder<SELF> builder = beginDefinitionList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link SubstitutionReplaceBuilder} instance.
     *
     * @param substText substitution text
     * @return a new child builder instance
     */
    default SubstitutionImageBuilder<SELF> beginSubstitutionImageBuilder(String substText) {
        return new SubstitutionImageBuilderImpl<SELF>(substText).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link SubstitutionImageBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF substitutionImage(String substName, Consumer<SubstitutionImageBuilder<SELF>> consumer) {
        SubstitutionImageBuilder<SELF> builder = beginSubstitutionImageBuilder(substName);
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Create and append substitution image to the parent RstNodeBuilder.
     *
     * @param substText substitution text
     * @param imageUri  relative uri to the image file
     * @return this instance
     * @see SubstitutionDefinition
     */
    default SELF substitutionImage(String substText, String imageUri) {
        return substitutionImage(substText, b -> b.setUri(imageUri));
    }

    /**
     * Creates a new {@link SubstitutionReplaceBuilder} instance.
     *
     * @param substText substitution text
     * @return a new child builder instance
     */
    default SubstitutionReplaceBuilder<SELF> beginSubstitutionReplace(String substText) {
        return new SubstitutionReplaceBuilderImpl<SELF>(substText).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link SubstitutionReplaceBuilder} instance, and pass to the consumer.
     *
     * @param substText substitution text
     * @param consumer  consumer
     * @return this instance
     */
    default SELF substitutionReplace(String substText, Consumer<SubstitutionReplaceBuilder<SELF>> consumer) {
        SubstitutionReplaceBuilder<SELF> builder = beginSubstitutionReplace(substText);
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Create and append substitution image to the parent RstNodeBuilder.
     *
     * @param substText       substitution text
     * @param replacementText the replacement text
     * @return this instance
     * @see SubstitutionDefinition
     */
    default SELF substitutionReplace(String substText, String replacementText) {
        return substitutionReplace(substText, b -> b.replacement(replacementText));
    }

    /**
     * Creates a new {@link ParsedLiteralBlock} instance.
     *
     * @return a new child builder instance
     */
    default ParsedLiteralBlockBuilder<SELF> beginParsedLiteralBlock() {
        return new ParsedLiteralBlockBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link GridTableBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF parsedLiteral(Consumer<ParsedLiteralBlockBuilder<SELF>> consumer) {
        ParsedLiteralBlockBuilder<SELF> builder = beginParsedLiteralBlock();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link FieldListBuilder} instance.
     *
     * @return a new child builder instance
     */
    default FieldListBuilder<SELF> beginFieldList() {
        return new FieldListBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link FieldListBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF fieldList(Consumer<FieldListBuilder<SELF>> consumer) {
        FieldListBuilder<SELF> builder = beginFieldList();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link ImageBuilder} instance.
     *
     * @return a new child builder instance
     */
    default ImageBuilder<SELF> beginImage(String imageUri) {
        return new ImageBuilderImpl<SELF>(imageUri).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link ImageBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF image(String imageUri, Consumer<ImageBuilder<SELF>> consumer) {
        ImageBuilder<SELF> builder = beginImage(imageUri);
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new {@link LineBlockBuilder} instance.
     *
     * @return a new child builder instance
     */
    default LineBlockBuilder<SELF> beginLineBlock() {
        return new LineBlockBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link LineBlockBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF lineBlock(Consumer<LineBlockBuilder<SELF>> consumer) {
        LineBlockBuilder<SELF> builder = beginLineBlock();
        consumer.accept(builder);
        return builder.end();
    }


    /**
     * Creates a new {@link FigureBuilder} instance.
     *
     * @return a new child builder instance
     */
    default FigureBuilder<SELF> beginFigure(String imageUri) {
        return new FigureBuilderImpl<SELF>(imageUri).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link FigureBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF figure(String imageUri, Consumer<FigureBuilder<SELF>> consumer) {
        FigureBuilder<SELF> builder = beginFigure(imageUri);
        consumer.accept(builder);
        return builder.end();
    }


    /**
     * Creates a new {@link CodeBuilder} instance and sets the language.
     *
     * @param language the code language for syntax highlighting
     * @return a new child builder instance
     */
    default CodeBuilder<SELF> beginCode(String language) {
        return new CodeBuilderImpl<SELF>(language).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link CodeBuilder} instance.
     *
     * @return a new child builder instance
     */
    default CodeBuilder<SELF> beginCode() {
        return beginCode(CodeBuilder.LANGUAGE_UNKNOWN);
    }

    /**
     * Creates a new {@link CodeBuilder} instance, and pass to the consumer.
     *
     * @param consumer consumer
     * @return this instance
     */
    default SELF code(Consumer<CodeBuilder<SELF>> consumer) {
        CodeBuilder<SELF> builder = beginCode();
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Creates a new `.. code-block::` {@link Directive} instance with passed language and text.
     *
     * @param language the code language for syntax highlighting
     * @param text     code sample
     * @return this instance
     */
    default SELF code(String language, String text) {
        CodeBuilder<SELF> builder = beginCode(language);
        builder.text(text);
        return builder.end();
    }

    /**
     * Creates a new `.. code-block::` {@link  Directive} instance with passed text.
     *
     * @param text code sample
     * @return this instance
     */
    default SELF code(String text) {
        CodeBuilder<SELF> builder = beginCode();
        builder.text(text);
        return builder.end();
    }

    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param name       role name
     * @param parentName parent role name
     * @return this instance
     */
    default SELF role(String name, String parentName) {
        StringUtils.requireNotNullOrEmpty(name, "name");
        StringUtils.requireNotNullOrEmpty(parentName, "parentName");
        DirectiveBuilder<SELF> builder = beginDirective(Directives.Role);
        builder.setArguments(String.format("%s(%s)", name, parentName));
        return builder.end();
    }


    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param name role name
     * @return this instance
     */
    default SELF role(String name) {
        StringUtils.requireNotNullOrEmpty(name, "name");
        DirectiveBuilder<SELF> builder = beginDirective(Directives.Role);
        builder.setArguments(name);
        return builder.end();
    }

}

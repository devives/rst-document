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
package com.devives.rst.builder.directive;

import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.directive.Directive;
import com.devives.rst.document.directive.Directives;

import java.util.function.Consumer;

public interface AdmonitionBuilders<PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends DirectiveBuilders<PARENT, CHILD, RESULT, SELF> {


    /**
     * Creates a new {@link AdmonitionBuilder} instance and sets the language.
     *
     * @param type the type of directive
     * @return a new child builder instance
     */
    default AdmonitionBuilder<SELF> beginAdmonition(Directive.Type type) {
        return new AdmonitionBuilderImpl<SELF>(type).setParentBuilder((SELF) this);
    }

    /**
     * Creates a new {@link AdmonitionBuilder} instance, and pass to the consumer.
     *
     * @param type     directive type
     * @param consumer
     * @return this instance
     */
    default SELF admonition(Directive.Type type, Consumer<AdmonitionBuilder<SELF>> consumer) {
        AdmonitionBuilder<SELF> builder = beginAdmonition(type);
        consumer.accept(builder);
        return builder.end();
    }

    default AdmonitionBuilder<SELF> beginNote() {
        return beginAdmonition(Directives.Note);
    }

    default SELF note(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Note, consumer);
    }

    default SELF note(String text) {
        return note(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginWarning() {
        return beginAdmonition(Directives.Warning);
    }

    default SELF warning(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Warning, consumer);
    }

    default SELF warning(String text) {
        return warning(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginTip() {
        return beginAdmonition(Directives.Tip);
    }

    default SELF tip(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Tip, consumer);
    }

    default SELF tip(String text) {
        return tip(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginAttention() {
        return beginAdmonition(Directives.Attention);
    }

    default SELF attention(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Attention, consumer);
    }

    default SELF attention(String text) {
        return attention(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginCaution() {
        return beginAdmonition(Directives.Caution);
    }

    default SELF caution(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Caution, consumer);
    }

    default SELF caution(String text) {
        return caution(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginDanger() {
        return beginAdmonition(Directives.Danger);
    }

    default SELF danger(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Danger, consumer);
    }

    default SELF danger(String text) {
        return danger(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginError() {
        return beginAdmonition(Directives.Error);
    }

    default SELF error(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Error, consumer);
    }

    default SELF error(String text) {
        return error(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginHint() {
        return beginAdmonition(Directives.Hint);
    }

    default SELF hint(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Hint, consumer);
    }

    default SELF hint(String text) {
        return hint(b -> b.paragraph(text));
    }

    default AdmonitionBuilder<SELF> beginImportant() {
        return beginAdmonition(Directives.Important);
    }

    default SELF important(Consumer<AdmonitionBuilder<SELF>> consumer) {
        return admonition(Directives.Important, consumer);
    }

    default SELF important(String text) {
        return important(b -> b.paragraph(text));
    }

}

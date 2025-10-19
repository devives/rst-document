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
package com.devives.rst.builder.inline;

import com.devives.rst.Rst;
import com.devives.rst.builder.RstElementBuilder;
import com.devives.rst.builder.RstNodeBuilder;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.inline.EmphasizedText;
import com.devives.rst.document.inline.InlineAnchor;
import com.devives.rst.document.inline.Link;
import com.devives.rst.document.inline.Role;

/**
 *
 */
public interface InlineBuilders<
        PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends InlineBuilders<PARENT, CHILD, RESULT, SELF>>
        extends TextBuilders<PARENT, CHILD, RESULT, SELF> {

    /**
     * Appends a {@link EmphasizedText} element to the root {@link RstElementBuilder}.
     *
     * @param value value for the new element
     * @return the builder instance
     * @see EmphasizedText#EmphasizedText(String, String)
     */
    default SELF bold(String value) {
        return addChild((CHILD) Rst.elements().bold(value));
    }

    /**
     * Appends a {@link EmphasizedText} element to the root {@link RstElementBuilder}.
     *
     * @param value value for the new element
     * @return the builder instance
     * @see EmphasizedText#EmphasizedText(String, String)
     */
    default SELF italic(String value) {
        return addChild((CHILD) Rst.elements().italic(value));
    }

    /**
     * Appends a {@link EmphasizedText} element to the root {@link RstElementBuilder}.
     *
     * @param value value for the new element
     * @return the builder instance
     * @see EmphasizedText#EmphasizedText(String, String)
     */
    default SELF literal(String value) {
        return addChild((CHILD) Rst.elements().inlineLiteral(value));
    }

    /**
     * Appends a {@link EmphasizedText} element to the root {@link RstElementBuilder}.
     *
     * @param value value for the new element
     * @return the builder instance
     * @see EmphasizedText#EmphasizedText(String, String)
     */
    default SELF interpreted(String value) {
        return addChild((CHILD) Rst.elements().interpretedText(value));
    }

    /**
     * Appends a {@link InlineAnchor} element to the root {@link RstElementBuilder}.
     *
     * @param name value for the new element
     * @return the builder instance
     * @see InlineAnchor#InlineAnchor(String)
     */
    default SELF inlineAnchor(String name) {
        return addChild((CHILD) Rst.elements().inlineAnchor(name));
    }


    // Link

    /**
     * Appends a {@link Link} element to the root {@link RstElementBuilder}.
     *
     * @param target url for the link or internal document anchor.
     * @param text   text for the link.
     * @return the builder instance
     * @see Link#Link(String, String)
     */
    default SELF link(String target, String text) {
        return addChild((CHILD) Rst.elements().link(target, text));
    }

    /**
     * Appends a {@link Link} element to the root {@link RstElementBuilder}.
     *
     * @param target url for the link
     * @return the builder instance
     * @see Link#Link(String)
     */
    default SELF link(String target) {
        return addChild((CHILD) Rst.elements().link(target));
    }

    /**
     * Appends a {@link Link} element to the root {@link RstElementBuilder}.
     *
     * @param target url for the link or internal document anchor.
     * @param text   text for the link.
     * @return the builder instance
     * @see Link#Link(String, String)
     */
    default SELF anonymousLink(String target, String text) {
        return addChild((CHILD) Rst.elements().anonymousLink(target, text));
    }

    /**
     * Appends a {@link Link} element to the root {@link RstElementBuilder}.
     *
     * @param target url for the link
     * @return the builder instance
     * @see Link#Link(String)
     */
    default SELF anonymousLink(String target) {
        return addChild((CHILD) Rst.elements().anonymousLink(target));
    }

    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param name   name for the new element
     * @param target target for the new element
     * @param title  title for the new element
     * @return the builder instance
     * @see Role#Role(String, String, String)
     */
    default SELF role(String name, String target, String title) {
        return addChild((CHILD) Rst.elements().role(name, target, title));
    }


    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param name   name for the new element
     * @param target target for the new element
     * @return the builder instance
     * @see Role#Role(String, String)
     */
    default SELF role(String name, String target) {
        return addChild((CHILD) Rst.elements().role(name, target));
    }


    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param target target for the new element
     * @return the builder instance
     * @see Role#Role(String, String)
     */
    default SELF substitution(String target) {
        return addChild((CHILD) Rst.elements().substitutionReference(target));
    }


    /**
     * Appends a {@link Role} element to the root {@link RstElementBuilder}.
     *
     * @param target target for the new element
     * @return the builder instance
     * @see Role#Role(String, String)
     */
    default SELF footnote(String target) {
        return addChild((CHILD) Rst.elements().footnoteReference(target));
    }


}

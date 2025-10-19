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
package com.devives.rst.document.directive;

import com.devives.rst.document.directive.Directive.Type;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#compound-paragraph">reStructuredText Directives</a>
 */
public abstract class Directives {

    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#generic-admonition">Generic Admonition</a>
     */
    public static final Directive.Type Admonition = new Directive.Type("admonition");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#admonitions">Admonitions</a>
     */
    public static final Directive.Type Attention = new Directive.Type("attention");
    public static final Directive.Type Caution = new Directive.Type("caution");
    public static final Directive.Type Danger = new Directive.Type("danger");
    public static final Directive.Type Error = new Directive.Type("error");
    public static final Directive.Type Hint = new Directive.Type("hint");
    public static final Directive.Type Important = new Directive.Type("important");
    public static final Directive.Type Note = new Directive.Type("note");
    public static final Directive.Type Tip = new Directive.Type("tip");
    public static final Directive.Type Warning = new Directive.Type("warning");

    public static final Type Image = new Type("image");
    /**
     * @see com.devives.rst.document.directive.Figure
     */
    public static final Type Figure = new Type("figure");
    public static final Type Topic = new Type("topic");
    public static final Type Sidebar = new Type("sidebar");
    public static final Type LineBlock = new Type("line-block");
    /**
     * @see ParsedLiteralBlock
     */
    public static final Type ParsedLiteral = new Type("parsed-literal");
    public static final Type Code = new Type("code");
    public static final Type Math = new Type("math");
    public static final Type Rubric = new Type("rubric");
    public static final Type Epigraph = new Type("epigraph");
    public static final Type Highlights = new Type("highlights");
    public static final Type PullQuote = new Type("pull-quote");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#compound-paragraph">Compound Paragraph</a>
     */
    public static final Type Compound = new Type("compound");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#container">Container</a>
     */
    public static final Type Container = new Type("container");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#table-of-contents">Table of Contents</a>
     */
    public static final Type Contents = new Type("contents");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#automatic-section-numbering">Automatic Section Numbering</a>
     */
    public static final Type Sectnum = new Type("sectnum");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#automatic-section-numbering">Automatic Section Numbering</a>
     */
    public static final Type SectionNumbering = new Type("section-numbering");
    // Document Header & Footer
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#document-header-footer">...</a>
     */
    public static final Type Header = new Type("header");
    public static final Type Footer = new Type("footer");
    // References
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#target-footnotes">Target Footnotes</a>
     */
    public static final Type TargetNotes = new Type("target-notes");
    public static final Type Footnotes = new Type("footnotes");
    public static final Type Citations = new Type("citations");
    // Miscellaneous
    public static final Type Replace = new Type("replace");
    public static final Type Unicode = new Type("unicode");
    public static final Type Date = new Type("date");
    // Including an External Document Fragment
    public static final Type Include = new Type("include");
    public static final Type Raw = new Type("raw");
    public static final Type Class = new Type("class");
    public static final Type Role = new Type("role");
    public static final Type DefaultRole = new Type("default-role");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#metadata">Metadata</a>
     */
    public static final Type Meta = new Type("meta");
    /**
     * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#metadata-document-title">Metadata Document Title</a>
     */
    public static final Type Title = new Type("title");

}

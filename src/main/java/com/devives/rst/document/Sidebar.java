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

/**
 * Sidebars are like miniature, parallel documents that occur inside other documents, providing related or reference material.
 * A sidebar is typically offset by a border and "floats" to the side of the page; the document's main text may flow around it.
 * Sidebars can also be likened to super-footnotes; their content is outside of the flow of the document's main text.
 * <p>
 * Sidebars may occur anywhere a section or transition may occur. Body elements (including sidebars) may not contain nested sidebars.
 * <p>
 * Is StructuralElement.
 *
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#sidebar">Sidebar</a>
 */
public class Sidebar extends RstNodeAbst<RstElement> implements StructuralElement<RstElement> {

}

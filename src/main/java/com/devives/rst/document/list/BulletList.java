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
package com.devives.rst.document.list;

/**
 * The BulletList class represents an automated bullet list. In reStructuredText, automated bullet lists are possible
 * by starting each item with a "*".
 */
public class BulletList extends AbstractList {
    /**
     * Creates an AutoList with items defined by the parameter String. Items are separated by '\n' characters. The type of
     * this list will determine which symbol will begin each item of the list
     *
     * @param marker the normal symbol that begins an item
     */
    public BulletList(char marker) {
        super(marker);
    }


}


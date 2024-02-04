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
 * The EnumeratedList class is a representation of automatically enumerated lists in reStructuredText. These include
 * alphabetic, numbered, and Roman numeral lists. To create an automatically enumerated list, all that's necessary is
 * to start the first item with an enumeration ("A.", "i.", "1.", etc.) and start the rest with "#.". If the first item
 * starts with "#.", a numbered list is automatically used.
 */
public class EnumeratedList extends AbstractList {

    private final char start;

    /**
     * creates an automatically enumerated list with the specified starting character
     *
     * @param marker marker
     * @param start  the starting enumeration's type (e.g. 'a', 'I', '1')
     */
    public EnumeratedList(char marker, char start) {
        super(marker);
        this.start = start;
    }

    @Override
    protected String getItemMark(int index) {
        return (index == 0 ? String.valueOf(start) : super.getItemMark(index)) + ".";
    }
}

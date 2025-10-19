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
package com.devives.rst.builder.list;

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumeratedListTest extends AbstractTest {

    @Test
    public void serialize_numberedList() throws Exception {
        String result = Rst.builders().document()
                .numberedList(blb -> blb
                        .item(ib -> ib.paragraph(pb -> pb.text("Item 1")))
                        .item("Item 2"))
                .build().serialize();
        System.out.print(result);
        String expected = "1. Item 1" + NL +
                "#. Item 2" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_alphabeticList() throws Exception {
        String result = Rst.builders().document()
                .alphabeticList(blb -> blb
                        .item(ib -> ib.paragraph(pb -> pb.text("Item 1")))
                        .item("Item 2"))
                .build().serialize();
        String expected = "a. Item 1" + NL +
                "#. Item 2" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_romanNumeralList() throws Exception {
        String result = Rst.builders().document()
                .romanNumeralList(blb -> blb
                        .item(ib -> ib.paragraph(pb -> pb.text("Item 1")))
                        .item("Item 2"))
                .build().serialize();
        String expected = "I. Item 1" + NL +
                "#. Item 2" + NL;
        Assertions.assertEquals(expected, result);
    }

}

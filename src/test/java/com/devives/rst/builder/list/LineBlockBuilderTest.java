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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineBlockBuilderTest extends AbstractTest {
    @Test
    public void serialize_bulletList() throws Exception {
        String result = new LineBlockBuilderImpl<>()
                .item(ib -> ib.text("First").lineBreak().text("   line"))
                .item("Second line")
                .item("   Third line")
                .build().serialize();
        String expected = "| First" + NL +
                "     line" + NL +
                "| Second line" + NL +
                "|    Third line" + NL;
        Assertions.assertEquals(expected, result);
    }
}

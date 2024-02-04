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
package com.devives.rst.builder.table;

import com.devives.rst.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTableBulderTest extends AbstractTest {

    @Test
    public void serialize() {
        String result = new GridTableBuilderImpl<>()
                .setHeadRowCount(1)
                .row(header -> header
                        .cell(cell -> cell.paragraph(p -> p.text("Aa")))
                        .cell(cell -> cell.paragraph(p -> p.text("Bb")))
                )
                .row(row -> row
                        .cell(cell -> cell.paragraph(p -> p.text("Cc")))
                        .cell(cell -> cell.paragraph(p -> p.text("Dd")))
                )
                .row(row -> row
                        .cell(cell -> cell.setColspan(2).paragraph(p -> p.text("Ee")))
                )
                .build().serialize();

        System.out.print(result);
        String expected = "+----+----+" + NL +
                "| Aa | Bb |" + NL +
                "+----+----+" + NL +
                "| Cc | Dd |" + NL +
                "+----+----+" + NL +
                "| Ee      |" + NL +
                "+----+----+" + NL;
        Assertions.assertEquals(expected, result);
    }

}

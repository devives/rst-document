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
package com.devives.rst.document;

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import com.devives.rst.document.list.LineBlock;
import com.devives.rst.document.list.LineBlockItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineBlockTest extends AbstractTest {

    @Test
    public void serialize_lineItemWithNL_equalsExpected() throws Exception {
        LineBlock lineBlock = Rst.elements().lineBlock();
        LineBlockItem lineBlockItem = Rst.elements().lineBlockItem();
        lineBlockItem.getChildren().add(Rst.elements().text("First line of first block item text."));
        lineBlockItem.getChildren().add(Rst.elements().text(NL));
        lineBlockItem.getChildren().add(Rst.elements().text("Second line of first block item text."));
        lineBlock.getChildren().add(lineBlockItem);
        String result = lineBlock.serialize();
        System.out.print(result);
        String expected = EMPTY +
                "| First line of first block item text." + NL +
                "  Second line of first block item text." + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_lineItemWithNLInText_equalsExpected() throws Exception {
        LineBlock lineBlock = Rst.elements().lineBlock();
        LineBlockItem lineBlockItem = Rst.elements().lineBlockItem();
        lineBlockItem.getChildren().add(Rst.elements().text("First line of first block item text." + NL + "Second line of first block item text."));
        lineBlock.getChildren().add(lineBlockItem);
        String result = lineBlock.serialize();
        System.out.print(result);
        String expected = EMPTY +
                "| First line of first block item text." + NL +
                "  Second line of first block item text." + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_multipleLineItems_equalsExpected() throws Exception {
        LineBlock lineBlock = Rst.elements().lineBlock();
        LineBlockItem lineBlockItem = Rst.elements().lineBlockItem();
        lineBlockItem.getChildren().add(Rst.elements().text("First line of first block item text."));
        lineBlock.getChildren().add(lineBlockItem);
        LineBlockItem lineBlockItem_2 = Rst.elements().lineBlockItem();
        lineBlockItem_2.getChildren().add(Rst.elements().text("First line of second block item text."));
        lineBlock.getChildren().add(lineBlockItem_2);
        String result = lineBlock.serialize();
        System.out.print(result);
        String expected = EMPTY +
                "| First line of first block item text." + NL +
                "| First line of second block item text." + NL;
        Assertions.assertEquals(expected, result);
    }

}

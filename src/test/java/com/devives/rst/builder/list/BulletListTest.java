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

public class BulletListTest extends AbstractTest {
    @Test
    public void serialize() throws Exception {
        String result = new BulletListBuilderImpl<>('*')
                .item(ib -> ib.paragraph(pb -> pb.text("Item 1")))
                .item(ib -> ib.paragraph(pb -> pb.text("Item 2")))
                .build().serialize();
        System.out.print(result);
        String expected = "* Item 1" + NL +
                "* Item 2" + NL;
        Assertions.assertEquals(expected, result);
    }


    @Test
    public void serialize_nested_lists() throws Exception {
        String result = Rst.builders().document()
                .bulletList(blb -> {
                    blb.item(ib -> {
                        ib.paragraph("Item 1");
                        ib.paragraph("Item 1 paragraph 2");
                        ib.bulletList(blb2 -> {
                            blb2.item(ib2 -> {
                                ib2.paragraph(pb -> pb
                                        .text("Sub Item 1")
                                        .lineBreak()
                                        .text("Second line"));
                            });
                            blb2.item(ib2 -> ib2
                                    .paragraph("Sub Item 2")
                                    .beginCode("java")
                                    .text(JAVA_CODE_SAMPLE)
                                    .end());
                        });
                    });
                    blb.item(ib -> {
                        ib.paragraph("Item 2");
                        ib.paragraph("Item 2 paragraph 2");
                    });
                })
                .build().serialize();
        System.out.print(result);
        String expected = "* Item 1" + NL +
                NL +
                "  Item 1 paragraph 2" + NL +
                NL +
                "  * Sub Item 1" + NL +
                "    Second line" + NL +
                "  * Sub Item 2" + NL +
                NL +
                "    .. code:: java" + NL +
                NL +
                "       for (int i = 0; i < 10; i++) {" + NL +
                "           System.out.println(i);" + NL +
                "       }" + NL +
                "* Item 2" + NL +
                NL +
                "  Item 2 paragraph 2" + NL;
        Assertions.assertEquals(expected, result);
    }

}

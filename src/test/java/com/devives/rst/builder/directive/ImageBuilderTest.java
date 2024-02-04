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
package com.devives.rst.builder.directive;

import com.devives.rst.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageBuilderTest extends AbstractTest {

    @Test
    public void serialize_noOptions() throws Exception {
        String result = new ImageBuilderImpl<>("image.png").build().serialize();
        System.out.print(result);
        String expected = ".. image:: image.png" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_noOptionsNoValue() throws Exception {
        String result = new ImageBuilderImpl<>("image.png").build().serialize();
        System.out.print(result);
        String expected = ".. image:: image.png" + NL;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_AllOptionsNoValue() throws Exception {
        String result = new ImageBuilderImpl<>("some.png")
                .setImageUri("image.png")
                .setAlt("Alternative text")
                .setHeight("100px")
                .setWidth("100px")
                .setAlign("left")
                .setScale("100%")
                .setLoading("...")
                .build()
                .serialize();
        System.out.print(result);
        String expected = ".. image:: image.png" + NL +
                "   :alt: Alternative text" + NL +
                "   :height: 100px" + NL +
                "   :width: 100px" + NL +
                "   :align: left" + NL +
                "   :scale: 100%" + NL +
                "   :loading: ..." + NL;
        Assertions.assertEquals(expected, result);
    }

}

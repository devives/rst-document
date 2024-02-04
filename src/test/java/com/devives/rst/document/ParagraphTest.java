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

import com.devives.rst.AbstractTest;
import com.devives.rst.Rst;
import com.devives.rst.document.inline.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParagraphTest extends AbstractTest {

    @Test
    public void serialize_String() throws Exception {
        Paragraph paragraph = Rst.elements().paragraph();
        paragraph.getChildren().add(new Text("foo"));
        paragraph.getChildren().add(new Text(SPACE));
        paragraph.getChildren().add(new Text("bar"));
        String result = paragraph.serialize();
        System.out.print(result);
        Assertions.assertEquals("foo bar" + NL, result);
    }

}

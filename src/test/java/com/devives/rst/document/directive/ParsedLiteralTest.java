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
package com.devives.rst.document.directive;

import com.devives.rst.AbstractTest;
import com.devives.rst.builder.directive.DirectiveBuilderImpl;
import com.devives.rst.document.inline.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParsedLiteralTest extends AbstractTest {

    @Test
    public void serialize_expectedString() throws Exception {
        Text text = new Text(
                " import org.junit.jupiter.api.Test;\n" +
                        " import org.openqa.selenium.chrome.ChromeDriver;\n" +
                        " import org.openqa.selenium.remote.RemoteWebDriver;");

        Directive directive = new DirectiveBuilderImpl<>(Directives.ParsedLiteral)
                .addChild(text)
                .build();

        String result = directive.serialize();
        System.out.print(result);
        String expected = ".. parsed-literal::" + NL +
                NL +
                "    import org.junit.jupiter.api.Test;" + NL +
                "    import org.openqa.selenium.chrome.ChromeDriver;" + NL +
                "    import org.openqa.selenium.remote.RemoteWebDriver;" + NL;
        Assertions.assertEquals(expected, result);
    }

}

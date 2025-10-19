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

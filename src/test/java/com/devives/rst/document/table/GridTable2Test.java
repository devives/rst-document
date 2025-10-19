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
package com.devives.rst.document.table;

import com.devives.rst.AbstractTest;
import com.devives.rst.document.Paragraph;
import com.devives.rst.document.inline.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled // Not implemented
public class GridTable2Test extends AbstractTest {

    @Test
    public void serialize() {
        GridTable2 table = new GridTable2();
        TableRow tr = new TableRow();
        tr.getChildren().add(newCell("Aa"));
        tr.getChildren().add(newCell("Bb"));
        table.getChildren().add(tr);
        tr = new TableRow();
        tr.getChildren().add(newCell("Cc"));
        tr.getChildren().add(newCell("Dd"));
        table.getChildren().add(tr);
        tr = new TableRow();
        TableCell tc = newCell("Ee");
        tc.setColspan(2);
        tr.getChildren().add(tc);
        table.getChildren().add(tr);

        String result = table.serialize();
        System.out.print(result);
        String expected = "+----+----+" + NL +
                "| Aa | Bb |" + NL +
                "+====+====+" + NL +
                "| Cc | Dd |" + NL +
                "+----+----+" + NL;
        Assertions.assertEquals(expected, result);
    }

    private TableCell newCell(String text) {
        TableCell cell = new TableCell();
        cell.getChildren().add(newParagraph(text));
        return cell;
    }

    private Paragraph newParagraph(String text) {
        Paragraph paragraph = new Paragraph();
        paragraph.getChildren().add(new Text(text));
        return paragraph;
    }
}

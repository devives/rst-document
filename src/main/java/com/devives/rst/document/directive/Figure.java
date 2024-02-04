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
package com.devives.rst.document.directive;

import com.devives.rst.document.RstElement;
import com.devives.rst.document.inline.InlineElement;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html#figure">Figure</a>
 */
public class Figure extends DirectiveAbst<RstElement> {

    private List<InlineElement> captions_ = new ArrayList<>();

    @Override
    protected String getName() {
        return Directives.Figure.getName();
    }

    public List<InlineElement> getCaptions() {
        return captions_;
    }

    public void setCaptions(List<InlineElement> captions) {
        captions_ = captions;
    }

    protected String serializeCaption() {
        String captions = "";
        if (captions_ != null && !captions_.isEmpty()) {
            captions = captions_.stream().map(RstElement::serialize).collect(Collectors.joining()).trim();
        }
        return captions;
    }

    @Override
    public String serialize() throws RstSerializationException {
        String definition = serializeDefinition();
        String[] arguments = serializeArguments();
        String[] options = serializeOptions();
        String caption = serializeCaption();
        String content = serializeElements();

        StringBuilder sb = new StringBuilder(definition);
        if (arguments.length > 0) {
            sb.append(SPACE);
            int argumentsIndent = definition.length() + 1;
            sb.append(StringUtils.joinLines(StringUtils.shiftLeftEdgeExceptFirstLine(arguments, argumentsIndent)));
        }
        sb.append(NL);
        if (options != null && options.length > 0) {
            String sOptions = StringUtils.joinLines(StringUtils.shiftLeftEdge(options, 3));
            sOptions = StringUtils.endWithNL(sOptions);
            sb.append(sOptions);
        }
        if (caption != null) {
            String sCaption = caption.trim();
            if (!sCaption.isEmpty()) {
                sb.append(NL);
                sCaption = StringUtils.shiftLeftEdge(sCaption, 3);
                sCaption = StringUtils.endWithNL(sCaption);
                sb.append(sCaption);
            }
        }
        if (content != null) {
            String sContent = content.trim();
            if (!sContent.isEmpty()) {
                sb.append(getContentSeparator());
                sContent = StringUtils.shiftLeftEdge(sContent, 3);
                sContent = StringUtils.endWithNL(sContent);
                sb.append(sContent);
            }
        }
        return sb.toString();
    }
}

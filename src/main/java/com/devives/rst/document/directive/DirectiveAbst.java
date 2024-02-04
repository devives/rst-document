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
import com.devives.rst.document.RstNodeAbst;
import com.devives.rst.document.inline.InlineElement;
import com.devives.rst.util.RstSerializationException;
import com.devives.rst.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see <a href="https://docutils.sourceforge.io/docs/ref/rst/directives.html">ReStructuredText Directives</a>
 */
public abstract class DirectiveAbst<CHILD extends RstElement> extends RstNodeAbst<CHILD> {

    private Arguments arguments_ = new Arguments();
    private Options options_ = new Options();

    protected abstract String getName();

    protected String getContentSeparator() {
        return NL;
    }

    protected String getContentItemsSeparator() {
        return NL;
    }

    public Arguments getArguments() {
        return arguments_;
    }

    public void setArguments(List<InlineElement> arguments) {
        arguments_ = new Arguments(Objects.requireNonNull(arguments));
    }

    public Options getOptions() {
        return options_;
    }

    public void setOptions(Map<String, Object> options) {
        this.options_ = new Options(Objects.requireNonNull(options));
    }

    protected String serializeDefinition() {
        return ".. " + getName() + "::";
    }

    protected String[] serializeArguments() {
        String arguments = "";
        if (arguments_ != null && !arguments_.isEmpty()) {
            arguments = arguments_.stream().map(RstElement::serialize).collect(Collectors.joining()).trim();
        }
        return arguments.isEmpty() ? new String[0] : StringUtils.splitLines(arguments);
    }

    protected String[] serializeOptions() {
        if (options_ == null) {
            return null;
        } else {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, Object> keyValue : options_.entrySet()) {
                String line = ":" + keyValue.getKey() + ":";
                if (keyValue.getValue() instanceof Object[]) {
                    line += (SPACE + Arrays.stream((Object[]) keyValue.getValue()).map(Object::toString).collect(Collectors.joining(",")));
                } else if (keyValue.getValue() instanceof Boolean) {
                    // do nothings
                } else if (keyValue.getValue() != null) {
                    line += (SPACE + keyValue.getValue());
                }
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        }
    }

    @Override
    protected void onCollectChildText(StringBuilder stringBuilder, String itemText) {
        if (stringBuilder.length() > 0) {
            String separator = getContentItemsSeparator();
            if (StringUtils.notNullOrEmpty(separator)) {
                stringBuilder.append(separator);
            }
        }
        super.onCollectChildText(stringBuilder, itemText);
    }

    @Override
    public String serialize() throws RstSerializationException {
        String definition = serializeDefinition();
        String[] arguments = serializeArguments();
        String[] options = serializeOptions();
        String content = serializeElements();

        StringBuilder sb = new StringBuilder(definition);

        if (arguments.length > 0) {
            sb.append(SPACE);
            int argumentsIndent = definition.length() + 1;
            sb.append(StringUtils.joinLines(StringUtils.shiftLeftEdgeExceptFirstLine(arguments, argumentsIndent)));
        }
        sb.append(NL);
        if (options != null && options.length > 0) {
            sb.append(StringUtils.joinLines(StringUtils.shiftLeftEdge(options, 3)));
            sb.append(NL);
        }
        if (content != null && !content.isEmpty()) {
            sb.append(getContentSeparator());
            sb.append(StringUtils.endWithNL(StringUtils.shiftLeftEdge(content.trim(), 3)));
        }
        return sb.toString();
    }

}

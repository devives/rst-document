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
package com.devives.rst.builder;

import com.devives.rst.builder.directive.SidebarBuilder;
import com.devives.rst.builder.directive.SidebarBuilderImpl;
import com.devives.rst.builder.directive.TopicBuilder;
import com.devives.rst.builder.directive.TopicBuilderImpl;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;

import java.util.function.Consumer;

/**
 *
 */
public interface StructuralElementBuilders<
        PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        CHILD extends RstElement,
        RESULT extends RstNode<CHILD>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, CHILD, RESULT, SELF>,
        SectionBuilders<PARENT, CHILD, RESULT, SELF> {


    default TopicBuilder<SELF> beginTopic() {
        return new TopicBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    default SELF topic(Consumer<TopicBuilder<SELF>> consumer) {
        TopicBuilder<SELF> builder = beginTopic();
        consumer.accept(builder);
        return builder.end();
    }

    default SidebarBuilder<SELF> beginSidebar() {
        return new SidebarBuilderImpl<SELF>().setParentBuilder((SELF) this);
    }

    default SELF sidebar(Consumer<SidebarBuilder<SELF>> consumer) {
        SidebarBuilder<SELF> builder = beginSidebar();
        consumer.accept(builder);
        return builder.end();
    }


}
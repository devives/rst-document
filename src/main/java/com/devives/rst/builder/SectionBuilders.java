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
package com.devives.rst.builder;

import com.devives.rst.Rst;
import com.devives.rst.document.RstElement;
import com.devives.rst.document.RstNode;
import com.devives.rst.document.Transition;
import com.devives.rst.util.StringUtils;

import java.util.function.Consumer;

public interface SectionBuilders<PARENT extends RstNodeBuilder<?, ?, ?, ?>,
        E extends RstElement,
        T extends RstNode<E>,
        SELF extends RstNodeBuilder<?, ?, ?, ?>>
        extends RstNodeBuilder<PARENT, E, T, SELF>,
        TitleBuilders<PARENT, E, T, SELF>,
        BodyBuilders<PARENT, E, T, SELF> {

    /**
     * Creates a new {@link SectionBuilder} instance.
     *
     * @return a new child builder instance
     */
    default SectionBuilder<SELF> beginSection() {
        return beginSection(null, 1);
    }

    /**
     * Creates a new {@link SectionBuilder} instance.
     *
     * @param title section title
     * @return a new child builder instance
     */
    default SectionBuilder<SELF> beginSection(String title) {
        return beginSection(title, 1);
    }

    /**
     * Creates a new {@link SectionBuilder} instance.
     *
     * @param title section title
     * @param level section level
     * @return a new child builder instance
     */
    default SectionBuilder<SELF> beginSection(String title, int level) {
        SectionBuilder<SELF> builder = new SectionBuilderImpl<SELF>().setParentBuilder((SELF) this);
        if (StringUtils.notNullOrEmpty(title)) {
            builder.title(title, level);
        }
        return builder;
    }

    /**
     * Creates a new {@link SectionBuilder} instance and pass it to the consumer.
     *
     * @param consumer builder consumer
     * @return this instance
     */
    default SELF section(Consumer<SectionBuilder<SELF>> consumer) {
        return section(null, 1, consumer);
    }

    /**
     * Creates a new {@link SectionBuilder} instance and pass it to the consumer.
     *
     * @param title    section title
     * @param consumer builder consumer
     * @return this instance
     */
    default SELF section(String title, Consumer<SectionBuilder<SELF>> consumer) {
        return section(title, 1, consumer);
    }

    /**
     * Creates a new {@link SectionBuilder} instance and pass it to the consumer.
     *
     * @param title    section title
     * @param level    section level
     * @param consumer builder consumer
     * @return this instance
     */
    default SELF section(String title, int level, Consumer<SectionBuilder<SELF>> consumer) {
        SectionBuilder<SELF> builder = beginSection(title, level);
        consumer.accept(builder);
        return builder.end();
    }

    /**
     * Create and append {@link Transition} element to the children list.
     *
     * @return this instance
     */
    default SELF transition() {
        this.addChild((E) Rst.elements().transition());
        return (SELF) this;
    }
}

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


import com.devives.rst.util.RstSerializationException;

import java.util.ArrayList;
import java.util.List;

/**
 * The element of RestructuredText document witch can contain children.
 *
 * @param <CHILD> The class of children.
 */
public abstract class RstNodeAbst<CHILD extends RstElement> implements RstNode<CHILD> {

    final List<CHILD> children = new ArrayList<>();

    /**
     * Method return the list of children elements.
     *
     * @return children
     */
    public List<CHILD> getChildren() {
        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize() throws RstSerializationException {
        return serializeElements();
    }

    /**
     * Method call {@link RstSerializable#serialize()} for all children and collect results.
     *
     * @return children serialized to Rst String.
     */
    protected String serializeElements() {
        final StringBuilder stringBuilder = new StringBuilder();
        children.forEach(child -> onCollectChildText(stringBuilder, onSerializeChild(child)));
        return stringBuilder.toString();
    }

    /**
     * Method serialize child element.
     * <p>
     * Overriding this method allow to change child serialization logic.
     *
     * @param child child element
     */
    protected String onSerializeChild(CHILD child) {
        return child.serialize();
    }

    /**
     * Method append item text to collector.
     * <p>
     * Overriding this method allow to format value additionally.
     *
     * @param stringBuilder collector
     * @param itemText      appendable text
     */
    protected void onCollectChildText(StringBuilder stringBuilder, String itemText) {
        stringBuilder.append(itemText);
    }

}

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
package com.devives.rst;

import com.devives.rst.builder.DefaultRstBuilderFactoryImpl;
import com.devives.rst.builder.RstBuilderFactory;
import com.devives.rst.document.DefaultRstElementFactoryImpl;
import com.devives.rst.document.RstElementFactory;

import java.util.Objects;

/**
 * Class is a static entrypoint to the RST document building.
 */
public final class Rst {

    private Rst() {
    }

    private static RstElementFactory ELEMENTS_FACTORY = new DefaultRstElementFactoryImpl();
    private static RstBuilderFactory BUILDERS_FACTORY = new DefaultRstBuilderFactoryImpl();

    /**
     * Sets the instance of RstElements factory, witch will be used by RstDocumentBuilder instances.
     *
     * @param factory Instance of RstElements factory.
     */
    public static void setElementFactory(RstElementFactory factory) {
        Rst.ELEMENTS_FACTORY = Objects.requireNonNull(factory, "factory");
    }

    /**
     * Sets the instance of RstBuilder factory.
     *
     * @param factory Instance of Rst-factory.
     */
    public static void setBuilderFactory(RstBuilderFactory factory) {
        Rst.BUILDERS_FACTORY = Objects.requireNonNull(factory, "factory");
    }

    /**
     * Return the instance of {@link RstElementFactory}, witch will be used by RstDocumentBuilder instances.
     *
     * @return {@link RstElementFactory} instance.
     */
    public static RstElementFactory elements() {
        return ELEMENTS_FACTORY;
    }

    /**
     * Return the instance of {@link  RstBuilderFactory}.
     *
     * @return {@link  RstBuilderFactory} instance.
     */
    public static RstBuilderFactory builders() {
        return BUILDERS_FACTORY;
    }

}

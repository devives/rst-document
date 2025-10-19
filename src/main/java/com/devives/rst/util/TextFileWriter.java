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
package com.devives.rst.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Писатель текстовых фалов.
 */
public class TextFileWriter {

    private final File file_;
    private final Supplier<String> contentSupplier_;
    private Charset charset_ = StandardCharsets.UTF_8;
    private boolean overwrite_ = true;

    /**
     * @param file            Путь к файлу, куда будет производиться запись.
     * @param contentSupplier Поставщик содержимого файла.
     */
    public TextFileWriter(File file, Supplier<String> contentSupplier) {
        file_ = Objects.requireNonNull(file);
        contentSupplier_ = Objects.requireNonNull(contentSupplier);
    }

    /**
     * Управляет перезаписью существующего файла. Если {@code true}, содержимое существующего файла будет презаписано.
     *
     * @return Флаг перезаписи.
     */
    public boolean isOverwrite() {
        return overwrite_;
    }

    /**
     * Устанавливает флаг перезаписи существующего файла.
     *
     * @param overwrite Флаг перезаписи.
     * @return Текущий объект.
     */
    public TextFileWriter setOverwrite(boolean overwrite) {
        overwrite_ = overwrite;
        return this;
    }

    /**
     * @return Кодировка содержимого файла.
     */
    public Charset getCharset() {
        return Objects.requireNonNull(charset_);
    }

    /**
     * Устанавливает кодировку записываемого содержимого файла.
     *
     * @param charset Кодировка.
     * @return Текущий объект.
     */
    public TextFileWriter setCharset(Charset charset) {
        charset_ = charset;
        return this;
    }

    /**
     * Создаёт/перезаписывает файл и записывает в него содержимое, полученное из поставщика, переданного в конструктор.
     *
     * @throws IOException
     */
    public void write() throws IOException {
        Files.createDirectories(file_.toPath().getParent());
        OpenOption openOption = file_.exists() ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE;
        byte[] bytes = Optional.ofNullable(contentSupplier_.get()).orElse("").getBytes(charset_);
        Files.write(file_.toPath(), bytes, openOption);
    }
}

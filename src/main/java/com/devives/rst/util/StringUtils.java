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
package com.devives.rst.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public abstract class StringUtils implements Constants {

    public static String surroundValueWith(String value, String surrounding) {
        return surrounding + value + surrounding;
    }

    public static String normalizeNewlines(String text) {
        StringBuilder sb = new StringBuilder();
        final int textLength = text.length();
        int pos = 0;
        for (int i = 0; i < textLength; i++) {
            char ch = text.charAt(i);
            switch (ch) {
                case '\n':
                    sb.append(text, pos, i);
                    sb.append(NL);
                    pos = i + 1;
                    break;
                case '\r':
                    sb.append(text, pos, i);
                    sb.append(NL);
                    if (i + 1 < textLength && text.charAt(i + 1) == '\n')
                        i++;
                    pos = i + 1;
                    break;
            }
        }
        sb.append(text, pos, textLength);
        return sb.toString();
    }

    /**
     * Метод добавляет заданное число пробелов в начало каждой новой строки текста.
     * <p>
     * Такими образом, "сдвигается" левая граница текста.
     *
     * @param text  текст
     * @param shift число пробелов
     * @return текст с добавленными пробелами
     */
    public static String shiftLeftEdge(String text, int shift) {
        String[] lines = shiftLeftEdge(splitLines(text), shift);
        return String.join(System.lineSeparator(), lines);
    }

    public static String shiftLeftEdgeExceptFirstLine(String text, int shift) {
        String[] lines = shiftLeftEdgeExceptFirstLine(splitLines(text), shift);
        return String.join(System.lineSeparator(), lines);
    }

    public static String[] shiftLeftEdge(String[] lines, int shift) {
        return doShiftLeftEdge(lines, shift, 0);
    }

    public static String[] shiftLeftEdgeExceptFirstLine(String[] lines, int shift) {
        return doShiftLeftEdge(lines, shift, 1);
    }

    private static String[] doShiftLeftEdge(String[] lines, int shift, int startLine) {
        String sLeftShift = buildString(SPACE_CHAR, shift);
        for (int i = startLine; i < lines.length; i++) {
            lines[i] = lines[i].trim().isEmpty() ? EMPTY : sLeftShift.concat(lines[i]);
        }
        return lines;
    }

    public static String alignLeftEdge(String text) {
        String[] lines = alignLeftEdge(splitLines(text));
        return String.join(System.lineSeparator(), lines);
    }

    public static String[] alignLeftEdge(String[] lines) {
        String[] results = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            results[i] = trimStart(lines[i], SPACE_CHAR);
        }
        return results;
    }

    public static String buildString(char chr, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, chr);
        return new String(chars);
    }

    public static String buildString(String s, int length) {
        switch (length) {
            case 0:
                return "";
            case 1:
                return s;
            case 2:
                return s + s;
            default:
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    sb.append(s);
                }
                return sb.toString();
        }

    }

    private static final Pattern PATTERN_SPLIT_LINES = Pattern.compile("\r?\n|\r");

    public static String[] splitLines(String text) {
        if (text == null) {
            return new String[0];
        } else {
            return PATTERN_SPLIT_LINES.split(text, -1);
        }
    }

    public static String joinLines(String[] lines) {
        return lines != null ? String.join(NL, lines) : "";
    }

    public static String endWithNL(String text) {
        if (text != null && !stripEnd(text, SPACE).endsWith(NL)) {
            text += NL;
        }
        return text;
    }

    public static String endWithSpace(String text) {
        if (text != null && !text.endsWith(SPACE)) {
            text += SPACE;
        }
        return text;
    }

    public static String endWithHiddenSpace(String text) {
        if (text != null && !text.endsWith(SPACE)) {
            text += "\\ ";
        }
        return text;
    }

    public static String surroundWithHiddenSpace(String text) {
        if (text != null && !text.isEmpty()) {
            if (!text.startsWith(SPACE))
                text = "\\ " + text;

            if (!text.endsWith(SPACE))
                text += "\\ ";
        }
        return text;
    }


    /**
     * Проверяет строку на равенство Null и пустой строке
     *
     * @param value строка
     * @return <code>Boolean</code>
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * @param value
     * @return
     */
    public static boolean notNullOrEmpty(String value) {
        return !isNullOrEmpty(value);
    }

    /**
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * @param cs Последовательность символов
     * @return
     */
    public static boolean notEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * Проверяет строку на равенство Null и пустой строке
     *
     * @param value строка
     * @param def   значение по умолчанию
     * @return <code>Boolean</code>
     */
    public static String getNullOrEmptyDef(String value, String def) {
        return isNullOrEmpty(value) ? requireNotNullOrEmpty(def) : value;
    }

    /**
     * Возвращает первое не isNull и не IsEmpty значение.
     *
     * @param values Значения
     * @return Значение
     */
    public static String getNotNullOrEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.isEmpty()) return value;
        }
        throw new RuntimeException("One of values must be not null or empty string.");
    }

    /**
     * Возвращает первое не isNull и не IsEmpty значение.
     *
     * @param values Поставщики строк
     * @return null, если все значения null, иначе первое не isNull и не IsEmpty значение.
     */
    @SafeVarargs
    public static String findFirstNotNullOrEmpty(String... values) {
        for (String value : values) {
            if (notNullOrEmpty(value)) return value;
        }
        return null;
    }

    /**
     * Проверяет значение на не равенство `null` и `пустой строке`.
     *
     * @param value значение
     * @return значение
     * @throws NullPointerException если значение `null'
     * @throws EmptyStringException если значение == `пустая строка`
     */
    public static String requireNotNullOrEmpty(String value) {
        Objects.requireNonNull(value);
        if (value.isEmpty()) {
            throw new EmptyStringException();
        }
        return value;
    }

    /**
     * Проверяет значение на не равенство `null` и `пустой строке`.
     *
     * @param value   значение
     * @param message сообщение исключения
     * @return значение
     * @throws NullPointerException если значение `null'
     * @throws EmptyStringException если значение == `пустая строка`
     */
    public static String requireNotNullOrEmpty(String value, String message) {
        Objects.requireNonNull(value, message);
        if (value.isEmpty()) {
            throw new EmptyStringException(message);
        }
        return value;
    }

    /**
     * Возвращает первое не isNull и не IsEmpty значение, полученное из функций-источников.
     *
     * @param suppliers Поставщики строк
     * @return первое не isNull и не IsEmpty значение.
     * @throws RuntimeException if no one of suppliers return value.
     */
    @SafeVarargs
    public static String getFirstNotNullOrEmpty(Supplier<String>... suppliers) {
        for (Supplier<String> supplier : suppliers) {
            String value = supplier.get();
            if (value != null && !value.isEmpty()) return value;
        }
        throw new RuntimeException("One of the suppliers must return not null or empty string.");
    }

    /**
     * Возвращает первое не isNull и не IsEmpty значение.
     *
     * @param suppliers Поставщики строк
     * @return null, если все значения null, иначе первое не isNull и не IsEmpty значение.
     */
    @SafeVarargs
    public static String findFirstNotNullOrEmpty(Supplier<String>... suppliers) {
        for (Supplier<String> supplier : suppliers) {
            String value = supplier.get();
            if (value != null && !value.isEmpty()) return value;
        }
        return null;
    }

    /**
     * Приводит строку к верхнему регистру если не null
     *
     * @param value строка
     * @return строка в верхнем регистре
     */
    public static String toUpperCase(String value) {
        return value == null ? null : value.toUpperCase();
    }

    /**
     * @param value
     * @param quoteChar
     * @return
     */
    public static String dequote(String value, char quoteChar) {
        if (value == null || value.length() < 2) {
            return value;
        }
        String quoteStr = Character.toString(quoteChar);
        if (value.startsWith(quoteStr) && value.endsWith(quoteStr)) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    /**
     * @param value
     * @param quoteChar
     * @return
     */
    public static String enquote(String value, char quoteChar) {
        if (value == null) {
            return value;
        }
        return quoteChar + value + quoteChar;
    }

    /**
     * @param value1
     * @param value2
     * @return
     */
    public static boolean equalsIgnoreCase(String value1, String value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equalsIgnoreCase(value2);
    }

    /**
     * Удаляет указанный символ из начала и конца строки, в т.ч. если символ повторяется.
     *
     * @param str Строка
     * @param chr Символ
     * @return Строка без указанного символа в начале и конце.
     */
    public static String trim(String str, char chr) {
        if (str == null) return null;
        int len = str.length();
        int st = 0;
        char[] val = str.toCharArray();

        while ((st < len) && (val[st] == chr)) {
            st++;
        }
        while ((st < len) && (val[len - 1] == chr)) {
            len--;
        }
        return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
    }

    /**
     * Удаляет указанный символ из начала строки, в т.ч. если символ повторяется.
     *
     * @param str Строка
     * @param chr Символ
     * @return Строка без указанного символа в начале.
     */
    public static String trimStart(String str, char chr) {
        if (str == null) return null;
        int begin = 0;
        int end = str.length();
        while (begin != end) {
            int b = (str.charAt(begin) == chr) ? begin + 1 : begin;
            if (b != begin) {
                begin = b;
            } else {
                break;
            }
        }
        return begin != 0 ? str.substring(begin, end) : str;
    }

    public static String join(String[] stringArray, String delimiter) {
        return join(stringArray, obj -> obj, delimiter);
    }

    public static <T> String join(List<T> list, String delimiter) {
        return join(list.toArray(new Object[0]), Object::toString, delimiter);
    }

    /**
     * Метод объединяет элементы массива в строку с разделителем
     *
     * @param objectArray     массив объектов
     * @param joinTransformer трансформатор объекта
     * @param delimiter       разделитель
     * @param <T>
     * @return строка стразделителями
     */
    public static <T> String join(T[] objectArray, JoinTransformer<T> joinTransformer, String delimiter) {
        return join(objectArray, joinTransformer, delimiter, false);
    }

    /**
     * Метод объединяет элементы массива в строку с разделителем
     *
     * @param objectArray     массив объектов
     * @param joinTransformer трансформатор объекта
     * @param delimiter       разделитель
     * @param ignoreNulls     если true, значения Null не будут попадать в результирующую строку
     * @param <T>
     * @return строка стразделителями
     */
    public static <T> String join(T[] objectArray, JoinTransformer<T> joinTransformer, String delimiter, boolean ignoreNulls) {
        return join(objectArray, objectArray.length, joinTransformer, delimiter, ignoreNulls);
    }

    /**
     * Метод объединяет элементы массива в строку с разделителем
     *
     * @param objectArray     массив объектов
     * @param length          кол-во объектов массива, которые будут преобразованы в строку
     * @param joinTransformer трансформатор объекта
     * @param delimiter       разделитель
     * @param ignoreNulls     если true, значения Null не будут попадать в результирующую строку
     * @param <T>
     * @return строка стразделителями
     */
    public static <T> String join(T[] objectArray, int length, JoinTransformer<T> joinTransformer, String delimiter, boolean ignoreNulls) {
        StringBuilder sb = new StringBuilder();
        // счётчик добавленных элементов.
        int count = 0;
        for (int i = 0; i < length; i++) {
            String v = joinTransformer.getName(objectArray[i]);
            if (!ignoreNulls || v != null) {
                if (count > 0) {
                    sb.append(delimiter);
                }
                sb.append(v);
                count++;
            }
        }
        return sb.toString();
    }

    public interface JoinTransformer<T> {
        String getName(T obj);
    }

    /**
     * Возвращает подстроку от начала строки {@code string} до начала подстроки {@code suffix}
     *
     * @param string �?сходная строка
     * @param suffix Суффикс
     * @return Подстрока
     */
    public static String substringBefore(String string, String suffix) {
        if (string == null) {
            return null;
        }
        int index = string.indexOf(suffix);
        if (index >= 0) {
            return string.substring(0, index);
        }
        return null;
    }

    /**
     * Возвращает подстроку от конца подстроки {@code prefix} до конца строки {@code string}
     *
     * @param string �?сходная строка
     * @param prefix Префикс
     * @return Подстрока
     */
    public static String substringAfter(String string, String prefix) {
        if (string == null) {
            return null;
        }
        int index = string.indexOf(prefix);
        if (index >= 0) {
            return string.substring(index + prefix.length());
        }
        return null;
    }

    /**
     * Represents a failed index search.
     *
     * @since 2.1
     */
    public static final int INDEX_NOT_FOUND = -1;

    // Substring between
    //-----------------------------------------------------------------------

    /**
     * <p>Gets the String that is nested in between two instances of the
     * same String.</p>
     * <p>
     * <p>A {@code null} input String returns {@code null}.
     * A {@code null} tag returns {@code null}.</p>
     * <p>
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str the String containing the substring, may be null
     * @param tag the String before and after the substring, may be null
     * @return the substring, {@code null} if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>Gets the String that is nested in between two Strings.
     * Only the first match is returned.</p>
     * <p>
     * <p>A {@code null} input String returns {@code null}.
     * A {@code null} open/close returns {@code null} (no match).
     * An empty ("") open and close returns an empty string.</p>
     * <p>
     * <pre>
     * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween(*, null, *)          = null
     * StringUtils.substringBetween(*, *, null)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "]")         = null
     * StringUtils.substringBetween("", "[", "]")        = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str   the String containing the substring, may be null
     * @param open  the String before the substring, may be null
     * @param close the String after the substring, may be null
     * @return the substring, {@code null} if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    // Port: org.apache.commons.lang3.StringUtils#countMatches
    public static int countMatches(CharSequence str, CharSequence sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = indexOf(str, sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // Port: org.apache.commons.lang3.CharSequenceUtils#indexOf
    static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    public static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    /**
     * Приводит первую букву строки к нижнему регистру
     *
     * @param str исходная строка
     * @return результирующая строка
     */
    public static String lowerFirstChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] c = str.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

    public static String lowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    public static String upperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    /**
     * Приводит первую букву строки к верхнему регистру
     *
     * @param str исходная строка
     * @return результирующая строка
     */
    public static String upperFirstChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] c = str.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }


    // Stripping
    //-----------------------------------------------------------------------

    /**
     * <p>Strips whitespace from the start and end of a String.</p>
     *
     * <p>This is similar to {@link #trim(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip("")       = ""
     * StringUtils.strip("   ")    = ""
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to remove whitespace from, may be null
     * @return the stripped String, {@code null} if null String input
     */
    public static String strip(final String str) {
        return strip(str, null);
    }

    /**
     * <p>Strips whitespace from the start and end of a String  returning
     * {@code null} if the String is empty ("") after the strip.</p>
     *
     * <p>This is similar to {@link #trimToNull(String)} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripToNull(null)     = null
     * StringUtils.stripToNull("")       = null
     * StringUtils.stripToNull("   ")    = null
     * StringUtils.stripToNull("abc")    = "abc"
     * StringUtils.stripToNull("  abc")  = "abc"
     * StringUtils.stripToNull("abc  ")  = "abc"
     * StringUtils.stripToNull(" abc ")  = "abc"
     * StringUtils.stripToNull(" ab c ") = "ab c"
     * </pre>
     *
     * @param str the String to be stripped, may be null
     * @return the stripped String,
     * {@code null} if whitespace, empty or null String input
     * @since 2.0
     */
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.isEmpty() ? null : str;
    }


    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #stripToNull(String)}.</p>
     *
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed String,
     * {@code null} if only chars &lt;= 32, empty or null String input
     * @since 2.0
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }


    /**
     * <p>Strips any of a set of characters from the start and end of a String.
     * This is similar to {@link String#trim()} but allows the characters
     * to be stripped to be controlled.</p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.
     * Alternatively use {@link #strip(String)}.</p>
     *
     * <pre>
     * StringUtils.strip(null, *)          = null
     * StringUtils.strip("", *)            = ""
     * StringUtils.strip("abc", null)      = "abc"
     * StringUtils.strip("  abc", null)    = "abc"
     * StringUtils.strip("abc  ", null)    = "abc"
     * StringUtils.strip(" abc ", null)    = "abc"
     * StringUtils.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str        the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String strip(String str, final String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    /**
     * <p>Strips any of a set of characters from the start of a String.</p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart("", *)            = ""
     * StringUtils.stripStart("abc", "")        = "abc"
     * StringUtils.stripStart("abc", null)      = "abc"
     * StringUtils.stripStart("  abc", null)    = "abc"
     * StringUtils.stripStart("abc  ", null)    = "abc  "
     * StringUtils.stripStart(" abc ", null)    = "abc "
     * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str        the String to remove characters from, may be null
     * @param stripChars the characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String stripStart(final String str, final String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    /**
     * <p>Strips any of a set of characters from the end of a String.</p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * StringUtils.stripEnd("120.00", ".0")   = "12"
     * </pre>
     *
     * @param str        the String to remove characters from, may be null
     * @param stripChars the set of characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static String stripEnd(final String str, final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

}

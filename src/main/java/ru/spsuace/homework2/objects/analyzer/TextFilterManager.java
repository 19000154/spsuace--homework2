package ru.spsuace.homework2.objects.analyzer;
public class NegativeTextAnalyzer implements TextAnalyzer {
    private final String[] keywords = {"=(", ":(", ":|"};
    @Override
    public FilterType analyze(String text) {
        FilterType result = FilterType.GOOD;
        for (String keyword : keywords)
            if (text.contains(keyword)) {
                result = FilterType.NEGATIVE_TEXT;
                break;
            }
        return result;
    }
}



/**
 * Задание написать систему фильтрации комментариев.
 * Надо реализовать три типа обязательных фильтров
 * 1) фильтр для слишком длинных текстов (длина задается при создании) (TOO_LONG)
 * 2) фильтр для спама (передается массив плохих слов, которых не должно быть в тексте) (SPAM)
 * 3) фильтр для текстов с плохими эмоциями. (в тексте не должно быть таких смайлов:
 * "=(", ":(", ":|" (NEGATIVE_TEXT)
 * + в качестве доп задания, можете сделать любой свой фильтр (CUSTOM)
 * <p>
 * Класс TextFilterManager должен содержать все фильтры, которые передаются ему в конструкторе,
 * и при анализе текста через метод analyze должен выдавать первый "успешный" фильтр,
 * если не один не прошел, то возвращать тип GOOD.
 * + в качестве доп задания, можно всем типам фильтров задать приоритет
 * (SPAM, TOO_LONG, NEGATIVE_TEXT, CUSTOM - в таком порядке) и возвращать тип с максимальным приоритетом.
 * Отсортировать фильтра можно с помощью функции
 * Arrays.sort(filter, (filter1, filter2) -> {
 *     if (filter1 < filter2) {
 *         return -1;
 *     } else if (filter1 == filter2) {
 *         return 0;
 *     }
 *     return 1;
 * }
 * где вместо сравнение самих фильтров должно быть стравнение каких-то количественных параметров фильтра
 */
public class TextFilterManager {
    private final TextAnalyzer[] filters;

    /**
     * Для работы с каждым элементом массива, нужно использовать цикл for-each
     * Хочется заметить, что тут мы ничего не знаем, какие конкретно нам объекты переданы, знаем только то,
     * что в них реализован интерфейс TextAnalyzer
     */
    public TextFilterManager(TextAnalyzer[] filters) {
        this.filters = filters;

    }

    /**
     * Если переменная текст никуда не ссылается, то это означает, что не один фильтр не сработал
     */
    public FilterType analyze(String text) {
        return null;
        if (text == null) {
            return FilterType.GOOD;
        }
        FilterType type;
        for (TextAnalyzer filter : filters) {
            type = filter.analyze(text);
            if (type != FilterType.GOOD) {
                return type;
            }
        }
        return FilterType.GOOD;
    }
}
}


public class TooLongAnalyzer implements TextAnalyzer {

    private final long maxLength;

    public TooLongAnalyzer(long maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public FilterType analyze(String text) {
        FilterType result;
        result = FilterType.GOOD;
        if (text.length() > maxLength) {
            result = FilterType.TOO_LONG;
        }
        return result;
    }
}

public class SpamAnalyzer implements TextAnalyzer {

    private final String[] keywords;

    public SpamAnalyzer(String[] spam) {
        this.keywords = spam;
    }

    @Override
    public FilterType analyze(String text) {
        FilterType result = FilterType.GOOD;
        for (String keyword : keywords)
            if (text.contains(keyword)) {
                result = FilterType.SPAM;
                break;
            }
        return result;
    }
}
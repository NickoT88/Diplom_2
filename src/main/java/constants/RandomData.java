package constants;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    //генерирование случайных значение типа String для полей login, password, firstName
    public static String RANDOM_EMAIL = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public static String RANDOM_PASS = RandomStringUtils.randomNumeric(5);
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}
package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.val;

import java.util.Locale;

import static java.lang.Thread.sleep;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123", "12345");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        Faker faker = new Faker(new Locale("en"));
        return new AuthInfo(faker.harryPotter().character(), "123qwerty", "12345");
    }

    public static String getCardPan(int id) {
        return "5559 0000 0000 000" + id;
    }
}
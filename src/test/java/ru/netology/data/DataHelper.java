package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123","12345");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        Faker faker = new Faker(new Locale("en"));
        return new AuthInfo(faker.harryPotter().character(), "123qwerty","12345");
    }

    public static void getCardBalance(CardState cardState, ElementsCollection cards) {
        final String balanceStart = "баланс: ";
        final String balanceFinish = " р.";
        val text = cards.find(Condition.attribute("data-test-id", cardState.getCardId())).text();
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = Integer.parseInt(text.substring(start + balanceStart.length(), finish));
        cardState.setBalance(value);
    }
}
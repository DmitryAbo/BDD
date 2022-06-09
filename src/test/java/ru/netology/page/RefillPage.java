package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RefillPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement button = $("button[data-test-id=\"action-transfer\"]");

    public RefillPage() {
        amount.should(visible, Duration.ofSeconds(15));
    }

    public DashboardPage refillMoney(String sum, String pan) {
        amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        amount.setValue(sum);
        from.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        from.setValue(pan);
        button.click();

        return new DashboardPage();
    }
}

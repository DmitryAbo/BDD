package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import ru.netology.data.CardState;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public RefillPage refillCard(String id) {
        $x("//div[@data-test-id = \"" + id + "\"]//child::button").click();
        return new RefillPage();
    }

    public DashboardPage() {
    }

    public CardState getCardState(String id) {
        val text = cards.find(Condition.attribute("data-test-id", id)).text();
        return new CardState(extractBalance(text), extractPan(text));
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    private String extractPan(String text) {
        return "5559 0000 0000 " + text.substring(15, 19);
    }
}

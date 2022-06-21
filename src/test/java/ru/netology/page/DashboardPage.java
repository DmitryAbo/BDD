package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;
import lombok.val;
import ru.netology.data.CardState;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
@Getter
public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");

    public RefillPage refillCard(String id) {
        $x("//div[@data-test-id = \"" + id + "\"]//child::button").click();
        return new RefillPage();
    }

    public DashboardPage() {
    }
}

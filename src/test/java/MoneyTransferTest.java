import org.junit.jupiter.api.*;
import ru.netology.data.AuthInfo;
import ru.netology.data.CardState;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {


    public AuthInfo authInfo = DataHelper.getAuthInfo();
    public CardState firstCard = new CardState(0, "5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    public CardState secondCard = new CardState(0, "5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");


    @BeforeEach
    public void startBrowser() {
        open("http://localhost:9999/");
    }


    @Test
    void transferFirstSecondAllCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = firstCard.getBalance();
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance - transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance + transactionValue);
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferFirstSecondUpperLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = firstCard.getBalance() - 1;
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance - transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance + transactionValue);
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan());
    }

    @Test
    void transferFirstSecondUnderLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = firstCard.getBalance() + 1;
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance - transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance + transactionValue);
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan());
    }

    @Test
    void transferSecondFirstAllCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = secondCard.getBalance();
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance + transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance - transactionValue);
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
    }

    @Test
    void transferSecondFirstUpperLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = secondCard.getBalance() - 1;
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance + transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance - transactionValue);
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
    }

    @Test
    void transferSecondFirstUnderLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var transactionValue = secondCard.getBalance() + 1;
        var firstCardStartBalance = firstCard.getBalance();
        var secondCardStartBalance = secondCard.getBalance();
        dashboardPage.refillCard(firstCard.getCardId()).refillMoney(String.valueOf(transactionValue), secondCard.getPan());
        DataHelper.getCardBalance(firstCard, dashboardPage.getCards());
        DataHelper.getCardBalance(secondCard, dashboardPage.getCards());
        var firstCardFinishBalance = firstCard.getBalance();
        var secondCardFinishBalance = secondCard.getBalance();
        Assertions.assertTrue(firstCardFinishBalance == firstCardStartBalance + transactionValue);
        Assertions.assertTrue(secondCardFinishBalance == secondCardStartBalance - transactionValue);
        dashboardPage.refillCard(secondCard.getCardId()).refillMoney(String.valueOf(transactionValue), firstCard.getPan());
    }

}

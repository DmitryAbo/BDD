import org.junit.jupiter.api.*;
import ru.netology.data.CardState;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class MoneyTransferTest {


    public DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
    public DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);


    @BeforeEach
    public void startBrowser() {
        open("http://localhost:9999/");
    }


    @Test
    void transferFirstSecondAllCash() {
        var cardFromId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardToId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance();                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferFirstSecondUpperLimitCash() {
        var cardFromId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardToId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance() - 1;                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferFirstSecondUnderLimitCash() {
        var cardFromId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardToId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance() + 1;                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferSecondFirstAllCash() {
        var cardToId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardFromId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance();                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferSecondFirstUpperLimitCash() {
        var cardToId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardFromId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance() - 1;                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

    @Test
    void transferSecondFirstUnderLimitCash() {
        var cardToId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        var cardFromId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        var dashboardPageStart = new LoginPage()
                .validLogin(authInfo)
                .validVerify(verificationCode);
        var cardFromStateStart = dashboardPageStart.getCardState(cardFromId);           //Исходный баланс карты списания
        var cardToStateStart = dashboardPageStart.getCardState(cardToId);               //Исходный баланс карты зачисления
        var sum = cardFromStateStart.getBalance() + 1;                                          //Сумма списания/зачисления
        var dashboardPageFinish = dashboardPageStart.refillCard(cardToId).refillMoney(String.valueOf(sum), cardFromStateStart.getPAN());
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardToId).getBalance(), cardToStateStart.getBalance() + sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardToId).getBalance() > 0);
        Assertions.assertEquals(dashboardPageFinish.getCardState(cardFromId).getBalance(), cardFromStateStart.getBalance() - sum);
        Assertions.assertTrue(dashboardPageFinish.getCardState(cardFromId).getBalance() > 0);
        dashboardPageFinish.refillCard(cardFromId).refillMoney(String.valueOf(sum), cardToStateStart.getPAN()); //возвращаем баланс карт в исходное состояние
    }

}

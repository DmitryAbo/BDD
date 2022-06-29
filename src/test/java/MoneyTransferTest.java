import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import ru.netology.data.AuthInfo;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTransferTest {


    public AuthInfo authInfo = DataHelper.getAuthInfo();
    public String firstCardPan = DataHelper.getCardPan(1);
    public String secondCardPan = DataHelper.getCardPan(2);


    @BeforeEach
    public void startBrowser() {
        open("http://localhost:9999/");
    }


    @Test
    void transferFirstSecondAllCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart;
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart - transactionValue);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart + transactionValue);
    }

    @Test
    void transferFirstSecondUpperLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart - 1;
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart - transactionValue);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart + transactionValue);
    }

    @Test
    void transferFirstSecondUnderLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart + 1;
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart);
    }

    @Test
    void transferSecondFirstAllCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart;
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart + transactionValue);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart - transactionValue);
    }

    @Test
    void transferSecondFirstUpperLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart - 1;
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart + transactionValue);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart - transactionValue);
    }

    @Test
    void transferSecondFirstUnderLimitCash() {
        var dashboardPage = new LoginPage()
                .validLogin(authInfo)
                .validVerify(authInfo.getVerificationCode());
        var firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        var secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        var transactionValue = firstCardBalanceStart + 1;
        dashboardPage.refillCard(firstCardPan).
                refillMoney(String.valueOf(transactionValue), secondCardPan);
        var firstCardBalanceFinish = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinish = dashboardPage.getSecondCardBalance();
        dashboardPage.refillCard(secondCardPan).
                refillMoney(String.valueOf(transactionValue), firstCardPan);
        Assertions.assertTrue(firstCardBalanceFinish == firstCardBalanceStart);
        Assertions.assertTrue(secondCardBalanceFinish == secondCardBalanceStart);
    }

}

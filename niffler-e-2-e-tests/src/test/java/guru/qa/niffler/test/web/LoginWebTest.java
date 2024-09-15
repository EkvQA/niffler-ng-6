package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginWebTest {

    private static final Config CFG = Config.getInstance();
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        loginPage = Selenide.open(CFG.frontUrl(), LoginPage.class);
    }

    @Test
    @DisplayName("Negative Login")
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        loginPage.login("test", "123456")
                .checkError("Bad credentials")
                .ensureMainPageNotVisible();
    }

    @Test
    @DisplayName("Positive Login")
    void mainPageShouldBeDisplayedAfterSuccessfulLogin() {
        mainPage = loginPage.login("test", "1234567")
                .successfulLogin();  // Переход на MainPage
        mainPage.checkStatisticsBlockVisible()
                .checkHistoryBlockVisible();
    }
}

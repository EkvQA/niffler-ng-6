package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final SelenideElement createAccountButton = $("a[href='/register']");
  private final SelenideElement errorMessage = $("[class='form__error']");


  public LoginPage login(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return this;
  }

  public MainPage successfulLogin() {
    return new MainPage();  // Возвращаем MainPage, когда логин успешен
  }

  public LoginPage checkError(String errorText) {
    errorMessage.shouldHave(text(errorText));
    return this;
  }

  public LoginPage ensureMainPageNotVisible() {
    new MainPage().checkHistoryNotExist().checkStatisticsNotExist();
    return this;
  }

  public LoginPage goToCreateAccountPage() {
    createAccountButton.click();
    return this;
  }

}

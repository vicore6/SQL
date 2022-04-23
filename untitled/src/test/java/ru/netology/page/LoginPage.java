package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement emptyLogin = $("[data-test-id=login] .input__sub");
    private SelenideElement emptyPassword = $("[data-test-id=password] .input__sub");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public void fieldСleaning() {
        loginField.doubleClick().sendKeys(Keys.DELETE);
        passwordField.doubleClick().sendKeys(Keys.DELETE);
    }

    public void logIn(String login, String password) {
        fieldСleaning();
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
    }

    public VerificationPage validLogIn(String login, String password) {
        logIn(login, password);
        return new VerificationPage();
    }

    public void invalidLogIn(String login, String password) {
        logIn(login, password);
        errorNotification.shouldBe(visible);
    }

    public void blockingMessage() {
        errorNotification.shouldBe(visible).shouldHave(exactText("Система заблокирована"));
    }

    public void emptyLogIn() {
        loginButton.click();
        emptyLogin.shouldBe(visible);
        emptyPassword.shouldBe(visible);
    }
}
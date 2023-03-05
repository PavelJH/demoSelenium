package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterHelpers extends CommonHelpers {
    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    public By errorMessageBlock = By.id("error-message");
    public By errorEmailMessageBlock = By.id("email-error-invalid");
    public By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");

    public RegisterHelpers(WebDriver driver) {
        super(driver);
    }


    public void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));
    }

    public void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
    }

    public void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    public void checkErrorMessage(By locator, String expectedErrorMessage) {
        String err = "Actual error message is not equal expected";
        checkItemText(locator, expectedErrorMessage, err);
    }
}

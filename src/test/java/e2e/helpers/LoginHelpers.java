package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginHelpers extends CommonHelpers {

    By loginForm = By.id("login-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By contactsTable = By.id("contacts-list");

    public LoginHelpers(WebDriver driver) {
        super(driver);
    }

    public void login() {
        String email = "test@gmail.com";
        String password = "test@gmail.com";
        Assert.assertTrue(isElementPresent(loginForm));
        fillField(email, emailField);
        fillField(password, passwordField);
        driver.findElement(loginButton).click();
        Assert.assertTrue(isElementPresent(contactsTable));
    }
}

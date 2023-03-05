package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegisterNewUserTest extends TestBase {
    Faker faker = new Faker();

    //Positive
    @Test(priority = 2)
    public void registerNewUserWithValidData() {
        //Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);
    }


    //Negative
    @Test(priority = 3)
    public void registerNewUserWithInvalidData() {
        //Arrange
        String userData = faker.internet().password();
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";

        //Act
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        Assert.assertFalse(app.getRegister().isElementPresent(app.getRegister().errorMessageBlock));
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorEmailMessageBlock, expectedEmailErrorMessage);
        app.getRegister().checkErrorMessage(app.getRegister().errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);
    }

    //Negative
    @Test(priority = 4)
    public void registerExistingUser() throws IOException, AWTException, InterruptedException {
        //Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";
        //Act
//        app.getRegister().startRecording();
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);
        Thread.sleep(5000);
//        app.getRegister().stopRecording();
    }
}

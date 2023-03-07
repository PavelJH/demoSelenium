package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.EditContactHelpers;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();

    @Test(priority = 1)
    public void userCanCreateEditRemoveContact() throws InterruptedException, IOException, AWTException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;
//        app.getLogin().startRecording();
        app.getLogin().login();


        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);

        String newFirstName = faker.internet().uuid();
        String newLastName = faker.internet().uuid();
        String newDescription = faker.lorem().paragraph(1);


        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(expectedCountRow);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeContactInfoForm(newFirstName, newLastName, newDescription);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(newFirstName, newLastName, newDescription);

        EditContactHelpers getRemoveContact = app.getEditContact();
        getRemoveContact.goToContactPageAndFillFilterField(newFirstName);
        getRemoveContact.openRemoveContactDialog();
        getRemoveContact.removeContact();
        getRemoveContact.checkCountRows(0);
//        app.getLogin().stopRecording();
    }
}

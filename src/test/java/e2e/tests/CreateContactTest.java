package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class CreateContactTest extends TestBase {
    //
    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void createNewContactDataProvider(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactDataProviderWithFileCSV(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
    }
}
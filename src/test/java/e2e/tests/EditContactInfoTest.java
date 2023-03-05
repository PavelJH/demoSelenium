package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactInfoTest extends TestBase {
    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void editContactInfo(String lastName, String description) throws InterruptedException {
        String firstName = "d8e4aecc-5d51-40fc-99ce-000dc03f787e";

        app.getLogin().login();
        app.getEditContact().changeLanguage();
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeLastNameAndDescription(lastName, description);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }
}

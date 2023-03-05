package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditContactHelpers extends ContactHelpers {
    public EditContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openEditForm() throws InterruptedException {
        Thread.sleep(1000);
        clickOnVisibleElement(By.id("btn-edit-contact"));
    }

    public void editeLastNameAndDescription(String lastName, String description) {
        fillField(lastName, (By.cssSelector("[name='input-ec-lastName']")));
        fillField(description, (By.cssSelector("[name='input-ec-description']")));
    }

    public void editeContactInfoForm(String firstName, String lastName, String description) {
        fillField(firstName, (By.name("input-ec-firstName")));
        editeLastNameAndDescription(lastName, description);
    }

    public void saveEditedContact() {
        clickOnVisibleElement(By.xpath("//*[@class='col-sm-3']//*[@type='submit']"));
    }

}

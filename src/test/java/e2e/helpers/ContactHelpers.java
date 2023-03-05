package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {
    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }

    public void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void openContact() {
        clickOnVisibleElement(By.xpath("//*[@id='contacts-list']//*[@class='list-group']"));
    }

    public void openRemoveContactDialog() {
        openDialog(By.xpath("//*[@id='contacts-list']//*[@class='list-group-item']/img"));
    }

    public void removeContact() {
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath("//*[@role='dialog']")));
    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) {
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-first-name']"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-last-name']"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-description']"), description, "Actual description is not equal expected description");
    }

    public void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class='list-group']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }
}

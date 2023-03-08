package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;
    CreateContactHelpers createContact;
    EditContactHelpers editContact;

    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }

    public CreateContactHelpers getCreateContact() {
        return createContact;
    }

    public EditContactHelpers getEditContact() {
        return editContact;
    }

    public WebDriver remoteDriverSelenoid() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();

        options.setCapability("broweserName","firefox");
        options.setCapability("browserVersion","90.0");
        options.setCapability("enableVNC", true);
        options.setCapability("enableLog", true);
        return new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
    }

    protected void init(boolean useRemoteDriver) throws MalformedURLException {
        if(useRemoteDriver == true){
            driver = remoteDriverSelenoid();
            System.out.println("Using remote driver (Selenoid)");
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Using local ChromeDriver");
        }

        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        createContact = new CreateContactHelpers(driver);
        editContact = new EditContactHelpers(driver);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, screenshot);
        return screenshot.getAbsolutePath();
    }

    protected void stop() {
        driver.quit();
    }
}

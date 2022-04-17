package modules.form;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.HomePage;

import java.time.Duration;

@DisplayName("Automation suite for contact form validation")
public class FormTest {

    private WebDriver browser;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();

        this.browser = new ChromeDriver();

        this.browser.manage().window().maximize();

        this.browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        this.browser.get("http://automationpractice.com");
    }

    @Test
    @DisplayName("TC#1 - Send a message via Contact form")
    public void testSendMessageSuccessfuly(){

        String successMessage = new HomePage(browser)
                .goToContactForm()
                .selectSubjectHeading("1")
                .addEmailAddress("test@mail.com")
                .addMessage("This is a test message")
                .sendMessage()
                .getSuccessMessage();

        Assertions.assertEquals("Your message has been successfully sent to our team.", successMessage);
    }

    @Test
    @DisplayName("TC#2 - Try to send a message with empty fields")
    public void testSendMessageWithEmptyFields(){
        String errorMessage = new HomePage(browser)
                .goToContactForm()
                .sendMessage()
                .getFailedMessage();

        Assertions.assertEquals("There is 1 error", errorMessage);
    }

    @Test
    @DisplayName("TC#3 - Try to send a message with an invalid email")
    public void testSendMessageWithInvalidEmail(){
        String emailErrorMessage = new HomePage(browser)
                .goToContactForm()
                .selectSubjectHeading("2")
                .addEmailAddress("123456")
                .addMessage("This is a test message")
                .sendMessage()
                .getEmailErrorMessage();

        Assertions.assertEquals("There is 1 error\n" +
                "Invalid email address.", emailErrorMessage);
    }

    @AfterEach
    public void afterEach(){
        browser.quit();
    }
}

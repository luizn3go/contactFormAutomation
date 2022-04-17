package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactPage {
    private WebDriver browser;

    public ContactPage(WebDriver browser){
        this.browser = browser;
    }

    public ContactPage selectSubjectHeading(String value){
        Select dropdown = new Select(browser.findElement(By.id("id_contact")));
        dropdown.selectByValue(value);

        return this;
    }

    public ContactPage addEmailAddress(String email){
        browser.findElement(By.id("email")).sendKeys(email);

        return this;
    }
    public ContactPage addMessage(String message) {
        browser.findElement(By.id("message")).sendKeys(message);

        return this;
    }

    public ContactPage sendMessage(){
        browser.findElement(By.id("submitMessage")).click();

        return this;
    }

    public String getSuccessMessage(){
        return browser.findElement(By.cssSelector(".alert-success")).getText();
    }

    public String getFailedMessage(){
        return browser.findElement(By.cssSelector(".alert-danger>p")).getText();
    }

    public String getEmailErrorMessage(){
        return browser.findElement(By.cssSelector(".alert-danger")).getText();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver browser;

    public HomePage(WebDriver browser){
        this.browser = browser;
    }

    public ContactPage goToContactForm(){
        browser.findElement((By.id("contact-link"))).click();

        return new ContactPage(browser);
    }
}

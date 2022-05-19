package tme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;


public class Translation_status_page extends Base_page {

    @FindBy(xpath = "//div[contains(text(),'PE')]")
    public WebElement PE;
    @FindBy(xpath = "(//mat-checkbox[@class='mat-checkbox mat-accent ng-untouched ng-pristine ng-valid'])[1]")
    public WebElement first_clickable_language;
    @FindBy(xpath = "//span[normalize-space()='Launch']")
    public WebElement launch;



    public Translation_status_page() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void translation_type_PE() {
        new Actions(Driver.get()).moveToElement(PE).click().build().perform();
    }

    public void clickable_language() {
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(first_clickable_language).click().build().perform();
        new Actions(Driver.get()).moveToElement(launch).click().build().perform();
    }





}






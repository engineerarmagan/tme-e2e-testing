package tme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.Driver;

import java.util.List;

public class Safety_terms_page extends Base_page {

    @FindBy(xpath = "//span[@class='mat-option-text']")
    public List<WebElement> show_only_options;
    @FindBy(xpath = "//mat-select[@placeholder='Show only']")
    public WebElement show_only_dropdown;
    @FindBy(xpath = "//span[contains(text(),'Unknown')]")
    public WebElement unknown;

    public Safety_terms_page() {
        PageFactory.initElements(Driver.get(), this);
    }
    public void show_only() {
        new Actions(Driver.get()).moveToElement(show_only_dropdown).click(show_only_dropdown).build().perform();


    }

    public void make_unknown_to_known() {
        new Actions(Driver.get()).moveToElement(show_only_dropdown).click(show_only_dropdown).build().perform();
        new Actions(Driver.get()).moveToElement(unknown).click(unknown).build().perform();

    }


}
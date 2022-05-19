package tme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.Driver;

import java.util.List;

public class Lim_page extends Base_page {

    @FindBy(xpath="//mat-icon[@id='arrow-down']")
    public WebElement lim_dropdown;
    @FindBy(xpath="//span[@class='mat-checkbox-label']")
    public List<WebElement> lim_languages;
    @FindBy(xpath="(//span[@class='mat-option-text'])[2]")
    public WebElement lim_second_sil;
    @FindBy(xpath="//span[contains(text(),'Edit')]")
    public WebElement edit_button;
    @FindBy(xpath="//th[contains(text(),'Change all')]//mat-icon[contains(text(),'done')]")
    public WebElement change_all_button;
    @FindBy(xpath="//span[contains(text(),'Save')]")
    public WebElement save_button;



    public Lim_page(){
        PageFactory.initElements(Driver.get(),this);
    }
    public void select_second_sil(){
    new Actions(Driver.get()).moveToElement(lim_dropdown).click().build().perform();
    new Actions(Driver.get()).moveToElement(lim_second_sil).click().build().perform();
}
public  void update_lim(){
    new Actions(Driver.get()).moveToElement(edit_button).click().build().perform();
    new Actions(Driver.get()).moveToElement(change_all_button).click().build().perform();
    new Actions(Driver.get()).moveToElement(save_button).click().build().perform();
}
}

package tme.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

public class Quality_assurance_page {
    @FindBy(xpath="//span[contains(text(),'End')]")
    public WebElement end_qa_button;
    @FindBy(xpath="//span[normalize-space()='Back to CP']")
    public WebElement back_to_cp_button;
    @FindBy(xpath="//mat-icon[contains(text(),'thumb_up')]")
    public WebElement pass_qa_button;
    @FindBy(xpath="//mat-icon[contains(text(),'thumb_down')]")
    public WebElement fail_qa_button;

    public Quality_assurance_page(){PageFactory.initElements(Driver.get(),this);}


    public void pass_qa(){
        BrowserUtils.waitFor(4);
        new Actions(Driver.get()).moveToElement(end_qa_button).click(end_qa_button).build().perform();
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(pass_qa_button).click(pass_qa_button).build().perform();
        Driver.get().switchTo().alert().accept();
        new Actions(Driver.get()).moveToElement(back_to_cp_button).click(back_to_cp_button).build().perform();
    }
    public void fail_qa(){
        new Actions(Driver.get()).moveToElement(end_qa_button).click().build().perform();
        new Actions(Driver.get()).moveToElement(fail_qa_button).click().build().perform();
        Driver.get().switchTo().alert().accept();
        new Actions(Driver.get()).moveToElement(back_to_cp_button).click().build().perform();
    }

}

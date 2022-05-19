package tme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.Driver;

import java.util.List;

public class Queue_page extends Base_page {


    @FindBy(xpath="//section/div/div")
    public List<WebElement> queue_columns;
    @FindBy(xpath="//mat-select[@aria-label='Queue Priority']")
    public WebElement priority_dropdown;
    @FindBy(xpath="//span[contains(text(),'Priority 2')]")
    public WebElement priority_2;
    @FindBy(xpath="//span[contains(text(),'Priority 1')]")
    public WebElement priority_1;
    @FindBy(xpath="//mat-select[@aria-label='Queue Name']")
    public WebElement queue_name_dropdown;
    @FindBy(xpath="//span[contains(text(),'Translation Queue')]")
    public WebElement translation_queue;
    @FindBy(xpath="//span[contains(text(),'Import Queue')]")
    public WebElement import_queue;
    @FindBy(xpath="//span[contains(text(),'Publish Queue')]")
    public WebElement publish_queue;
    @FindBy(xpath="//mat-select[@aria-label='Translation Type']")
    public WebElement translation_type_dropdown;
    @FindBy(xpath="//span[contains(text(),'MT')]")
    public WebElement MT;
    @FindBy(xpath="//span[contains(text(),'NON-MT')]")
    public WebElement NON_MT;
    @FindBy(xpath="//span[contains(text(),'Filter')]")
    public WebElement filter;
    @FindBy(xpath="//span[contains(text(),'Clear')]")
    public WebElement clear;

    public Queue_page(){
        PageFactory.initElements(Driver.get(),this);
    }
}

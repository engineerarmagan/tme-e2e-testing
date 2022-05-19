package tme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.Driver;

public class Metadata_page extends Base_page {

    public Metadata_page(){
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(name = "//input[@placeholder='Subject']")
    public WebElement subject_input;
    @FindBy(name = "//input[@placeholder='Affected Publication']")
    public WebElement affected_publication;
    @FindBy(name = "//button[@aria-label='Open calendar']")
    public WebElement open_calendar;
    @FindBy(name = " //span[normalize-space()='Save']")
    public WebElement save_button;
    @FindBy(name = " //span[normalize-space()='Save Selection']")
    public WebElement save_selection;






}


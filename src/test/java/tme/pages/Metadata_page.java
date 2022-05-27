package tme.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.io.File;
import java.util.List;

public class Metadata_page extends Base_page {

    public Metadata_page(){
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(xpath = "//input[@placeholder='Subject']")
    public WebElement subject_input;
    @FindBy(xpath = "//input[@placeholder='Affected Publication']")
    public WebElement affected_publication;
    @FindBy(xpath = "//button[@aria-label='Open calendar']")
    public WebElement open_calendar;
    @FindBy(xpath = " //span[normalize-space()='Save']")
    public WebElement save_button;
    @FindBy(xpath = " //span[normalize-space()='Save Selection']")
    public WebElement save_selection_button;
    @FindBy(xpath = "//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")
    public WebElement todays_date;
    @FindBy(xpath = "//button[normalize-space()='Revalidate & Approve Metadata']")
    public WebElement revalidate_metadata;
    @FindBy(xpath = "//button[contains(text(),'Edit Metadata')]")
    public WebElement edit_metadata_button;
    @FindBy(xpath = "//div[contains(text(),'Metadata')]")
    public WebElement metadata_button;
    @FindBy(xpath=" (//div[contains(text(),'Metadata Objects')]//parent::div)[1]//following-sibling::div//button")
    public List<WebElement> metadata_objects;





    public void set_line_off_date(){
        System.out.println("inside line off date method");
        //edit_metadata_button.click();
        BrowserUtils.waitFor(5);
        edit_metadata_button.click();
        System.out.println("edit metadata yi tikladik");
        BrowserUtils.waitFor(5);
        open_calendar.click();
        System.out.println("calendari tiklamaya");
        BrowserUtils.waitFor(5);
        todays_date.click();
        save_button.click();
        BrowserUtils.waitFor(2);
        revalidate_metadata.click();
        Driver.get().navigate().to(stage_details_url);
        new Stage_details_page().revalidate_metadata();
    }

    public void select_metadata(){
        metadata_button.click();
    }

    public void download_metadata_object(){
        BrowserUtils.waitFor(3);
        String first_object_name=metadata_objects.get(1).getText();
        metadata_objects.get(1).click();
        BrowserUtils.waitFor(3);
       // Driver.get().switchTo().alert().accept();

        BrowserUtils.waitFor(5);
        File folder=new File(System.getProperty("user.dir"));
        File[] list_of_files=folder.listFiles();
        boolean found=false;
        File f=null;
        for (File list_of_file:list_of_files){
            if(list_of_file.isFile()){
                String file_name2=list_of_file.getName();


                if(file_name2.contains("Unconfirmed")){
                    System.out.println("file "+first_object_name+ " is downloaded");
                    f=new File(file_name2);
                    found=true;
                }
            }
        }
        Assert.assertTrue("not found",found);
        f.deleteOnExit();

    }



public void metadata_validation_check(){




}


}


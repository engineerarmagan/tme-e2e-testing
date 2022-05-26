package tme.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Safety_terms_page extends Base_page {

    @FindBy(xpath = "//span[@class='mat-option-text']")
    public List<WebElement> show_only_options;
    @FindBy(xpath = "//mat-select[@placeholder='Show only']")
    public WebElement show_only_dropdown;
    @FindBy(xpath = "//span[contains(text(),'Unknown')]")
    public WebElement unknown;
    @FindBy(xpath = "//span[normalize-space()='Download CSV']")
    public WebElement download_csv;
    @FindBy(xpath = "//span[normalize-space()='Translated']")
    public WebElement translated;


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

    public void file_download(String file_name){

        download_csv.click();
        BrowserUtils.waitFor(5);
        File folder=new File(System.getProperty("user.dir"));
        File[] list_of_files=folder.listFiles();
        boolean found=false;
        File f=null;
        for (File list_of_file:list_of_files){
            if(list_of_file.isFile()){
                String file_name2=list_of_file.getName();

                if(file_name2.contains(file_name)){
                    System.out.println("file "+list_of_file.getName()+ " is exists");
                    f=new File(file_name2);
                    found=true;
                }
            }
        }
        Assert.assertTrue("not found",found);
        f.deleteOnExit();

    }
public void select_translated(){
        translated.click();
}
}
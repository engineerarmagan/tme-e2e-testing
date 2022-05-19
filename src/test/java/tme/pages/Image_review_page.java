package tme.pages;


import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

public class Image_review_page {


    @FindBy(xpath="//mat-icon[@svgicon='thumbs-up']")
    public WebElement thumbs_up_icon;
    @FindBy(xpath="//mat-icon[@svgicon='thumbs-down']")
    public WebElement thumbs_down_icon;
    @FindBy(xpath="//mat-icon[@svgicon='right-arrow']")
    public WebElement next_image_icon;
    @FindBy(xpath="//span[contains(text(),'Accept remaining')]")
    public WebElement accept_remaining_button;
    @FindBy(xpath="//span[contains(text(),'Save')]")
    public WebElement save_icon; // appears only when all images accepted OR rejected for a small period
    // If you reject one image it opens a new page to download and upload DTP kit
    @FindBy(xpath="//span[normalize-space()='Prepare download']")
    public WebElement prepare_download_button;
    @FindBy(xpath="//mat-icon[normalize-space()='archive']")
    public WebElement download_icon; // this button downloading a zip file to downloads
    @FindBy(xpath="//input[@id='fileinput']")
    public WebElement choose_file_button;
    @FindBy(xpath="//span[normalize-space()='Upload']")
    public WebElement upload_button;
    @FindBy(xpath="//span[normalize-space()='Return to content page']")
    public WebElement return_to_content_page;
    @FindBy(xpath="//span[normalize-space()='Image review']")
    public WebElement image_review_button;

    public Image_review_page(){
        PageFactory.initElements(Driver.get(),this);
    }

        public void image_review() {
            System.out.println("Checking images");
            new Actions(Driver.get()).moveToElement(image_review_button).click(image_review_button).build().perform();
            BrowserUtils.waitFor(3);
        }
        public void image_check(){
            try{
               if(next_image_icon.isDisplayed()) thumbs_up();
           }catch(WebDriverException e){
                    System.out.println("this one was the last image");
                }}
           public void leave_image_review_page(){
            new Actions(Driver.get()).moveToElement(thumbs_up_icon).click().build().perform();
           BrowserUtils.waitFor(5);
           System.out.println("return to content page time");
            new Actions(Driver.get()).moveToElement(return_to_content_page).click().build().perform();}

        public void save_images(){
        new Actions(Driver.get()).moveToElement(save_icon).click().build().perform();
        }
        public void thumbs_up(){
        new Actions(Driver.get()).moveToElement(thumbs_up_icon).click().build().perform();
        BrowserUtils.waitFor(1);
        image_check();
        }
        public void image_check_tmna(){
            System.out.println("Checking images");
            new Actions(Driver.get()).moveToElement(image_review_button).click(image_review_button).build().perform();
            BrowserUtils.waitFor(3);
            for(int i=0;i<1000;i++)
            {
                System.out.println("checking for next image icon");
                if(next_image_icon.isEnabled())
                {
                    System.out.println("icon is displayed");
                    new Actions(Driver.get()).moveToElement(thumbs_up_icon).click().build().perform();
                    BrowserUtils.waitFor(1);
                }
                else
                {
                    BrowserUtils.waitFor(4);
                }
            }
            new Actions(Driver.get()).moveToElement(return_to_content_page).click().build().perform();
        }}



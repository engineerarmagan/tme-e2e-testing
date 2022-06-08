package tme.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.time.Duration;

public class Post_edit_page {

    public Post_edit_page(){PageFactory.initElements(Driver.get(),this);}

    @FindBy (xpath="//span[normalize-space()='Post Edit']")
    public WebElement post_edit_button;

    @FindBy (xpath="(//span[contains(text(),'Edit')])[1]")
    public WebElement edit_button;

    @FindBy(xpath="//div[contains(text(),'€')][1]")
    public WebElement translation_cost;

    @FindBy(xpath="//span[normalize-space()='Approve']")
    public WebElement approve_button;

    @FindBy(xpath="//td[@class='centralText']//input[@type='checkbox']")
    public WebElement select_all_for_PE;

    @FindBy(xpath="//tr[@class='indent10 ng-star-inserted bordered']")
    public WebElement select_part_for_PE;

    @FindBy(xpath="//span[normalize-space()='Save']")
    public WebElement save_button;

    @FindBy(xpath="//span[normalize-space()='Request New Cost']")
    public WebElement request_new_cost_button;

    @FindBy(xpath="//div[normalize-space()='Current']//preceding-sibling::div/span")
    public WebElement current_translation_quality;

    @FindBy(xpath=" //tr[2]/td[1]/following-sibling::td[7]/input")
    public WebElement general_checkbox;

    public void get_post_edit_details(){
        post_edit_button.click();
        BrowserUtils.waitFor(1);

        String translation_cost=new Post_edit_page().translation_cost.getText();
        String cost_number=translation_cost.split("€")[0];
        System.out.println(cost_number);
        System.out.println("current quality=" +current_translation_quality.getText().split("%")[0]);


    }


    public void ask_for_post_edit(){
        String postedit_url=Driver.get().getCurrentUrl();
        BrowserUtils.waitFor(3);
        edit_button.click();
        System.out.println("clicked edit buttton");
        BrowserUtils.waitFor(2);
//        select_all_for_PE.click();
//        BrowserUtils.waitFor(1);
//        select_all_for_PE.click();
//        BrowserUtils.waitFor(3);
        //select_part_for_PE.click();
        general_checkbox.click();
        BrowserUtils.waitFor(3);
        System.out.println("selected general");
        save_button.click();
        BrowserUtils.waitFor(3);
        System.out.println("saved");
        request_new_cost_button.click();
        BrowserUtils.waitFor(5);
        System.out.println("new cost request send");

        WebDriverWait wait=new WebDriverWait(Driver.get(), Duration.ofSeconds(1200));
        wait.pollingEvery(Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Approve']")));
        String cost_after_PE=new Post_edit_page().translation_cost.getText();
        String cost=cost_after_PE.split("€")[0];
        System.out.println("cost after pe "+ cost);
        approve_button.click();
        System.out.println("approve button clicked");
    }






}
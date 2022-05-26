package tme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;
import java.util.List;


public class History_page extends Base_page {

    @FindBy(xpath="//span[normalize-space()='Clear']")
    public WebElement clear_button;
    @FindBy(xpath="//mat-select[@id='choice-filter-5']")
    public WebElement language_selection_dropdown;
    @FindBy(xpath="//mat-select[@id='choice-filter-8']")
    public WebElement status_selection_dropdown;
    @FindBy(xpath="//mat-select[@id='choice-filter-7']")
    public WebElement stage_selection_dropdown;
    @FindBy(xpath="//span[normalize-space()='Filter']")
    public WebElement filter;
    @FindBy(xpath="//tbody/tr[1]")
    public WebElement first_in_list;
    @FindBy(xpath="//tbody/tr[4]")
    public WebElement nth_in_list;
    @FindBy(xpath="//th[@class='sortable-header ng-star-inserted']")
    public List<WebElement> history_headers; //only sortable ones
    @FindBy(xpath="//input[@id='input-choice-filter-2']")
    public WebElement alternatepub_id_input;


    public History_page(){

        PageFactory.initElements(Driver.get(),this);
    }


    public void select_first_publication_from_list(){
        BrowserUtils.waitFor(2);
        first_in_list.click();

    }
    public void select_nth_publication_from_list(){
        BrowserUtils.waitFor(2);
        nth_in_list.click();

    }

    public void clear_filter(){
        clear_button.click();
    }

//    public void selects_publication_type(String type) {
//
//        BrowserUtils.waitFor(2);
//        new Actions(Driver.get()).moveToElement(publication_selection_dropdown).click().build().perform();
//        String pub_type = "//span[contains(text(),'" + type + "')]";
//       // String pub_type = "//span[text()='"+ type + "']";
//        WebElement publication_type = Driver.get().findElement(By.xpath(pub_type));
//        String pub_type_for_project_name=publication_type.getText();
//        System.out.println(pub_type_for_project_name);
//        new Actions(Driver.get()).moveToElement(publication_type).click(publication_type).build().perform();
//        BrowserUtils.waitFor(2);

//    }
    public void selects_stage_type(String type){
        BrowserUtils.waitFor(2);
        stage_selection_dropdown.click();
        String stag_type="//span[contains(text(),'"+type+"')]";
        WebElement stage_type=Driver.get().findElement(By.xpath(stag_type));
        stage_type.click();
        //new Actions(Driver.get()).moveToElement(stage_type).click(stage_type).build().perform();
    }
    public void select_language(String type){
        BrowserUtils.waitFor(2);
        language_selection_dropdown.click();
        String language="//mat-option//span[contains(text(),'"+ type+"')]";
        WebElement language_type = Driver.get().findElement(By.xpath(language));
        language_type.click();
       // new Actions(Driver.get()).moveToElement(language_type).click(language_type).build().perform();
    }
    public void select_status(String type){
        BrowserUtils.waitFor(2);
        status_selection_dropdown.click();
        String status = "//span[contains(text(),'" + type + "')]";
        WebElement status_type = Driver.get().findElement(By.xpath(status));
        status_type.click();
        //new Actions(Driver.get()).moveToElement(status_type).click(status_type).build().perform();
    }
    public void filter(){

        new Actions(Driver.get()).moveToElement(filter).doubleClick().build().perform();

    BrowserUtils.waitFor(4);
}

    public void alternate_pubid_search(){
    System.out.println("alternate_pubid_search");
    alternatepub_id_input.sendKeys("BM33B3U");

}


}

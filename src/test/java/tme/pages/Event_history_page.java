package tme.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.util.List;


public class Event_history_page {



    @FindBy(xpath="(//span[contains(text(),'Stage/Area')])[1]")
    WebElement event_history_page_stage_dropdown;

    @FindBy(xpath="(//span[contains(text(),'Event')])[1]")
    WebElement event_history_page_event_type_dropdown;

    @FindBy(xpath="//tr/td[7]")
    List<WebElement> filtered_events_list;
    @FindBy(xpath="//span[normalize-space()='Filter']")
    public WebElement filter;
    static String stage_type="";
    public Event_history_page(){
        PageFactory.initElements(Driver.get(),this);
    }

    public void selects_stage_type(String type){
        BrowserUtils.waitFor(5);
        new Actions(Driver.get()).moveToElement(event_history_page_stage_dropdown).click().build().perform();
        String stag_type="//span[contains(text(),'"+type+"')]";
        WebElement stage_type= Driver.get().findElement(By.xpath(stag_type));
        new Actions(Driver.get()).moveToElement(stage_type).click(stage_type).build().perform();
    }
    public void selects_event_type(String type){
        filter.click();
        BrowserUtils.waitFor(4);
        new Actions(Driver.get()).moveToElement(event_history_page_event_type_dropdown).click(event_history_page_event_type_dropdown).build().perform();
       // event_history_page_event_type_dropdown.click();
        String event_stage_type="//span[contains(text(),'"+type+"')]";
        stage_type=type;
        WebElement stage_type= Driver.get().findElement(By.xpath(event_stage_type));
        new Actions(Driver.get()).moveToElement(stage_type).click(stage_type).build().perform();
    }

    public void get_filtered_events_list() {

        List<String> actual_list = BrowserUtils.getElementsText(filtered_events_list);
        System.out.println(actual_list);
        Assert.assertTrue(actual_list.contains(stage_type));

        for (String a : actual_list) {
            Assert.assertTrue(a.equals(stage_type));

        }
        System.out.println("Filtered list contains only "+ stage_type);
    }}

package tme.pages;

import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.util.List;

public class Queue_priority_page {

    @FindBy(xpath="//span[contains(text(),'Priority 1')]//following-sibling::div//div")
    public List<WebElement> priority_1_publications;
    @FindBy(xpath="//span[contains(text(),'Priority 2')]//following-sibling::div//div")
    public List<WebElement> priority_2_publications;
    @FindBy(xpath=" //span[contains(text(),'Save')]")
    WebElement save_button;
    @FindBy(xpath="(//span[contains(text(),'Priority 2')]//following-sibling::div//div)[1]")
    WebElement first_from_priority_2;
    @FindBy(xpath="(//span[contains(text(),'Priority 1')]//following-sibling::div//div)[1]")
    WebElement first_from_priority_1;
    @FindBy(xpath="(//div[@dragula='DRAGULA_FACTS'])[1]")
    WebElement first_box;
    public static int size_before;
    public static int size_after;

    public Queue_priority_page(){

        PageFactory.initElements(Driver.get(),this);
    }


    public void get_priority_one_publications(){
        BrowserUtils.waitFor(5);
        List<String> priority_1= BrowserUtils.getElementsText(priority_1_publications);
        System.out.println(priority_1);
        size_before=priority_1_publications.size();
    }
    public void priority_one_publications_after_drag(){
        BrowserUtils.waitFor(5);
        List<String> priority_1= BrowserUtils.getElementsText(priority_1_publications);
        System.out.println(priority_1);
        size_after=priority_1_publications.size();
    }


    public void get_priority_two_publications(){
        List<String> priority_2=BrowserUtils.getElementsText(priority_2_publications);
        System.out.println(priority_2);
    }
    public void drag_drop_from_2_to_1(){
        BrowserUtils.waitFor(3);
        new Actions(Driver.get()).dragAndDrop(first_from_priority_2,first_from_priority_1).build().perform();
        BrowserUtils.waitFor(3);
     }

     public void drag_and_drop(){
        if(priority_2_publications.size()>=priority_1_publications.size()) drag_drop_from_2_to_1();
        else drag_drop_from_1_to_2();
     }

    private void drag_drop_from_1_to_2() {
        BrowserUtils.waitFor(3);
        new Actions(Driver.get()).dragAndDrop(first_from_priority_1,first_from_priority_2).build().perform();
        BrowserUtils.waitFor(3);
    }


    public void save() {
        save_button.click();
    }
    public void size_comparision(){
        System.out.println(size_before);
        System.out.println(size_after);
        Assert.assertFalse(size_before==size_after);
    }
}

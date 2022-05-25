package tme.pages;
import io.cucumber.java.et.Ja;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class Cost_approval_page {
    @FindBy(xpath="//tr/td[1]")
    public List<WebElement> all_CPIDs;
    @FindBy(xpath="//span[normalize-space()='Approve']")
    public WebElement approve_button;
    public Cost_approval_page(){
        PageFactory.initElements(Driver.get(),this);
    }


    public void approve_cost(){
        System.out.println("approve cost methodun ici");

        String my_cpid= Xtm_dashboard_page.CPID;
        List<String> my_checkbox=all_CPIDs.stream().filter(s -> s.getText().contains(my_cpid))
                .map(this::check_approval_checkbox)
                .collect(Collectors.toList());
        }

    private String check_approval_checkbox(WebElement s) {
        System.out.println("check approval checkbox ici");
        BrowserUtils.waitFor(2);
        s.findElement(By.xpath("following-sibling::td[15]")).click();
        return null;
    }
    public void click_approve_button(){
        JavascriptExecutor js=(JavascriptExecutor) Driver.get();
        js.executeScript("window.scrollBy(0,-500)","");
        BrowserUtils.waitFor(2);
        approve_button.click();
        System.out.println("click approve button");
    }

    public void approve_cost_tmna(){
        System.out.println("at_approve_cost_tmna");

        String my_cpid= Xtm_dashboard_page.CPID;
        List<String> my_checkbox=all_CPIDs.stream().filter(s -> s.getText().contains(my_cpid))
                .map(this::check_approval_checkbox_tmna)
                .collect(Collectors.toList());

    }

    private String check_approval_checkbox_tmna(WebElement s) {
        System.out.println("at checkbox");
        s.findElement(By.xpath("/following-sibling::td[18]")).click();
        return null;
    }
}



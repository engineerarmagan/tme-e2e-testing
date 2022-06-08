package tme.pages;
import io.cucumber.java.et.Ja;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
       // String my_cpid= "NM40P1U~1~EN~22Tundra-HV-Tundra_NM40P0U_EN_22-02-10_UX~ES~T2~1";
        List<String> my_checkbox=all_CPIDs.stream().filter(s -> s.getText().contains(my_cpid))
                .map(this::check_approval_checkbox)
                .collect(Collectors.toList());
        }

    private String check_approval_checkbox(WebElement s) {
        System.out.println("check approval checkbox ici");
        BrowserUtils.waitFor(2);
        Dimension dimension = new Dimension(1920, 1080);
        Driver.get().manage().window().setSize(dimension);
        String current_url=Driver.get().getCurrentUrl();
        System.out.println(current_url);
        if(current_url.contains("toyota"))
            { System.out.println("url contains toyota");
               if(Base_page.pub_type_for_project_name.equals("BULLETIN"))
                        {
                            System.out.println("bulletin type cost approval");
                   s.findElement(By.xpath("following-sibling::td[9]")).click();
                        }
               else {
                   System.out.println("non bulletin type cost approval");
                   s.findElement(By.xpath("following-sibling::td[15]")).click();
                    }
            }

        if(current_url.contains("tmna")){
            System.out.println("url contains tmna");
            s.findElement(By.xpath("following-sibling::td[18]")).click();
        }
        System.out.println("checkbox should be clicked now ");
        System.out.println("approve_button is displayed="+ approve_button.isDisplayed());
        Assert.assertTrue(approve_button.isDisplayed());
        return null;
    }
    public void click_approve_button(){
        JavascriptExecutor js=(JavascriptExecutor) Driver.get();
        js.executeScript("window.scrollTo(0,0)","");
        BrowserUtils.waitFor(5);
        new Actions(Driver.get()).moveToElement(approve_button).click().build().perform();
        BrowserUtils.waitFor(3);
        System.out.println("clicked approve button");
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


    //this method is to run a test from the middle
    public void approve_cost_for_specific_CPID(){

        System.out.println("approve cost for specific cpid methodun ici");
        String my_cpid= "NM40P1U~1~EN~22Tundra-HV-Tundra_NM40P0U_EN_22-02-10_UX~ES~T2~1";
        System.out.println(my_cpid);
        List<String> my_checkbox=all_CPIDs.stream().filter(s -> s.getText().contains(my_cpid))
                .map(this::check_approval_checkbox)
                .collect(Collectors.toList());
    }
}



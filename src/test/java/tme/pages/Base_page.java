package tme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tme.utilities.Driver;
import tme.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public abstract class Base_page {

    @FindBy(xpath = "//li[@class='pointer font-medium']")
    public List<WebElement> menu_options; //top headers

    @FindBy(xpath="//tr/th")
    public List<WebElement> dashboard_columns;

    public static String stage_details_url;
    public static String project_name;
    public static String CPID;
    public static String pub_type_for_project_name;
    @FindBy(xpath="//input[@id='PROJECT_NAME']")
    public static WebElement project_name_field;

    @FindBy(xpath="//div/span[contains(text(),'Publication Type')]")
    public WebElement publication_selection_dropdown;


    public Base_page() {

        PageFactory.initElements(Driver.get(), this);
    }

    public void navigate_to_tab(String tab) {
        tab=tab.toLowerCase(Locale.ROOT);
        String tabLocator = "//div[@id='"+ tab + "-link']";

        try {
            BrowserUtils.waitForClickability(By.xpath(tabLocator), 5);
            WebElement tabElement = Driver.get().findElement(By.xpath(tabLocator));
            new Actions(Driver.get()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(tabLocator), 5);
        }
    }

    public void navigate_to_subtab(String tab, String subtab) {
        String tab_locator = "//div[contains(text(),'"+ tab + "')]";
        String sub_tab_locator = "//div[contains(text(),'" + subtab + "')]";
        BrowserUtils.waitFor(2);
        WebElement tab1 = Driver.get().findElement(By.xpath(tab_locator));
        new Actions(Driver.get()).moveToElement(tab1).click(tab1).build().perform();
        BrowserUtils.waitFor(2);
        WebElement subtab1 = Driver.get().findElement(By.xpath(sub_tab_locator));
        new Actions(Driver.get()).moveToElement(subtab1).click(subtab1).build().perform();
    }
    public static void Guardhouse_id() {
        String currentURL = Driver.get().getCurrentUrl();
        String[] URLsplit = currentURL.split("~");
        String Guardhouse_id = URLsplit[3];
        System.out.println("Guardhouse_id="+ Guardhouse_id);
    }

    public void  get_required_details()  {
      if (pub_type_for_project_name.equals("QRS")){
          System.out.println("IF pub_type_for_project_name="+pub_type_for_project_name);
          stage_details_url = Driver.get().getCurrentUrl();
          System.out.println("stage_details_url = " + stage_details_url);
          String [] CPID_list=stage_details_url.split("detail/");
          CPID=CPID_list[1];
          System.out.println("CPID=" + CPID);
          String[] project_name_list=CPID.split("~");

          project_name = project_name_list[0] + " v" + project_name_list[1] + " " + project_name_list[2] + "_to_" + project_name_list[4] + " " + project_name_list[6] + " " + project_name_list[5] ;
          System.out.println("project name="+project_name);
      }else{
          System.out.println("ELSE pub_type_for_project_name="+pub_type_for_project_name);
        stage_details_url = Driver.get().getCurrentUrl();
        System.out.println("stage_details_url = " + stage_details_url);
        String [] CPID_list=stage_details_url.split("detail/");
        CPID=CPID_list[1];
        System.out.println("CPID=" + CPID);
        String[] project_name_list=CPID.split("~");

        project_name = project_name_list[0] + " v" + project_name_list[1] + " " + project_name_list[2] + "_to_" + project_name_list[4] + " " + project_name_list[6] + " " + project_name_list[5] + " " + "PE";
        System.out.println("project name="+project_name);

    }}
    public void put_project_name()  {
        new Actions(Driver.get()).moveToElement(project_name_field).click().sendKeys(project_name).build().perform();
        BrowserUtils.waitFor(3);
    }
    public void selects_publication_type(String type) {

        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(publication_selection_dropdown).click().build().perform();

        if (type.equals("RM")){
            String pub_type = "(//span[contains(text(),'" + type + "')])[2]";
            WebElement publication_type = Driver.get().findElement(By.xpath(pub_type));
            new Actions(Driver.get()).moveToElement(publication_type).click(publication_type).build().perform();
            pub_type_for_project_name=publication_type.getText();
            BrowserUtils.waitFor(2);
        }
        else
        {
            System.out.println("pub type check");
        String pub_type = "//span[contains(text(),'" + type + "')]";
        WebElement publication_type = Driver.get().findElement(By.xpath(pub_type));
        new Actions(Driver.get()).moveToElement(publication_type).click(publication_type).build().perform();
        pub_type_for_project_name=publication_type.getText();
        BrowserUtils.waitFor(2);
}

    }

}




package tme.pages;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.io.*;

import static io.restassured.RestAssured.given;

public class Xtm_dashboard_page extends Base_page{


    @FindBy(xpath="//a[normalize-space()='Projects']")
    public WebElement project_button;
    @FindBy(xpath="(//label[normalize-space()='All'])[1]")
    public WebElement all_button;
    @FindBy(xpath="//a[normalize-space()='Advanced search']")
    public WebElement advanced_search_button;
//    @FindBy(xpath="//input[@id='PROJECT_NAME']")
//    public static WebElement project_name_field;
    @FindBy(xpath="//span[normalize-space()='Search']")
    public WebElement search_button;
    @FindBy(xpath="(//tr/td/i)[1]")
    public WebElement menu_icon;
    @FindBy(xpath="//span[contains(text(),'Actions')]")
    public WebElement actions;
    @FindBy(xpath="//span[contains(text(),'Finish')]")
    public WebElement finish_project;
    @FindBy(xpath="//button[@id='btn-OK']")
    public WebElement OK;
//    public static String stage_details_url;
//    public static String project_name;
//    public static String CPID;
    @FindBy(xpath="(//tbody/tr)[2]")
    public WebElement search_result;
    public static String XTM_projects_url;
    @FindBy(xpath="//td[@class='dataTables_empty']")
    public WebElement no_project;


    public Xtm_dashboard_page() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void project_all(){

        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(project_button).click().build().perform();
        Driver.get().switchTo().frame("projectsIframe");
        new Actions(Driver.get()).moveToElement(all_button).click().build().perform();
        XTM_projects_url=Driver.get().getCurrentUrl();
    }


    public void advanced_search() {
        BrowserUtils.waitFor(5);
        new Actions(Driver.get()).moveToElement(advanced_search_button).click().build().perform();

    }
    static int number=0;
    public void search(){
        BrowserUtils.waitFor(3);
        new Actions(Driver.get()).moveToElement(search_button).click().build().perform();
        BrowserUtils.waitFor(2);

       try{
        if(no_project.isDisplayed()&&number<10){
            BrowserUtils.waitFor(30);
            new Actions(Driver.get()).moveToElement(search_button).click().build().perform();
            System.out.println("searched "+ number+ " times");
            number++;
            search();
        }else{
            System.out.println("else project found");
        }
    }
       catch(NoSuchElementException e){
           System.out.println("project found");
      }}
//static int minute=0;
//    public void check_search_result() {
//        new Actions(Driver.get()).moveToElement(search_button).click().build().perform();
//        if(no_project.isDisplayed()&&minute<5) search();
//    }
//    public void search(){
//        BrowserUtils.waitFor(30);
//    new Actions(Driver.get()).moveToElement(search_button).click().build().perform();
//    minute ++;
//}

    public void select_first_result() {
        new Actions(Driver.get()).moveToElement(search_result).click().build().perform();
        BrowserUtils.waitFor(3);
        }


    public void finish_project(){
        BrowserUtils.waitFor(3);
        new Actions(Driver.get()).moveToElement(menu_icon).click().build().perform();
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(actions).click().build().perform();
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(finish_project).click().build().perform();
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(OK).click().build().perform();
    }

//    public void  get_required_details()  {
//        stage_details_url = Driver.get().getCurrentUrl();
//        System.out.println("stage_details_url = " + stage_details_url);
//        String [] CPID_list=stage_details_url.split("detail/");
//        CPID=CPID_list[1];
//        System.out.println("CPID=" + CPID);
//        String[] project_name_list=CPID.split("~");
//        project_name = project_name_list[0] + " v" + project_name_list[1] + " " + project_name_list[2] + "_to_" + project_name_list[4] + " " + project_name_list[6] + " " + project_name_list[5] + " " + "PE";
//        System.out.println("project name="+project_name);
//
//    }
//    public void put_project_name()  {
//        new Actions(Driver.get()).moveToElement(project_name_field).click().sendKeys(project_name).build().perform();
//        BrowserUtils.waitFor(3);
//    }
    public void XTM_project_url(){
        Driver.get().get(XTM_projects_url);
        System.out.println(XTM_projects_url);
    }


        }







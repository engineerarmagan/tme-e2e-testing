package tme.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

public class TMNA_Stage_details_page extends Base_page{

    @FindBy(xpath = "//span[normalize-space()='Translation Status']")
    public WebElement translation_status_button;
    @FindBy(xpath = "//button[contains(text(),'View Costs')]")
    public WebElement view_costs_button;
    @FindBy(xpath = "//button[contains(text(),'LIM Updated')]")
    public WebElement lim_updated_button;
    @FindBy(xpath = "//span[contains(text(),'LIM')]")
    public WebElement LIM_button;
    @FindBy(xpath = "//span[contains(text(),'Safety Terms')]")
    public WebElement safety_terms_button;
    @FindBy(xpath = "//button[contains(text(),'Safety Terms Updated')]")
    public WebElement safety_terms_updated_button;
    @FindBy(xpath = "//span[normalize-space()='Image review']")
    public WebElement image_review_button;
    @FindBy(xpath = "//input[@id='mat-slide-toggle-2-input']")
    public WebElement QA_toggle;
    @FindBy(xpath = "//input[@id='mat-slide-toggle-1-input']")
    public WebElement block_at_publish_toggle;
    @FindBy(xpath = "//td[contains(text(),'Stage')]//following-sibling::td")
    public WebElement Stage;
    @FindBy(xpath = "//td[contains(text(),'Status')]//following-sibling::td")
    public WebElement Status;
    @FindBy(xpath = "//button[normalize-space()='Approve Metadata']")
    public WebElement approve_metadata_button;
    @FindBy(xpath = "//span[normalize-space()='Quality Assurance']")
    public WebElement quality_assurance_button;
    @FindBy(xpath = "//h3[normalize-space()='EXTRACT']")
    public WebElement Extract;
    @FindBy(xpath = "//li[contains(text(),'Reason')]")
    public WebElement metadata_fail_reason;
    @FindBy(xpath = "//div[contains(text(),'Metadata approval required')]")
    public WebElement metadata_approval_required;
    @FindBy(xpath = "//button[normalize-space()='Revalidate & Approve Metadata']")
    public WebElement revalidate_metadata;




    static int minute=0;
    public void stage_status_check() {
        BrowserUtils.waitFor(15);
        Driver.get().navigate().refresh();
        System.out.println("inside status check TMNA method");
        System.out.println("current url = " + Driver.get().getCurrentUrl());
        BrowserUtils.waitFor(5);
        WebElement Stage = Driver.get().findElement(By.xpath("//td[contains(text(),'Stage')]//following-sibling::td"));
        WebElement Status = Driver.get().findElement(By.xpath("//td[contains(text(),'Status')]//following-sibling::td"));

        String stage = Stage.getText();
        String status = Status.getText();
        System.out.println("stage="+stage);
        System.out.println("status="+status);
        if (stage.contains("EXTRACT") && status.contains("WAITING")) extract_waiting();
        if (stage.equalsIgnoreCase("QA") && status.contains("WAITING")) qa_waiting();
        if (stage.contains("IMAGE_REVIEW") && status.contains("WAITING")) image_review_waiting();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute<20) translate_processing();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute==20) translation_failure();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSING")) publish_processing();
        if (stage.contains("PUBLISH")&& status.contains("WAITING")) stage_status_check();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSED")) publish_processed();
    }

    public void click_view_costs() {
        System.out.println("inside status check method");
        BrowserUtils.waitFor(2);
        WebElement Stage = Driver.get().findElement(By.xpath("//td[contains(text(),'Stage')]//following-sibling::td"));
        WebElement Status = Driver.get().findElement(By.xpath("//td[contains(text(),'Status')]//following-sibling::td"));

        String stage = Stage.getText();
        String status = Status.getText();
        System.out.println(stage);
        System.out.println(status);
        if (stage.equalsIgnoreCase("PRETRANSLATE") && status.contains("WAITING")) {
            new Actions(Driver.get()).moveToElement(view_costs_button).click().build().perform();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().approve_cost_tmna();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().click_approve_button();
            System.out.println("if pretranslate waiting");
        }
        if(stage.equalsIgnoreCase("TRANSLATE") && status.contains("PROCESSING")){
            System.out.println("if translate processing when checking the cost");
            System.out.println("no cost, go and finish the project");
        }


    }


    private void qa_waiting() {
        new Actions(Driver.get()).moveToElement(quality_assurance_button).click(quality_assurance_button).build().perform();
        new Quality_assurance_page().pass_qa();
        stage_status_check();
    }


    private void translation_failure() {
        System.out.println("translation took longer than expected");
    }

    private void translate_processing() {
        System.out.println(minute +"status translate processing");
        BrowserUtils.waitFor(60);
        System.out.println("one minute passed");
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        minute++;
        System.out.println("minute"+minute);
        System.out.println("checking status again");
        stage_status_check();
    }


    private void image_review_waiting() {
        new Image_review_page().image_check();
        stage_status_check();
    }

    private void publish_processed() {
        WebElement Stage = Driver.get().findElement(By.xpath("//td[contains(text(),'Stage')]//following-sibling::td"));
        WebElement Status = Driver.get().findElement(By.xpath("//td[contains(text(),'Status')]//following-sibling::td"));
        String stage = Stage.getText();
        String status = Status.getText();
        Assert.assertTrue(stage.contains("PUBLISH")&&status.contains("PROCESSED"));
        System.out.println("Translation Completed Successfully");
    }

    private void publish_processing() {
        BrowserUtils.waitFor(10);
        stage_status_check();
    }

    public void extract_waiting(){
        System.out.println("inside extract waiting method");
//        try{
        System.out.println(Driver.get().findElement(By.xpath("//button[normalize-space()='Revalidate & Approve Metadata']")).getText());
        System.out.println(revalidate_metadata.isDisplayed());
        if(revalidate_metadata.isDisplayed()) {
            revalidate_metadata();
            System.out.println("bu ne simdi");
        }
        if(metadata_approval_required.isDisplayed()) approve_metadata();
        //if(Driver.get().findElement(By.xpath("//button[normalize-space()='Revalidate & Approve Metadata']")).isDisplayed()) revalidate_metadata();

//         }catch(Exception e){
//            System.out.println("nothing appeared");
//        }
    }


    public void approve_metadata(){
        approve_metadata_button.click();
        Driver.get().get(stage_details_url);
        BrowserUtils.waitFor(3);
        stage_status_check();
    }


    public void revalidate_metadata(){
        System.out.println("revalidate methodun icindeyiz");
        Extract.click();
        BrowserUtils.waitFor(3);
        String reason=metadata_fail_reason.getText();
        System.out.println(reason);
        if(reason.contains("must be"))
        {
            String [] reasons=reason.split("must be");
            System.out.println("inside reason contains must be");
            System.out.println("one of the reason is="+ reasons[0]);
            int j= reasons.length;
            for(int i=0;i<reasons.length;i++)
            {
                System.out.println("inside for loop");
                if(reasons[i].contains("Line off")) new Metadata_page().set_line_off_date();
                if(reasons[i].contains("model/model")) set_model_code();

            }
        }
        if(reason.contains("is  missing")){
            String[] reasons = reason.split(" is missing.");
            System.out.println("inside reason contains is missing");
            System.out.println(reasons[1]);
            int j = reasons.length;
            for (int i = 0; i < reasons.length; i++) {
                if (reasons[i].equals("AffectedPublication")) set_affected_publication();
                if (reasons[i].equals("IssueDate")) set_issue_date();
                if (reasons[i].equals("Subject")) set_subject();
            }
        }

    }

    public void set_model_code(){
        revalidate_metadata();
    }
    public void set_affected_publication(){
        revalidate_metadata();
    }
    public void set_issue_date(){
        revalidate_metadata();
    }
    public void set_subject(){
        revalidate_metadata();
    }



























}

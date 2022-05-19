package tme.pages;


import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.time.Duration;


public class Stage_details_page extends Base_page {

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
    @FindBy(xpath = "//td[contains(text(),'Stage')]//following-sibling::td")
    public WebElement current_stage_level;
    @FindBy(xpath = "//td[contains(text(),'Status')]//following-sibling::td")
    public WebElement current_status_level;
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
    public WebElement approve_metadata;
    @FindBy(xpath = "//span[normalize-space()='Quality Assurance']")
    public WebElement quality_assurance_button;
    @FindBy(xpath = "//h3[normalize-space()='EXTRACT']")
    public WebElement Extract;
    @FindBy(xpath = "//li[contains(text(),'Reason')]")
    public WebElement metadata_fail_reason;


    public Stage_details_page() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void click_translation_status() {
        new Actions(Driver.get()).moveToElement(translation_status_button).click().build().perform();

    }

//    public void approve_translation_cost() {
//
//        try {
//            if (Driver.get().findElement(By.xpath("//button[contains(text(),'View Costs')]")).isDisplayed()) {
//                new Actions(Driver.get()).moveToElement(view_costs_button).click().build().perform();
//                System.out.println("Click view cost");
//            } else if (Driver.get().findElement(By.xpath("//span[contains(text(),'LIM')]")).isDisplayed()) {
//                new Actions(Driver.get()).moveToElement(LIM_button).click().build().perform();
//                new Lim_page().update_lim();
//                new Actions(Driver.get()).moveToElement(lim_updated_button).click().build().perform();
//            } else if (Driver.get().findElement(By.xpath("//span[contains(text(),'Safety Terms')]")).isDisplayed()) {
//                new Actions(Driver.get()).moveToElement(safety_terms_button).click().build().perform();
//                new Safety_terms_page().make_unknown_to_known();
//                new Actions(Driver.get()).moveToElement(safety_terms_updated_button).click().build().perform();
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("Non of them appears");
//        }
//    }

    public void publish_blocked_check() {

        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(300));
        wait.pollingEvery(Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(current_status_level, "class", "Blocked"));
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
            new Cost_approval_page().approve_cost();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().click_approve_button();
            System.out.println("if pretranslate waiting");
        }
        if(stage.equalsIgnoreCase("TRANSLATE") && status.contains("PROCESSING")){
            System.out.println("if translate processing when checking the cost");
            System.out.println("no cost, go and finish the project");
        }


    }



    public void click_view_costs_tmna() {
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

    public void translation_with_QA() {
        String QA_status = Driver.get().findElement(By.xpath("//input[@id='mat-slide-toggle-2-input']")).getAttribute("aria-checked");
        if (QA_status.equals("false")) {
            new Actions(Driver.get()).moveToElement(QA_toggle).click().build().perform();
        }
    }

    public void translation_without_QA() {
        String QA_status = Driver.get().findElement(By.xpath("//input[@id='mat-slide-toggle-2-input']")).getAttribute("aria-checked");
        if (QA_status.equals("true")) {
            new Actions(Driver.get()).moveToElement(QA_toggle).click().build().perform();
        }
    }

    public void block_at_publish() {
        new Actions(Driver.get()).moveToElement(block_at_publish_toggle).click().build().perform();
        String block_status = block_at_publish_toggle.getAttribute("aria-checked");
        if (block_status.equals("false")) {
            new Actions(Driver.get()).moveToElement(block_at_publish_toggle).click().build().perform();
        }
    }

    public void dont_block_at_publish() {
        String block_status = block_at_publish_toggle.getAttribute("aria-checked");
        if (block_status.equals("true")) {
            new Actions(Driver.get()).moveToElement(block_at_publish_toggle).click().build().perform();
        }
    }

    public void approve_metadata() {
        new Actions(Driver.get()).moveToElement(approve_metadata).click().build().perform();
    }




    public void wait_PREQA() {
        BrowserUtils.waitFor(420);
        System.out.println("waiting 7 minutes");
        Driver.get().navigate().refresh();
        System.out.println("refreshed");

    }

    public void wait_until_QA_WAIT() {

        String stage = Stage.getText();
        String status = Status.getText();
        while (stage.contains("QA") && status.contains("PROCESSING")) {
            try {
                Driver.get().navigate().refresh();
                BrowserUtils.waitFor(5);
                break;
            } catch (WebDriverException e) {

            }
        }
    }

    public void stage_check() {

    }

    public void wait_until_QA_PROCESSING() {

        String stage = Stage.getText();
        String status = Status.getText();
        while (stage.contains("QA") && status.contains("WAITING")) {
            try {
                Driver.get().navigate().refresh();
                BrowserUtils.waitFor(5);
                break;
            } catch (WebDriverException e) {

            }
        }
    }
    static int minute=0;
    public void stage_status_check() {

        System.out.println("inside status check method");
        BrowserUtils.waitFor(2);
        WebElement Stage = Driver.get().findElement(By.xpath("//td[contains(text(),'Stage')]//following-sibling::td"));
        WebElement Status = Driver.get().findElement(By.xpath("//td[contains(text(),'Status')]//following-sibling::td"));


        String stage = Stage.getText();
        String status = Status.getText();
        System.out.println(stage);
        System.out.println(status);
        if (stage.equalsIgnoreCase("QA") && status.contains("WAITING")) qa_waiting();
        if (stage.equalsIgnoreCase("QA") && status.contains("PROCESSING")) qa_processing();
        if (stage.equalsIgnoreCase("QA") && status.contains("PUBLISHED")) qa_publishedt();
        if (stage.equalsIgnoreCase("PREQA") && status.contains("PROCESSING")) preqa_processing();
        if (stage.contains("IMAGE_REVIEW") && status.contains("WAITING")) image_review_waiting();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute<60) translate_processing();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute==60) translation_failure();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSING")) publish_processing();
        if (stage.contains("PUBLISH")&& status.contains("BLOCKED")) translation_completed();


    }

    static int minutes=0;
    public void stage_status_check_tmna() {
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
        if (stage.equalsIgnoreCase("QA") && status.contains("WAITING")) qa_waiting_tmna();
        if (stage.contains("IMAGE_REVIEW") && status.contains("WAITING")) image_review_waiting_tmna();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute<20) translate_processing_tmna();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute==20) translation_failure_tmna();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSING")) publish_processing_tmna();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSED")) publish_processed_tmna();
    }

    private void translation_failure_tmna() {
        System.out.println("translation took longer than expected");
    }

    private void translate_processing_tmna() {
        System.out.println(minute +"status translate processing");
        BrowserUtils.waitFor(60);
        System.out.println("one minute passed");
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        minute++;
        System.out.println("minute"+minute);
        System.out.println("checking status again");
        stage_status_check_tmna();
    }

    private void image_review_waiting_tmna() {
        new Image_review_page().image_check();
        stage_status_check_tmna();
    }

    private void publish_processed_tmna() {
        WebElement Stage = Driver.get().findElement(By.xpath("//td[contains(text(),'Stage')]//following-sibling::td"));
        WebElement Status = Driver.get().findElement(By.xpath("//td[contains(text(),'Status')]//following-sibling::td"));
        String stage = Stage.getText();
        String status = Status.getText();
        Assert.assertTrue(stage.contains("PUBLISH")&&status.contains("PROCESSED"));
        System.out.println("Translation Completed Successfully");
    }

    private void publish_processing_tmna() {
        BrowserUtils.waitFor(10);
        stage_status_check_tmna();
    }

    private void qa_waiting_tmna() {
        new Actions(Driver.get()).moveToElement(quality_assurance_button).click(quality_assurance_button).build().perform();
        new Quality_assurance_page().pass_qa();
        stage_status_check_tmna();
    }

    public void translation_failure(){

        System.out.println("translation took longer than expected");
    }
    public void translation_completed(){
        System.out.println("translation completed and publish is blocked");
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
        new Image_review_page().image_review();
        BrowserUtils.waitFor(5);
        new Image_review_page().image_check();
        new Image_review_page().leave_image_review_page();
        stage_status_check();
    }

    public void preqa_processing() {
        System.out.println("status is preqa processing");
        new Api().post_receivedOK();
        BrowserUtils.waitFor(2);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        System.out.println("checking status again");
        stage_status_check();
    }

    public void qa_waiting() {
        System.out.println( "status is qa waiting");
        new Api().post_qaclaimed();
        BrowserUtils.waitFor(2);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        System.out.println("checking status again");
        stage_status_check();

    }

    public void qa_processing() {
        System.out.println( "status is qa processing");
        new Api().post_published();
        BrowserUtils.waitFor(2);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        System.out.println("checking status again");
        stage_status_check();
    }

    public void qa_publishedt() {
        System.out.println("status qa published test completed");

    }
    public void publish_processing(){
        System.out.println("status publish processing");
        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        stage_status_check();
    }
    public void edit_metadata(){

    }
}







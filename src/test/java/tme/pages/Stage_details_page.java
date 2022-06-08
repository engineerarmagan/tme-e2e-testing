package tme.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
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
    @FindBy(xpath = "//span[normalize-space()='Image review']")
    public WebElement image_review_button;
    @FindBy(xpath = "//td[contains(text(),'QA by Value Chain')]//following-sibling::td//input")
    public WebElement QA_toggle_input;
    @FindBy(xpath = "//td[contains(text(),'Block at Publish')]//following-sibling::td//input")
    public WebElement block_at_publish_toggle_input;
    @FindBy(xpath = "//td[contains(text(),'QA by Value Chain')]//following-sibling::td//mat-slide-toggle")
    public WebElement QA_toggle;
    @FindBy(xpath = "//td[contains(text(),'Block at Publish')]//following-sibling::td//mat-slide-toggle")
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
    public WebElement revalidate_metadata_button;
    @FindBy(xpath = "//button[contains(text(),'Re-Import')]")
    public WebElement re_import_button;

    @FindBy(xpath = "//div[contains(text(),'Message: Internal Error')]")
    public WebElement internal_error;
    @FindBy(xpath = " //button[contains(text(),'Acknowledge')]")
    public WebElement acknowledge_button;

    static int minute=0;
    static int processing=0;
    static String fail_reason="";

    public Stage_details_page() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void translation_with_QA() {
        System.out.println("translation with QA");
        String QA_status = QA_toggle_input.getAttribute("aria-checked");
        System.out.println(QA_status);
        if (QA_status.equals("false")) {
            new Actions(Driver.get()).moveToElement(QA_toggle).click().build().perform();
        }
       Assert.assertTrue(QA_status.equals("true"));
    }

    public void translation_without_QA() {
        System.out.println("translation without QA");
        String QA_status = QA_toggle_input.getAttribute("aria-checked");
        System.out.println(QA_status);
        if (QA_status.equals("true")) QA_toggle.click();
    }

    public void block_at_publish() {
        String block_status = block_at_publish_toggle_input.getAttribute("aria-checked");
        if (block_status.equals("false")) {
           new Actions(Driver.get()).moveToElement(block_at_publish_toggle).click().build().perform();
        }
    }

    public void dont_block_at_publish() {
        String block_status = block_at_publish_toggle_input.getAttribute("aria-checked");
        System.out.println("block status="+block_status);
        if (block_status.equals("true")){
            System.out.println("block status is true should click");
        BrowserUtils.waitFor(5);
            new Actions(Driver.get()).moveToElement(block_at_publish_toggle).click(block_at_publish_toggle).build().perform();
        System.out.println("clicked to turn off");
     }else{
            System.out.println("block status is false, nothing to do");
        }}





   public void approve_metadata(){
        approve_metadata_button.click();
        Driver.get().get(stage_details_url);
        BrowserUtils.waitFor(3);
        stage_status_check();
   }

    public void click_translation_status() {
        translation_status_button.click();
    }

    public void stage_status_check() {

        System.out.println("inside status check method");
        BrowserUtils.waitFor(2);

        String stage = Stage.getText();
        String status = Status.getText();
        System.out.println(stage);
        System.out.println(status);
        if (stage.contains("IMPORT") && status.contains("WAITING")) import_waiting();
        if (stage.contains("IMPORT") && status.contains("PROCESSING")) import_processing();
        if (stage.contains("EXTRACT") && status.contains("WAITING")) extract_waiting();
        if (stage.contains("EXTRACT") && status.contains("PROCESSING")) extract_processing();
        if (stage.contains("CONVERT") && status.contains("PROCESSING")) convert_processing();
        if (stage.contains("PRETRANSLATE") && status.contains("WAITING")) click_view_costs();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute<60) translate_processing();
        if (stage.contains("TRANSLATE") && status.contains("PROCESSING") && minute==60) translation_failure();
        if (stage.contains("IMAGE_REVIEW") && status.contains("WAITING")) image_review_waiting();
        if (stage.contains("PUBLISH")&& status.contains("PROCESSING")) publish_processing();
        if (stage.contains("PUBLISH")&& status.contains("WAITING")) publish_waiting();
        if (stage.contains("PUBLISH")&& status.contains("BLOCKED")) translation_completed();
        if (stage.equalsIgnoreCase("PREQA") && status.contains("PROCESSING")) preqa_processing();
        if (stage.equalsIgnoreCase("QA") && status.contains("WAITING")) qa_waiting();
        if (stage.equalsIgnoreCase("QA") && status.contains("PROCESSING")) qa_processing();
        if (stage.equalsIgnoreCase("QA") && status.contains("PUBLISHED")) qa_publishedt();

    }
    public void required_action_check() {
        int minute=0;
        BrowserUtils.waitFor(30);
        System.out.println("required action check");
        Driver.get().navigate().to(stage_details_url);
        BrowserUtils.waitFor(2);
        String stage = Stage.getText();
        String status = Status.getText();

        if (stage.contains("PRETRANSLATE") && status.contains("WAITING"))
             {
                 if (LIM_button.isDisplayed())
                    {
                         LIM_button.click();
                         new Lim_page().update_lim();
                         Driver.get().navigate().to(stage_details_url);
                         BrowserUtils.waitFor(2);
                        lim_updated_button.click();
                        BrowserUtils.waitFor(4);
                     }
                 else acknowledge_button.click();
             }

        if (stage.contains("PRETRANSLATE") && status.contains("ERRORED")) {
            System.out.println("there is an error, test should fail");
            Assert.assertFalse(internal_error.isDisplayed());
        }
        if(stage.contains("PRETRANSLATE") && status.contains("PROCESSING")&&processing<3)
        {   processing ++;
            System.out.println("pretranslate processing , continue");
            Driver.get().navigate().to(stage_details_url);
            required_action_check();
        }
        else{
            System.out.println("continue");
        }
    }






    private void publish_waiting() {
        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        stage_status_check();
    }

    private void extract_processing() {
        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        stage_status_check();
    }

    private void convert_processing() {
        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        stage_status_check();
    }

    private void import_processing() {
        BrowserUtils.waitFor(30);
        Driver.get().navigate().refresh();
        stage_status_check();
    }

    private void import_waiting() {
        BrowserUtils.waitFor(2);
         re_import_button.click();
         stage_status_check();
    }

    private void translate_processing() {

        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        minute++;
        System.out.println("translate processing "+minute+ "passed");
        stage_status_check();
    }
    public void translation_failure(){
        System.out.println("translation took longer than expected");
    }
    private void image_review_waiting() {
        new Image_review_page().image_review();
        BrowserUtils.waitFor(5);
        new Image_review_page().image_check();
        new Image_review_page().leave_image_review_page();
        BrowserUtils.waitFor(2);
        stage_status_check();
    }
    public void publish_processing(){
        System.out.println("status publish processing");
        BrowserUtils.waitFor(60);
        Driver.get().navigate().refresh();
        BrowserUtils.waitFor(2);
        stage_status_check();
    }
    public void translation_completed(){
        System.out.println("translation completed and publish is blocked");
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

    public void publish_blocked_check() {

        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(300));
        wait.pollingEvery(Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(Status, "class", "Blocked"));
    }

    public void click_view_costs() {
        System.out.println("inside view cost method");
        BrowserUtils.waitFor(2);
        String stage = Stage.getText();
        String status = Status.getText();
        System.out.println(stage);
        System.out.println(status);
        if (stage.equalsIgnoreCase("PRETRANSLATE") && status.contains("WAITING")) {
            view_costs_button.click();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().approve_cost();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().click_approve_button();
            System.out.println("if pretranslate waiting ended");
        }
        if(stage.equalsIgnoreCase("TRANSLATE") && status.contains("PROCESSING")){

            System.out.println("Cost below threshold - automatically approved");
        }
        else{
            System.out.println("else,Cost below threshold - automatically approved");
        }
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

    public void extract_waiting() {
        if (approve_metadata_button.isDisplayed()) approve_metadata_button.click();

        //if (revalidate_metadata_button.isDisplayed()) revalidate_metadata();
        else {
            System.out.println("inside extract waiting else");
            Driver.get().navigate().refresh();
        }
        BrowserUtils.waitFor(60);
        Driver.get().navigate().to(stage_details_url);
        stage_status_check();
    }


    public void get_fail_reasons(){
        Extract.click();
        BrowserUtils.waitFor(3);
        fail_reason=metadata_fail_reason.getText();
        System.out.println(fail_reason);
    }
    public void revalidate_metadata(){
        System.out.println("revalidate methodun icindeyiz");
        if(fail_reason.contains("must be"))
        {
            String [] reasons=fail_reason.split("must be");
            System.out.println("inside reason contains must be");
            System.out.println("one of the reason is="+ reasons[0]);
            int j= reasons.length;
            for(int i=0;i<reasons.length;i++)
            {
                System.out.println("inside for loop");
                if(reasons[i].contains("Line off")) new Metadata_page().set_line_off_date();
                if(reasons[i].contains("model/model")) set_model_code();
                else{
                    System.out.println("new fail reason found, please set a solution for this reason");
                }
            }
        }
        if(fail_reason.contains("is  missing")){
            String[] reasons = fail_reason.split(" is missing.");
            System.out.println("inside reason contains is missing");
            System.out.println(reasons[1]);
            int j = reasons.length;
            for (int i = 0; i < reasons.length; i++) {
                if (reasons[i].equals("AffectedPublication")) set_affected_publication();
                if (reasons[i].equals("IssueDate")) set_issue_date();
                if (reasons[i].equals("Subject")) set_subject();
            }
        }
    revalidate_metadata();
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
}







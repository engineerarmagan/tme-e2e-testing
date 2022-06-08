package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tme.pages.*;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

public class Launch_translation_step_defs {



    @Given("user selects translation type PE")
    public void user_selects_translation_type_pe() {
        BrowserUtils.waitFor(1);
        new Translation_status_page().translation_type_PE();

    }
    @And("selects the language and launch")
    public void selects_the_language_and_launch() {
        BrowserUtils.waitFor(1);
        new Translation_status_page().clickable_language();
        new Translation_status_page();
        Base_page.Guardhouse_id();

    }

    @When("clear the filter")
    public void clear_the_filter() {
        new History_page().clear_filter();
        BrowserUtils.waitFor(2);
    }

    @Then("user gets CPID and project name")
    public void checks_the_stage_details()  {
        new Stage_details_page().get_required_details();
    }



    @Then("wait until cost analysis complete")
    public void wait_until_cost_analysis_complete() {
        System.out.println("Waiting for cost analyze to complete");
        new Xtm_general_info_page().wait_analyse_completion();
        System.out.println("analyse completed");

    }
    @Then("go to projects stage page")
    public void go_to_projects_stage_page() {
        System.out.println("sd:go to projects stage page");
    }

    @Given("user navigates to stage details page")
    public void user_navigates_to_stage_details_page() {

        Driver.get().get(Stage_details_page.stage_details_url);
        BrowserUtils.waitFor(4);
    }

    @And("approves the translation cost")
    public void approvesTheTranslationCost() {
        BrowserUtils.waitFor(3);
        new Cost_approval_page().approve_cost();
        BrowserUtils.waitFor(3);
        new Cost_approval_page().click_approve_button();

    }
    @Then("approves the translation cost for specific CPID")
    public void approvesTheTranslationCostForSpecificCPID() {
        new Stage_details_page().click_view_costs();
        BrowserUtils.waitFor(2);
        new Cost_approval_page().approve_cost_for_specific_CPID();
        BrowserUtils.waitFor(3);
        new Cost_approval_page().click_approve_button();

    }
    @Given("the user navigates to previous XTM page")
    public void theUserNavigatesToPreviousXTMPage() {
new Xtm_dashboard_page().XTM_project_url();
    }


    @When("selects view costs and approves if required")
    public void selectsViewCosts() {
        BrowserUtils.waitFor(2);
        new Stage_details_page().click_view_costs();

    }
    @When("selects view costs and approves if required for TMNA")
    public void selectsViewCostsTmna() {
        BrowserUtils.waitFor(2);
        new TMNA_Stage_details_page().click_view_costs();

    }

    @And("do the Image review checks")
    public void doTheImageReviewChecks() {

        //new Image_review_page().complete_image_review();
    }

    @Given("user navigates to URL")
    public void userNavigatesTo() {
        System.out.println("user should navigate to stage detail url");
        Driver.get().get("https://systest.e2e-tmna.com/dashboard#/detail/NM40P1U~1~EN~22Tundra-HV-Tundra_NM40P0U_EN_22-02-10_UX~ES~T2~1");
        System.out.println(Driver.get().getCurrentUrl()) ;
    BrowserUtils.waitFor(3);
    }

    @And("change the QA by value chain to ON")
    public void changeTheQAByValueChainToON() {
        BrowserUtils.waitFor(3);
        new Stage_details_page().translation_with_QA();
        BrowserUtils.waitFor(3);
    }
    @And("change the QA by value chain to OFF")
    public void changeTheQAByValueChainToOFF() {
        BrowserUtils.waitFor(3);
        new Stage_details_page().translation_without_QA();
        BrowserUtils.waitFor(3);
    }

    @And("change the Block at publish to OFF")
    public void changeTheBlockAtPublishToOFF() {
        BrowserUtils.waitFor(2);
        new Stage_details_page().dont_block_at_publish();
        BrowserUtils.waitFor(2);

    }
    @And("change the Block at publish to ON")
    public void changeTheBlockAtPublishToON() {
        BrowserUtils.waitFor(1);
        new Stage_details_page().block_at_publish();
}

    @When("the status is PREQA-PROCESSING")
    public void theStatusIsPREQAPROCESSING() {
        System.out.println("now_wait_preqa method");
        BrowserUtils.waitFor(420);
        Driver.get().navigate().refresh();
       // new Api().post_receivedOK();
        new Stage_details_page().wait_until_QA_PROCESSING();
      //  new Api().post_qaclaimed();
        new Stage_details_page().wait_until_QA_WAIT();
        //new Api().post_published();




    }
    @When("user checks the status")
    public void userChecksTheStatus() {
        new Stage_details_page().stage_status_check();
    }
    @And("user checks the status of TMNA")
    public void userChecksTheStatusOfTMNA() {
        new TMNA_Stage_details_page().stage_status_check();
    }

    @Then("approves the translation cost for TMNA")
    public void approvesTheTranslationCostForTMNA() {

            BrowserUtils.waitFor(3);
            new Cost_approval_page().approve_cost_tmna();
            BrowserUtils.waitFor(3);
            new Cost_approval_page().click_approve_button();
    }


    @When("the user pass the QA")
    public void theUserPassTheQA() {
        new Quality_assurance_page().pass_qa();
    }
    @When("the user fails the QA")
    public void theUserFailTheQA() {
        new Quality_assurance_page().fail_qa();
    }

    @Then("user should see PUBLISH-PROCESSED")
    public void userShouldSeePUBLISHPROCESSED() {
        WebElement Stage = Driver.get().findElement(By.xpath("//tr[13]/td[2]"));
        WebElement Status = Driver.get().findElement(By.xpath("//tr[14]/td[2]"));
        Assert.assertTrue(new Stage_details_page().Stage.getText().equals("PUBLISH")&&new Stage_details_page().Status.getText().equals("PROCESSED"));
    }

    @And("put the alternate pub id")
    public void putTheAlternatePubId() {
        new History_page().alternate_pubid_search();
    }

    @Given("the user uses given CPID")
    public void theUserUsesGivenCPID() {
        String CPID="BM33B3U~0~EN~18Camry-HV_BM33B0U_EN_20-09-28_UX~FR~T2~2";
    }

    @Then("user should ask for Post Edit")
    public void userShouldAskForPostEdit() {
        new Post_edit_page().get_post_edit_details();
        new Post_edit_page().ask_for_post_edit();
        BrowserUtils.waitFor(300);

    }

    @Given("user checks and completes the required actions")
    public void userChecksAndCompletesTheRequiredActions() {

    new Stage_details_page().required_action_check();

    }
}

package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import tme.pages.Dashboard_page;
import tme.pages.History_page;
import tme.pages.Stage_details_page;
import tme.pages.Translation_status_page;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.util.List;

public class History_step_defs {

    @When("the user navigates to {string}")
    public void the_user_navigates_to(String tab) {
        new Dashboard_page().navigate_to_tab(tab);
    }



    @Then("user should see following sortable headers")
    public void user_should_see_following_headers(List<String> history_headers) {
        BrowserUtils.waitFor(2);
        List<String> actual_headers= BrowserUtils.getElementsText(new History_page().history_headers);// to get one element  add get(0).gettext()
        Assert.assertEquals(history_headers,actual_headers);
        System.out.println("history_headers=" + history_headers);
        System.out.println("actual_headers=" + actual_headers);

    }
    @Given("user filters publication type {string}")
    public void selects_publication_type(String type) {
        new History_page().selects_publication_type(type);

    }

    @And("selects first from the list")
    public void select_first(){
        BrowserUtils.waitFor(2);
        new History_page().select_first_publication_from_list();
        BrowserUtils.waitFor(2);
    }
    @Then ("click translation status")
    public void translation_status(){
        new Stage_details_page().click_translation_status();
    }

    @When("filters status {string}")
    public void filters_status(String type) {
        new History_page().select_status(type);
    }
    @Then("filters language {string}")
    public void filters_language(String type) {
        new History_page().select_language(type);

    }
    @Given("filters")
    public void click_filter() {
        new History_page().filter();
    }

    @Then("publication stage should be PUBLISH")
    public void publicationStageShouldBePUBLISH() {

    }

    @And("publication status should be BLOCKED")
    public void publicationStatusShouldBeBLOCKED() {
    }

    @When("selects nth from the list")
    public void selectsNthFromTheList() {
        new History_page().select_nth_publication_from_list();
    }
}
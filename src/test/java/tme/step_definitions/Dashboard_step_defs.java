package tme.step_definitions;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.Browser;
import tme.pages.*;
import tme.utilities.BrowserUtils;

import java.io.IOException;
import java.util.List;

public class Dashboard_step_defs extends Base_page {

    @Then("user should see following options")
    public void user_should_see_following_options(List<String> menu_options) {
        BrowserUtils.waitFor(2);
        List<String> actual_options= BrowserUtils.getElementsText(new Dashboard_page().menu_options);// to get one element  add get(0).gettext()
        Assert.assertEquals(menu_options,actual_options);
        System.out.println("menu_options=" + menu_options);
        System.out.println("actual_options=" + actual_options);

    }
    @Then("user should see following columns")
    public void user_should_see_following_columns(List<String> dashboard_columns) {
        BrowserUtils.waitFor(2);
        List<String> actual_columns= BrowserUtils.getElementsText(new Dashboard_page().dashboard_columns);
        Assert.assertEquals(dashboard_columns,actual_columns);
        System.out.println("dashboard_columns=" + dashboard_columns);
        System.out.println("actual_columns=" + actual_columns);

    }

    @Given("user on All Project page")
    public void user_on_all_project_page() {
        new Xtm_dashboard_page().project_all();

    }
    @When("search the project")
    public void search_the_project()  {
        new Xtm_dashboard_page().advanced_search();
        new Xtm_dashboard_page().put_project_name();
        new Xtm_dashboard_page().search();
        BrowserUtils.waitFor(3);
    }
    @When("finish the project")
    public void finish_the_project() {

        new Xtm_dashboard_page().finish_project();
    }


    @And("select the first result")
    public void selectTheFirstResult() {
        new Xtm_dashboard_page().select_first_result();
    }

    @Then("publication status should be {string}")
    public void publicationStatusShouldBe(String arg0) {
        new Stage_details_page().publish_blocked_check();
    }


}


package tme.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tme.pages.Import_publications;
import tme.pages.Lim_page;
import tme.utilities.BrowserUtils;

import java.util.List;

public class Lim_step_defs {

    @When("the user navigates to {string} {string}")
    public void the_user_navigates_to(String tab, String subtab) {
        BrowserUtils.waitFor(3);
    new Lim_page().navigate_to_subtab(tab,subtab);

    }
    @When("selects from dropdown")
    public void selects_from_dropdown() {
    new Lim_page().select_second_sil();
    }
    @Then("user should see language options")
    public void user_should_see_language_options(List<String> lim_languages) {
        BrowserUtils.waitFor(2);
        List<String> actual_languages= BrowserUtils.getElementsText(new Lim_page().lim_languages);
        Assert.assertEquals(lim_languages,actual_languages);
        System.out.println("lim_languages=" + lim_languages);
        System.out.println("actual_languages=" + actual_languages);
    }

//    @Given("user changes file name")
//    public void userChangesFileName() {
//        new Import_publications().get_files_inside_the_folder();
//        new Import_publications().rename_file(String type);
//        new Import_publications().upload_to_S3();
//    }
}

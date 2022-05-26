package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tme.pages.Queue_page;
import tme.pages.Safety_terms_page;
import tme.pages.Sil_page;
import tme.utilities.BrowserUtils;

import java.util.List;

public class Sil_step_defs {

    @Then("user should see following SIL columns")
    public void user_should_see_following_queue_columns(List<String> sil_columns) {

        BrowserUtils.waitFor(2);
        List<String> actual_columns= BrowserUtils.getElementsText(new Sil_page().sil_columns);
        Assert.assertEquals(sil_columns,actual_columns);
        System.out.println("queue_columns=" + sil_columns);
        System.out.println("actual_columns=" + actual_columns);



    }


    @Then("user selects a sil")
    public void userSelectsASil() {
    new Sil_page().select_sil();

    }

    @And("user can download the csv file with name {string}")
    public void userCanDownloadTheCsvFileWithName(String file_name) {
        new Safety_terms_page().file_download(file_name);
    }
}

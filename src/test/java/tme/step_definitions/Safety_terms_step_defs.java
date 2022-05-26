package tme.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import tme.pages.Safety_terms_page;
import tme.pages.Api;
import tme.pages.Stage_details_page;
import tme.pages.Xtm_dashboard_page;
import tme.utilities.BrowserUtils;

import java.util.List;

public class Safety_terms_step_defs {

    @Then("should see the show only options as")
    public void should_see_the_show_only_options_as(List<String> show_only_options) {
        new Safety_terms_page().show_only();
        BrowserUtils.waitFor(2);
            List<String> actual_options= BrowserUtils.getElementsText(new Safety_terms_page().show_only_options);
            Assert.assertEquals(show_only_options,actual_options);
            System.out.println("show_only_options=" + show_only_options);
            System.out.println("actual_options=" + actual_options);

    }

    @Given("API sends received OK")
    public void apiSendsReceivedOK() {
        new Api().post_receivedOK();
        new Stage_details_page().wait_until_QA_PROCESSING();
        new Api().post_qaclaimed();
        new Stage_details_page().wait_until_QA_WAIT();
        new Api().post_published();
    }



    @Then("user clicks Translated")
    public void userClicksTranslated() {
    new Safety_terms_page().select_translated();
    }
}

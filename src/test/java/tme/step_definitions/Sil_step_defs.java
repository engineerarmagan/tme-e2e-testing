package tme.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tme.pages.Queue_page;
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


}

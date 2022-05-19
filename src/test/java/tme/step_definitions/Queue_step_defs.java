package tme.step_definitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import tme.pages.History_page;
import tme.pages.Queue_page;
import tme.utilities.BrowserUtils;

import java.util.List;

public class Queue_step_defs {

    @Then("user should see following queue columns")
    public void user_should_see_following_queue_columns(List<String> queue_columns) {

            BrowserUtils.waitFor(2);
            List<String> actual_columns= BrowserUtils.getElementsText(new Queue_page().queue_columns);
            Assert.assertEquals(queue_columns,actual_columns);
            System.out.println("queue_columns=" + queue_columns);
            System.out.println("actual_columns=" + actual_columns);



        }



}

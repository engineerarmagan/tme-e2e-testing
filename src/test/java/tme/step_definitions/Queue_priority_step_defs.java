package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import tme.pages.Queue_priority_page;

public class Queue_priority_step_defs {



    @Then("gets the priorities list")
    public void getsThePrioritiesList() {
        new Queue_priority_page().get_priority_one_publications();
        new Queue_priority_page().get_priority_two_publications();
        new Queue_priority_page().save();
    }

    @Given("user drags and drops a publication")
    public void userDragsAndDropsAPublication() {
        System.out.println("on dragging and dropping");
        new Queue_priority_page().drag_and_drop();
    }

    @And("save the new order")
    public void saveTheNewOrder() {
        new Queue_priority_page().save();
    }

    @Then("check the new order is saved")
    public void checkTheNewOrderIsSaved() {
        new Queue_priority_page().priority_one_publications_after_drag();
       new Queue_priority_page().size_comparision();


    }
}

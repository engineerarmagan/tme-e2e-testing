package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tme.pages.Event_history_page;

public class Event_history_step_defs {






    @Given("the user filters event stage {string}")
    public void theUserFiltersEventStage(String type) {
    new Event_history_page().selects_stage_type(type);
    }

    @And("filters the event type {string}")
    public void filtersTheEventType(String type) {
    new Event_history_page().selects_event_type(type);
    }


    @Then("user should see only the filtered event type")
    public void userShouldSeeOnlyTheFilteredEventType() {
        new Event_history_page().get_filtered_events_list();
    }


}

package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import tme.pages.Metadata_page;

public class Metadata_step_defs {



    @And("user selects Metadata")
    public void userSelectsMetadata() {
        new Metadata_page().select_metadata();

    }

    @Then("user should be able to download metadata objects")
    public void userShouldBeAbleToDownloadMetadataObjects() {
    new Metadata_page().download_metadata_object();
    }
}

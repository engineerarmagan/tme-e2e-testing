package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tme.pages.History_page;
import tme.pages.Stage_details_page;

public class Import_step_defs {
    @And("approves the metadata")
    public void approvesTheMetadata() {
        new Stage_details_page().approve_metadata();

    }

    @Then("checks the uploaded file exists")
    public void checksTheUploadedFileExists() {


    }

    @Given("user uploads publication type")
    public void userUploadsPublicationType() {
       //new S3_import().upload_to_S3();
    }

    @And("filters stage {string}")
    public void filtersStage(String type) {
    new History_page().selects_stage_type(type);

    }
}

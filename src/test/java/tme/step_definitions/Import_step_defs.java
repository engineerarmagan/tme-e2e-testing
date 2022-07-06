package tme.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tme.pages.*;

public class Import_step_defs {
    @And("check metadata status")
    public void checkMetadataStatus() {
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

    @Given("user imports publication type {string}")
    public void userImportsPublicationType(String type) {
        new Import_publications().get_files_inside_the_folder(type);
        new Import_publications().rename_file(type);
        new Import_publications().upload_to_S3();

    }
    @Given("user imports TMNA publication type {string}")
    public void userImportsTMNAPublicationType(String type) {
        new TMNA_S3_import().get_files_inside_the_folder(type);
        new TMNA_S3_import().rename_file(type);
        new TMNA_S3_import().upload_to_S3();

    }

    @Given("user imports tmna")
    public void userImportsTmna() {
        new TMNA_Api().upload_tmna();
    }
}

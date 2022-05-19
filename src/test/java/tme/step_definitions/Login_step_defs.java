package tme.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import tme.pages.Login_page;
import tme.pages.Maintest;
import tme.utilities.BrowserUtils;
import tme.utilities.ConfigurationReader;
import tme.utilities.Driver;

public class Login_step_defs {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        String url= ConfigurationReader.get("url");
        Driver.get().get(url);
        BrowserUtils.waitFor(2);
    }

    @When("the user enters the credentials")
    public void the_user_enters_the_credentials() {
      String username=ConfigurationReader.get("username");
      String password=ConfigurationReader.get("password");

        Login_page login_page=new Login_page();
        login_page.login(username,password);

    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        BrowserUtils.waitFor(2);
        String actual_title=Driver.get().getTitle();
        Assert.assertEquals("E2E",actual_title);

    }
    @When("the user is on XTM login page")
    public void the_user_is_on_xtm_login_page() {
        String xtm_url= ConfigurationReader.get("xtm_url");
        Driver.get().get(xtm_url);

    }
    @When("enter the XTM credentials")
    public void enter_the_xtm_credentials() {
        String client=ConfigurationReader.get("xtm_client");
        String username=ConfigurationReader.get("xtm_username");
        String password=ConfigurationReader.get("xtm_password");
        Login_page login_page=new Login_page();
        login_page.xtm_login(client,username,password);

    }
    @Then("user should able to login to XTM")
    public void user_should_able_to_login_to_xtm() {
        BrowserUtils.waitFor(2);
        String actual_title=Driver.get().getTitle();
        Assert.assertEquals("Configuration",actual_title);

    }

    @When("the user is on the TMNA login page")
    public void theUserIsOnTheTMNALoginPage() {
        String tmna= ConfigurationReader.get("tmna_url");
        Driver.get().get(tmna);
        BrowserUtils.waitFor(2);
    }

    @Given("ali call veli")
    public void aliCallVeli() {
        Maintest maintest=new Maintest();
        maintest.check_missings();
    }
}

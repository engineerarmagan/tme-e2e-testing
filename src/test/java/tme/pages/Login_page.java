package tme.pages;

import tme.utilities.BrowserUtils;
import tme.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page extends Base_page{

    public Login_page(){
        PageFactory.initElements(Driver.get(),this);
    }

    //driver.findElement(By.id("username"));
    @FindAll({
            @FindBy(id = "username"),
            @FindBy(name ="username")
    })
    public WebElement usernameInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    //driver.findElement(By.id("_submit"));
    @FindBy(id = "login")
    public WebElement loginBtn;
    @FindBy(id="client")
    public WebElement xtm_client_field;
    @FindBy(id="username")
    public WebElement xtm_username_field;
    @FindBy(id="password")
    public WebElement xtm_password_field;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement xtm_login_btn;


    public void login(String username,String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
    public void xtm_login(String xtm_client,String xtm_username,String xtm_password){
        BrowserUtils.waitFor(2);
        xtm_client_field.sendKeys(xtm_client);
        xtm_username_field.sendKeys(xtm_username);
        xtm_password_field.sendKeys(xtm_password);
        xtm_login_btn.click();
}

}

package tme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;
import java.time.Duration;

    public class Xtm_general_info_page {
    @FindBy(xpath="//div[contains(text(),'is still being analysed')]")
    public WebElement still_analysing;


    public Xtm_general_info_page() {
        PageFactory.initElements(Driver.get(), this);
    }



public void wait_analyse_completion(){
        BrowserUtils.waitFor(5);
        WebDriverWait wait=new WebDriverWait(Driver.get(),Duration.ofSeconds(1200));
        wait.pollingEvery(Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'is still being analysed')]")));
}




}




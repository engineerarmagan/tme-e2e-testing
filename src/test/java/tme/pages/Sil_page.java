package tme.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.BrowserUtils;
import tme.utilities.Driver;

import java.util.List;

public class Sil_page extends Base_page {

    @FindBy(xpath="//thead-sortable/tr/th")
    public List<WebElement> sil_columns;

    @FindBy(xpath=" (//a[@id='details-link'])[3]")
    public WebElement third_sil;


    public Sil_page() {
        PageFactory.initElements(Driver.get(), this);
    }


public void select_sil(){
    BrowserUtils.waitFor(3);
        third_sil.click();
    BrowserUtils.waitFor(3);
}


}

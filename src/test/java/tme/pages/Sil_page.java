package tme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tme.utilities.Driver;

import java.util.List;

public class Sil_page extends Base_page {

    @FindBy(xpath="//thead-sortable/tr/th")
    public List<WebElement> sil_columns;

    public Sil_page() {
        PageFactory.initElements(Driver.get(), this);
    }

}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement imgProfile;

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "oxd-userdropdown-icon")
    public WebElement btnProfile;
    @FindBy(linkText = "Logout")
    public WebElement btnLogOut;

    public void doLogout() {
        btnProfile.click();
        btnLogOut.click();
    }

}

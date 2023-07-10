package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;
import page.MyInfoPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    @Test(priority = 1, description = "User can not login with invalid username")
    public void doLoginWithInvalidUsername() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String errorMessageActual = loginPage.doLoginWithWrongCreds("wronguser", empObj.get("password").toString());
        String errorMessageExpected = "Invalid credentials";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }
    @Test(priority = 2, description = "User can not login with invalid password")
    public void doLoginWithInvalidPassword() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String errorMessageActual = loginPage.doLoginWithWrongCreds(empObj.get("username").toString(), "wrongpass");
        String errorMessageExpected = "Invalid credentials";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }
    @Test(priority = 3, description = "User can login with newly created employee", groups = "smoke")
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String username = empObj.get("username").toString();
        String password = empObj.get("password").toString();
        String nameExpected = empObj.get("firstName").toString()+ " " + empObj.get("lastName").toString();
        loginPage.doLogin(username, password);
        String nameActual = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        Assert.assertEquals(nameActual, nameExpected);
    }
    @Test(priority = 4, description = "Employee can select Blood Type")
    public void saveInfoBySendKey() throws InterruptedException {
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.menuMyInfo.get(2).click();
        Thread.sleep(3000);
        Utils.doScroll(driver);
        myInfoPage.selectBloodType.get(2).sendKeys("O+");
        myInfoPage.btnSave.get(1).click();
        Thread.sleep(2000);
        String txtActual = myInfoPage.selectBloodType.get(2).getText();
        String txtExpected = "-- Select --";
        Assert.assertEquals(txtActual, txtExpected);

    }
    @Test(priority = 5, description = "Employee can save Blood Type by Save button")
    public void saveInfoWithoutClickSaveButton() throws InterruptedException {
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.menuMyInfo.get(2).click();
        Thread.sleep(3000);
        Utils.doScroll(driver);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.btnSave.get(1).click();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        String txtActual = myInfoPage.selectBloodType.get(2).getText();
        String txtExpected = "-- Select --";
        Assert.assertEquals(txtActual, txtExpected);

    }
    @Test(priority = 6, description = "Employee can save Gender and Blood Type")
    public void saveInfo() throws InterruptedException {
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.menuMyInfo.get(2).click();
        Thread.sleep(3000);
        Utils.doScroll(driver);
        myInfoPage.genderBtn.get(0).click();
        myInfoPage.btnSave.get(0).click();
        Thread.sleep(1000);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.btnSave.get(1).click();
        Thread.sleep(1000);
        String txtActual = myInfoPage.selectBloodType.get(2).getText();
        String txtExpected = "O+";
        Assert.assertEquals(txtActual, txtExpected);

    }
    @Test(priority = 7, description = "Employee can Update Blood Type ")
    public void UpdateBloodTypeBySendKey() throws InterruptedException {
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.menuMyInfo.get(2).click();
        Thread.sleep(3000);
        Utils.doScroll(driver);
        myInfoPage.selectBloodType.get(2).sendKeys("O+");
        myInfoPage.btnSave.get(1).click();
        Thread.sleep(1000);
        String txtActual = myInfoPage.selectBloodType.get(2).getText();
        String txtExpected = "-- Select --";
        Assert.assertEquals(txtActual, txtExpected);

    }
    @Test(priority = 8, description = "Employee can Update Blood Type by selecting any Type", groups = "smoke")
    public void updateBloodType() throws InterruptedException {
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.menuMyInfo.get(2).click();
        Thread.sleep(3000);
        Utils.doScroll(driver);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        myInfoPage.selectBloodType.get(2).click();
        myInfoPage.btnSave.get(1).click();
        Thread.sleep(1000);
        String txtActual = myInfoPage.selectBloodType.get(2).getText();
        String txtExpected = "AB-";
        Assert.assertEquals(txtActual, txtExpected);
    }
    @AfterTest(groups = "smoke")
    public void doLogout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}


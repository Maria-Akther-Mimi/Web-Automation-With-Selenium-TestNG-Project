package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.DashboardPage;
import page.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboard;
    @Test(priority = 1, description = "User can't login with invalid username")
    public void doLoginWithInvalidUsername() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(0);
        String errorMessageActual = loginPage.doLoginWithWrongCreds("wronguser", empObj.get("password").toString());
        String errorMessageExpected = "Invalid credentials";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }
    @Test(priority = 2, description = "User can't login with invalid password")
    public void doLoginWithInvalidPassword() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(0);
        String errorMessageActual = loginPage.doLoginWithWrongCreds(empObj.get("username").toString(), "wrongpass");
        String errorMessageExpected = "Invalid credentials";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }
    @Test(priority = 3, description = "User can login with valid Username and Password", groups = "smoke")
    public void doLogin() throws InterruptedException, IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(0);
        loginPage.doLogin(empObj.get("username").toString(), empObj.get("password").toString());
        dashboard = new DashboardPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboard.imgProfile.isDisplayed());
        Thread.sleep(3000);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "/index.php/dashboard/index";
        softAssert.assertTrue(urlActual.contains(urlExpected));
        softAssert.assertAll();
    }
    @AfterTest(groups = "smoke")
    public void doLogout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}


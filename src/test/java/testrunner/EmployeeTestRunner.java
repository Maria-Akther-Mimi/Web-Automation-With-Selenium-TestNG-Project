package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;
import page.PIMPage;
import page.UserModelPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class EmployeeTestRunner extends Setup {
    @BeforeTest(groups = "smoke")
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }
    @Test(priority = 1, description = "Employee cannot be created without username")
    public void createEmployeeWithoutUsername() throws InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(1).click();//PIM
        pimPage.buttonElem.get(2).click();//add employee button

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-main-title")));

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        pimPage.textElem.get(1).sendKeys(firstName);//first name
        String lastName = faker.name().lastName();
        pimPage.textElem.get(3).sendKeys(lastName);//last name

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-switch-input")));
        //Thread.sleep(1000);
        driver.findElement(By.className("oxd-switch-input")).click();
        String password = Utils.generateStrongPassword();
        pimPage.textElem.get(6).sendKeys(password);//password
        pimPage.textElem.get(7).sendKeys(password);//confirm password

        pimPage.buttonElem.get(1).click();//save

        String titleActual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String titleExpected = "Required";
        Assert.assertEquals(titleActual, titleExpected);
    }

    @Test(priority = 2, description = "Employee cannot be created without password")
    public void createEmployeeWithoutPassword() throws InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(1).click();//PIM
        pimPage.buttonElem.get(2).click();//add employee button

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-main-title")));

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        pimPage.textElem.get(1).sendKeys(firstName);//first name
        String lastName = faker.name().lastName();
        pimPage.textElem.get(3).sendKeys(lastName);//last name

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-switch-input")));
        //Thread.sleep(1000);
        driver.findElement(By.className("oxd-switch-input")).click();
        String username = faker.name().username() + Utils.generateRandomId(100,999);
        pimPage.textElem.get(5).sendKeys(username);//username

        pimPage.buttonElem.get(1).click();//save

        String titleActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        String titleExpected = "Required";
        Assert.assertEquals(titleActual, titleExpected);
    }
    @Test(priority = 3, description = "Employee created Successfully")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(1).click();//PIM
        pimPage.buttonElem.get(2).click();//add employee button

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-main-title")));

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        pimPage.textElem.get(1).sendKeys(firstName);//first name
        String lastName = faker.name().lastName();
        pimPage.textElem.get(3).sendKeys(lastName);//last name
        String employeeid = pimPage.textElem.get(4).getAttribute("value");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-switch-input")));
        Thread.sleep(1000);
        driver.findElement(By.className("oxd-switch-input")).click();

        String username = faker.name().username() + Utils.generateRandomId(100,999);
        String password = Utils.generateStrongPassword();
        //String password = faker.internet().password(8,10,true,true,true);
        pimPage.textElem.get(5).sendKeys(username);//username
        pimPage.textElem.get(6).sendKeys(password);//password
        pimPage.textElem.get(7).sendKeys(password);//confirm password

        pimPage.buttonElem.get(1).click();//save

        Thread.sleep(7000);
        String titleActual = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        String titleExpected = "Personal Details";

        UserModelPage model = new UserModelPage();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setEmployeeid(employeeid);
        model.setUsername(username);
        model.setPassword(password);
        Utils.saveInfo(model);

        Assert.assertEquals(titleActual, titleExpected);
    }
    @Test(priority = 4, description = "Search employee by valid id", groups = "smoke")
    public void searchEmployeeByID() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(1).click();//PIM
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String employeeId = empObj.get("id").toString();
        pimPage.textElem.get(1).sendKeys(employeeId);
        Thread.sleep(2000);
        pimPage.buttonElem.get(1).click();
        Thread.sleep(1000);
        String textActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String textExpected = "Record Found";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 5, description = "Search employee by invalid id")
    public void searchEmployeeByInvalidID() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(1).click();//PIM
        pimPage.textElem.get(1).sendKeys("34#6");
        Thread.sleep(2000);
        pimPage.buttonElem.get(1).click();
        Thread.sleep(1000);
        String textActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String textExpected = "No Records Found";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 6, description = "Search employee by valid name")
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(8).click();//PIM
        JSONArray empArray = Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String employeeName = empObj.get("firstName").toString();

        driver.findElements(By.tagName("input")).get(1).sendKeys(employeeName);
        Thread.sleep(3000);
        driver.findElement(By.className("oxd-autocomplete-option")).click();
        Thread.sleep(2000);
        pimPage.buttonElem.get(1).click();
        Thread.sleep(1000);
        String textActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String textExpected = "Record Found";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 7, description = "Search employee by invalid name")
    public void searchEmployeeByInvalidName() throws IOException, ParseException, InterruptedException {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.menuPIM.get(8).click();

        driver.findElements(By.tagName("input")).get(1).sendKeys("abcdef");
        pimPage.buttonElem.get(1).click();
        Thread.sleep(1000);
        String textActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String textExpected = "Invalid";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 8, description = "Successfully logout", groups = "smoke")
    public void doLogout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String testExpected = "Login";
        Assert.assertTrue(textActual.equals(testExpected));
    }
    @Test(priority = 9, description = "If the user is not logged in the system ,the logout option will not be visible")
    public void doLogoutBeforeLogin() {
        boolean isLogoutVisible;
        try {
            WebElement btnLogout = driver.findElement(By.linkText("Logout"));
            if(btnLogout.isDisplayed()) {
                isLogoutVisible = true;
            } else {
                isLogoutVisible = false;
            }
        } catch (NoSuchElementException e) {
            isLogoutVisible = false;
        }
        Assert.assertFalse(isLogoutVisible);
    }
}

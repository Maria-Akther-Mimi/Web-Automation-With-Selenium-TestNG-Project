package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import page.UserModelPage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void takeScreenShot(WebDriver driver) throws IOException {
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }

    public static void saveInfo(UserModelPage model) throws IOException, ParseException {
        String filePath = "./src/test/resources/Employees.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", model.getFirstname());
        jsonObject.put("lastName", model.getLastname());
        jsonObject.put("id", model.getEmployeeid());
        jsonObject.put("username", model.getUsername());
        jsonObject.put("password", model.getPassword());

        jsonArray.add(jsonObject);

        FileWriter writer = new FileWriter(filePath);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public static JSONArray readJSONArray(String url) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray empErray = (JSONArray) parser.parse(new FileReader(url));
        return empErray;
    }

    public static String generateStrongPassword() {
        String password = "";
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+";
        boolean hasLowercase = false;
        boolean hasNumber = false;

        while (password.length() < 8 || !hasLowercase || !hasNumber) {
            int index = (int) (Math.random() * chars.length());
            char c = chars.charAt(index);

            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }

            password += c;
        }
        return password;
    }

    public static int generateRandomId(int min, int max) {
        double rand = Math.random()*(max-min)+min;
        return (int)rand;
    }

    public static void doScroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
    }
}

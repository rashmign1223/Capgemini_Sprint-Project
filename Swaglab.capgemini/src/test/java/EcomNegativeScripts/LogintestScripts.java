
package EcomNegativeScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import SwaglabBase.Baseclass;
import SwagLabPOMpage.LoginPage;
import SwagLabsgenericUtility.Excelutility;
import SwagLabsgenericUtility.ScreenShot;

public class LogintestScripts extends Baseclass {

    Excelutility excel = new Excelutility();

    // Read test ID from Sheet3
    public String getTestID(int row) throws IOException {
        return excel.readDataFromExcel("Sheet3", row, 0);
    }

    // LOGIN-FUN-002: Invalid username
    @Test(groups = "functional")
    public void invalidUsernameTest() throws IOException {

        String testId = getTestID(1);

        LoginPage login = new LoginPage(driver);
        login.login("invalid_user", "secret_sauce");

        String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertTrue(
                errorMessage.contains("Username and password do not match"),
                "Error message was not displayed for invalid username.");

        System.out.println(testId + " - PASSED");
        ScreenShot.TakeScreenShot(driver,testId);

    }

    // LOGIN-INT-003: Invalid login must not open Inventory
    @Test(groups = "Integration")
    public void invalidLoginCannotAccessInventory() throws IOException {

        String testId = getTestID(2);

        LoginPage login = new LoginPage(driver);
        login.login("invalid_user", "invalid_password");

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Invalid login should not allow Inventory access.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo.com"),
                "User should remain on the SauceDemo login page.");

        System.out.println(testId + " - PASSED");
        ScreenShot.TakeScreenShot(driver,testId);
    }

    // LOGIN-SM-003: Invalid credentials are rejected
    @Test(groups = "Smoke")
    public void invalidCredentialsSmokeTest() throws IOException {

        String testId = getTestID(3);

        LoginPage login = new LoginPage(driver);
        login.login("wrong_user", "wrong_password");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("[data-test='error']")).isDisplayed(),
                "Login error was not displayed.");

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Invalid credentials should not open Inventory.");

        System.out.println(testId + " - PASSED");
        ScreenShot.TakeScreenShot(driver,testId);
    }

    // LOGIN-SYS-002: Blank username and password
    @Test(groups = "System")
    public void blankCredentialsTest() throws IOException {

        String testId = getTestID(4);

        LoginPage login = new LoginPage(driver);
        login.login("", "");

        String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertTrue(
                errorMessage.contains("Username is required"),
                "Required-credentials error was not displayed.");

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Blank credentials should not open Inventory.");

        System.out.println(testId + " - PASSED");
        ScreenShot.TakeScreenShot(driver,testId);
    }
}

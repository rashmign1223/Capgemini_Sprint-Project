
package SwaglabBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import SwagLabPOMpage.LoginPage;
import SwagLabPOMpage.ProductPage;
import SwagLabPOMpage.CartPage;
import SwagLabPOMpage.Checkout;


public class Baseclass {

    protected WebDriver driver;

    // All POM page objects
    protected LoginPage login;
    protected ProductPage product;
    protected CartPage cart;
    protected Checkout checkout;

    // Properties data
    protected String url;
    protected String username;
    protected String password;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void BM(@Optional("chrome") String browser)
            throws IOException {

        // Read properties file
        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(
                "./src/main/resources/data.Properties")) {

            p.load(fis);
        }

        url = p.getProperty("url");
        username = p.getProperty("username");
        password = p.getProperty("password");

        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "URL is missing in data.Properties");
        }

        // Launch browser
        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        // Browser settings
        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Open application
        driver.get(url);

        // Initialize all POM pages
        login = new LoginPage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
        checkout = new Checkout(driver);

        System.out.println("Browser launched: " + browser);
        System.out.println("Application opened: " + url);
    }

    // Login using properties file credentials
    protected void loginToApplication() {

        login.getun(username);
        login.getpwd(password);
        login.getlogin();
    }

    @AfterMethod(alwaysRun = true)
    public void AM() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        // Clear page objects
        login = null;
        product = null;
        cart = null;
        checkout = null;
    }
}



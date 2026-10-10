
package LOGIN;

import org.testng.annotations.Test;

import SwaglabBase.Baseclass;
import SwagLabPOMpage.LoginPage;

public class logintest extends Baseclass {

    @Test(groups = "smoke")
    public void loginPositiveTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        System.out.println("Positive Login Test Passed");
    }
}
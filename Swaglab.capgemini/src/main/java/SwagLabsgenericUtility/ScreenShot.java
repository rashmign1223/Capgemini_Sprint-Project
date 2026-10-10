package SwagLabsgenericUtility;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {

	public static void TakeScreenShot(WebDriver Driver,String screenshotName) throws IOException {
		TakesScreenshot tks = (TakesScreenshot)Driver;
		File scr = tks.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ss/"+screenshotName+".png");
		FileHandler.copy(scr, dest);
	}
	

}

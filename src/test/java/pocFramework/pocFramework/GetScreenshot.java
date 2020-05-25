package pocFramework.pocFramework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class GetScreenshot extends TestBase {

	public static String captureScreen(String ScreenshotName) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = "./TestReport/Screenshots/" + ScreenshotName + "_" + getcurrentdateandtime() + ".png";
		File target = new File(dest);
		FileHandler.copy(src, target);
		return dest;
	}

	public static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {

		}
		return str;
	}

}

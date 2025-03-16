package HomeLoan;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PersonalLoan {
	static WebDriver driver;
	static JavascriptExecutor js;
	static ChromeOptions option;
	static EdgeOptions options;
	static long Ends;
	static long Starts;

@BeforeClass(groups = "regression")
public void laucher() {
			option = new ChromeOptions();
			option.addArguments("start-maximized");
			option.addArguments("disable-popups");
			option.addArguments("disable-notification");
			driver = new ChromeDriver(option);
	driver.get("https://www.axisbank.com/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}
@Test(priority = 1,groups = "smoke")
public void ClickAction() {
	js = (JavascriptExecutor) driver;
	WebElement Scroll = driver.findElement(By.xpath("//a[@class='carditem' and @data-eventlabel='Personal Loan']"));
	js.executeScript("arguments[0].scrollIntoView(true)", Scroll);
	js.executeScript("arguments[0].click()", Scroll);
}
@AfterClass(groups = "regression")
public void closure() throws InterruptedException {
	Thread.sleep(3000);
	driver.quit();
}
@AfterMethod(groups = "regression")
public void Ends_on() {
	Ends = System.currentTimeMillis();
	System.out.println(Ends);
}
@BeforeMethod(groups = "regression")
public void Starts_on() {
	Starts = System.currentTimeMillis();
	System.out.println(Starts);
}

}

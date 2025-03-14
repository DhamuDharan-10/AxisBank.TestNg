package HomeLoan;

import java.awt.Robot;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.IObject;



public class Loans {
	static WebDriver driver;
	static ChromeOptions option;
	static JavascriptExecutor js;
	static Actions Mouse;
	static long Starts;
	static long endes;
	static Robot KeyBoard;
	static Select DropDowns;
	static WebDriverWait Waits;
	
	@DataProvider(name = "Softcodeing")  
		public Object [][] method1 () {
			return  new Object[][] {{2000000},{5000000}};
		}
	@DataProvider(name = "SoftcodeingOne")
	public Object[][] method2 () {
		return new Object[][] {{9787857768l,"Dhamodharan"}};
	}
@Parameters({"dhamu"})
@BeforeClass (groups = "smoke")
public void launcher(String weblauncher) {
	option = new ChromeOptions();
	option.addArguments("start-maximized");
	option.addArguments("disable-notification");
	option.addArguments("disable-popups");
	driver = new ChromeDriver(option);
	driver.get(weblauncher);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}
@BeforeMethod(groups = "smoke")
   public void starts_on() {
	Starts = System.currentTimeMillis();
	System.out.println(Starts);
}
   
@Test(priority = 1,groups = "sanity")
public void dashBoard() {
	WebElement HomeLoan = driver.findElement(By.xpath("//a[@data-eventlabel='Home Loan']"));
	js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true)", HomeLoan);
	boolean Visiblity = HomeLoan.isDisplayed();
	System.out.println(Visiblity);
	js.executeScript("arguments[0].click()", HomeLoan);
	
}
@AfterMethod(groups = "smoke")
public void ends_on() {
	endes = System.currentTimeMillis();
	System.out.println(endes);
}
@Test(priority = 2,dataProvider =  "Softcodeing",groups = "sanity")
public void AmountFixer(int amt) {
	WebElement AmountLimit = driver.findElement(By.xpath("//input[@id='loan_amount']"));
	Mouse = new Actions (driver);
	Mouse.doubleClick(AmountLimit).build().perform();
	AmountLimit.sendKeys(String.valueOf(amt));
	driver.findElement(By.xpath("//a[text()='Apply Now ']")).click();
	
	// AmountLimit.click();
	// AmountLimit.sendKeys("2000000");
	
}
@Test(priority = 3,groups = "regression")
public void windows_handle() {
	String originalWindow = driver.getWindowHandle();
	for(String windowHandle : driver.getWindowHandles()) {
		if(!windowHandle.equals(originalWindow)) {
			driver.switchTo().window(windowHandle);
			String newWindowTitle = driver.getTitle();
            Assert.assertNotEquals(newWindowTitle, "Original Window Title");
            
            System.out.println("Switched to new window with title: " + newWindowTitle);
		}
	}
}
@Test(priority = 4,dataProvider = "SoftcodeingOne",groups = "regression")
public void Details(long mobileno , String Name) {
   
    WebElement Drops = driver.findElement(By.xpath("//select[@id='ContentPlaceHolder1_Genesys1_ddlexistingcust']"));
    Drops.click();
    
    DropDowns = new Select(Drops);
    DropDowns.selectByVisibleText("No");
    WebElement Email = driver.findElement(By.xpath("//input[@placeholder='Email ID']"));
    js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].setAttribute('value','dharandhamu706@gmail.com')", Email);
    Waits = new WebDriverWait  (driver, Duration.ofSeconds(10));
    WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name']"));
    Waits.until(ExpectedConditions.visibilityOf(name));
    name.sendKeys(Name);

    
    WebElement mobile = driver.findElement(By.xpath("//input[@id='txtMobile']"));
    Waits.until(ExpectedConditions.visibilityOf(mobile)); 
    mobile.sendKeys(String.valueOf(mobileno));
}
@AfterClass(groups = "smoke")
public void closure() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}

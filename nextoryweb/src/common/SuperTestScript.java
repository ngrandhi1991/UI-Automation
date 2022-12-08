package common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.ITestContext;
import org.testng.annotations.*;

import generics.Excel;
import generics.Property;

@Listeners(CustomListener.class)
public class SuperTestScript implements AutomationConstants{
	
	public Logger log;
	public WebDriver driver;
	public static String url;
	public static long timeout;
	public static boolean loginrequired = true;
	public static boolean logoutrequired = false;
	//public static String[] subs = {"Silver","Guld","Familj2","Familj3","Familj4","Silver","Guld"};
	
	public SuperTestScript() {
		
		log = Logger.getLogger(this.getClass());
		Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
	}
	
	
	@BeforeSuite(alwaysRun=true)
	@Parameters({"country"})
	public void initFramework(ITestContext context,@Optional String country) {
		
		log.info("Initialising Automation FrameWork");
		if(country.equals("SE"))
			url = Property.getPropertyValue(CONFIG_PATH + CONFIG_FILE,"URL_SE");
		else if(country.equals("FI"))
			url = Property.getPropertyValue(CONFIG_PATH + CONFIG_FILE, "URL_FI");
		else
			url = Property.getPropertyValue(CONFIG_PATH + CONFIG_FILE, "URL_DE");
		timeout = Long.parseLong(Property.getPropertyValue(CONFIG_PATH + CONFIG_FILE, "IMPLICIT"));
	}
	
	@AfterSuite(alwaysRun=true)
	public void closeFramework() {
		
		log.info("Closing Automation FrameWork");
	}
	
	@Parameters({"browser"})
	@BeforeTest(alwaysRun=true) 
	public void initBrowser(@Optional("chrome") String browser) {
		
		log.info("Execution Started in browser");
	}
	
	@Parameters({"browser"})
	@AfterTest(alwaysRun=true)
	public void closeBrowser(@Optional("chrome") String browser) {
		log.info("Closing the Browser");
	}
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun=true)
	public void initApplication(@Optional("chrome") String browser) {
		
		log.info("Browser :" +browser);
		if (browser.equals("firefox")) 
		{
			System.setProperty(GECKO_KEY, DRIVER_PATH + GECKO_FILE);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox(); 
			capabilities.setCapability("marionette", true);
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("binary", "C:\\Program Files\\Mozilla Firefox\\firefox.exe"); 
			driver=new FirefoxDriver(capabilities);
		}

		else if (browser.equals("phantom")) 
		{
			System.setProperty(PHANTOM_KEY, DRIVER_PATH + PHANTOM_FILE);
			driver = new PhantomJSDriver();
		} 
		else 
		{
			System.setProperty(CHROME_KEY, DRIVER_PATH + CHROME_FILE);
			driver = new ChromeDriver();
		}
		
		//driver.manage().window().maximize();
		driver.get(url);
		driver.manage().deleteAllCookies();
		log.info("Timeout:" + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	@AfterMethod(alwaysRun=true)
	public void cleanApplication() throws InterruptedException 
	{
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Parameters(value = {"country"})
	@BeforeSuite(alwaysRun=true)
	public void FilltheExcel(ITestContext context,@Optional String country) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		SimpleDateFormat sd = new SimpleDateFormat("ddMMMyy_HHmmss"); 
		
		if(country.equals("SE")) {
			for (int i = 1; i <= Excel.getRowCount(AutomationConstants.INPUT_PATH, "NewEmail_SE"); i++)
			{
			
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail_SE", i, 2, "test_" + i + "_" + sd.format(new Date())+System.currentTimeMillis()+ "@frescano.se");
			
			}
		}else if(country.equals("FI")) {
			for (int i = 1; i <= Excel.getRowCount(AutomationConstants.INPUT_PATH, "NewEmail_FI"); i++)
			{
			
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail_FI", i, 2, "test_" + i + "_" + sd.format(new Date())+System.currentTimeMillis()+ "@frescano.fi");
			
			}
		}else {
			for (int i = 1; i <= Excel.getRowCount(AutomationConstants.INPUT_PATH, "NewEmail_DE"); i++)
			{
			
				Excel.setExcelData(AutomationConstants.INPUT_PATH, "NewEmail_DE", i, 2, "test_" + i + "_" + sd.format(new Date())+System.currentTimeMillis()+ "@frescano.de");
			
			}
		}
	}
	
}

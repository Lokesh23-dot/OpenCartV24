package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // Logger is class
	public Properties prop;
	
	@BeforeClass(alwaysRun=true)
	@Parameters({"browser","OS"})
	public void setUp(String br, String os) throws IOException
	{
		
		// Loading Config.Properties file:
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		prop= new Properties();
		prop.load(file); // loading config.properties file.
		
		
		// In this line LogManager is a predefined class and getLogger is method inside it. It is used to fetching the log4j2 file.
		logger=LogManager.getLogger(this.getClass()); 
		
		// Remote execution:
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			// Selecting OS:
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Invalid OS");
				return; // we are giving this return because if the OS itself invalid than we dont need to continue execution further.
			}
			
			// Selecting browser:
			if(br.equalsIgnoreCase("chrome"))
				{
					cap.setBrowserName("chrome");
				}
			else if(br.equalsIgnoreCase("edge"))
				{
					cap.setBrowserName("MicrosoftEdge");
				}
			else if(br.equalsIgnoreCase("firefox"))
				{
					cap.setBrowserName("firefox");
				}
			else 
				{
					System.out.println("Invalid browser");
					return; // we are giving this return because if the browser itself invalid than we dont need to continue execution further.
				}
			
			driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
	
		// The below code is for Local system execution.
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			if(br.equalsIgnoreCase("chrome")) 
			{
				driver= new ChromeDriver();
			}
		
			else if(br.equalsIgnoreCase("edge"))
			{
				driver= new EdgeDriver();
			}	
			
			else if(br.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();
			}
				
			else
			{
				System.out.println("Invalid browser name");
				return; // we are giving this return because if the browser itself invalid than we dont need to continue execution further.
			}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("Url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber= RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedAlphaNumeric= RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNumeric;
	}
	
	public String CaptureScreenShot(String tname)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String timestamp= df.format(dt);
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile= ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath= System.getProperty("user.dir")+"\\screenShots"+tname+"_"+timestamp+".png";
		File targetFile= new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}

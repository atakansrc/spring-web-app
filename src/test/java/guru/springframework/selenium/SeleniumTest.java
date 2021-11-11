package guru.springframework.selenium;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {
	
	WebDriver drv;
	WebElement ele;
	String url = "http://localhost:8088/";
	String id = "ID";

	@BeforeClass
	public void initializeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--allow--insecure-localhost");
		opt.addArguments("acceptInsecureCerts");
		opt.addArguments("--ignore-certificate-errors");
		opt.addArguments("--disable-notifications");
		opt.addArguments("--headless");
		drv = new ChromeDriver(opt);
	}
	
	@AfterClass
	public void endTest() {
		drv.quit();
	}
	@Test
	public void openBrowser() {
		drv.get(url);
	}

	@Test(dependsOnMethods = { "openBrowser" })
	public void editLastProduct() throws InterruptedException { 
		drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		drv.findElement(By.xpath("/html/body/div/nav/div/div/ul/li[1]/a")).click();
		drv.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[position()=last()]/td[6]/a")).click();
		
		drv.findElement(By.id("productId")).clear();
		drv.findElement(By.id("productId")).sendKeys(id + Math.random());
		
		drv.findElement(By.id("description")).sendKeys(" UPDATED");
		
		String price = drv.findElement(By.id("price")).getAttribute("value");
		System.out.println(price);
		
		String image = drv.findElement(By.id("imageUrl")).getAttribute("value");
		System.out.println(image);
		
		((JavascriptExecutor) drv).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(drv.getWindowHandles());
		drv.switchTo().window(tabs.get(1));
		drv.get(image);
	    
	    
		Thread.sleep(5000);
		
	}

}

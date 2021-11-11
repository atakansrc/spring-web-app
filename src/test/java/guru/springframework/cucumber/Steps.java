package guru.springframework.cucumber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps {
	
	WebElement ele;
	WebDriver drv;
	String url = "http://localhost:8088/";
	String id = "ID";

	@Before
	public void launch_chrome() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--allow--insecure-localhost");
		opt.addArguments("acceptInsecureCerts");
		opt.addArguments("--ignore-certificate-errors");
		opt.addArguments("--disable-notifications");
		opt.addArguments("disable-infobars");
		opt.addArguments("--headless");
		drv = new ChromeDriver(opt);
	}

	@After(order = 0)
	public void close_chrome() {
		drv.quit();
	}

	@When("URL is localhost")
	public void url_is_localhost() {
		drv.get("http://localhost:8088/");
	}

	@Then("Find products tab and click")
	public void find_products_tab_and_click() {
		drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		drv.findElement(By.xpath("/html/body/div/nav/div/div/ul/li[1]/a")).click();
	}

	@Then("Select last item at table")
	public void select_last_item_at_table() {
		drv.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[position()=last()]/td[6]/a")).click();

	}

	@Then("Edit attributes")
	public void edit_attributes() {
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
	}

	@Then("Edit is successfull.")
	public void edit_is_successfull() {
		System.out.println("ITEM SUCCESSFULLY UPDATED...");
	}
}

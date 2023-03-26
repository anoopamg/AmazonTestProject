package AmazonPjct;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		
		if(driver.getTitle().equalsIgnoreCase("Title is :Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and "
				+ "More - Amazon.in"));
		{
			System.out.println("Amazon website launched successfully");
		}
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Samsung mobile");
		
		WebElement searchSubmit = driver.findElement(By.xpath("//input[@id='nav-search-submit-button'][@type='submit']"));
		searchSubmit.click();
		System.out.println("Search for Samsung mobile successful");
		
		List <WebElement> products = driver.findElements(By.xpath("//div[@class='a-section']//h2//span[starts-with(text(),'Samsung')]"));	
		
		List <WebElement> productPrices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"
				+ "//div[contains(@class,'price')]//span[@class='a-price-whole']"));
		
		WebElement productCurrency = driver.findElement(By.xpath("//div[@class='sg-row']//span[@class='a-price-symbol']"));
		
		ArrayList<String> prodNames = new ArrayList<String>();
		for(WebElement xyz:products)
		{
			prodNames.add(xyz.getText());
		}
		String productCurr = productCurrency.getText();
		
		String price;
		ArrayList<String> productPrice = new ArrayList<String>();
		for(WebElement abc:productPrices)
		{
			price = productCurr+abc.getText();
			productPrice.add(price);
		}
		
		HashMap<String,String> productList = new HashMap<String,String>();
		System.out.println("List :");
		
		for(int i=0;i<products.size();i++) {
			productList.put(prodNames.get(i), productPrice.get(i));
		}
		Iterator<Entry<String, String>> itr = productList.entrySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        
        TakesScreenshot tsObj = (TakesScreenshot)driver;
		File fileObj = tsObj.getScreenshotAs(OutputType.FILE);
		String folder="src/Screenshots";
		File screenshot = new File(folder+"/AmazonTest_SamsungSearch.png");
		
		FileUtils.copyFile(fileObj, screenshot);

	}

}

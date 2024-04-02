import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
//Script to add ('Cucumber','Brocolli','Beetroot') items into Cart. 
 */
public class ShopGroceryTest {

	public static void main(String[] args) 
	{
		String[] desiredItems = {"Cucumber","Brocolli","Beetroot"};		
		WebDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		addItemsToCart(driver,desiredItems);
		driver.findElement(By.cssSelector("[alt='Cart']")).click();
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")));
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
	}
	
	public static void addItemsToCart(WebDriver driver, String[] desiredItems)
	{
		int count=0;
		List<WebElement> productNames = driver.findElements(By.cssSelector("h4.product-name"));
		List<String> desiredItemsList = Arrays.asList(desiredItems); 
		for(int i=0;i<productNames.size();i++)
		{
			String itemName = productNames.get(i).getText().split(" ")[0].trim();
			if(desiredItemsList.contains(itemName))
			{				
				//static xpath which never changes
				//productNames.get(i).findElement(By.xpath("//*[text()='ADD TO CART']")).click();
				driver.findElements(By.xpath("//*[@class='product-action']/button")).get(i).click();
				count++;
			}
			if(count>desiredItems.length)
				break;
		}
		
	}

}

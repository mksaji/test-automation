package com.mksaji.seleniumtests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazonTests {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://www.amazon.in";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void test() throws Exception {
	    // Test Home page
		driver.get(baseUrl + "/");
	    assertEquals("Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle());
	    try {
	      assertTrue(isElementPresent(By.cssSelector("span.nav-logo-base.nav-sprite")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    //Sign in Test, no password
	    driver.findElement(By.cssSelector("#nav-link-yourAccount > span.nav-line-1")).click();
	    driver.findElement(By.cssSelector("h1.a-spacing-small")).click();
	    assertEquals("Amazon Sign In", driver.getTitle());
	    driver.findElement(By.id("ap_email")).clear();
	    driver.findElement(By.id("ap_email")).sendKeys("sajeevmanikkoth@gmail.com");
	    driver.findElement(By.id("signInSubmit")).click();
	    assertEquals("Amazon Sign In", driver.getTitle());
	    try {
	      assertEquals("Please enter your password.", driver.findElement(By.cssSelector("span.a-list-item")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    //Successful Sign in
	    driver.findElement(By.id("ap_password")).clear();
	    driver.findElement(By.id("ap_password")).sendKeys("5april2004");
	    driver.findElement(By.id("signInSubmit")).click();
	    assertEquals("Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle());
	    try {
	      assertTrue(isElementPresent(By.cssSelector("span.nav-logo-base.nav-sprite")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    //Test Cart page navigation
	    driver.findElement(By.id("nav-cart-count")).click();
	    assertEquals("Amazon.in Shopping Cart", driver.getTitle());
	    try {
	      assertEquals("Your Recently Viewed Items and Featured Recommendations", driver.findElement(By.cssSelector("div.rhf-header")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    //Test Navigation back to Home page
	    driver.findElement(By.linkText("Amazon.in homepage")).click();
	    assertEquals("Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle());
	    try {
	      assertTrue(isElementPresent(By.cssSelector("span.nav-logo-base.nav-sprite")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  //Common Utility functions
	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	
}

package com.sceanario2;

import org.testng.annotations.Test;

import com.scenario2Utility.Propertiesreader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class LoginTest {
	
	WebDriver driver;
	LoginClass lg=new LoginClass(driver);
	Propertiesreader prop;
	AdminClass ad=new AdminClass(driver);
	
  

  @BeforeTest
  public void beforeTest() {
	  prop=new Propertiesreader();
	  driver=new ChromeDriver();
	  driver.get(prop.getData("url"));
	  lg.addWait();
	  driver.manage().window().maximize();
	  lg=new LoginClass(driver);
  }
  
       @Test(priority=1)
        public void loginTest()
       {
    	   lg.login(prop.getData("username"), prop.getData("password"));
    	   
       }
       
       @Test(priority=2)
       public void validateUrl()
       {
    	   String url=lg.appUrl();
    	   Assert.assertEquals(url,"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "Url not matched. Test failed ");
    	   System.out.println("Url Matched launch success. Test Passed");
       }
       
       
       @Test(priority=3)
       public void validateTitle()
       {
    	   System.out.println("Page Tilte: "+lg.appTitle());
    	   Assert.assertEquals(lg.appTitle().contains("OrangeHRM"),true, "Title not matched. Test failed");
    	   System.out.println("Title matched launch success.  Test passed");
       }
       
       
     @AfterClass
     public void tearDown() {
    	 if(driver!=null)
    		 driver.quit();
    	 else
    		 System.out.println("Driver is null");
     }

     
}








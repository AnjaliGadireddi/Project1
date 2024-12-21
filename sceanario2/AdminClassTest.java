package com.sceanario2;

  import org.testng.annotations.Test;
  import com.scenario2Utility.Propertiesreader;
  import org.testng.annotations.BeforeClass;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  import org.testng.Assert;
  import org.testng.annotations.AfterClass;
  import org.testng.annotations.BeforeTest;

  public class AdminClassTest {
  	
  	Propertiesreader prop;
  	WebDriver driver;
  	LoginClass lg=new LoginClass(driver);
  	AdminClass ad=new AdminClass(driver);	
  	 @BeforeClass
  	  public void login() {
  		 lg.login(prop.getData("username"), prop.getData("password"));
  	     }

  	 @BeforeTest
  	  public void setUp() {
  		 prop=new Propertiesreader();
  		 driver=new ChromeDriver();
  		 driver.get(prop.getData("url"));
  		 lg.addWait();
  		 driver.manage().window().maximize();
  		 lg=new LoginClass(driver);
  		 ad=new AdminClass(driver);
  	  }
  	   
  	  @Test(priority=1)
  	  public void getLeftSidemenuOptions() {
  		  System.out.println("Left side Menu options");
  		  lg.addWait();
  		  Assert.assertEquals(ad.getOptionsCount(),12,"Count Not matched .Test failed!!");
  		  System.out.println("Left side count Matched to 12.  Test Passed!!!");
  		  lg.addWait();
  		  System.out.println("Click on Admin Option from left side Menu");
  		  ad.clickElement(ad.adminOption);
  		  lg.addWait();
  		  System.out.println("Clicked on Admin page");
  		  Assert.assertEquals(ad.adminHeader.getText(),"Admin","Failed to launch Admin Page . Test Failed");
  		  System.out.println("Launched the Admin pager succesfully . Test passed");
  		  	  
  	  }
  	 
  	  @Test(priority=2)
  	  public void searchByUsername()
  	  {
  		  ad.searchTextBox.sendKeys("Admin");
  		  lg.addWait();
  		  System.out.println("Search with Employ: Admin");
  		  ad.clickElement(ad.searchBtn);
  		  lg.addWait();
  		  String output=ad.recordfoundHeader.getText();
  		  System.out.println(output);
  		  lg.refreshPage();
  		  lg.addWait();
  	  }
  	  
  	  @Test(priority=3)
  	  public void searchByRole()
  	  {
  		  System.out.println("Search employee with Role: Admin");
  		  ad.searchWithAdminRole();
  		  System.out.println(ad.recordfoundHeader.getText());
  		  lg.addWait();
  		  lg.refreshPage();
  		  lg.addWait();
  	  }
  	  
  	  @Test(priority=4)
  	  public void searchByUserStatus()
  	  {
  		  System.out.println("Search employee with Satus: Enabled");
  		  ad.searchWithStatus();
  		  System.out.println(ad.recordfoundHeader.getText());
  		  lg.addWait();
  		  lg.refreshPage();
  		  lg.addWait();
  	  }
  	  
  	  @AfterClass
  	  public void afterClass() {
  		  driver.quit();
  	  }

  }

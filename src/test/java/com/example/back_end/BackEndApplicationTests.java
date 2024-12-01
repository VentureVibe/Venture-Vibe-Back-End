//package com.example.back_end;
//
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testng.annotations.BeforeMethod;
//
//@SpringBootTest
//class BackEndApplicationTests {
//
//	WebDriver driver;
//
//	@BeforeMethod
//	public void openLandingPage(){
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:5173/");
//	}
//
//	@Test
//	public void loginTest(){
//		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
//
//	}
//
//}

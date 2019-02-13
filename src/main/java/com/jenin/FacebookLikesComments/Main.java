package com.jenin.FacebookLikesComments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {

	//private static String XLXS_FILE = "Friend_list.xlsx";
	private static String firstName = "Arafat"; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		System.out.println("Running...");

		String url = new String("https://facebook.com");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();       
		//options.addArguments("--headless");
		//options.addArguments("--disable-gpu");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		Login login = new Login();
		System.out.println("Trying to login facebook...");

		if(login.main(driver, url, firstName)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("\nEverything complete...");
		}else {
			System.out.println("Log in failed finaly, program aborted...");
		}
		driver.close();

	}

}

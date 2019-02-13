package com.jenin.FacebookLikesComments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;


public class Login {

  private String USR_PSSWD_FILE = System.getProperty("user.dir") + "/resource/usr_psswd.txt";
  private String loginFirstName = "//*[text()[normalize-space(.) = \"";

  private Boolean IsLogedin(WebDriver driver)
  { 
    try
    {
      if(driver.findElements(By.xpath(loginFirstName)).isEmpty()) {
        return false;
      }else {
        return true;
      }
    }
    catch (final NoSuchElementException e)
    {
      return false;
    }
  }

  public Boolean main(WebDriver driver, String url, String Fname) {
    // TODO Auto-generated method stub4
    loginFirstName = loginFirstName + Fname + "\"]]";
    driver.navigate().to(url);

    File file = new File("cookie.data");
    Boolean Islogin_with_cookie = false;

    if(file.exists() && !file.isDirectory()) {
      try {
        FileReader fileReader = new FileReader(file);
        BufferedReader Buffreader = new BufferedReader(fileReader);
        String strline;
        while((strline = Buffreader.readLine()) != null) {
          StringTokenizer token = new StringTokenizer(strline, ";");

          while(token.hasMoreTokens()) {
            String name = token.nextToken();
            String value = token.nextToken();
            String domain = token.nextToken();
            String path = token.nextToken();
            Date expiry = null;

            String val;
            if(!(val=token.nextToken()).equals("null")) {
              DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
              //Tue Aug 18 19:16:42 BDT 2020
              try {
                expiry = df.parse(val);
              } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
            Boolean isSecure = 	new Boolean(token.nextToken()).booleanValue();
            Cookie ck = new  Cookie(name, value, domain, path, expiry, isSecure);
            driver.manage().addCookie(ck);

          }
        }
        Buffreader.close();
      }catch(IOException e){
        e.printStackTrace();

      } catch (UnableToSetCookieException e) {
        // TODO Auto-generated catch block

        e.printStackTrace();
      }
      System.out.println("Trying to login using Cookie...Cookie Pushed...");
      Islogin_with_cookie = true;
      driver.navigate().refresh();
    }

    if(IsLogedin(driver) == false) {
      if(Islogin_with_cookie == true) {
        System.out.print("Login using cookie failed...");
      }
      driver.findElement(By.id("email")).sendKeys(getUserName());
      driver.findElement(By.id("pass")).sendKeys(getPsswd());
      driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
      System.out.println("Try to login using usr and psswd...");
      if(!driver.findElements(By.id("approvals_code")).isEmpty()) {
        Scanner scan = new Scanner(System.in);
        while(true) {
          String twofactor = new String();
          System.out.print("Enter 'Done' when complete twofactor: ");
          twofactor = scan.nextLine();
          System.out.println("You Entered: "+twofactor);
          if(twofactor.equals("Done")) break;
        }
        scan.close();
        driver.navigate().refresh();

      }
      try {
        if(file.exists() && !file.isDirectory()) {
          file.delete();
          System.out.println("Previous 'cookie.data' file deleted");
        }
        file.createNewFile();
        FileWriter fileWrite = new FileWriter(file);
        BufferedWriter Bwrite = new BufferedWriter(fileWrite);
        for(Cookie ck : driver.manage().getCookies()){
          Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
          Bwrite.newLine();
        }
        Bwrite.close();			
        fileWrite.close();
        System.out.println("'cookie.data' file written...");
      } catch (UnableToSetCookieException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }catch(IOException e) {
        e.printStackTrace();
      }
    }

    if(IsLogedin(driver) == true) {
      System.out.println("Log in successful...");
      return true;
    }else {
      System.out.println("Log in Failed...");
      return false;
    }

  }

  private String getUserName() {
    String usr = null;
    try {
      File file = new File(USR_PSSWD_FILE);
      BufferedReader br = new BufferedReader(new FileReader(file));
      usr =  br.readLine();
      br.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return usr;
  }


  private String getPsswd() {
    String psswd = null;
    try {
      File file = new File(USR_PSSWD_FILE);
      BufferedReader br = new BufferedReader(new FileReader(file));
      psswd =  br.readLine();
      psswd =  br.readLine();
      br.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return psswd;
  }

}

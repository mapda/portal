package main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a new instance of the Firefox driver
        System.setProperty("webdriver.chrome.driver", "/home/jemila/Gecko/chromedriver");
        WebDriver driver;
        driver = new ChromeDriver();
        //maximize window
        driver.manage().window().maximize();
        //Launch portal
        String url = "http://portal.aait.edu.et";
        driver.get(url);
        //type username
        driver.findElement(By.id("UserName")).sendKeys("user");
        //type password
        driver.findElement(By.name("Password")).sendKeys("password", Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='ml2']")).click();
        Thread.sleep(1000);
        String sourceCode = driver.getPageSource();
        String table = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div/div/table")).getText();
        //String table = sourceCode.substring(sourceCode.indexOf("<table style=\"border: none !important;\" class=\"table table-bordered table-striped table-hover\">")+95, sourceCode.indexOf("</table>"));
        System.out.println("table: "+table);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("filename.txt"), "utf-8"))) {
            writer.write(table);
        }
        catch (IOException e){

        }

        driver.close();
    }
}

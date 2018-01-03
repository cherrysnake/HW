
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class MainTest {

  //  public static void main(String[] args) {

         WebDriver driver;

        @BeforeEach

        public void setUp () {
            String iua = "http://www.i.ua/";

            File file = new File(".\\drivers\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            WebDriver driver = new ChromeDriver();

            String textLetter = "test text";
            driver.get(iua);

            String emailIua = "email";
        }

        @Test

        public void Test() {
            LoginPage l = new LoginPage(driver);
        }




        @BeforeEach

        public void shutDown () {
            driver.quit();
        }
    }


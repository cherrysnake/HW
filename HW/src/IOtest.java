import java.io.File;
import java.lang.String;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;


public class IOtest {

    String BASE_URL = "http://i.ua";
    WebDriver driver;

    @BeforeEach
    public void setUp () {
        File file = new File(".\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");


        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        driver.navigate().to(BASE_URL);
    }


    @Test
    public void firstTest ()  {

        //test verifies that user can log in using correct credentials
        driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[2]")).click();
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[1]/input")).sendKeys("cherrysnake");
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[2]/input")).sendKeys("Pa%%word");

        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/input[6]")).click();

        String actualGreeting = driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[2]")).getText();
        String expGreeting = "Привет, Marina";

        assertEquals(expGreeting, actualGreeting, "user wasn't logged in");

        //log out
        driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[8]")).click();

    }

    @Test
    public void secondTest () {

        //test verifies that user cannnot log in using wrong password
        driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[2]")).click();
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[1]/input")).sendKeys("wrongCredentials");
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[2]/input")).sendKeys("somePswd");

        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/input[6]")).click();

        String currentUrl = driver.getCurrentUrl();
        String currentWiget = driver.findElement(By.xpath(".//*[@id='lform_errCtrl']/div[1]")).getText();

        String expectedUrl = "https://passport.i.ua/login/";
        String expectedWiget = "Неверный логин или пароль";

        assertEquals(expectedUrl, currentUrl, "somehow user was able to log in - page assertion");
        assertEquals(expectedWiget, currentWiget, "somehow user was able to log in - widget assertion");
    }

    @Test
    public void thirdTest () {

        //test verifies that user sends mail to himself and mail data is correct
        String expectedSubject = "someSubject";
        String expectedText = "some text";
        String expectedTo = "cherrysnake@i.ua";

        //log in
        driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[2]")).click();
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[1]/input")).sendKeys("cherrysnake");
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/fieldset/p[2]/input")).sendKeys("Pa%%word");

        //go to mail box
        driver.findElement(By.xpath(".//*[@id='FloatLogin']/div/div/form/input[6]")).click();

        driver.findElement(By.xpath("html/body/div[3]/div[3]/div[3]/div[2]/div[2]/div[3]/ul/li[1]/a")).click();
        //create new mail
        driver.findElement(By.xpath("html/body/div[1]/div[5]/div[1]/div[1]/p/a")).click();
        driver.findElement(By.xpath(".//*[@id='to']")).sendKeys(expectedTo);
        driver.findElement(By.xpath("html/body/div[4]/div[5]/div[1]/div[1]/div[1]/div/form/div[5]/div[2]/span/input[1]")).sendKeys(expectedSubject);
        driver.findElement(By.xpath(".//*[@id='text']")).sendKeys(expectedText);
        driver.findElement(By.xpath("html/body/div[4]/div[5]/div[1]/div[1]/p[1]/input[1]")).click();
        //check for mail
        driver.findElement(By.xpath("html/body/div[1]/div[4]/ul/li[5]/a")).click();
        driver.findElement(By.xpath(".//*[@id='mesgList']/form/div[1]/a/span[2]")).click();


        String currentTo = driver.findElement(By.xpath("html/body/div[1]/div[5]/div[2]/div[2]/ul/li/div[1]/div/div[2]/div[2]/div[2]")).getText();
        assertEquals(expectedTo, currentTo, "Destination is correct");

        String currentText = driver.findElement(By.xpath("html/body/div[1]/div[5]/div[2]/div[2]/ul/li/div[1]/div/div[3]/pre")).getText();
        assertEquals(expectedText, currentText, "Text is correct");

        String currentSubject = driver.findElement(By.xpath("html/body/div[1]/div[5]/div[2]/div[2]/ul/li/div[1]/div/div[2]/h3")).getText();
        assertEquals(expectedSubject, currentSubject, "Subject is correct");

        //log out
        driver.findElement(By.xpath(".//*[@id='header_overall']/div[1]/ul[1]/li[1]/a")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[3]/ul[1]/li[8]")).click();
    }
    @AfterEach
    public void shutDown () {
        driver.quit();
    }
}




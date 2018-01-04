
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

  //  public static void main(String[] args) {
  private LoginPage logPage;
  private SendMail sendMailPage;
  private MailinatorCheck mailinatorCheck;

    WebDriver driver;

    String IUA_URL = "http://i.ua";
    String Mailignator_URL = "http://www.mailinator.com";
    private String loginName = "Marina Chernish";
    private String textText = "some text";
    private String subjectText = "Test mail";

        @BeforeEach

        public void setUp () {


            File file = new File(".\\drivers\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);

        }

        @Disabled

         public void FirstTest() {

            driver.get(IUA_URL);
            driver.navigate().to(IUA_URL);

            logPage = PageFactory.initElements(driver, LoginPage.class);
            logPage.loggingIn();

            sendMailPage = PageFactory.initElements(driver, SendMail.class);
            sendMailPage.SendOneMail();

        }

    @Test

    public void SecondTest() {

        driver.get(Mailignator_URL);
        driver.navigate().to(Mailignator_URL);

        mailinatorCheck = PageFactory.initElements(driver, MailinatorCheck.class);
        mailinatorCheck.OpenMail();
        String sender = mailinatorCheck.GetSender();
        assertEquals(loginName, sender, "Sender is correct");

        try {
            mailinatorCheck.GoToMail();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String subject = mailinatorCheck.GetSubject();
        assertEquals(subjectText, subject, "Subject is correct");
        String text = mailinatorCheck.GetText();
        assertEquals(textText, text, "Text is correct");



    }


        @AfterEach

        public void shutDown () {
            driver.close();
        }
    }


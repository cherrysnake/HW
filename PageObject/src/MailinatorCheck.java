import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MailinatorCheck {


    private WebDriver driver;

    private void waitForVisibility(WebElement element, String text) throws Error{
        new WebDriverWait(driver, 20).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    private void waitForFrameVisibility() throws Error{
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mailFrameName));
    }

        public MailinatorCheck(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        private final String inboxNameId = "inboxfield";
        private final String goButtonPath = "html/body/section[1]/div/div[3]/div[2]/div[2]/div[1]/span/button";
        private final String openMailPath = ".//*[@id='inboxpane']/li[1]/div/div[3]";
        private final String subjectPath = ".//*[@id='inboxpane']/li[1]/div/div[4]";
        private final String mailFrameName = "msg_body";
        private final String textPath = "html/body";
        private final String queryFieldPath = ".//*[@id='query_data']/div[4]";
        private final String inpoxPanePath = ".//*[@id='inboxpane']";



        private String inboxNameText = "cherrysnake";
        private String textText = "some text";
        private String subjectText = "Test mail";

        @FindBy(id = inboxNameId)
        WebElement inboxName;

        @FindBy(xpath = goButtonPath)
        WebElement goButton;

        @FindBy(xpath = openMailPath)
        WebElement openMail;

        @FindBy(xpath = subjectPath)
        WebElement subject;

        @FindBy(xpath = textPath)
        WebElement text;

        @FindBy(xpath = queryFieldPath)
        WebElement queryField;

    @FindBy(xpath = inpoxPanePath)
    WebElement inpoxPane;


        public MailinatorCheck OpenMail()  {
            inboxName.sendKeys(inboxNameText);
            goButton.click();
            return new MailinatorCheck(driver);
        }

        public MailinatorCheck GoToMail(){


            waitForVisibility(queryField, "cherrysnake@mailinator.com");

            waitForVisibility(inpoxPane, "Test mail");

           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            openMail.click();

            waitForFrameVisibility();

            waitForVisibility(text, "реклама");

            return new MailinatorCheck(driver);
        }

        public String GetSender(){

            return openMail.getText();
        }

        public String GetSubject() {
            return subject.getText();
        }


        public String GetText() {

            return text.getText();
        }

}

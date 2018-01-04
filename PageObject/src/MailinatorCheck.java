import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailinatorCheck {

        private WebDriver driver;

        public MailinatorCheck(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        private final String inboxNameId = "inboxfield";
        private final String goButtonPath = "html/body/section[1]/div/div[3]/div[2]/div[2]/div[1]/span/button";
        private final String openMailPath = ".//*[@id='row_1515020908-2000225771967-cherrysnake']/div/div[3]";
        private final String subjectPath = ".//*[@id='msgpane']/div[1]/div[1]";
        private final String textPath = "html/body";

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

        public MailinatorCheck OpenMail() {

            inboxName.sendKeys(inboxNameText);
            goButton.click();
            return new MailinatorCheck(driver);
        }

        public void GoToMail() throws InterruptedException {
            openMail.click();
            wait(100);
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

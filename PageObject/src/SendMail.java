import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendMail {

    private WebDriver driver;

    public SendMail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

  //  private final String mailPath = ".//*[@id='FloatLogin']/div/div/form/input[6]";
    private final String createMailPath = ".//*[@id='tabcont600']/div[2]/div[1]/div[1]/a[3]";
    private final String toFieldId = "to";
    private final String textFieldId = "text";
    private final String subjectFieldPath = "html/body/div[4]/div[5]/div[1]/div[1]/div[1]/div/form/div[5]/div[2]/span/input[1]";
    private final String sendButtonPath = "html/body/div[4]/div[5]/div[1]/div[1]/p[1]/input[1]";

    private String toText = "cherrysnake@mailinator.com";
    private String textText = "some text";
    private String subjectText = "Test mail";

    @FindBy(xpath = createMailPath)
    WebElement createMail;

    @FindBy(id = toFieldId)
    WebElement toField;

    @FindBy(id = textFieldId)
    WebElement textField;

    @FindBy(xpath = subjectFieldPath)
    WebElement subjectField;

    @FindBy(xpath = sendButtonPath)
    WebElement sendButton;

    public SendMail SendOneMail() {

        createMail.click();
        toField.sendKeys(toText);
        textField.sendKeys(textText);
        subjectField.sendKeys(subjectText);
        sendButton.click();
        return new SendMail(driver);
    }
}

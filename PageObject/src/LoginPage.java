import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String loginButtonPath = "html/body/div[2]/div[3]/ul[1]/li[2]";
    private final String loginFieldPath = ".//*[@id='FloatLogin']/div/div/form/fieldset/p[1]/input";
    private final String loginPasswordPath = ".//*[@id='FloatLogin']/div/div/form/fieldset/p[2]/input";
    private final String loginOKpath = ".//*[@id='FloatLogin']/div/div/form/input[6]";

    private String loginName = "cherrysnake";
    private String password = "Pa%%word";

    @FindBy(xpath = loginButtonPath)
    WebElement loginButton;

    @FindBy(xpath = loginFieldPath)
    WebElement loginField;

    @FindBy(xpath = loginPasswordPath)
    WebElement loginPassword;

    @FindBy(xpath = loginOKpath)
    WebElement loginOK;

    public LoginPage loggingIn() {

        loginButton.click();
        loginField.sendKeys(loginName);
        loginPassword.sendKeys(password);
        loginOK.click();
        return new LoginPage(driver);
    }

}

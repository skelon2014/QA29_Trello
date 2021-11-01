package framework;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    BoardHelper board;
    UserHelper user;
    CardHelper card;
    ListHelper list;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws InterruptedException {
        if (browser.equals(BrowserType.CHROME)) {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        WebDriverManager.chromedriver().setup();
//        wd = new ChromeDriver(chromeOptions);
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.EDGE)) {
            wd = new EdgeDriver();
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://trello.com/");

        board = new BoardHelper(wd);
        user = new UserHelper(wd);
        card = new CardHelper(wd);
        list = new ListHelper(wd);

        user.login(new User().withUser("rochman.elena@gmail.com").withPassword("12345.com"));
    }

    public BoardHelper board() {
        return board;
    }

    public UserHelper user() {
        return user;
    }

    public CardHelper card() {
        return card;
    }

    public ListHelper list() {
        return list;
    }

    public void stop() {
        wd.quit();
    }


}

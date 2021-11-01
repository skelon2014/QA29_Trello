package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public boolean isHomeButtonOnHeader() {
        return isElementPresent(By.cssSelector("[data-test-id='header-home-button']"));
    }

    public boolean isTrelloButtonOnHeader() {
        return isElementPresent(By.cssSelector("[aria-label='Back to home']"));
    }

    public void retunToHomePage() {
        if (isTrelloButtonOnHeader()) {
            click(By.cssSelector("[aria-label='Back to home']"));
        } else
            click(By.cssSelector("[data-test-id='header-home-button']"));
    }

    public void waitForElementAndClick(int timeOut, By locator) {
        new WebDriverWait(wd, Duration.ofSeconds(timeOut))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }
}

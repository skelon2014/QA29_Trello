package framework;

import model.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardHelper extends HelperBase {
    public CardHelper(WebDriver wd) {
        super(wd);
    }


    public void initCreation() {

        waitForElementAndClick(5, By.cssSelector(".js-add-a-card"));
//input.js-add-card // blue button
    }

    public void confirmCreation() {
        click(By.cssSelector(".js-add-card"));
    }

    public void fillCreationForm(Card card) {

        type(By.cssSelector(".js-card-title"), card.getCardName());
        if (card.getColor() != null) {
            openCardMenu();
            selectLabelsFromMenu();
            click(By.cssSelector("[data-color=" + card.getColor() + "]"));
        }

    }

    private void selectLabelsFromMenu() {
        click(By.cssSelector(".js-label-selector"));
    }

    private void openCardMenu() {
        click(By.cssSelector(".js-cc-menu"));
    }
}

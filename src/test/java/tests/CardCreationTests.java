package tests;

import model.Card;
import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> cards() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"card-1", "yellow"});
        list.add(new Object[]{"CARD", "red"});
        list.add(new Object[]{"!#$%-", "green"});

        return list.iterator();
    }

    @BeforeClass
    public void preconditions() throws InterruptedException {
        if (!app.user().isTrelloButtonOnHeader()) {
            app.user().login(new User().withUser("rochman.elena@gmail.com").withPassword("12345.com"));
        }

        app.board().clickOnTheFirstBoard();

        if (app.list().getCount() == 0) {
            app.list().create("Test");
        }
    }

    @Test(dataProvider = "cards")
    public void cardWithLableCreationTestFromDataProvider(String title, String color) {
//if(app.card().is) {
        app.card().initCreation();
//}
        app.card().fillCreationForm(new Card().withName(title).withColor(color));
        app.card().confirmCreation();

    }

    @Test
    public void cardWithLableCreationTest() {
        app.board().clickOnTheFirstBoard();
        if (app.list().getCount() == 0) {
            app.list().create("Test");
        }

        app.card().initCreation();
        app.card().fillCreationForm(new Card().withName("new card").withColor("yellow"));
        app.card().confirmCreation();

    }

    @Test
    public void cardCreationTest() {
        app.board().clickOnTheFirstBoard();
        if (app.list().getCount() == 0) {
            app.list().create("Test");
        }
        app.card().initCreation();
        app.card().fillCreationForm(new Card().withName("new card"));
        app.card().confirmCreation();
    }
}

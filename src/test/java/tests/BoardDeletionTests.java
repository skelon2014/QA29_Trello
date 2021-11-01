package tests;

import model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BoardDeletionTests extends TestBase{
    @BeforeMethod(enabled = false)
    public void preconditions() throws InterruptedException {
        if(!app.user().isTrelloButtonOnHeader()){
            app.user().login(new User().withUser("rochman.elena@gmail.com").withPassword("12345.com"));
        }
    }

    @Test
    public void testBoardDeletion(){
        int boardsCountBeforeDeletion = app.board().getBoardsCount();
        while (boardsCountBeforeDeletion>1) {
            //click on first board
            // click(By.xpath("//*[@class='boards-page-board-section-header-name'][2]/../..//li"));
            app.board().clickOnTheFirstBoard();
            app.board().openSideBoardMenu();
            app.board().openMore();
            app.board().closeBoard();
            app.board().retunToHomePage();
            boardsCountBeforeDeletion = app.board().getBoardsCount();
        }
        int boardsCountAfterDeletion = app.board().getBoardsCount();

        // Assert.assertEquals(boardsCountAfterDeletion, boardsCountBeforeDeletion-1);

    }
}

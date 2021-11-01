package tests;

import model.Board;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreation extends TestBase{
    @BeforeClass
    public void preconditions() throws InterruptedException {
        if(!app.user().isTrelloButtonOnHeader()){
            app.user().login(new User().withUser("rochman.elena@gmail.com").withPassword("12345.com"));
        }
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
    }

    @DataProvider
    public Iterator<Object[]> boards(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"board-1" });
        list.add(new Object[]{"Board-" });
        list.add(new Object[]{"BOARD-" });
        list.add(new Object[]{"!#$%-"});

        return  list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> boardsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/boards.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new Board().withName(split[0])});
            line = reader.readLine();
        }

        return list.iterator();
    }


    @Test(dataProvider = "boards")
    public void testBoardCtreationFromDataProvider(String name) throws InterruptedException {
        int boardsCountBeforeCreation = app.board().getBoardsCount();
        app.board().initBoardCreationFromHeader();
        app.board().fillBoardCreationForm(new Board().withName(name));
        Thread.sleep(15000);
        app.board().retunToHomePage();
        int boardsCountAfterCreation = app.board().getBoardsCount();

        Assert.assertEquals(boardsCountAfterCreation, boardsCountBeforeCreation+1);

    }

    @Test(dataProvider = "boardsCSV")
    public void testBoardCtreationFromDataProviderCSV(Board board) throws InterruptedException {
        int boardsCountBeforeCreation = app.board().getBoardsCount();
        app.board().initBoardCreationFromHeader();
        app.board().fillBoardCreationForm(board);
        Thread.sleep(15000);
        app.board().retunToHomePage();
        int boardsCountAfterCreation = app.board().getBoardsCount();

        Assert.assertEquals(boardsCountAfterCreation, boardsCountBeforeCreation+1);

    }

    @Test
    public void testBoardCtreation() throws InterruptedException {
        int boardsCountBeforeCreation = app.board().getBoardsCount();
        System.out.println(boardsCountBeforeCreation);

        app.board().initBoardCreationFromHeader();

        app.board().fillBoardCreationForm(new Board().withName("test-board"));

        Thread.sleep(15000);
        app.board().retunToHomePage();
        int boardsCountAfterCreation = app.board().getBoardsCount();
        System.out.println(boardsCountAfterCreation);

        Assert.assertEquals(boardsCountAfterCreation, boardsCountBeforeCreation+1);

    }

}

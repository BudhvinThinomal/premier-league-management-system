package content.services;

import content.entities.FootballClub;
import content.entities.SportsClub;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;

public class GraphicalUserInterface {
    //Declaring the arraylists
    private ArrayList<SportsClub> guiFootballClubList;
    private ArrayList<SportsClub> guiPlayedMatchList;
    //Initialize object to communicate with PremierLeagueManager class
    PremierLeagueManager managerObj = new PremierLeagueManager();

    //args constructor for initialize arraylists
    public GraphicalUserInterface(ArrayList<SportsClub> footballClubList, ArrayList<SportsClub> playedMatchList) {
        this.guiFootballClubList = footballClubList;
        this.guiPlayedMatchList = playedMatchList;
    }

    /*
     *Use to display menu
     *Include primary stage and main scene
     *Include four buttons for operations
     */
    public void graphicalUserInterfaceMenu() {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);
        System.out.println("\n==> GUI loaded successfully!!");

        Stage primStage = new Stage();
        primStage.setTitle("The Premier League");
        AnchorPane primAncPane = new AnchorPane();

        Label mainTitle = new Label("PREMIER LEAGUE - " + seasonYear);
        mainTitle.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 42; " +
                "-fx-background-radius : 15;");
        mainTitle.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainTitle,415.0);
        AnchorPane.setLeftAnchor(mainTitle, 500.0);
        AnchorPane.setRightAnchor(mainTitle, 500.0);

        Button listBtn = new Button("Premier League Table");
        listBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d0066; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 25; " +
                "-fx-background-radius : 15; -fx-border-color : #4d004d; -fx-border-width : 5; -fx-border-radius : 10; -fx-translate-x : 125.0; -fx-translate-y : 775.0;");
        listBtn.setPrefWidth(365.0);

        Button randomPlayMatchBtn = new Button("Play Random Match");
        randomPlayMatchBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d0066; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 25; " +
                "-fx-background-radius : 15; -fx-border-color : #4d004d; -fx-border-width : 5; -fx-border-radius : 10; -fx-translate-x : 500.0; -fx-translate-y : 885.0;");
        randomPlayMatchBtn.setPrefWidth(365.0);

        Button datePlayedMatchBtn = new Button("Played Matches");
        datePlayedMatchBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d0066; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 25; " +
                "-fx-background-radius : 15; -fx-border-color : #4d004d; -fx-border-width : 5; -fx-border-radius : 10; -fx-translate-x : 1080.0; -fx-translate-y : 885.0;");
        datePlayedMatchBtn.setPrefWidth(365.0);

        Button searchPlayedMatchBtn = new Button("Search Played Matches");
        searchPlayedMatchBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d0066; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 25; " +
                "-fx-background-radius : 15; -fx-border-color : #4d004d; -fx-border-width : 5; -fx-border-radius : 10; -fx-translate-x : 1450.0; -fx-translate-y : 775.0;");
        searchPlayedMatchBtn.setPrefWidth(365.0);

        Button exitToBtn = new Button("Exit !!");
        exitToBtn.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-background-radius : 10; -fx-translate-x : 1800.0; -fx-translate-y : 25.0;");
        exitToBtn.setPrefSize(100,50);

        primAncPane.getChildren().addAll(mainTitle, listBtn, randomPlayMatchBtn, datePlayedMatchBtn, searchPlayedMatchBtn, exitToBtn);

        exitToBtn.setOnAction(event -> primStage.close());

        //Background image insert using try and catch
        try {
            Image mainMenuImage = new Image("backgroundImg/menu.png");
            BackgroundSize bgrSize = new BackgroundSize(100, 100, true, true, true, true);
            BackgroundImage bgrMainMenuImage = new BackgroundImage(mainMenuImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgrSize);
            Background background = new Background(bgrMainMenuImage);
            primAncPane.setBackground(background);
            primAncPane.setStyle("-fx-text-fill : black; -fx-font-weight : bold; -fx-font-size : 20;");
        } catch (IllegalArgumentException e) {
            primAncPane.setStyle("-fx-text-fill : black; -fx-font-weight : bold; -fx-font-size : 20; -fx-background-color : #80d4ff;");
            System.out.println(" ***Background Image Not Found!!***");
        }
        Scene primScene = new Scene(primAncPane,1920,1000);
        primStage.setScene(primScene);

        listBtn.setOnAction(event -> premierLeagueTablePart(primStage, primScene));

        randomPlayMatchBtn.setOnAction(event -> randomPlayedMatchPart(primStage, primScene));

        datePlayedMatchBtn.setOnAction(event -> playedMatchesPart(primStage, primScene));

        searchPlayedMatchBtn.setOnAction(event -> searchMatchesPart(primStage, primScene));

        primStage.setMaximized(true);
        primStage.setResizable(false);
        primStage.showAndWait();
    }

    /*
     *Use to display premier league table
     *Sorted according to number of points
     *Can be sorted according to number of goals scored and number of wins
     *Three buttons are use for sorting
     */
    private void premierLeagueTablePart(Stage primStage, Scene primScene) {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);

        AnchorPane tableListAncPane = ancPane();

        Button tableListBackToBtn = backBtn();
        tableListBackToBtn.setOnAction(event -> primStage.setScene(primScene));

        Button tableListExitToBtn = exitBtn();
        tableListExitToBtn.setOnAction(event -> primStage.close());

        Label tableListMainTitle = new Label("PREMIER LEAGUE TABLE - " + seasonYear);
        tableListMainTitle.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-font-size : 25; -fx-background-radius : 10; -fx-translate-x : 725.0; -fx-translate-y : 20.0;");
        tableListMainTitle.setAlignment(Pos.CENTER);

        Label nameTable = new Label("FOOTBALL CLUB NAME");
        nameTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 100.0; -fx-translate-y : 120.0;");
        nameTable.setAlignment(Pos.CENTER);
        nameTable.setPrefSize(250,55);

        Label locationTable = new Label("CLUB LOCATION");
        locationTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 350.0; -fx-translate-y : 120.0;");
        locationTable.setAlignment(Pos.CENTER);
        locationTable.setPrefSize(250,55);

        Label chairmanTable = new Label("CHAIRMAN");
        chairmanTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 600.0; -fx-translate-y : 120.0;");
        chairmanTable.setAlignment(Pos.CENTER);
        chairmanTable.setPrefSize(250,55);

        Label foundedYearTable = new Label("FOUNDED YEAR");
        foundedYearTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-wrap-text : true; -fx-translate-x : 850.0; -fx-translate-y : 120.0; -fx-text-alignment : 'CENTER';");
        foundedYearTable.setPrefSize(120,55);

        Label matchesPlayedTable = new Label("MATCHES PLAYED");
        matchesPlayedTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-wrap-text : true; -fx-translate-x : 970.0; -fx-translate-y : 120.0; -fx-text-alignment : 'CENTER';");
        matchesPlayedTable.setPrefSize(100,55);

        Label winTable = new Label("WIN");
        winTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 1070.0; -fx-translate-y : 120.0;");
        winTable.setAlignment(Pos.CENTER);
        winTable.setPrefSize(100,55);

        Label defeatsTable = new Label("DEFEATS");
        defeatsTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 1170.0; -fx-translate-y : 120.0;");
        defeatsTable.setAlignment(Pos.CENTER);
        defeatsTable.setPrefSize(100,55);

        Label drawTable = new Label("DRAWS");
        drawTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 1270.0; -fx-translate-y : 120.0;");
        drawTable.setAlignment(Pos.CENTER);
        drawTable.setPrefSize(100,55);

        Label scoredTable = new Label("GOALS SCORED");
        scoredTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-wrap-text : true; -fx-translate-x : 1370.0; -fx-translate-y : 120.0; -fx-text-alignment : 'CENTER';");
        scoredTable.setPrefSize(100,55);

        Label receivedTable = new Label("GOALS RECEIVED");
        receivedTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-wrap-text : true; -fx-translate-x : 1470.0; -fx-translate-y : 120.0; -fx-text-alignment : 'CENTER';");
        receivedTable.setPrefSize(100,55);

        Label differenceTable = new Label("GOAL DIFFERENCE");
        differenceTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-wrap-text : true; -fx-translate-x : 1570.0; -fx-translate-y : 120.0; -fx-text-alignment : 'CENTER';");
        differenceTable.setPrefSize(120,55);

        Label pointsTable = new Label("POINTS");
        pointsTable.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-border-color : #ffffff; -fx-border-width : 1; -fx-translate-x : 1690.0; -fx-translate-y : 120.0;");
        pointsTable.setAlignment(Pos.CENTER);
        pointsTable.setPrefSize(100,55);

        Button sortGoalsBtn = new Button("Sort :- Goals Scored");
        sortGoalsBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-translate-x : 1270.0; -fx-translate-y : 60.0;");
        sortGoalsBtn.setPrefSize(220,30);

        Button sortWinBtn = new Button("Sort :- Number of Wins");
        sortWinBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-translate-x : 1515.0; -fx-translate-y : 60.0;");
        sortWinBtn.setPrefSize(220,30);

        Button sortPointsBtn = new Button("Sort :- Number of Points");
        sortPointsBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10;");
        sortPointsBtn.setPrefSize(240,30);
        sortPointsBtn.setVisible(false);

        Label sortPointName = new Label("Sorted According to The Number of Points");
        sortPointName.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-translate-x : 180.0; -fx-translate-y : 60.0; -fx-font-size : 18; -fx-padding : 5;");
        sortPointName.setPrefSize(430,30);
        sortPointName.setAlignment(Pos.CENTER);

        Label sortGoalsName = new Label("Sorted According to The Goals Scored");
        sortGoalsName.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-translate-x : 180.0; -fx-translate-y : 60.0; -fx-font-size : 18; -fx-padding : 5;");
        sortGoalsName.setAlignment(Pos.CENTER);
        sortGoalsName.setPrefSize(430,30);
        sortGoalsName.setVisible(false);

        Label sortWinName = new Label("Sorted According to The Number of Wins");
        sortWinName.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-translate-x : 180.0; -fx-translate-y : 60.0; -fx-font-size : 18; -fx-padding : 5;");
        sortWinName.setAlignment(Pos.CENTER);
        sortWinName.setPrefSize(430,30);
        sortWinName.setVisible(false);

        Label tableListEmpty = new Label("***The PREMIER LEAGUE TABLE is Already Empty!!***");
        tableListEmpty.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-font-size : 25; -fx-background-radius : 10; -fx-translate-x : 580.0; -fx-translate-y : 400.0; -fx-padding : 10; -fx-opacity: 0.8;");
        tableListEmpty.setAlignment(Pos.CENTER);
        tableListEmpty.setVisible(false);

        tableListAncPane.getChildren().addAll(tableListBackToBtn, tableListMainTitle, tableListExitToBtn, nameTable, locationTable, chairmanTable, foundedYearTable,
                matchesPlayedTable, winTable, defeatsTable, drawTable, scoredTable, receivedTable, differenceTable, pointsTable, sortGoalsBtn, sortWinBtn, sortPointsBtn, sortPointName, sortGoalsName, sortWinName, tableListEmpty);

        if ((guiFootballClubList.size()) == 0){
            tableListEmpty.setVisible(true);
        }

        ArrayList<FootballClub> sortedFootballClubList = new ArrayList<>();
        for (SportsClub clubList : guiFootballClubList){
            FootballClub tempClubList = (FootballClub) clubList;
            sortedFootballClubList.add(tempClubList);
        }
        //sort according to points using comparator class
        sortedFootballClubList.sort(new PointsCompare());
        //calling method for initialize statistics
        sortedClubList(sortedFootballClubList, tableListAncPane);

        sortGoalsBtn.setOnAction(event -> sortListToGoals(sortGoalsBtn, sortWinBtn, sortPointsBtn, sortedFootballClubList, tableListAncPane, sortPointName, sortGoalsName, sortWinName));

        sortWinBtn.setOnAction(event -> sortListToWin(sortWinBtn, sortGoalsBtn, sortPointsBtn, sortedFootballClubList, tableListAncPane, sortPointName, sortGoalsName, sortWinName));

        sortPointsBtn.setOnAction(event -> sortListToPoints(sortWinBtn, sortGoalsBtn, sortPointsBtn, sortedFootballClubList, tableListAncPane, sortPointName, sortGoalsName, sortWinName));

        Scene tableListScene = new Scene(tableListAncPane,1920,1000);
        primStage.setScene(tableListScene);
    }

    //Use to sort according to number of goals scored using comparator class
    private void sortListToGoals(Button sortGoalsBtn, Button sortWinBtn, Button sortPointsBtn, ArrayList<FootballClub> sortedFootballClubList, AnchorPane tableListAncPane, Label sortPointName, Label sortGoalsName, Label sortWinName) {
        sortGoalsBtn.setVisible(false);
        sortPointName.setVisible(false);
        sortWinName.setVisible(false);
        sortGoalsName.setVisible(true);
        sortPointsBtn.setTranslateX(1270.0);
        sortPointsBtn.setTranslateY(60.0);
        sortWinBtn.setVisible(true);
        sortPointsBtn.setVisible(true);
        sortedFootballClubList.sort(new GoalScoredCompare());
        sortedClubList(sortedFootballClubList, tableListAncPane);
    }

    //Use to sort according to number of wins using comparator class
    private void sortListToWin(Button sortWinBtn, Button sortGoalsBtn, Button sortPointsBtn, ArrayList<FootballClub> sortedFootballClubList, AnchorPane tableListAncPane, Label sortPointName, Label sortGoalsName, Label sortWinName) {
        sortWinBtn.setVisible(false);
        sortPointName.setVisible(false);
        sortGoalsName.setVisible(false);
        sortWinName.setVisible(true);
        sortPointsBtn.setTranslateX(1515.0);
        sortPointsBtn.setTranslateY(60.0);
        sortGoalsBtn.setVisible(true);
        sortPointsBtn.setVisible(true);
        sortedFootballClubList.sort(new MatchWinCompare());
        sortedClubList(sortedFootballClubList, tableListAncPane);
    }

    //Use to sort according to number of points using comparator class
    private void sortListToPoints(Button sortWinBtn, Button sortGoalsBtn, Button sortPointsBtn, ArrayList<FootballClub> sortedFootballClubList, AnchorPane tableListAncPane, Label sortPointName, Label sortGoalsName, Label sortWinName) {
        sortGoalsName.setVisible(false);
        sortWinName.setVisible(false);
        sortPointName.setVisible(true);
        sortPointsBtn.setVisible(false);
        sortGoalsBtn.setVisible(true);
        sortWinBtn.setVisible(true);
        sortedFootballClubList.sort(new PointsCompare());
        sortedClubList(sortedFootballClubList, tableListAncPane);
    }

    //Use to initialize statistics to premier league table using labels
    private void sortedClubList(ArrayList<FootballClub> sortedFootballClubList, AnchorPane tableListAncPane) {
        double columnHeight = 175.0;
        for (FootballClub guiSportClub : sortedFootballClubList) {

            Label clubName = new Label(guiSportClub.getClubName());
            clubName.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 100.0;");
            clubName.setAlignment(Pos.CENTER);
            clubName.setPrefSize(250, 40);
            clubName.setTranslateY(columnHeight);

            Label clubLocation = new Label(guiSportClub.getClubLocation());
            clubLocation.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 350.0;");
            clubLocation.setAlignment(Pos.CENTER);
            clubLocation.setPrefSize(250, 40);
            clubLocation.setTranslateY(columnHeight);

            Label clubChairman = new Label(guiSportClub.getChairman());
            clubChairman.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 600.0;");
            clubChairman.setAlignment(Pos.CENTER);
            clubChairman.setPrefSize(250, 40);
            clubChairman.setTranslateY(columnHeight);

            Label clubFound = new Label(guiSportClub.getFoundedYear());
            clubFound.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 850.0;");
            clubFound.setAlignment(Pos.CENTER);
            clubFound.setPrefSize(120, 40);
            clubFound.setTranslateY(columnHeight);

            Label clubMatches = new Label(String.valueOf(guiSportClub.getNumOfMatchesPlayed()));
            clubMatches.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 970.0;");
            clubMatches.setAlignment(Pos.CENTER);
            clubMatches.setPrefSize(100, 40);
            clubMatches.setTranslateY(columnHeight);

            Label clubWins = new Label(String.valueOf(guiSportClub.getWin()));
            clubWins.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1070.0;");
            clubWins.setAlignment(Pos.CENTER);
            clubWins.setPrefSize(100, 40);
            clubWins.setTranslateY(columnHeight);

            Label clubDefeats = new Label(String.valueOf(guiSportClub.getDefeats()));
            clubDefeats.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1170.0;");
            clubDefeats.setAlignment(Pos.CENTER);
            clubDefeats.setPrefSize(100, 40);
            clubDefeats.setTranslateY(columnHeight);

            Label clubDraws = new Label(String.valueOf(guiSportClub.getDraws()));
            clubDraws.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1270.0;");
            clubDraws.setAlignment(Pos.CENTER);
            clubDraws.setPrefSize(100, 40);
            clubDraws.setTranslateY(columnHeight);

            Label clubScored = new Label(String.valueOf(guiSportClub.getNumOfGoalsScored()));
            clubScored.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1370.0;");
            clubScored.setAlignment(Pos.CENTER);
            clubScored.setPrefSize(100, 40);
            clubScored.setTranslateY(columnHeight);

            Label clubReceived = new Label(String.valueOf(guiSportClub.getNumOfGoalsReceived()));
            clubReceived.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1470.0;");
            clubReceived.setAlignment(Pos.CENTER);
            clubReceived.setPrefSize(100, 40);
            clubReceived.setTranslateY(columnHeight);

            Label clubDifference = new Label(String.valueOf(guiSportClub.getGoalDifference()));
            clubDifference.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1570.0;");
            clubDifference.setAlignment(Pos.CENTER);
            clubDifference.setPrefSize(120, 40);
            clubDifference.setTranslateY(columnHeight);

            Label clubPoints = new Label(String.valueOf(guiSportClub.getNumOfPoints()));
            clubPoints.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-border-color : #4d004d; -fx-border-width : 1; -fx-translate-x : 1690.0;");
            clubPoints.setAlignment(Pos.CENTER);
            clubPoints.setPrefSize(100, 40);
            clubPoints.setTranslateY(columnHeight);

            tableListAncPane.getChildren().addAll(clubName, clubLocation, clubChairman, clubFound, clubMatches, clubWins, clubDefeats, clubDraws, clubScored, clubReceived, clubDifference, clubPoints);
            columnHeight += 40.0;
        }
    }

    /*
     *Use to display random played match
     *Match played between random teams on randomly generated date
     *Statistics also generated randomly
     */
    private void randomPlayedMatchPart(Stage primStage, Scene primScene) {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);

        AnchorPane randomMatchPane = ancPane();

        Button randomBackBtn = backBtn();
        randomBackBtn.setOnAction(event -> primStage.setScene(primScene));

        Button randomExitToBtn = exitBtn();
        randomExitToBtn.setOnAction(event -> primStage.close());

        Label randomMainTitle = new Label("PREMIER LEAGUE Random Played Match - " + seasonYear);
        randomMainTitle.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-font-size : 25; -fx-background-radius : 10; -fx-translate-x : 630.0; -fx-translate-y : 20.0; -fx-padding: 10;");
        randomMainTitle.setAlignment(Pos.CENTER);

        Button playBtn = new Button("Let's Play!!");
        playBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-font-size : 24; -fx-translate-x : 200.0; -fx-translate-y : 200.0; -fx-padding : 5;");
        playBtn.setPrefSize(200,40);

        Label randomBackground = new Label();
        randomBackground.setStyle("-fx-background-color : #4d004d; -fx-background-radius : 10; -fx-translate-x : 150.0; -fx-translate-y : 230.0;  -fx-opacity: 0.7;");
        randomBackground.setPrefSize(1600,500);

        Label randomLine1 = new Label();
        randomLine1.setStyle("-fx-background-color : #ffffff; -fx-translate-x : 640.0; -fx-translate-y : 230.0;");
        randomLine1.setPrefSize(5,500);
        Label randomLine2 = new Label();
        randomLine2.setStyle("-fx-background-color : #ffffff; -fx-translate-x : 1190.0; -fx-translate-y : 230.0;");
        randomLine2.setPrefSize(5,500);

        Label randomWin = new Label("-Played Match Result-");
        randomWin.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 22; " +
                "-fx-translate-x : 150.0; -fx-translate-y : 260.0; -fx-border-width : 2; -fx-border-color: #ffffff;");
        randomWin.setAlignment(Pos.CENTER);
        randomWin.setPrefSize(490,40);

        Label randomTeamOne = new Label("-Team 01-");
        randomTeamOne.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 22; " +
                "-fx-translate-x : 645.0; -fx-translate-y : 260.0; -fx-border-width : 2; -fx-border-color: #ffffff;");
        randomTeamOne.setAlignment(Pos.CENTER);
        randomTeamOne.setPrefSize(545,40);

        Label randomTeamTwo = new Label("-Team 02-");
        randomTeamTwo.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 22; " +
                "-fx-translate-x : 1195.0; -fx-translate-y : 260.0; -fx-border-width : 2; -fx-border-color: #ffffff;");
        randomTeamTwo.setAlignment(Pos.CENTER);
        randomTeamTwo.setPrefSize(555,40);

        Label randomDate = new Label("-Match Played Date-");
        randomDate.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 22; " +
                "-fx-translate-x : 150.0; -fx-translate-y : 560.0; -fx-border-width : 2; -fx-border-color: #ffffff;");
        randomDate.setAlignment(Pos.CENTER);
        randomDate.setPrefSize(490,40);

        Button viewTableBtn = new Button("View Premier League Table");
        viewTableBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-background-radius : 10; -fx-font-size : 24; -fx-translate-x : 1345.0; -fx-translate-y : 200.0; -fx-padding : 5;");
        viewTableBtn.setPrefWidth(375);

        Label randomEmpty = new Label("***The Premier League hasn't Enough Clubs for Play Match!!***");
        randomEmpty.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 24; " +
                "-fx-background-radius : 10; -fx-translate-x : 150.0; -fx-translate-y : 200.0; -fx-padding : 5;");
        randomEmpty.setPrefWidth(850);
        randomEmpty.setVisible(false);

        randomMatchPane.getChildren().addAll(randomBackBtn, randomMainTitle, randomExitToBtn, randomBackground, randomLine1, randomLine2, playBtn, randomWin, randomTeamOne, randomTeamTwo, randomDate, viewTableBtn, randomEmpty);

        if ((guiFootballClubList.size()) < 2){
            playBtn.setDisable(true);
            randomEmpty.setVisible(true);
        }

        playBtn.setOnAction(event -> randomPlayedMatch(randomMatchPane));
        viewTableBtn.setOnAction(event -> premierLeagueTablePart(primStage, primScene));

        Scene randomMatchScene = new Scene(randomMatchPane,1920,1000);
        primStage.setScene(randomMatchScene);
    }

    //Use to initialize statistics to random played match using labels
    private void randomPlayedMatch(AnchorPane randomMatchPane) {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);
        int randomTeamOne = (int) (Math.random() * guiFootballClubList.size());
        int randomTeamTwo;
        do {
            randomTeamTwo = (int) (Math.random() * guiFootballClubList.size());
        } while (randomTeamOne == randomTeamTwo);

        FootballClub teamOneName = (FootballClub) guiFootballClubList.get(randomTeamOne);
        FootballClub teamTwoName = (FootballClub) guiFootballClubList.get(randomTeamTwo);
        int randomScoredOne = (int) (Math.random() * 11);
        int randomScoredTwo = (int) (Math.random() * 11);

        int randomMonth;
        int randomDay;
        String formattedRandomDate;
        do {
            randomMonth = (int) ((Math.random() * 12) + 1);
            randomDay = (int) ((Math.random() * 31) + 1);
            formattedRandomDate = randomDay + "/" + randomMonth + "/" + seasonYear;
        } while (!managerObj.validatingDate(formattedRandomDate));

        int firstWin = 0;
        int secondWin = 0;
        int firstDefeat = 0;
        int secondDefeat = 0;
        int firstDraw = 0;
        int secondDraw = 0;
        int firstPoints = 0;
        int secondPoints = 0;
        int firstNumOfMatchPlayed;
        int secondNumOfMatchPlayed;
        String winningTeamName;

        if (randomScoredOne > randomScoredTwo) {
            firstWin = secondDefeat = 1;
            winningTeamName = teamOneName.getClubName() + " Won the Match";
            firstPoints = 3;
        } else if (randomScoredOne < randomScoredTwo){
            secondWin = firstDefeat = 1;
            winningTeamName = teamTwoName.getClubName() + " Won the Match";
            secondPoints = 3;
        } else {
            firstDraw = secondDraw = 1;
            winningTeamName = "Played Match was Draw";
            firstPoints = secondPoints = 1;
        }
        firstNumOfMatchPlayed = secondNumOfMatchPlayed = 1;
        SportsClub addPlayedMatch = new FootballClub((teamOneName.getClubName()),(teamOneName.getClubLocation()),(teamOneName.getChairman()),(teamOneName.getFoundedYear()),firstWin,firstDraw,firstDefeat,randomScoredOne,randomScoredTwo,firstPoints,firstNumOfMatchPlayed,formattedRandomDate,(teamTwoName.getClubName()));
        guiPlayedMatchList.add(addPlayedMatch);

        teamOneName.setWin(firstWin);
        teamTwoName.setWin(secondWin);
        teamOneName.setDefeats(firstDefeat);
        teamTwoName.setDefeats(secondDefeat);
        teamOneName.setDraws(firstDraw);
        teamTwoName.setDraws(secondDraw);
        teamOneName.setNumOfGoalsScored(randomScoredOne);
        teamTwoName.setNumOfGoalsScored(randomScoredTwo);
        teamOneName.setNumOfGoalsReceived(randomScoredTwo);
        teamTwoName.setNumOfGoalsReceived(randomScoredOne);
        teamOneName.setNumOfPoints(firstPoints);
        teamTwoName.setNumOfPoints(secondPoints);
        teamOneName.setNumOfMatchesPlayed(firstNumOfMatchPlayed);
        teamTwoName.setNumOfMatchesPlayed(secondNumOfMatchPlayed);
        managerObj.updatePlayedMatch(guiFootballClubList, guiPlayedMatchList);

        Label winTeamName = new Label(winningTeamName);
        winTeamName.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 155.0; -fx-translate-y : 350.0; -fx-background-radius : 10; -fx-padding : 15;");
        winTeamName.setAlignment(Pos.CENTER);
        winTeamName.setPrefWidth(480);

        Label teamOneLabel = new Label("Team Name : " + teamOneName.getClubName());
        teamOneLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 650.0; -fx-translate-y : 355.0; -fx-background-radius : 10; -fx-padding : 10;");
        teamOneLabel.setPrefWidth(535);

        Label teamTwoLabel = new Label("Team Name : " + teamTwoName.getClubName());
        teamTwoLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 1200.0; -fx-translate-y : 355.0; -fx-background-radius : 10; -fx-padding : 10;");
        teamTwoLabel.setPrefWidth(545);

        Label oneGoalLabel = new Label("Goal Scored :  " + randomScoredOne);
        oneGoalLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 650.0; -fx-translate-y : 455.0; -fx-background-radius : 10; -fx-padding : 10;");
        oneGoalLabel.setPrefWidth(230);

        Label twoGoalLabel = new Label("Goal Scored :  " + randomScoredTwo);
        twoGoalLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 1200.0; -fx-translate-y : 455.0; -fx-background-radius : 10; -fx-padding : 10;");
        twoGoalLabel.setPrefWidth(230);

        Label oneReceivedLabel = new Label("Goal Received :  " + randomScoredTwo);
        oneReceivedLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 935.0; -fx-translate-y : 510.0; -fx-background-radius : 10; -fx-padding : 10;");
        oneReceivedLabel.setPrefWidth(230);

        Label twoReceivedLabel = new Label("Goal Received :  " + randomScoredOne);
        twoReceivedLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 1495.0; -fx-translate-y : 510.0; -fx-background-radius : 10; -fx-padding : 10;");
        twoReceivedLabel.setPrefWidth(230);

        Label oneDifferenceLabel = new Label("Goal Difference :  " + (randomScoredOne - randomScoredTwo));
        oneDifferenceLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 650.0; -fx-translate-y : 615.0; -fx-background-radius : 10; -fx-padding : 10;");
        oneDifferenceLabel.setPrefWidth(230);

        Label twoDifferenceLabel = new Label("Goal Difference :  " + (randomScoredTwo - randomScoredOne));
        twoDifferenceLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 1200.0; -fx-translate-y : 615.0; -fx-background-radius : 10; -fx-padding : 10;");
        twoDifferenceLabel.setPrefWidth(230);

        Label randomDateLabel = new Label("Date :  " + formattedRandomDate);
        randomDateLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                "-fx-translate-x : 295.0; -fx-translate-y : 630.0; -fx-background-radius : 10; -fx-padding : 10;");
        randomDateLabel.setPrefWidth(200);

        randomMatchPane.getChildren().addAll(winTeamName, teamOneLabel, teamTwoLabel, oneGoalLabel, twoGoalLabel, oneReceivedLabel, twoReceivedLabel, oneDifferenceLabel, twoDifferenceLabel, randomDateLabel);
    }

    /*
     *Use to display played matches
     *Played matches are sorted into ascending oder
     *Played matches are display in scrollPane
     */
    private void playedMatchesPart(Stage primStage, Scene primScene) {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);

        AnchorPane playedMatchAncPane = ancPane();

        AnchorPane subPlayedAncPane = new AnchorPane();

        Button playedBackBtn = backBtn();
        playedBackBtn.setOnAction(event -> primStage.setScene(primScene));

        Button playedExitToBtn = exitBtn();
        playedExitToBtn.setOnAction(event -> primStage.close());

        Label playedMatchMainTitle = new Label("PREMIER LEAGUE Played Matches - " + seasonYear);
        playedMatchMainTitle.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-font-size : 25; -fx-background-radius : 10; -fx-translate-x : 660.0; -fx-translate-y : 20.0; -fx-padding: 10;");
        playedMatchMainTitle.setAlignment(Pos.CENTER);

        Label playedEmpty = new Label("***The Premier League Clubs hasn't Played Matches!!***");
        playedEmpty.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 24; " +
                "-fx-background-radius : 10; -fx-translate-x : 580.0; -fx-translate-y : 400.0; -fx-padding : 20; -fx-opacity: 0.8;");
        playedEmpty.setVisible(false);

        if ((guiPlayedMatchList.size()) == 0){
            playedEmpty.setVisible(true);
        } else {
            ArrayList<FootballClub> dateSortedList = new ArrayList<>();
            for (SportsClub clubList : guiPlayedMatchList) {
                FootballClub tempClubList = (FootballClub) clubList;
                dateSortedList.add(tempClubList);
            }
            dateSortedList.sort(new DatePlayedCompare());
            displayStatisticsLabel(subPlayedAncPane, dateSortedList);
        }
        ScrollPane playedMatchScrPane = new ScrollPane(subPlayedAncPane);
        playedMatchScrPane.setStyle("-fx-padding : 0; -fx-background : transparent;  -fx-background-color : transparent; -fx-translate-x : 75.0; -fx-translate-y : 100.0; -fx-opacity : 0.9;");
        playedMatchScrPane.pannableProperty().set(true);
        playedMatchScrPane.setPrefWidth(1775);
        playedMatchScrPane.setMinHeight(800);

        playedMatchAncPane.getChildren().addAll(playedBackBtn, playedExitToBtn, playedMatchMainTitle , playedMatchScrPane, playedEmpty);
        Scene playedMatchScene = new Scene(playedMatchAncPane,1920,1000);
        primStage.setScene(playedMatchScene);
    }

    /*
     *Use to search played matches using date
     *Played matches are display in scrollPane
     *Created textBox and button for searching
     *If playedMatchList empty search button will be disable
     */
    private void searchMatchesPart(Stage primStage, Scene primScene) {
        Calendar currentYear = Calendar.getInstance();
        int seasonYear = currentYear.get(Calendar.YEAR);

        AnchorPane searchAncPane = ancPane();

        AnchorPane subSearchAncPane = new AnchorPane();

        Button searchBackBtn = backBtn();
        searchBackBtn.setOnAction(event -> primStage.setScene(primScene));

        Button searchExitToBtn = exitBtn();
        searchExitToBtn.setOnAction(event -> primStage.close());

        Label searchMatchMainTitle = new Label("Search PREMIER LEAGUE Played Matches - " + seasonYear);
        searchMatchMainTitle.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                "-fx-font-size : 25; -fx-background-radius : 10; -fx-translate-x : 630.0; -fx-translate-y : 20.0; -fx-padding: 10;");
        searchMatchMainTitle.setAlignment(Pos.CENTER);

        Label searchEmpty = new Label("***The Premier League Clubs hasn't Played Matches for Search!!***");
        searchEmpty.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 24; " +
                "-fx-background-radius : 10; -fx-translate-x : 520.0; -fx-translate-y : 400.0; -fx-padding : 20; -fx-opacity: 0.8;");
        searchEmpty.setVisible(false);

        Label searchMatchLabel = new Label("Please Enter Date : ");
        searchMatchLabel.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold;  " +
                "-fx-background-radius : 2; -fx-translate-x : 150.0; -fx-translate-y : 125.0;");
        searchMatchLabel.setPrefHeight(40);

        TextField searchMatchText = new TextField();
        searchMatchText.setPromptText("(DD/MM/YYYY)");
        searchMatchText.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold;  " +
                "-fx-background-radius : 2; -fx-translate-x : 325.0; -fx-translate-y : 125.0; -fx-padding : 5;");
        searchMatchText.setAlignment(Pos.CENTER);
        searchMatchText.setPrefSize(220,40);

        Button searchMatchBtn = new Button("SEARCH");
        searchMatchBtn.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold;  " +
                "-fx-background-radius : 5; -fx-translate-x : 545.0; -fx-translate-y : 125.0; -fx-border-color : #ffffff; -fx-border-radius : 2;");
        searchMatchBtn.setPrefHeight(40);

        if ((guiPlayedMatchList.size()) == 0){
            searchEmpty.setVisible(true);
            searchMatchBtn.setDisable(true);
        } else {
            searchMatchBtn.setOnAction(event -> searchMatchesDisplay(subSearchAncPane, searchMatchText));
        }
        ScrollPane searchScrPane = new ScrollPane(subSearchAncPane);
        searchScrPane.setStyle("-fx-padding : 0; -fx-background : transparent;  -fx-background-color : transparent; -fx-translate-x : 75.0; -fx-translate-y : 175.0; -fx-opacity : 0.9;");
        searchScrPane.pannableProperty().set(true);
        searchScrPane.setPrefWidth(1775);
        searchScrPane.setMinHeight(800);

        searchAncPane.getChildren().addAll(searchBackBtn, searchExitToBtn, searchMatchMainTitle , searchScrPane, searchEmpty, searchMatchLabel, searchMatchText, searchMatchBtn);
        Scene playedMatchScene = new Scene(searchAncPane,1920,1000);
        primStage.setScene(playedMatchScene);
    }

    //Use to search played matches according to input date
    private void searchMatchesDisplay(AnchorPane subSearchAncPane, TextField searchMatchText){
        ArrayList<FootballClub> tempSearchList = new ArrayList<>();

        String searchText = searchMatchText.getText();
        searchMatchText.clear();
        if (managerObj.validatingDate(searchText)) {
            for (SportsClub dateSearchList : guiPlayedMatchList) {
                FootballClub tempDate = (FootballClub) dateSearchList;
                if ((tempDate.getPlayedMatchDate()).equals(searchText))
                    tempSearchList.add(tempDate);
            }
            if ((tempSearchList.size()) == 0) {
                Label dateEmpty = new Label("***The Premier League Clubs hasn't Played Matches for Search Date!!***");
                dateEmpty.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 24; " +
                        "-fx-background-radius : 10; -fx-translate-x : 450.0; -fx-translate-y : 200.0; -fx-padding : 20; -fx-opacity: 0.8;");
                subSearchAncPane.getChildren().add(dateEmpty);
            }else
                displayStatisticsLabel(subSearchAncPane, tempSearchList);
        }else {
            Stage conBox = new Stage();                                 //confirmation box
            conBox.setTitle("Search Played Match");
            Button confirmBtn = new Button("Ok");
            confirmBtn.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-size : 18; -fx-font-weight : bolder;");
            Label notifyMsg = new Label("Please Enter \nThe Valid Date!!");
            notifyMsg.setStyle("-fx-text-fill : #4d004d; -fx-font-size : 20; -fx-font-weight : bolder; -fx-text-alignment : 'CENTER';");
            VBox notifyBox = new VBox();
            notifyBox.setStyle("-fx-background-color : #d9d9d9;");
            notifyBox.setSpacing(20);
            notifyBox.setAlignment(Pos.CENTER);
            notifyBox.setPadding(new Insets(20));
            notifyBox.getChildren().addAll(notifyMsg, confirmBtn);

            confirmBtn.setOnAction(event1 -> conBox.close());
            conBox.setScene(new Scene(notifyBox, 200, 175));
            conBox.showAndWait();
        }
    }

    //Use to initialize statistics to played matches and search played matches using labels
    private void displayStatisticsLabel(AnchorPane subAncPane, ArrayList<FootballClub> tempPlayedList) {
        double templateHeight = 10.0;
        double dateHeight = 111.0;
        double upperLabelHeight = 20.0;
        double lowerLabelHeight = 165.0;

        for (FootballClub tempPlayedClub : tempPlayedList) {
            Label backgroundTemplate = new Label();
            backgroundTemplate.setStyle("-fx-background-color : #ffffff; -fx-background-radius : 15; -fx-translate-x : 85.0;");
            backgroundTemplate.setPrefSize(1610, 250);
            backgroundTemplate.setTranslateY(templateHeight);

            Label playedDateLabel = new Label("Date :  " + tempPlayedClub.getPlayedMatchDate());
            playedDateLabel.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                    "-fx-translate-x : 95.0; -fx-background-radius : 10; -fx-padding : 10;");
            playedDateLabel.setPrefWidth(200);
            playedDateLabel.setTranslateY(dateHeight);

            Label playedTeamOne = new Label("Team Name : " + tempPlayedClub.getClubName());
            playedTeamOne.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-translate-x : 290.0; -fx-background-radius : 10; -fx-padding : 30;");
            playedTeamOne.setPrefWidth(500);
            playedTeamOne.setTranslateY(upperLabelHeight);

            Label statisticsTeamOne = new Label("Goal Scored :  " + tempPlayedClub.getNumOfGoalsScored() + "\t\tGoal Difference :  " + tempPlayedClub.getGoalDifference()
                    + "\nGoal Received :  " + tempPlayedClub.getNumOfGoalsReceived() + "\t\tPoints :  " + tempPlayedClub.getNumOfPoints());
            statisticsTeamOne.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-translate-x : 792.0; -fx-background-radius : 10; -fx-padding : 18;");
            statisticsTeamOne.setPrefWidth(500);
            statisticsTeamOne.setTranslateY(upperLabelHeight);

            Label playedTeamTwo = new Label("Team Name : " + tempPlayedClub.getRival());
            playedTeamTwo.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-translate-x : 290.0; -fx-background-radius : 10; -fx-padding : 30;");
            playedTeamTwo.setPrefWidth(500);
            playedTeamTwo.setTranslateY(lowerLabelHeight);

            int teamTwoDifference = ((tempPlayedClub.getNumOfGoalsReceived()) - (tempPlayedClub.getNumOfGoalsScored()));
            int teamTwoPoints = 0;
            if ((tempPlayedClub.getNumOfPoints()) == 0) {
                teamTwoPoints = 3;
            } else if ((tempPlayedClub.getNumOfPoints()) == 1)
                teamTwoPoints = 1;

            Label statisticsTeamTwo = new Label("Goal Scored :  " + tempPlayedClub.getNumOfGoalsReceived() + "\t\tGoal Difference :  " + teamTwoDifference
                    + "\nGoal Received :  " + tempPlayedClub.getNumOfGoalsScored() + "\t\tPoints :  " + teamTwoPoints);
            statisticsTeamTwo.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; " +
                    "-fx-translate-x : 792.0; -fx-background-radius : 10; -fx-padding : 18;");
            statisticsTeamTwo.setPrefWidth(500);
            statisticsTeamTwo.setTranslateY(lowerLabelHeight);

            Label playedWinTeam = new Label();
            playedWinTeam.setStyle("-fx-background-color : #4d004d; -fx-text-fill : #ffffff; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-font-size : 18; " +
                    "-fx-translate-x : 1285.0; -fx-background-radius : 10; -fx-padding : 10;");
            playedWinTeam.setPrefWidth(400);
            playedWinTeam.setAlignment(Pos.CENTER);
            playedWinTeam.setTranslateY(dateHeight);

            if (((tempPlayedClub.getDefeats()) == 0) && ((tempPlayedClub.getDraws()) == 0)) {
                playedWinTeam.setText(tempPlayedClub.getClubName() + " Won the Match");
            } else if (((tempPlayedClub.getWin()) == 0) && ((tempPlayedClub.getDraws()) == 0)) {
                playedWinTeam.setText(tempPlayedClub.getRival() + " Won the Match");
            } else
                playedWinTeam.setText("Played Match was Draw");

            subAncPane.getChildren().addAll(backgroundTemplate, playedDateLabel, playedTeamOne, statisticsTeamOne, playedTeamTwo, statisticsTeamTwo, playedWinTeam);
            templateHeight += 270.0;
            dateHeight += 270.0;
            upperLabelHeight += 270.0;
            lowerLabelHeight += 270.0;
        }
        subAncPane.setMinHeight(templateHeight);
    }

    //Defined method to initialize background image
    private static AnchorPane ancPane() {
        AnchorPane ancPane = new AnchorPane();
        try {
            Image mainMenuImage = new Image("backgroundImg/subTopic.png");
            BackgroundSize bgrSize = new BackgroundSize(1920, 1150, false, false, false, false);
            BackgroundImage bgrMainMenuImage = new BackgroundImage(mainMenuImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgrSize);
            Background background = new Background(bgrMainMenuImage);
            ancPane.setBackground(background);
            ancPane.setStyle("-fx-text-fill : black; -fx-font-weight : bold; -fx-font-size : 16;");
        } catch (IllegalArgumentException e) {
            ancPane.setStyle("-fx-text-fill : black; -fx-font-weight : bold; -fx-font-size: 16; -fx-background-color : #80d4ff;");
            System.out.println(" ***Background Image Not Found!!***");
        }
        return ancPane;
    }

    //Defined method to back button
    private static Button backBtn() {
        Button backBtn = new Button(" <<- Back");
        backBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-background-radius : 10;");
        backBtn.setPrefSize(120,30);
        backBtn.setTranslateX(10.0);
        backBtn.setTranslateY(20.0);
        return backBtn;
    }

    //Defined method to exit button
    private static Button exitBtn() {
        Button exitBtn = new Button("Exit !!");
        exitBtn.setStyle("-fx-background-color : #ffffff; -fx-text-fill : #4d004d; -fx-font-family : 'Arial Black'; -fx-font-weight : bold; -fx-background-radius : 10;");
        exitBtn.setPrefSize(100,30);
        exitBtn.setTranslateX(1800.0);
        exitBtn.setTranslateY(20.0);
        return exitBtn;
    }
}

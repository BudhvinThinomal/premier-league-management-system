package content.services;

import content.entities.FootballClub;
import content.entities.SportsClub;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Calendar;
import java.util.Date;


public class PremierLeagueManager implements LeagueManager {
    //declaring the attributes and array lists
    private ArrayList<SportsClub> footballClubList = new ArrayList<>();
    private ArrayList<SportsClub> playedMatchList = new ArrayList<>();
    private static final int teamLimit = 20;
    private int availableTeamCount = 20;

    /*
     *Use to create football club in premier league
     *Football clubs add to the array list as objects
     */
    @Override
    public void addFootballClub(SportsClub sportsClub) {
        if ((footballClubList.size() + 1) > teamLimit) {
            System.out.println("\n ***Due to maximum team limit has reached " + sportsClub.getClubName() + " football club cannot be included to the Premier League!!***\n");
        } else {
            for (SportsClub footballClub : footballClubList) {
                if ((sportsClub.getClubName()).equals(footballClub.getClubName())) {
                    System.out.println("\n ***This " + sportsClub.getClubName() + " football club already added to the Premier League!!***\n");
                    return;
                }
            }
            footballClubList.add(sportsClub);       //add values to arraylist
            availableTeamCount -= 1;
            System.out.println("\n==> "+sportsClub.getClubName() + " football club is successfully added to the Premier League!!\n");
            System.out.println("=> Available free space for football teams in Premier League : "+availableTeamCount+"\n");
        }
    }

    /*
     *Use to delete football club from premier league
     *Football clubs remove from the array list
     */
    @Override
    public void relegateFootballClub(String footballClubName) {
        Scanner scan = new Scanner(System.in);
        if (footballClubList.size() == 0) {
            System.out.println("\n ***The Premier League is already empty!!"+ footballClubName +" isn't an existing football club in Premier League!!***\n");
        } else {
            for (SportsClub footballClub : footballClubList) {
                if (footballClubName.equals(footballClub.getClubName())) {
                    System.out.println("\n==> Football club statistics: ");
                    System.out.println("=> Football club name: " + footballClub.getClubName());
                    System.out.println("=> Football club location: " + footballClub.getClubLocation());
                    System.out.println("=> Football club chairman: " + footballClub.getChairman());
                    System.out.println("=> Football club founded year: " + footballClub.getFoundedYear());
                    System.out.println("\n ***WARNING!!***\n==> If you delete this football club from the Premier League all the data related to it 'll be deleted!!\n=> If you wish to continue this process please enter \"Y\" OR if you wish to stop this process please enter \"N\" : ");
                    String conformation = scan.next().toUpperCase();        //turn the string value to uppercase
                    switch (conformation) {
                        case "Y":
                            footballClubList.remove(footballClub);             //remove club from arraylist
                            System.out.println("\n==> "+ footballClubName + " football club deleted successfully from Premier League!!");
                            availableTeamCount += 1;
                            System.out.println("=> Available free space for football teams in Premier League : " + availableTeamCount + "\n");
                            return;
                        case "N":
                            return;
                        default:
                            System.out.println(" ***Please enter the given command!!***");
                            return;
                    }
                }
            }
            System.out.println("\n ***\""+ footballClubName +"\" isn't an existing football club in Premier League!!***\n");
        }
    }

    /*
     *Use to display football club in premier league
     *Football club statistics get from arraylist
     *Use get methods to get statistics
     */
    @Override
    public void displaySelectedFootballClubStatics(String footballClubName) {
        if (footballClubList.size() == 0) {
            System.out.println("\n ***The Premier League is already empty!!***\n");
        } else {
            for (SportsClub footballClub : footballClubList) {
                if (footballClubName.equals(footballClub.getClubName())) {
                    System.out.println("\n==> Football club statistics: ");
                    System.out.println("=> Football club name: " + footballClub.getClubName());
                    System.out.println("=> Football club location: " + footballClub.getClubLocation());
                    System.out.println("=> Football club chairman: " + footballClub.getChairman());
                    System.out.println("=> Football club founded year: " + footballClub.getFoundedYear());
                    FootballClub tempClub = (FootballClub) footballClub;   //create variable to access subclass methods

                    String tableFormat = "|          %-10d|          %-10d|          %-10d|          %-10d|          %-10d|          %-10d|          %-10d|          %-10d|%n";
                    System.out.println("\n|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|");
                    System.out.println("|   MATCHES PLAYED   |         WIN        |       DEFEATS      |       DRAWS        |    GOALS SCORED    |   GOALS RECEIVED   |   GOAL DIFFERENCE  |       POINTS       |");
                    System.out.println("|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|");
                    System.out.format(tableFormat,tempClub.getNumOfMatchesPlayed(),tempClub.getWin(),tempClub.getDefeats(),tempClub.getDraws(),tempClub.getNumOfGoalsScored(), tempClub.getNumOfGoalsReceived(),tempClub.getGoalDifference(),tempClub.getNumOfPoints());
                    System.out.println("|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|");
                    return;
                }
            }
            System.out.println("\n ****\""+footballClubName +"\" isn't an existing football club in Premier League!!***\n");
        }
    }

    /*
     *Use to display premier league table
     *Football clubs statistics get from clone array list
     *Use get methods to get statistics
     */
    @Override
    public void displayPremierLeagueTable() {
        //add values to temporary arraylist
        ArrayList<FootballClub> sortedFootballClubList = new ArrayList<>();
        for (SportsClub sportsClub : footballClubList) {
            FootballClub sortClub = (FootballClub) sportsClub;
            sortedFootballClubList.add(sortClub);
        }

        if (footballClubList.size() == 0) {
            System.out.println("\n ***The Premier League Table is already empty!!***\n");
        } else {
            sortedFootballClubList.sort(new PointsCompare());       //sort according to points using comparator class

            String tableFormat = "|%-18s  |%-18s  |%-18s  |  %-18s|        %-10d|        %-10d|        %-10d|        %-10d|        %-10d|        %-10d|        %-10d|        %-10d|%n";
            System.out.println("|--------------------|--------------------|--------------------|--------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|");
            System.out.println("|      FC NAME       |    CLUB LOCATION   |      CHAIRMAN      |    FOUNDED YEAR    |  MATCHES PLAYED  |       WIN        |      DEFEATS     |      DRAWS       |   GOALS SCORED   |  GOALS RECEIVED  | GOAL DIFFERENCE  |      POINTS      |");
            System.out.println("|--------------------|--------------------|--------------------|--------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|");
            for (SportsClub footballClub : sortedFootballClubList) {
                FootballClub tempClub = (FootballClub) footballClub;   //create variable to access subclass methods

                System.out.format(tableFormat,footballClub.getClubName(), footballClub.getClubLocation(), footballClub.getChairman(), footballClub.getFoundedYear(), tempClub.getNumOfMatchesPlayed(), tempClub.getWin(), tempClub.getDefeats(), tempClub.getDraws(), tempClub.getNumOfGoalsScored(), +tempClub.getNumOfGoalsReceived(), tempClub.getGoalDifference(), tempClub.getNumOfPoints());
                System.out.println("|--------------------|--------------------|--------------------|--------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|------------------|");
            }
        }
    }

    /*
     *Use to add played match with date to premier league table and update team statistics
     *Football club statistics get from arraylist
     *Use get methods to get statistics
     *Premier league table statistics update according to the played match statistics
     */
    @Override
    public void addPlayedMatch(String firstFootballClubName,String secondFootballClubName) {
        Calendar currentYear = Calendar.getInstance();
        if (footballClubList.size() <= 1) {
            System.out.println("\n ***The Premier League hasn't enough clubs for play match!!***\n ***Therefore can't play matches between "+ firstFootballClubName +" & "+ secondFootballClubName +"***\n");
        } else {
            for (SportsClub firstFootballClub : footballClubList){
                if (firstFootballClubName.equals(firstFootballClub.getClubName())){

                    for (SportsClub secondFootballClub : footballClubList){
                        if (secondFootballClubName.equals(secondFootballClub.getClubName())) {
                            System.out.println("==> "+ firstFootballClubName +" Vs "+secondFootballClubName+"\n");
                            System.out.println("=> Please enter the date the game was played");

                            while (true) {
                                FootballClub addDetailsTeamOne = (FootballClub) firstFootballClub;
                                FootballClub addDetailsTeamTwo = (FootballClub) secondFootballClub;
                                Scanner scan = new Scanner(System.in);
                                int firstWin = 0;
                                int secondWin = 0;
                                int firstDefeat = 0;
                                int secondDefeat = 0;
                                int firstDraw = 0;
                                int secondDraw = 0;
                                int firstGoalsScored;
                                int secondGoalsReceived;
                                int firstGoalsReceived;
                                int secondGoalsScored;
                                int firstPoints;
                                int secondPoints;
                                int firstNumOfMatchPlayed;
                                int secondNumOfMatchPlayed;
                                try {
                                    int playedMatchYear = currentYear.get(Calendar.YEAR);

                                    System.out.println("=> Please enter the month (only month ex: xx) : ");
                                    int playedMatchMonth = scan.nextInt();

                                    System.out.println("=> Please enter the day (only day ex: xx) : ");
                                    int playedMatchDay = scan.nextInt();

                                    String formattedDate = playedMatchDay+"/"+playedMatchMonth+"/"+playedMatchYear;
                                    if (validatingDate(formattedDate)) {

                                        System.out.println("=> Please enter \"W\" if the "+firstFootballClubName+" football club \"Won\" the match OR\n" +
                                                "=> Please enter \"L\" if the "+firstFootballClubName+" football club \"Lose\" the match OR\n" +
                                                "=> Please enter \"D\" if the match was draw : ");
                                        String result = scan.next().toUpperCase();
                                        if (result.equals("W") || result.equals("L") || result.equals("D")){
                                            if (result.equals("W"))
                                                firstWin = secondDefeat = 1 ;
                                            else if (result.equals("L"))
                                                secondWin = firstDefeat = 1;
                                            else
                                                firstDraw = secondDraw = 1;
                                        }else{
                                            System.out.println(" ***Please enter the given command!!***");
                                            continue;
                                        }
                                        System.out.println("=> Please enter the number of Goals Scored by the "+firstFootballClubName+" football club:");
                                        firstGoalsScored = secondGoalsReceived = scan.nextInt();

                                        System.out.println("=> Please enter the number of Goals Received by the "+firstFootballClubName+" football club:");
                                        firstGoalsReceived = secondGoalsScored = scan.nextInt();

                                        if ((firstWin == 1 && (firstGoalsScored <= firstGoalsReceived)) || (firstDefeat == 1 && (firstGoalsReceived <= firstGoalsScored)) || (firstDraw == 1 && !(firstGoalsReceived == firstGoalsScored))) {
                                            System.out.println(" ***Entered data doesn't match!!***\n");
                                            continue;
                                        }

                                        firstPoints = (firstWin == 1)? 3 : 0;
                                        secondPoints = (secondWin == 1)? 3 : 0;
                                        if (firstDraw == 1)
                                            firstPoints = secondPoints = 1;
                                        firstNumOfMatchPlayed = secondNumOfMatchPlayed = 1;

                                        //add played match values to new list
                                        SportsClub addMatch = new FootballClub((firstFootballClub.getClubName()),(firstFootballClub.getClubLocation()),(firstFootballClub.getChairman()),(firstFootballClub.getFoundedYear()),firstWin,firstDraw,firstDefeat,firstGoalsScored,firstGoalsReceived,firstPoints,firstNumOfMatchPlayed,formattedDate,secondFootballClubName);
                                        playedMatchList.add(addMatch);

                                        //update statistics of the match played clubs
                                        addDetailsTeamOne.setWin(firstWin);
                                        addDetailsTeamTwo.setWin(secondWin);
                                        addDetailsTeamOne.setDefeats(firstDefeat);
                                        addDetailsTeamTwo.setDefeats(secondDefeat);
                                        addDetailsTeamOne.setDraws(firstDraw);
                                        addDetailsTeamTwo.setDraws(secondDraw);
                                        addDetailsTeamOne.setNumOfGoalsScored(firstGoalsScored);
                                        addDetailsTeamTwo.setNumOfGoalsScored(secondGoalsScored);
                                        addDetailsTeamOne.setNumOfGoalsReceived(firstGoalsReceived);
                                        addDetailsTeamTwo.setNumOfGoalsReceived(secondGoalsReceived);
                                        addDetailsTeamOne.setNumOfPoints(firstPoints);
                                        addDetailsTeamTwo.setNumOfPoints(secondPoints);
                                        addDetailsTeamOne.setNumOfMatchesPlayed(firstNumOfMatchPlayed);
                                        addDetailsTeamTwo.setNumOfMatchesPlayed(secondNumOfMatchPlayed);
                                        System.out.println("\n=> Played match successfully added to the Premier League!!\n");
                                        return;
                                    } else
                                        System.out.println(" ***Please enter the valid date!!***");
                                }catch (InputMismatchException e){
                                    System.out.println(" ***Please enter only numbers!!***\n    ERROR : " + e +"\n");
                                }
                            }

                        }
                    }

                }
            }
            System.out.println(" ***One or both teams aren't include in the Premier League!!***");
        }
    }

    /*
     *Use to save all the teams details of premier league to file
     *Team details save as byte stream
     */
    @Override
    public void saveDetails() {
        try {
            File footballClubDetails = new File("src/main/resources/dataFolder/Football_Clubs.txt");

            FileOutputStream fOutputStr = new FileOutputStream(footballClubDetails);
            ObjectOutputStream oOutputStr = new ObjectOutputStream(fOutputStr);

            //write footballClubList to file
            for (SportsClub fbC : footballClubList) {
                oOutputStr.writeObject(fbC);
            }
            //write playedMatchList to file
            for (SportsClub fbC : playedMatchList) {
                oOutputStr.writeObject(fbC);
            }
            oOutputStr.close();
            fOutputStr.close();
            System.out.println("\n==> Data stored successfully!!");
        }catch (IOException e) {
            System.out.println("\n ***Error*** : " + e);
        }
    }

    /*
     *Use to load all the teams details of premier league from file
     *Team details add to array list as objects
     */
    @Override
    public void loadDetails() {
        ArrayList <SportsClub> temporaryLoadList = new ArrayList<>();
        try {
            FileInputStream fInputStr = new FileInputStream("src/main/resources/dataFolder/Football_Clubs.txt");
            ObjectInputStream oInputStr = new ObjectInputStream(fInputStr);

            while (true) {
                try {
                    //add all values to one temporary arraylist
                    SportsClub fbC = (SportsClub) oInputStr.readObject();
                    temporaryLoadList.add(fbC);

                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            fInputStr.close();
            oInputStr.close();

            //add temporary arraylist values to footballClub arraylist and playedMatch arraylist
            for (SportsClub footballClubs : temporaryLoadList){
                FootballClub temporary = (FootballClub) footballClubs;

                if (temporary.getRival() == null)
                    footballClubList.add(footballClubs);
                else
                    playedMatchList.add(footballClubs);
            }
            availableTeamCount -= footballClubList.size();
            System.out.println("\n==> Data load successfully!!");
        } catch (IOException e) {
            System.out.println("\n ***No existing data to load!!***");
        }
    }

    /*
     *Use to display JavaFx GUI by calling method
     */
    @Override
    public void graphicalUserInterface() {
        //Initialize object to communicate with GraphicalUserInterface class
        GraphicalUserInterface graphicalUserInterfaceObj = new GraphicalUserInterface(footballClubList, playedMatchList);
        graphicalUserInterfaceObj.graphicalUserInterfaceMenu();
    }

    /*
     *Use to initialize values to arraylists and save data
     */
    public void updatePlayedMatch(ArrayList<SportsClub> guiFootballClubList, ArrayList<SportsClub> guiPlayedMatchList){
        footballClubList = guiFootballClubList;
        playedMatchList = guiPlayedMatchList;
        saveDetails();
    }

    //Use to validate date
    @Override
    public boolean validatingDate(String stringFormattedDate) {
        Date currentDate = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/y");
        formattedDate.setLenient(false);
        try {
            Date inputDate = formattedDate.parse(stringFormattedDate);
            if (inputDate.compareTo(currentDate) > 0)
                return false;
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

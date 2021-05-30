package content.services;

import content.entities.SportsClub;
import content.entities.FootballClub;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class AngularInterface {
    //Declaring the arraylists
    private static ArrayList<SportsClub> angularFootballClubList = new ArrayList<>();
    private static ArrayList<SportsClub> angularPlayedMatchList = new ArrayList<>();
    //Initialize object to communicate with PremierLeagueManager class
    PremierLeagueManager managerObj = new PremierLeagueManager();

    /*
     *Use to remove data from arraylists
     *Use to initialize arraylists
     */
    public void angularUserInterfaceBasic() {
        angularFootballClubList = new ArrayList<>();
        angularPlayedMatchList = new ArrayList<>();
        loadDetails();
    }

    /*
     *Use to display premier league table
     *Sorted according to number of points
     *Can be sorted according to number of goals scored and number of wins
     */
    //Use to sort according to number of points using comparator class
    public ArrayList<FootballClub> sortListToPoints() {
        angularUserInterfaceBasic();
        ArrayList<FootballClub> sortedFootballClubList = new ArrayList<>();
        for (SportsClub clubList : angularFootballClubList) {
            FootballClub tempClubList = (FootballClub) clubList;
            sortedFootballClubList.add(tempClubList);
        }
        //sort according to points using comparator class
        sortedFootballClubList.sort(new PointsCompare());
        return sortedFootballClubList;
    }

    //Use to sort according to number of goals scored using comparator class
    public ArrayList<FootballClub> sortListToGoals() {
        angularUserInterfaceBasic();
        ArrayList<FootballClub> sortedFootballClubList = new ArrayList<>();
        for (SportsClub clubList : angularFootballClubList) {
            FootballClub tempClubList = (FootballClub) clubList;
            sortedFootballClubList.add(tempClubList);
        }
        //sort according to points using comparator class
        sortedFootballClubList.sort(new GoalScoredCompare());
        return sortedFootballClubList;
    }

    //Use to sort according to number of wins using comparator class
    public ArrayList<FootballClub> sortListToWins() {
        angularUserInterfaceBasic();
        ArrayList<FootballClub> sortedFootballClubList = new ArrayList<>();
        for (SportsClub clubList : angularFootballClubList) {
            FootballClub tempClubList = (FootballClub) clubList;
            sortedFootballClubList.add(tempClubList);
        }
        //sort according to points using comparator class
        sortedFootballClubList.sort(new MatchWinCompare());
        return sortedFootballClubList;
    }

    /*
     *Use to display random played match
     *Match played between random teams on randomly generated date
     *Statistics also generated randomly
     */
    public ArrayList<FootballClub> randomPlayedMatchPart() {
        angularUserInterfaceBasic();
        ArrayList<FootballClub> tempRandomMatch = new ArrayList<>();
        if ((angularFootballClubList.size()) >= 2) {
            Calendar currentYear = Calendar.getInstance();
            int seasonYear = currentYear.get(Calendar.YEAR);
            int randomTeamOne = (int) (Math.random() * angularFootballClubList.size());
            int randomTeamTwo;
            do {
                randomTeamTwo = (int) (Math.random() * angularFootballClubList.size());
            } while (randomTeamOne == randomTeamTwo);

            FootballClub teamOneName = (FootballClub) angularFootballClubList.get(randomTeamOne);
            FootballClub teamTwoName = (FootballClub) angularFootballClubList.get(randomTeamTwo);
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

            if (randomScoredOne > randomScoredTwo) {
                firstWin = secondDefeat = 1;
                firstPoints = 3;
            } else if (randomScoredOne < randomScoredTwo) {
                secondWin = firstDefeat = 1;
                secondPoints = 3;
            } else {
                firstDraw = secondDraw = 1;
                firstPoints = secondPoints = 1;
            }
            firstNumOfMatchPlayed = secondNumOfMatchPlayed = 1;
            SportsClub addPlayedMatch = new FootballClub((teamOneName.getClubName()), (teamOneName.getClubLocation()), (teamOneName.getChairman()), (teamOneName.getFoundedYear()), firstWin, firstDraw, firstDefeat, randomScoredOne, randomScoredTwo, firstPoints, firstNumOfMatchPlayed, formattedRandomDate, (teamTwoName.getClubName()));
            angularPlayedMatchList.add(addPlayedMatch);

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
            managerObj.updatePlayedMatch(angularFootballClubList, angularPlayedMatchList);

            tempRandomMatch.add((FootballClub) addPlayedMatch);
        }
        return tempRandomMatch;
    }

    /*
     *Use to display played matches & search played matches
     *Played matches are sorted into ascending oder
     */
    public ArrayList<FootballClub> playedMatchesPart() {
        angularUserInterfaceBasic();
        ArrayList<FootballClub> dateSortedList = new ArrayList<>();
        for (SportsClub clubList : angularPlayedMatchList) {
            FootballClub tempClubList = (FootballClub) clubList;
            dateSortedList.add(tempClubList);
        }
        dateSortedList.sort(new DatePlayedCompare());
        return dateSortedList;
    }

    /*
     *Use to load all the teams details of premier league from file
     *Team details add to array list as objects
     */
    public static void loadDetails() {
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

            //add temporary arraylist values to angularFootballClub arraylist and angularPlayedMatch arraylist
            for (SportsClub footballClubs : temporaryLoadList){
                FootballClub temporary = (FootballClub) footballClubs;

                if (temporary.getRival() == null)
                    angularFootballClubList.add(footballClubs);
                else
                    angularPlayedMatchList.add(footballClubs);
            }
            System.out.println("\n==> Data load successfully!!");
        } catch (IOException e) {
            System.out.println("\n ***No existing data to load!!***");
        }
    }

}

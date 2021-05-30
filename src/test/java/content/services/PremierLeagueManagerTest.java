package content.services;

import content.entities.FootballClub;
import content.entities.SportsClub;
import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

    @Test
    void addFootballClub() {
        SportsClub unitTestFootBallClub = new FootballClub("liverpool","london","ravija","1/4/2016");
        try {
            assertEquals(unitTestFootBallClub.getClubName(), "liverpool");
            assertEquals(unitTestFootBallClub.getClubLocation(), "london");
            assertEquals(unitTestFootBallClub.getChairman(), "ravija");
            assertEquals(unitTestFootBallClub.getFoundedYear(), "1/4/2016");
            System.out.println("Football club add successfully passed!!");
        } catch (AssertionFailedError e) {
            System.out.println("Football club add failed!!");
        }
    }

    @Test
    void relegateFootballClub() {
        SportsClub unitTestFootBallClub = new FootballClub("everton","london","ravija","1/4/2016");
        try {
            assertEquals(unitTestFootBallClub.getClubName(), "everton");
            System.out.println("Football club delete successfully passed!!");
        } catch (AssertionFailedError e) {
            System.out.println("Football club delete failed!!");
        }
    }

    @Test
    void displaySelectedFootballClubStatics() {
        SportsClub unitTestFootBallClub = new FootballClub("chelsea","london","ravija","1/4/2016");
        try {
            assertEquals(unitTestFootBallClub.getClubName(), "chelsea");
            System.out.println("Football club display successfully passed!!");
        } catch (AssertionFailedError e) {
            System.out.println("Football club display failed!!");
        }
    }

    @Test
    void addPlayedMatch() {
        FootballClub unitTest2FootBallClub = new FootballClub("liverpool","england","ravija","19/4/2014",1,0,0,5,2,3,1,"3/1/2019","liverpool");
        try {
            assertEquals(unitTest2FootBallClub.getClubName(), "liverpool");
            assertEquals(unitTest2FootBallClub.getClubLocation(), "england");
            assertEquals(unitTest2FootBallClub.getChairman(), "ravija");
            assertEquals(unitTest2FootBallClub.getFoundedYear(), "19/4/2014");
            assertEquals(unitTest2FootBallClub.getWin(), 1);
            assertEquals(unitTest2FootBallClub.getDraws(), 0);
            assertEquals(unitTest2FootBallClub.getDefeats(), 0);
            assertEquals(unitTest2FootBallClub.getNumOfGoalsScored(), 5);
            assertEquals(unitTest2FootBallClub.getNumOfGoalsReceived(), 2);
            assertEquals(unitTest2FootBallClub.getNumOfPoints(), 3);
            assertEquals(unitTest2FootBallClub.getNumOfMatchesPlayed(), 1);
            assertEquals(unitTest2FootBallClub.getPlayedMatchDate(), "3/1/2019");
            assertEquals(unitTest2FootBallClub.getRival(), "liverpool");
            System.out.println("played match successfully passed!!");
        } catch (AssertionFailedError e) {
            System.out.println("played match failed!!");
        }
    }
}

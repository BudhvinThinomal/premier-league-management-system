package content.services;

import content.entities.FootballClub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

//comparator class for compare points
class PointsCompare implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub pointsClub1, FootballClub pointsClub2) {
        if(pointsClub1.getNumOfPoints() < pointsClub2.getNumOfPoints())
            return 1;
        else if (pointsClub1.getNumOfPoints() > pointsClub2.getNumOfPoints())
            return -1;
        else {
            int goalDifference1 = pointsClub1.getNumOfGoalsScored() - pointsClub1.getNumOfGoalsReceived();
            int goalDifference2 = pointsClub2.getNumOfGoalsScored() - pointsClub2.getNumOfGoalsReceived();
            if(goalDifference1 < goalDifference2)
                return 1;
            else if(goalDifference1 > goalDifference2)
                return -1;
            else
                return 0;
        }
    }
}

//comparator class for compare goals scored
class GoalScoredCompare implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub goalScoredClub1, FootballClub goalScoredClub2) {
        if(goalScoredClub1.getNumOfGoalsScored() < goalScoredClub2.getNumOfGoalsScored())
            return 1;
        else if (goalScoredClub1.getNumOfGoalsScored() > goalScoredClub2.getNumOfGoalsScored())
            return -1;
        else {
            int goalDifference1 = goalScoredClub1.getNumOfGoalsScored() - goalScoredClub1.getNumOfGoalsReceived();
            int goalDifference2 = goalScoredClub2.getNumOfGoalsScored() - goalScoredClub2.getNumOfGoalsReceived();
            if(goalDifference1 < goalDifference2)
                return 1;
            else if(goalDifference1 > goalDifference2)
                return -1;
            else
                return 0;
        }
    }
}

//comparator class for compare wins
class MatchWinCompare implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub winClub1, FootballClub winClub2) {
        if(winClub1.getWin() < winClub2.getWin())
            return 1;
        else if (winClub1.getWin() > winClub2.getWin())
            return -1;
        else {
            int goalDifference1 = winClub1.getNumOfGoalsScored() - winClub1.getNumOfGoalsReceived();
            int goalDifference2 = winClub2.getNumOfGoalsScored() - winClub2.getNumOfGoalsReceived();
            if(goalDifference1 < goalDifference2)
                return 1;
            else if(goalDifference1 > goalDifference2)
                return -1;
            else
                return 0;
        }
    }
}

//comparator class for compare dates
class DatePlayedCompare implements Comparator<FootballClub> {

    SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/y");
    @Override
    public int compare(FootballClub clubDate1, FootballClub clubDate2) {
        try {
            return formattedDate.parse(clubDate1.getPlayedMatchDate()).compareTo(formattedDate.parse(clubDate2.getPlayedMatchDate()));
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

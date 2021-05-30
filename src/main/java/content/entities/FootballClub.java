package content.entities;

public class FootballClub extends SportsClub {
    //declaring the attributes
    private int win;
    private int draws;
    private int defeats;
    private int numOfGoalsScored;
    private int numOfGoalsReceived;
    private int goalDifference;
    private int numOfPoints;
    private int numOfMatchesPlayed;
    private String playedMatchDate;
    private String rival;

    //args constructor for add clubs
    public FootballClub(String clubName, String clubLocation, String chairman, String foundedYear) {
        super(clubName, clubLocation, chairman, foundedYear);
    }

    //args constructor for add played matches
    public FootballClub(String clubName, String clubLocation, String chairman, String foundedYear, int win, int draws, int defeats,  int numOfGoalsScored, int numOfGoalsReceived, int numOfPoints, int numOfMatchesPlayed, String playedMatchDate, String rival) {
        super(clubName, clubLocation, chairman, foundedYear);
        this.win = win;
        this.draws = draws;
        this.defeats = defeats;
        this.numOfGoalsScored = numOfGoalsScored;
        this.numOfGoalsReceived = numOfGoalsReceived;
        this.numOfPoints = numOfPoints;
        this.numOfMatchesPlayed = numOfMatchesPlayed;
        this.playedMatchDate = playedMatchDate;
        this.rival = rival;
    }

    //getter method for wins
    public int getWin() {
        return win;
    }

    //setter method for win
    public void setWin(int win) {
        this.win = this.win + win;
    }

    //getter method for draws
    public int getDraws() {
        return draws;
    }

    //setter method for draws
    public void setDraws(int draws) {
        this.draws = this.draws + draws;
    }

    //getter method for defeats
    public int getDefeats() {
        return defeats;
    }

    //setter method for defeats
    public void setDefeats(int defeats) {
        this.defeats = this.defeats + defeats;
    }

    //getter method for number of goals scored
    public int getNumOfGoalsScored() {
        return numOfGoalsScored;
    }

    //setter method for number of goals scored
    public void setNumOfGoalsScored(int numOfGoalsScored) {
        this.numOfGoalsScored = this.numOfGoalsScored + numOfGoalsScored;
    }

    //getter method for number of goals received
    public int getNumOfGoalsReceived() {
        return numOfGoalsReceived;
    }

    //setter method for number of goals received
    public void setNumOfGoalsReceived(int numOfGoalsReceived) {
        this.numOfGoalsReceived = this.numOfGoalsReceived + numOfGoalsReceived;
    }

    //getter method for goal difference
    public int getGoalDifference() {
        goalDifference = numOfGoalsScored - numOfGoalsReceived;
        return goalDifference;
    }

    //getter method for number of points
    public int getNumOfPoints() {
        return numOfPoints;
    }

    //setter method for number of points
    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = this.numOfPoints + numOfPoints;
    }

    //getter method for number of matches played
    public int getNumOfMatchesPlayed() {
        return numOfMatchesPlayed;
    }

    //setter method for number of matches played
    public void setNumOfMatchesPlayed(int numOfMatchesPlayed) {
        this.numOfMatchesPlayed = this.numOfMatchesPlayed + numOfMatchesPlayed;
    }

    //getter method for played match date
    public String getPlayedMatchDate() {
        return playedMatchDate;
    }

    //getter method for get second team who played match
    public String getRival() {
        return rival;
    }

    //called to toString method
    @Override
    public String toString() {
        return super.toString()+ " win = " + win + ", defeats = " + defeats + ", draws = " + draws + ", numOfGoalsScored = " + numOfGoalsScored +  ", numOfGoalsReceived = " + numOfGoalsReceived + ", goalDifference = " + goalDifference + ", numOfPoints = " + numOfPoints + ", numOfMatchesPlayed = " + numOfMatchesPlayed + ", playedMatchDate = "+ playedMatchDate +", rival = "+ rival +"||";
    }
}

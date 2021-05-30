package content.entities;

import java.io.Serializable;

public abstract class SportsClub implements Serializable {
    //declaring the attributes
    private static final long serialVersionUID = 101L;
    private String clubName;
    private String clubLocation;
    private String chairman;
    private String foundedYear;

    //args constructor
    public SportsClub(String clubName, String clubLocation, String chairman, String foundedYear) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.chairman = chairman;
        this.foundedYear = foundedYear;
    }

    //getter method for club name
    public String getClubName() {
        return clubName;
    }

    //getter method for club location
    public String getClubLocation() {
        return clubLocation;
    }

    //getter method for club chairman
    public String getChairman() {
        return chairman;
    }

    //getter method for club found year
    public String getFoundedYear() {
        return foundedYear;
    }

    //called to toString method
    @Override
    public String toString() {
        return "Football Club|| " + "clubName = " + clubName + ", clubLocation = " + clubLocation + ", chairman = " + chairman + ", foundedYear = " + foundedYear + "|";
    }
}

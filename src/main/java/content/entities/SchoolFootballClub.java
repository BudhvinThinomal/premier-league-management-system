package content.entities;

public class SchoolFootballClub extends FootballClub{
    //declaring the attributes
    private String under18ClubName;

    //args constructor
    public SchoolFootballClub(String clubName, String clubLocation, String chairman, String foundedYear, String under18ClubName) {
        super(clubName, clubLocation, chairman, foundedYear);
        this.under18ClubName = under18ClubName;
    }

    //getter and setter methods
    public String getUnder18ClubName() {
        return under18ClubName;
    }

    public void setUnder18ClubName(String under18ClubName) {
        this.under18ClubName = under18ClubName;
    }

    //called to toString method
    @Override
    public String toString() {
        return super.toString()+ "under18ClubName='" + under18ClubName + "||";
    }
}

package content.entities;

public class UniversityFootballClub extends FootballClub{
    //declaring the attributes
    private String under23ClubName;

    //args constructor
    public UniversityFootballClub(String clubName, String clubLocation, String chairman, String foundedYear, String under23ClubName) {
        super(clubName, clubLocation, chairman, foundedYear);
        this.under23ClubName = under23ClubName;
    }

    //getter and setter methods
    public String getUnder23ClubName() {
        return under23ClubName;
    }

    public void setUnder23ClubName(String under23ClubName) {
        this.under23ClubName = under23ClubName;
    }

    //called to toString method
    @Override
    public String toString() {
        return super.toString()+ "under23ClubName='" + under23ClubName + "||";
    }
}

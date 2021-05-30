package content.services;

import content.entities.SportsClub;

//Interface
public interface LeagueManager {
    void addFootballClub(SportsClub sportsClub);
    void relegateFootballClub(String footballClubName);
    void displaySelectedFootballClubStatics(String footballClubName);
    void displayPremierLeagueTable();
    void addPlayedMatch(String firstFootballClubName,String secondFootballClubName);
    void saveDetails();
    void loadDetails();
    void graphicalUserInterface();
    boolean validatingDate(String stringFormattedDate);
}

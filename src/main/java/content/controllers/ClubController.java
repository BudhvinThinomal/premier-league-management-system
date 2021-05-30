package content.controllers;

import content.entities.FootballClub;
import content.services.AngularInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("premier/league")
@CrossOrigin
public class ClubController {
    /*
     *Use to communicate with frontend
     * Use to return required data
     */
    AngularInterface angManagerObj = new AngularInterface();

    @GetMapping("/table/points")
    public ArrayList<FootballClub> premierLeagueTablePoints() {
        return angManagerObj.sortListToPoints();
    }

    @GetMapping("/table/goals")
    public ArrayList<FootballClub> premierLeagueTableGoals() {
        return angManagerObj.sortListToGoals();
    }

    @GetMapping("/table/wins")
    public ArrayList<FootballClub> premierLeagueTableWins() {
        return angManagerObj.sortListToWins();
    }

    @GetMapping("/randomMatch")
    public ArrayList<FootballClub> premierLeagueRandomMatch() {
        return angManagerObj.randomPlayedMatchPart();
    }

    @GetMapping("/playedMatches")
    public ArrayList<FootballClub> premierLeaguePlayedMatches() {
        return angManagerObj.playedMatchesPart();
    }

}

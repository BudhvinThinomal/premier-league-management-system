import { Component, OnInit } from '@angular/core';
import {UiService} from '../ui.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  title = 'The Premier League';
  premierLeagueTable: any = [];
  displayTitle = 'points';

  constructor(private uiService: UiService) { }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.uiService.premierLeagueTablePoints().subscribe((response) => this.premierLeagueTable = response);
  }

  // tslint:disable-next-line:typedef
  public premierLeaguePoints() {
    this.displayTitle = 'points';
    this.uiService.premierLeagueTablePoints().subscribe((response) => this.premierLeagueTable = response);
  }

  // tslint:disable-next-line:typedef
  public premierLeagueGoals() {
    this.displayTitle = 'goals';
    this.uiService.premierLeagueTableGoals().subscribe((response) => this.premierLeagueTable = response);
  }

  // tslint:disable-next-line:typedef
  public premierLeagueWins() {
    this.displayTitle = 'wins';
    this.uiService.premierLeagueTableWins().subscribe((response) => this.premierLeagueTable = response);
  }

}

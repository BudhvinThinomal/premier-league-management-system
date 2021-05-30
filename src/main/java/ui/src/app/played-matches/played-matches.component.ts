import { Component, OnInit } from '@angular/core';
import {UiService} from '../ui.service';

@Component({
  selector: 'app-played-matches',
  templateUrl: './played-matches.component.html',
  styleUrls: ['./played-matches.component.css']
})
export class PlayedMatchesComponent implements OnInit {
  title = 'The Premier League';
  seasonYear = (new Date()).getFullYear();
  playedMatches: any = [];

  constructor(private uiService: UiService) { }

  ngOnInit(): void {
    this.uiService.premierLeaguePlayedMatches().subscribe((response) => this.playedMatches = response);
  }

}

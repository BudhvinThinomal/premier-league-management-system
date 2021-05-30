import {Component, OnInit} from '@angular/core';
import {UiService} from '../ui.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-search-matches',
  templateUrl: './search-matches.component.html',
  styleUrls: ['./search-matches.component.css']
})
export class SearchMatchesComponent implements OnInit {
  title = 'The Premier League';
  seasonYear = (new Date()).getFullYear();
  lastDate = new Date();
  selectDate: any;
  searchMatches: any = [];
  selectMatches: any = [];
  searchEmpty: boolean;

  constructor(private uiService: UiService) { }

  ngOnInit(): void {
    this.uiService.premierLeaguePlayedMatches().subscribe((response) => this.searchMatches = response);
  }

  // tslint:disable-next-line:typedef
  public premierLeagueSearchPlayedMatch() {
    const dateSendingToServer = new DatePipe('en-ie').transform(this.selectDate, 'd/M/yyyy');
    this.selectMatches = this.searchMatches.filter(filter => filter.playedMatchDate.includes(dateSendingToServer));
    // tslint:disable-next-line:triple-equals
    if (this.selectMatches.length == 0)
      {this.searchEmpty = true;  }
    else
      { this.searchEmpty = false; }
  }
}

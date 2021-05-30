import { Component, OnInit } from '@angular/core';
import {UiService} from '../ui.service';

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {
  title = 'The Premier League';
  seasonYear = (new Date()).getFullYear();
  randomMatch: any = [];
  emptyList: boolean;

  constructor(private uiService: UiService) { }

  ngOnInit(): void {
    this.emptyList = false;
  }

  // tslint:disable-next-line:typedef
  public premierLeagueRandomMatch() {
    this.emptyList = true;
    this.uiService.premierLeagueRandomPlay().subscribe((response) => this.randomMatch = response);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UiService {


  constructor(private http: HttpClient) { }


  // tslint:disable-next-line:typedef
  premierLeagueTablePoints() {
    return this.http.get('http://localhost:8080/premier/league/table/points');
  }

  // tslint:disable-next-line:typedef
  premierLeagueTableGoals() {
    return this.http.get('http://localhost:8080/premier/league/table/goals');
  }

  // tslint:disable-next-line:typedef
  premierLeagueTableWins() {
    return this.http.get('http://localhost:8080/premier/league/table/wins');
  }

  // tslint:disable-next-line:typedef
  premierLeagueRandomPlay() {
    return this.http.get('http://localhost:8080/premier/league/randomMatch');
  }

  // tslint:disable-next-line:typedef
  premierLeaguePlayedMatches() {
    return this.http.get('http://localhost:8080/premier/league/playedMatches');
  }
}

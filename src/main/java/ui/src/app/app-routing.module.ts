import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { MainDesignComponent } from './main-design/main-design.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import { PlayedMatchesComponent } from './played-matches/played-matches.component';
import { SearchMatchesComponent } from './search-matches/search-matches.component';


const routes: Routes = [
  // Navigation routes
  {
    path: '',
    component: MainDesignComponent,
    children: [
      { path: '', component: HomeComponent, pathMatch: 'full' },
      { path: 'league-table', component: LeagueTableComponent },
      { path: 'random-match', component: RandomMatchComponent },
      { path: 'played-matches', component: PlayedMatchesComponent },
      { path: 'search-matches', component: SearchMatchesComponent },
    ]
  },
  // Redirecting to home
  { path: '', redirectTo: '', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

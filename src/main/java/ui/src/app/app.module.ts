import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import { HomeComponent } from './home/home.component';
import { MainDesignComponent } from './main-design/main-design.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import { PlayedMatchesComponent } from './played-matches/played-matches.component';
import { SearchMatchesComponent } from './search-matches/search-matches.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UiService } from './ui.service';
import {HttpClientModule} from '@angular/common/http';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MainDesignComponent,
    LeagueTableComponent,
    RandomMatchComponent,
    PlayedMatchesComponent,
    SearchMatchesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [UiService, {provide: MAT_DATE_LOCALE, useValue: 'en-ie'}],
  bootstrap: [AppComponent]
})
export class AppModule { }

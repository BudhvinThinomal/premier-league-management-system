import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'The Premier League';
  seasonYear = (new Date()).getFullYear();

  constructor() { }

  ngOnInit(): void {
  }

}

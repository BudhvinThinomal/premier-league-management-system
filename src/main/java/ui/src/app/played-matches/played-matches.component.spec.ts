import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayedMatchesComponent } from './played-matches.component';

describe('PlayedMatchesComponent', () => {
  let component: PlayedMatchesComponent;
  let fixture: ComponentFixture<PlayedMatchesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayedMatchesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayedMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

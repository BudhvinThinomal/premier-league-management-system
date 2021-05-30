import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainDesignComponent } from './main-design.component';

describe('MainDesignComponent', () => {
  let component: MainDesignComponent;
  let fixture: ComponentFixture<MainDesignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainDesignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainDesignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

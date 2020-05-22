import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllPatientsComponent } from './show-all-patients.component';

describe('ShowAllPatientsComponent', () => {
  let component: ShowAllPatientsComponent;
  let fixture: ComponentFixture<ShowAllPatientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllPatientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllPatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportsChronicDiseasesComponent } from './reports-chronic-diseases.component';

describe('ReportsChronicDiseasesComponent', () => {
  let component: ReportsChronicDiseasesComponent;
  let fixture: ComponentFixture<ReportsChronicDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportsChronicDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportsChronicDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

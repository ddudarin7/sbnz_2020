import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportBreedDiseasesComponent } from './report-breed-diseases.component';

describe('ReportBreedDiseasesComponent', () => {
  let component: ReportBreedDiseasesComponent;
  let fixture: ComponentFixture<ReportBreedDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportBreedDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportBreedDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllDiseasesComponent } from './show-all-diseases.component';

describe('ShowAllDiseasesComponent', () => {
  let component: ShowAllDiseasesComponent;
  let fixture: ComponentFixture<ShowAllDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

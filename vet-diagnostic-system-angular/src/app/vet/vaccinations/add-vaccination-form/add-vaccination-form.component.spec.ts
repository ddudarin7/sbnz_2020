import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVaccinationFormComponent } from './add-vaccination-form.component';

describe('AddVaccinationFormComponent', () => {
  let component: AddVaccinationFormComponent;
  let fixture: ComponentFixture<AddVaccinationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddVaccinationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddVaccinationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

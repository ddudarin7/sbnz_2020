import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewMedicationComponent } from './create-new-medication.component';

describe('CreateNewMedicationComponent', () => {
  let component: CreateNewMedicationComponent;
  let fixture: ComponentFixture<CreateNewMedicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewMedicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewMedicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

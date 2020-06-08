import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewMedicineComponent } from './create-new-medicine.component';

describe('CreateNewMedicineComponent', () => {
  let component: CreateNewMedicineComponent;
  let fixture: ComponentFixture<CreateNewMedicineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewMedicineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

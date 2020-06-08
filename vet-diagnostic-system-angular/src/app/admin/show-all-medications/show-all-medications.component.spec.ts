import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllMedicationsComponent } from './show-all-medications.component';

describe('ShowAllMedicationsComponent', () => {
  let component: ShowAllMedicationsComponent;
  let fixture: ComponentFixture<ShowAllMedicationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllMedicationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllMedicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

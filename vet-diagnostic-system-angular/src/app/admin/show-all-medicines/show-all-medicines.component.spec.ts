import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllMedicinesComponent } from './show-all-medicines.component';

describe('ShowAllMedicinesComponent', () => {
  let component: ShowAllMedicinesComponent;
  let fixture: ComponentFixture<ShowAllMedicinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllMedicinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

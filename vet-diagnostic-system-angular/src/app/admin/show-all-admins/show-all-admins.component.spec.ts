import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllAdminsComponent } from './show-all-admins.component';

describe('ShowAllAdminsComponent', () => {
  let component: ShowAllAdminsComponent;
  let fixture: ComponentFixture<ShowAllAdminsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllAdminsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllAdminsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

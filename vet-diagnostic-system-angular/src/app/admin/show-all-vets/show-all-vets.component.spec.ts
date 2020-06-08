import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllVetsComponent } from './show-all-vets.component';

describe('ShowAllVetsComponent', () => {
  let component: ShowAllVetsComponent;
  let fixture: ComponentFixture<ShowAllVetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllVetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllVetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

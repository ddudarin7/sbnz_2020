import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RulesInputComponent } from './rules-input.component';

describe('RulesInputComponent', () => {
  let component: RulesInputComponent;
  let fixture: ComponentFixture<RulesInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RulesInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RulesInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

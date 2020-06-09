import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportWeakImmunityComponent } from './report-weak-immunity.component';

describe('ReportWeakImmunityComponent', () => {
  let component: ReportWeakImmunityComponent;
  let fixture: ComponentFixture<ReportWeakImmunityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportWeakImmunityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportWeakImmunityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

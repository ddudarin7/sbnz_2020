import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiagnoseInfoComponent } from './diagnose-info.component';

describe('DiagnoseInfoComponent', () => {
  let component: DiagnoseInfoComponent;
  let fixture: ComponentFixture<DiagnoseInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiagnoseInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiagnoseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

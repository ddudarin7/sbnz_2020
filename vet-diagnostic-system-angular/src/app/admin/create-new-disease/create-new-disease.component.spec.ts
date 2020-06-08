import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewDiseaseComponent } from './create-new-disease.component';

describe('CreateNewDiseaseComponent', () => {
  let component: CreateNewDiseaseComponent;
  let fixture: ComponentFixture<CreateNewDiseaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewDiseaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewDiseaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

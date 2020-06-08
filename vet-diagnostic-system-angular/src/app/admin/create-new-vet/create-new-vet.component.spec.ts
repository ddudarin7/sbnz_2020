import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewVetComponent } from './create-new-vet.component';

describe('CreateNewVetComponent', () => {
  let component: CreateNewVetComponent;
  let fixture: ComponentFixture<CreateNewVetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewVetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewVetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

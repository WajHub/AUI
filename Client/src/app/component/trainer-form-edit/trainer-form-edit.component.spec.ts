import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerFormEditComponent } from './trainer-form-edit.component';

describe('TrainerFormEditComponent', () => {
  let component: TrainerFormEditComponent;
  let fixture: ComponentFixture<TrainerFormEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainerFormEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainerFormEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

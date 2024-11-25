import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonFormEditComponent } from './pokemon-form-edit.component';

describe('PokemonFormEditComponent', () => {
  let component: PokemonFormEditComponent;
  let fixture: ComponentFixture<PokemonFormEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PokemonFormEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PokemonFormEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

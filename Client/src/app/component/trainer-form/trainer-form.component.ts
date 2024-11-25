import {Component} from '@angular/core';
import {
  MatDialog,
  MatDialogModule
} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {TrainerService} from '../../service/trainer/trainer.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-trainer-form',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, ReactiveFormsModule],
  templateUrl: './trainer-form.component.html',
  styleUrl: './trainer-form.component.css'
})
export class TrainerFormComponent {

  trainerForm: FormGroup;

  constructor(private serviceTrainer: TrainerService,
              private formBuilder: FormBuilder,
              private matDialog: MatDialog) {
    this.trainerForm = this.formBuilder.group({
      name: ["", Validators.required],
      age: [null, [Validators.required, Validators.min(0)]]
    })
  }

  onSubmit(): void{
    // Argument Trainer
    if (this.trainerForm.valid) {
      this.serviceTrainer.createTrainer(this.trainerForm.value).subscribe({
        next: response => console.log('Trainer created response:', response),
        error: err => console.error('Error creating trainer:', err)
      });
      console.warn('Trainer form submitted:', this.trainerForm.value);
      this.trainerForm.reset();
      this.matDialog.closeAll();
    } else {
      console.warn('Form is invalid');
    }
  }


  protected readonly name = name;
}

import { Component } from '@angular/core';
import {TrainerService} from '../../service/trainer/trainer.service';
import {Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from '@angular/material/dialog';
import {ActivatedRoute} from '@angular/router';
import {Trainer} from '../../model/trainer';

@Component({
  selector: 'app-trainer-form-edit',
  standalone: true,
  imports: [
    MatButtonModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './trainer-form-edit.component.html',
  styleUrl: './trainer-form-edit.component.css'
})
export class TrainerFormEditComponent {
  trainerId: string | undefined;
  trainer: Trainer | null | undefined;
  trainerForm!: FormGroup


  constructor(private serviceTrainer: TrainerService,
              private formBuilder: FormBuilder,
              private matDialog: MatDialog) {
    this.trainerForm = formBuilder.group({name:["", Validators.required], age:[1, [Validators.required, Validators.min(0)]]});
  }

  ngOnInit(){
    if(this.trainerId) {
      this.getTrainerDetails(this.trainerId)
    }
  }

  getTrainerDetails(id: string) {
    this.serviceTrainer.findTrainerById(id)
      .subscribe({
        next: (trainerDetails)=> {
          this.trainer = trainerDetails;
          this.setForm();
        },
        error: (er) => {
          this.trainer = null;
        }
      })
  }

  setForm(){
    this.trainerForm = this.formBuilder.group({
      name: [this.trainer?.name, Validators.required],
      age: [this.trainer?.age, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(){
    if (this.trainerForm?.valid && this.trainer) {
      this.serviceTrainer.updateTrainer(this.trainer!.id, this.trainerForm.value.name).subscribe({
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

}

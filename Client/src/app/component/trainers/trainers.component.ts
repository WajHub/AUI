import {Component, inject} from '@angular/core';
import { TrainerService } from '../../service/trainer/trainer.service';
import { Trainer } from '../../model/trainer';
import {RouterLink} from '@angular/router';
import {TrainerFormComponent} from '../trainer-form/trainer-form.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {TrainerFormEditComponent} from '../trainer-form-edit/trainer-form-edit.component';

@Component({
  selector: 'app-trainers',
  standalone: true,
  imports: [RouterLink, MatButtonModule,MatDialogModule],
  templateUrl: './trainers.component.html',
  styleUrl: './trainers.component.css',
  providers: [TrainerService]
})
export class TrainersComponent {

  trainers: Trainer [] = [];

  constructor(private service: TrainerService,
              public dialog: MatDialog,
              public dialogEdit: MatDialog){}

  ngOnInit(){
    this.findAllTrainers();
  }

  findAllTrainers(){
    return this.service.findAllTrainers().subscribe(data =>{
      this.trainers = data;
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(TrainerFormComponent,{
      hasBackdrop: true
     });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.findAllTrainers();
      }, 300);
    });
  }

  openDialogEdit(id: string){
    const dialogRef = this.dialogEdit.open(TrainerFormEditComponent,{
      hasBackdrop: true
    });
    dialogRef.componentInstance.trainerId = id;
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.findAllTrainers();
      }, 300);
    });
  }

  deleteTrainer(id: string):void {
    this.service.deleteTrainer(id).subscribe(result =>{
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.findAllTrainers();
      }, 300);
    });
  }
}

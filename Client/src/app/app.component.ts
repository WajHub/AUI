import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavComponent } from "./component/nav/nav.component";
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {MatDialogModule} from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, NavComponent, MatDialogModule],
  providers: [HttpClient],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}

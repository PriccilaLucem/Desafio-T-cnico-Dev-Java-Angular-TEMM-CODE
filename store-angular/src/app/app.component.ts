import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HomeComponent } from './home/home.component';

@Component({
  imports: [HomeComponent],
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true 
})
export class AppComponent {
  title = 'store-angular';
  
  constructor(private http : HttpClient) {}
  
}

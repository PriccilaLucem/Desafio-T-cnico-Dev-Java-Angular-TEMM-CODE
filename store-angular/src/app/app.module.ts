import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { MatDialogModule } from '@angular/material/dialog'; 
import { MatButtonModule } from '@angular/material/button';
import { CategoryCreateComponent } from './category-create/category-create.component';
import { CategoriesComponent } from './categories/categories.component';
@NgModule({
  declarations: [
    AppComponent,
    CategoryCreateComponent,
    CategoriesComponent
   
  ],
  imports: [
    BrowserModule,
    MatDialogModule,
    MatButtonModule,
  ],
  bootstrap: [AppComponent],
  providers: [
]


})
export class AppModule { }

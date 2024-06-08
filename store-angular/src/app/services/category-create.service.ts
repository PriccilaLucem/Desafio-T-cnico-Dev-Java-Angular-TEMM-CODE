import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CategoryCreateComponent } from 'app/category-create/category-create.component';

@Injectable({
  providedIn: 'root'
})
export class CategoryCreateService {

  is_open: boolean = false;
  
  constructor(private dialog: MatDialog) {}
  
  open(data: any) {
    if(this.is_open){
      this.is_open = false
      return this.dialog.closeAll()
    }else{
        this.is_open = true
        return this.dialog.open(CategoryCreateComponent, {
          width: '50%',
          height: '50%',
          panelClass: 'pos-absolute'
        })
    }};
  }


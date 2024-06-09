import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProductCreateComponent } from '../components/product-create/product-create.component';

@Injectable({
  providedIn: 'root'
})
export class ProductCreateService {
  is_open: boolean = false;
  
  constructor(private dialog: MatDialog) {}
  
  open(data: any) {
    if(this.is_open){
      this.is_open = false
      return this.dialog.closeAll()
    }else{
        this.is_open = true
        return this.dialog.open(ProductCreateComponent, {
          width: '50%',
          height: '50%',
          panelClass: 'pos-absolute'
        })
    }};
  }

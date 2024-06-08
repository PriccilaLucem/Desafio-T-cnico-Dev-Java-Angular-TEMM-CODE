import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductUpdateService {
  private productUpdatedSource = new Subject<void>();
  productUpdated$ = this.productUpdatedSource.asObservable();

  emitProductUpdate() {
    this.productUpdatedSource.next();
  }
}

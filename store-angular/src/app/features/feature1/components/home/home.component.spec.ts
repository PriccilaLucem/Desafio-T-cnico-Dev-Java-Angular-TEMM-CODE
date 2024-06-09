import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home.component';

@Component({ selector: 'app-dummy', template: '' })
class DummyComponent {}

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DummyComponent],
      imports: [CommonModule, FormsModule, HomeComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should toggle products', () => {
    expect(component.showProducts).toBeFalsy();
    component.toggleProducts();
    expect(component.showProducts).toBeTruthy();
    expect(component.showCategories).toBeFalsy();
  });
  
  it('should toggle categories', () => {
    expect(component.showCategories).toBeFalsy();
    component.toggleCategories();
    expect(component.showCategories).toBeTruthy();
    expect(component.showProducts).toBeFalsy();
  });
  
  
});

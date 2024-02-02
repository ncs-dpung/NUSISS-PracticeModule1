import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryManagementComponent } from './inventory-management.component';

describe('InventoryManagementComponent', () => {
  let component: InventoryManagementComponent;
  let fixture: ComponentFixture<InventoryManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InventoryManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InventoryManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

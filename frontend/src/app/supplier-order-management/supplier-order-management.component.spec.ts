import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierOrderManagementComponent } from './supplier-order-management.component';

describe('SupplierOrderManagementComponent', () => {
  let component: SupplierOrderManagementComponent;
  let fixture: ComponentFixture<SupplierOrderManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierOrderManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SupplierOrderManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

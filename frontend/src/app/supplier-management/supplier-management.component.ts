import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Supplier } from '../supplier-management/supplier.model';
import { SupplierService } from '../services/supplier.service';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-supplier-management',
  standalone: true,
  imports: [
    NgClass,
    FormsModule,
    CommonModule],
  templateUrl: './supplier-management.component.html',
  styleUrl: './supplier-management.component.scss'
})
export class SupplierManagementComponent implements OnInit {

  suppliers: Supplier[] = [];
  newSupplier: Supplier = { id: 0, supplierName: '', supplierContact: '', supplierAddress: '' };
  selectedSupplier: Supplier = { id: 0, supplierName: '', supplierContact: '', supplierAddress: '' };
  showModal: boolean = false;
  showUpdateModal: boolean = false;
  showOrderModal = false;
  showHistoryModal = false;

  constructor(private router: Router, private supplierService: SupplierService) { }

  ngOnInit(): void {
    this.loadSuppliers();
  }

  loadSuppliers(): void {
    this.supplierService.getSuppliers().subscribe({
      next: (data) => {
        this.suppliers = data;
        console.log('Suppliers loaded', this.suppliers); // To see if suppliers are loaded correctly
      },
      error: (error) => {
        console.error('Error fetching suppliers', error);
      }
    });
  }

  onSubmit(): void {
    this.supplierService.createSupplier(this.newSupplier).subscribe(supplier => {
      this.suppliers.push(supplier);
      this.toggleModal();
    });
  }

  selectSupplierForUpdate(supplier: Supplier): void {
    this.selectedSupplier = { ...supplier };
    this.toggleUpdateModal(true);
  }

  onUpdateSupplier(supplierId: number): void {
    // Find the supplier in the array
    const supplierToUpdate = this.suppliers.find(s => s.id === supplierId);

    // If supplier is found, proceed with update
    if (supplierToUpdate) {
      this.selectedSupplier = { ...supplierToUpdate }; // Make a copy of the supplier to be updated
      this.showModal = true; // Show the modal for updating
    } else {
      console.error(`Supplier with ID ${supplierId} not found.`);
    }
  }

  onDeleteSupplier(supplierId: number): void {
    this.supplierService.deleteSupplier(supplierId).subscribe(() => {
      this.suppliers = this.suppliers.filter(supplier => supplier.id !== supplierId);
    });
  }

  toggleModal(): void {
    this.showModal = !this.showModal;
  }

  navigate(path: string): void {
    this.router.navigate([path]);
  }

  toggleUpdateModal(show: boolean) {
    this.showUpdateModal = show;
    if (show) {
      //this.selectedItem = {...item}; // Assuming `item` is the item to update
    }

  }

  toggleOrderModal(): void {
    this.showOrderModal = !this.showOrderModal;
  }

  toggleHistoryModal(): void {
    this.showHistoryModal = !this.showHistoryModal;
  }


}

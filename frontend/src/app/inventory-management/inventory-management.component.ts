import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Inventory } from '../inventory-management/inventory.model';
import { Product } from '../inventory-management/product.model';
import { InventoryService } from '../services/inventory.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inventory-management',
  standalone: true,
  imports: [
    NgClass,
    FormsModule,
    CommonModule
  ],
  templateUrl: './inventory-management.component.html',
  styleUrl: './inventory-management.component.scss'
})
export class InventoryManagementComponent implements OnInit {



  showModal: boolean = false;

  products: Product[] = [];

  newProduct: Product = {
    id: null,
    categoryId: null,
    name: '',
    price: 1.00,
    quantityAvailable: 1,
    categoryName:'',
    batchNo: '',
    reorderLevel:0,
    stockLevel:'',
    supplierName:'',
    supplierId:0
  };

  selectedProduct: Product = {
    id: null,
    categoryId: null,
    name: '',
    price: 1.00,
    quantityAvailable: 1,
    categoryName:'',
    batchNo: '',
    reorderLevel:0,
    stockLevel:'',
    supplierName:'',
    supplierId:0
  };

  constructor(private router: Router, private inventoryService: InventoryService) { }


  ngOnInit() {
    this.loadProducts();
  }

  showUpdateModal = false;


  toggleModal() {
    this.showModal = !this.showModal;
  }

  onSubmit() {
    console.log('New item:', this.newProduct);
    // Close the modal
        this.inventoryService.createProduct(this.newProduct).subscribe(product => {
          this.products.push(product);
          this.toggleModal();
        });

    this.showModal = false;
    this.loadProducts();

  }


  navigate(path: string): void {
    this.router.navigate([path]);
  }

  toggleUpdateModal(show: boolean) {
    this.showUpdateModal = show;

  }

  selectProductForUpdate(product: Product): void {
    this.selectedProduct = { ...product };
    this.toggleUpdateModal(true);
  }

  loadProducts() {
    this.inventoryService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  onUpdateProduct(productId:number) {

    if (productId === undefined) {
      console.error('Cannot update a Product without an ID');
      return;
    }
    this.inventoryService.updateProduct(productId,this.selectedProduct).subscribe({
      next: (updatedProduct) => {
        // Find the index of the customer in the array
        const index = this.products.findIndex(product => product.id === updatedProduct.id);
        if (index !== -1) {
          // Update the customer in the array
          this.products[index] = updatedProduct;
        }

        this.showUpdateModal = false;
        this.loadProducts();
      },
      error: (error) => {
        console.error('Error updating Products', error);
      }
    });
  }

  deleteProduct(productId: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.inventoryService.deleteProduct(productId).subscribe({
        next: (response) => {
          this.products = this.products.filter(product => product.id !== productId);
          console.log('Product deleted successfully', response);
        },
        error: (error) => {
          console.error('Error deleting Product', error);
        }
      });
    }
  }
}

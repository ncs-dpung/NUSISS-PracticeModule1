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
    product_id: null,
    category_id: null,
    name: '',
    price: 1,
    quantity: 1,
    batch_no: '',
  };

  selectedProduct: Product = {
    product_id: null,
    category_id: null,
    name: '',
    price: 1,
    quantity: 1,
    batch_no: '',
  };

  constructor(private router: Router, private productService: InventoryService) { }


  ngOnInit() {
    this.loadProducts();
  }

  showUpdateModal = false;


  toggleModal() {
    this.showModal = !this.showModal;
  }

  onSubmit() {
    // Here you'd handle adding the new item to your inventory list
    console.log('New item:', this.newProduct);
    // Close the modal
    this.showModal = false;

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

  loadProducts() {
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  onUpdateItem() {
    // Implement update logic here, such as making an HTTP request to update the item in the backend
    console.log(this.selectedProduct);
    this.toggleUpdateModal(false); // Hide modal after update
  }

  deleteProduct(productId: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(productId).subscribe({
        next: (response) => {
          this.products = this.products.filter(product => product.product_id !== productId);
          console.log('Product deleted successfully', response);
        },
        error: (error) => {
          console.error('Error deleting Product', error);
        }
      });
    }
  }
}

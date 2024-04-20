import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Order } from '../order-management/order.model';
import { Product } from '../inventory-management/product.model';
import { Order_Items } from '../order-management/order_items.model';
import { Order_Status } from '../order-management/order_status.model';
import { OrderService } from '../services/order.service';
import { InventoryService } from '../services/inventory.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-management',
  standalone: true,
  imports: [
    NgClass,
    FormsModule,
    CommonModule],
  templateUrl: './order-management.component.html',
  styleUrl: './order-management.component.scss'
})
export class OrderManagementComponent implements OnInit {

  showModal: boolean = false;

  showUpdateModal = false;

  constructor(private router: Router, private orderService: OrderService, private inventoryService: InventoryService) { }

  orders: Order[] = [];
  products: Product[] = [];

  newOrder: Order = {
    orderId: null,
    customerId: null,
    datePlaced: new Date('01/01/2000'),
    dateShipped: new Date('01/01/2000'),
    order_status_id: null,
    customerName: '',
    items: [{
      productId: null,
      productName: '',
      quantity: 0,
      price: 0,

    }],
    total: 0,
    staffFirstName: '',
    staffLastName: '',
    status: { id: 0, name: 'PENDING' }
  };

  selectedOrder: Order = {
    orderId: null,
    customerId: null,
    datePlaced: new Date('01/01/2000'),
    dateShipped: new Date('01/01/2000'),
    order_status_id: null,
    customerName: '',
    items: [{
      productId: null,
      productName: '',
      quantity: 0,
      price: 0,

    }],
    total: 0,
    staffFirstName: '',
    staffLastName: '',
    status: { id: null, name: 'PENDING' }
  };


  ngOnInit() {
    this.loadAllOrders();
  }

  loadAllOrders(): void {
    this.orderService.getAllOrders().subscribe(orders => {
      this.orders = orders;
    });

    this.inventoryService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  toggleModal() {
    this.showModal = !this.showModal;
  }

  onSubmitOrder() {
    console.log('New Order:', this.newOrder);

    this.orderService.createOrder(this.newOrder).subscribe(order => {
      this.orders.push(order);
      this.showModal = false;
    });
  }

  calculateTotal() {
    this.selectedOrder.total = this.selectedOrder.items.reduce((acc, item) => {
      const quantity = item.quantity || 0;
      const price = item.price || 0;
      return acc + (price * quantity);
    }, 0);
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

  onUpdateItem() {

    this.toggleUpdateModal(false); // Hide modal after update
  }

  onUpdateOrder() {
    if (this.selectedOrder.orderId === undefined) {
      console.error('Cannot update a Order without an ID');
      return;
    }
    this.orderService.updateOrder(this.selectedOrder.orderId!, this.selectedOrder).subscribe({
      next: (updatedOrder) => {
        // Find the index of the customer in the array
        const index = this.orders.findIndex(order => order.orderId === updatedOrder.orderId);
        if (index !== -1) {
          // Update the customer in the array
          this.orders[index] = updatedOrder;
        }

        this.showUpdateModal = false;
        this.loadAllOrders();
      },
      error: (error) => {
        console.error('Error updating Orders', error);
      }
    });
  }

  deleteProduct(orderId: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.orderService.deleteOrder(orderId).subscribe({
        next: (response) => {
          this.orders = this.orders.filter(order => order.orderId !== orderId);
          console.log('Product deleted successfully', response);
        },
        error: (error) => {
          console.error('Error deleting Product', error);
        }
      });
    }
  }

  selectOrderForUpdate(order: Order): void {
    this.selectedOrder = { ...order };
    this.toggleUpdateModal(true);
  }

  addItem() {

    this.selectedOrder.items.push({ ...this.tempItem });

    this.tempItem = {
      productId: null,
      productName: '',
      quantity: 1,
      price: 0
    };

    this.calculateTotal();
  }

  deleteItem(index: number) {

    this.selectedOrder.items.splice(index, 1);

    this.calculateTotal();
  }

  // Temporary item object for new item details
  tempItem: any = {
    productId: null,
    productName: '',
    quantity: 1,
    price: 0
  };

  onProductSelected(itemIndex: number, selectedProductName: string) {
    const product = this.products.find(product => product.name === selectedProductName);
    if (product) {
      this.selectedOrder.items[itemIndex].productId = product.id;
      this.selectedOrder.items[itemIndex].price = product.price;
    }
  }

}

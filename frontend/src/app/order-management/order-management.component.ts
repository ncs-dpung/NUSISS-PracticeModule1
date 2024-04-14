import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { Order } from '../order-management/order.model';
import { Order_Items } from '../order-management/order_items.model';
import { Order_Status } from '../order-management/order_status.model';
import { OrderService } from '../services/order.service';
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
export class OrderManagementComponent implements OnInit{

  showModal: boolean = false;
  
  showUpdateModal = false;

  constructor(private router: Router, private orderService: OrderService) {}

  orders: Order[] = [];

  newOrder: Order = {
    orderId: null,
    customerId: null,
    datePlaced: new Date('01/01/2000'),
    dateShipped: new Date('01/01/2000'),
    order_status_id: null,
    customerName:'',
    items:[],
    total:0,
    staffFirstName:'',
    staffLastName:'',
    status: [{ id: 5, name: 'PENDING' }],



  };

  selectedOrder: Order = {
    orderId: null,
    customerId: null,
    datePlaced: new Date('01/01/2000'),
    dateShipped: new Date('01/01/2000'),
    order_status_id: null,
    customerName:'',
    items:[],
    total:0,
    staffFirstName:'',
    staffLastName:'',
    status:[],

  };


  ngOnInit() {
    this.loadAllOrders();
  }

  loadAllOrders(): void {
    this.orderService.getAllOrders().subscribe(orders => {
      this.orders = orders;
    });
  }

  toggleModal() {
    this.showModal = !this.showModal;
  }

  onSubmit() {

    // Close the modal
    this.showModal = false;
    // Reset the new item or do whatever is needed post-submission
    //this.newOrder = {};
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

  onUpdateOrder(orderId:number) {
    // Find the supplier in the array
    const orderToUpdate = this.orders.find(s => s.orderId === orderId);

    // If supplier is found, proceed with update
    if (orderToUpdate) {
      this.selectedOrder = { ...orderToUpdate }; // Make a copy of the supplier to be updated
      this.toggleUpdateModal(true);// Show the modal for updating
    } else {
      console.error(`Supplier with ID ${orderId} not found.`);
    }
    console.log(this.selectedOrder);
    this.toggleUpdateModal(false); // Hide modal after update
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
}

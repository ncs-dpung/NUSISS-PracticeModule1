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
    order_id: null,
    staff_id: null,
    customer_id: null,
    date_placed: new Date('01/01/2000'),
    date_shipped: new Date('01/01/2000'),
    order_status_id: null,
  };

  selectedProduct: Order = {
    order_id: null,
    staff_id: null,
    customer_id: null,
    date_placed: new Date('01/01/2000'),
    date_shipped: new Date('01/01/2000'),
    order_status_id: null,
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
}

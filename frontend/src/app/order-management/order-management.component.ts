import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Order } from '../order-management/order.model';
import { Product } from '../inventory-management/product.model';
import { Order_Items } from '../order-management/order_items.model';
import { OrderStatus } from '../order-management/order_status.model';
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

  displayDatePlaced: string = '';
  displayDateShipped: string = '';

  constructor(private router: Router, private orderService: OrderService, private inventoryService: InventoryService) { }

  orders: Order[] = [];
  products: Product[] = [];
  orderStatus:OrderStatus={
    id:3,
    name:'Delivered'
  };

  newOrder: Order = {
    orderId: 0,
    customerId: 0,
    staffId:0,
    datePlaced: 1714521600000,
    dateShipped: null,
    order_status_id: null,
    customerName: '',
    items: [{
      productId: 1,
      productName: '',
      quantity: 0,
      price: 0,

    }],
    total: 0,
    staffFirstName: '',
    staffLastName: '',
    status: { id: 1, name: 'PENDING' }
  };

  selectedOrder: Order = {
    orderId: null,
    customerId: null,
    staffId:null,
    datePlaced: 1714521600000,
    dateShipped: 1714521600000,
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
    status: { id: 1, name: 'PENDING' }
  };


  ngOnInit() {
    this.loadAllOrders();
    this.loadProducts();
  }

  loadAllOrders(): void {
    this.orderService.getAllOrders().subscribe(orders => {
      this.orders = orders;
    });

  }

  loadProducts() {
    this.inventoryService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  convertTimestampToDate(timestamp: number | string): string {
    if (!timestamp) {
      return ''; // Return an empty string or some default value if the input is null, undefined, or empty
    }
    const date = new Date(timestamp);
    return date.toISOString().split('T')[0];
  }

  trackByItems(index: number, item: any): number {
    return item.productId;
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
        return acc + (item.quantity || 0) * (item.price || 0);
      }, 0);
    }
  



  navigate(path: string): void {
    this.router.navigate([path]);
  }

  toggleUpdateModal(show: boolean) {
    this.showUpdateModal = show;

  }

  onUpdateItem() {

    this.toggleUpdateModal(false); // Hide modal after update
  }

  onUpdateOrder() {

    if (this.selectedOrder.orderId === undefined) {
      console.error('Cannot update a Order without an ID');
      return;
    }
      // Assuming displayDatePlaced and displayDateShipped are in 'YYYY-MM-DD' format
      const datePlacedTimestamp = this.displayDatePlaced ? new Date(this.displayDatePlaced).getTime() : null;
      const dateShippedTimestamp = this.displayDateShipped ? new Date(this.displayDateShipped).getTime() : null;

  // Assign the timestamps to the selectedOrder object
  const updatedOrder: Order = {
    ...this.selectedOrder,
    datePlaced: datePlacedTimestamp,
    dateShipped: dateShippedTimestamp,
  };

    this.orderService.updateOrder(updatedOrder.orderId!, updatedOrder).subscribe({
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
    if (confirm('Are you sure you want to delete this Order?')) {
      this.orderService.deleteOrder(orderId).subscribe({
        next: (response) => {
          this.orders = this.orders.filter(order => order.orderId !== orderId);
          console.log('Order deleted successfully', response);
        },
        error: (error) => {
          console.error('Error deleting Order', error);
          if (error.status === 500) {
            alert(error.error.message); 
          } else {
            alert('An unexpected error occurred.'); 
          }
        }
      });
    }
  }

  updateOrderStatus(orderId: number): void {
    if (confirm('Are you sure you want to Complete this Order?')) {
      let newStatus = {
        status: {
          id: 3, // Assuming '3' corresponds to 'Delivered'
          name: "Delivered"
        },
        deliveryDate: new Date().toISOString() // Sets the current date and time
      };
      this.orderService.updateOrderStatus(orderId, newStatus).subscribe({
        next: (response) => {
          console.log('Order Complete', response);
  
          // Find the order in the orders array and update its status
          const orderIndex = this.orders.findIndex(order => order.orderId === orderId);
          if (orderIndex !== -1) {
            this.orders[orderIndex].status = newStatus.status;
            this.orders[orderIndex].dateShipped = Date.parse(newStatus.deliveryDate);
          }
        },
        error: (error) => {
          console.error('Error Completing Order', error);
        }
      });
    }
  }

  selectOrderForUpdate(order: Order): void {
    this.displayDatePlaced = order.datePlaced ? this.convertTimestampToDate(order.datePlaced) : '';
    this.displayDateShipped = order.dateShipped ? this.convertTimestampToDate(order.dateShipped) : '';
    
  
    this.selectedOrder = { ...order };
      this.toggleUpdateModal(true);
      this.calculateTotal();
    
  }

  addItem() {

    this.selectedOrder.items.push({
      ...this.tempItem
    });
  
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
    const product = this.products.find(p => p.name === selectedProductName);
    if (product) {
      this.selectedOrder.items[itemIndex] = {
        ...this.selectedOrder.items[itemIndex],
        productId: product.id,
        price: product.price
      };
    } else {
      this.selectedOrder.items[itemIndex] = {
        ...this.selectedOrder.items[itemIndex],
        productId: null,
        price: 0
      };
    }
    this.calculateTotal();
  }

}

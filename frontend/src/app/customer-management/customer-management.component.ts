import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Customer } from './customer.model';
import { CustomerService } from '../customer.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-customer-management',
  standalone: true,
  imports: [
    NgClass,
    FormsModule,
    CommonModule],
  templateUrl: './customer-management.component.html',
  styleUrl: './customer-management.component.scss'
})
export class CustomerManagementComponent implements OnInit {

   newCustomer: Customer = {
     customerName: '',
     customerContact: ''
   };

     selectedUser:Customer = {
          id: null,
          customerName: '',
          customerContact: ''
     };

  constructor(private router: Router, private customerService: CustomerService) { }

  customers: Customer[] = [];

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
    }, error => {
      console.error('Error fetching customers', error);
    });
  }

  showCustomerModal = false;
  newUser = {
    userName: '',
    email: '',
    contact: '',
    address: '',
    role: 'user' // Default role
  };

  toggleCustomerModal(): void {
    this.showCustomerModal = !this.showCustomerModal;
  }

  navigate(path: string): void {
    this.router.navigate([path]);
  }
  onSubmitCustomer(): void {
  this.customerService.createCustomer(this.newCustomer).subscribe({
       next: (customer) => {
         console.log('Customer added:', customer);
       },
       error: (error) => {
         console.error('Error adding customer', error);

       }
     });
   }

   onUpdateCustomer(): void {
     if (this.selectedUser.id === undefined) {
       // Handle error: selected user must have an id to be updated
       console.error('Cannot update a customer without an ID');
       return;
     }

     this.customerService.updateCustomer(this.selectedUser).subscribe({
       next: (updatedCustomer) => {
         // Replace the customer in your local array or re-fetch the list
         // Close the modal and clear/reset the selectedUser
         this.showUpdateModal = false;
         // ... any other logic like showing a success message
       },
       error: (error) => {
         console.error('Error updating customer', error);
         // ... any error handling like showing an error message
       }
     });
   }


   deleteCustomer(customerId: number): void {
     if (confirm('Are you sure you want to delete this customer?')) {
       this.customerService.deleteCustomer(customerId).subscribe({
         next: (response) => {
           // Optionally, filter out the deleted customer from the local array
           this.customers = this.customers.filter(customer => customer.id !== customerId);
           console.log('Customer deleted successfully', response);
         },
         error: (error) => {
           console.error('Error deleting customer', error);
         }
       });
     }
   }

  showUpdateModal = false;

  toggleUpdateModal(show: boolean): void {
    this.showUpdateModal = show;
  }

  onUpdateUser(): void {

    // After updating, you might want to close the modal
    this.showUpdateModal = false;
  }

  showOrderHistoryModal = false;
  customerOrders = [
    // Example data - replace with data from your backend
    {
      id: '12345',
      productList: ['Product 1', 'Product 2'],
      status: 'Completed',
      orderDate: new Date(),
      completeDate: new Date(),
      totalAmount: '100.00',
      processManager: 'Manager Name'
    }

  ];

  toggleOrderHistoryModal(): void {
    this.showOrderHistoryModal = !this.showOrderHistoryModal;
  }

}

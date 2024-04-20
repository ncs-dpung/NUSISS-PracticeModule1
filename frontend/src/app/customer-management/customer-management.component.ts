import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Customer } from '../customer-management/customer.model';
import { CustomerService } from '../services/customer.service';
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
     this.loadCustomers();
   }

   onUpdateCustomer(): void {
    if (this.selectedUser.id === undefined) {
      console.error('Cannot update a customer without an ID');
      return;
    }

    this.customerService.updateCustomer(this.selectedUser).subscribe({
      next: (updatedCustomer) => {
        // Find the index of the customer in the array
        const index = this.customers.findIndex(customer => customer.id === updatedCustomer.id);
        if (index !== -1) {
          // Update the customer in the array
          this.customers[index] = updatedCustomer;
        }

        this.showUpdateModal = false;
        this.loadCustomers();
      },
      error: (error) => {
        console.error('Error updating customer', error);
      }
    });
  }



   deleteCustomer(customerId: number): void {
     if (confirm('Are you sure you want to delete this customer?')) {
       this.customerService.deleteCustomer(customerId).subscribe({
         next: (response) => {
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

  selectCustomerForUpdate(customer: Customer): void {
    this.selectedUser = { ...customer };
    this.toggleUpdateModal(true);
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

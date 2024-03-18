import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-customer-management',
  standalone: true,
  imports: [
  NgClass,
      FormsModule],
  templateUrl: './customer-management.component.html',
  styleUrl: './customer-management.component.scss'
})
export class CustomerManagementComponent {

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

  constructor(private router: Router) {}

  navigate(path: string): void {
    this.router.navigate([path]);
  }
    onSubmitCustomer(): void {
      // Here you would typically make a service call to your backend to save the new user
      console.log('User data to submit:', this.newUser);
      // After submission, you might want to close the modal and clear the form
      this.showCustomerModal = false;
      this.newUser = {
        userName: '',
        email: '',
        contact: '',
        address: '',
        role: 'user'
      };
    }

    // In your user-management.component.ts
    selectedUser = {
      userName: '',
      role: '',
      email: '',
      contact: '',
      address: '',
      modified: '' // This could be the date the user was last updated
    };

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

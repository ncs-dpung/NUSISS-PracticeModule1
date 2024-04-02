import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Staff } from '../user-access-management/staff.model';

@Component({
  selector: 'app-user-access-management',
  standalone: true,
  imports: [
      NgClass,
      FormsModule],
  templateUrl: './user-access-management.component.html',
  styleUrl: './user-access-management.component.scss'
})
export class UserAccessManagementComponent {

  showUserModal = false;
    newUser = {
      userName: '',
      email: '',
      contact: '',
      address: '',
      role: 'user' // Default role
    };

   toggleUserModal(): void {
      this.showUserModal = !this.showUserModal;
    }

  constructor(private router: Router) {}

  navigate(path: string): void {
    this.router.navigate([path]);
  }
    onSubmitUser(): void {
      // Here you would typically make a service call to your backend to save the new user
      console.log('User data to submit:', this.newUser);
      // After submission, you might want to close the modal and clear the form
      this.showUserModal = false;
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
      // Implement your logic to update the user here, such as calling a service to update the user data on the server
      console.log('Updated user data:', this.selectedUser);
      // After updating, you might want to close the modal
      this.showUpdateModal = false;
    }


}

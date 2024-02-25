import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-order-management',
  standalone: true,
  imports: [
      NgClass,
      FormsModule],
  templateUrl: './order-management.component.html',
  styleUrl: './order-management.component.scss'
})
export class OrderManagementComponent {

  showModal: boolean = false;
  newItem: any = {
    name: '',
    condition: 'new',
    available: 0,
    reserved: 0,
    price: '',
    modified: new Date().toISOString().split('T')[0]
  }; // Replace with your item model
  showUpdateModal = false;
  selectedItem: any = {};


  toggleModal() {
    this.showModal = !this.showModal;
  }

  onSubmit() {
    // Here you'd handle adding the new item to your inventory list
    console.log('New item:', this.newItem);
    // Close the modal
    this.showModal = false;
    // Reset the new item or do whatever is needed post-submission
    this.newItem = {};
  }
  constructor(private router: Router) {}

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
    // Implement update logic here, such as making an HTTP request to update the item in the backend
    console.log(this.selectedItem);
    this.toggleUpdateModal(false); // Hide modal after update
  }
}

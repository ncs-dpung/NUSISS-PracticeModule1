import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Staff } from '../user-access-management/staff.model';
import { StaffService } from '../services/staff.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-user-access-management',
  standalone: true,
  imports: [
    NgClass,
    FormsModule,
    CommonModule],
  templateUrl: './user-access-management.component.html',
  styleUrl: './user-access-management.component.scss'
})
export class UserAccessManagementComponent implements OnInit {

  showUserModal = false;

  staffs: Staff[] = [];

  newStaff: Staff = {
    id: null,
    firstName: '',
    lastName: '',
    position: '',
    department: '',
    email: '',
    phone_number: '',
    address: '',
    created_at: new Date('01/01/2000'),
    updated_at: new Date('01/01/2000'),
    created_by: '',
    updated_by: '',
  };

  selectedStaff: Staff = {
    id: null,
    firstName: '',
    lastName: '',
    position: '',
    department: '',
    email: '',
    phone_number: '',
    address: '',
    created_at: new Date('01/01/2000'),
    updated_at: new Date('01/01/2000'),
    created_by: '',
    updated_by: '',
  };

  constructor(private router: Router, private staffService: StaffService) { }

  ngOnInit(): void {
    this.loadSaffs();
  }

  loadSaffs() {
    this.staffService.getAllStaff().subscribe((staffs) => {
      this.staffs = staffs;
    });
  }

  onUpdateStaff(staffId: number) {
    // Find the supplier in the array
    const staffToUpdate = this.staffs.find(s => s.id === staffId);

    // If supplier is found, proceed with update
    if (staffToUpdate) {
      this.selectedStaff = { ...staffToUpdate }; // Make a copy of the supplier to be updated
    } else {
      console.error(`Supplier with ID ${staffId} not found.`);
    }
    console.log(this.selectedStaff);
    this.toggleUpdateModal(false); // Hide modal after update
  }

  deleteStaff(staffId: number): void {
    if (confirm('Are you sure you want to delete this staff?')) {
      this.staffService.deleteStaff(staffId).subscribe({
        next: (response) => {
          this.staffs = this.staffs.filter(staff => staff.id !== staffId);
          console.log('Staff deleted successfully', response);
        },
        error: (error) => {
          console.error('Error Deleting This Staff', error);
        }
      });
    }
  }

  toggleUserModal(): void {
    this.showUserModal = !this.showUserModal;
  }



  navigate(path: string): void {
    this.router.navigate([path]);
  }

  onSubmitStaff(): void {
    console.log('New item:', this.newStaff);
    // Close the modal
    this.staffService.addStaff(this.newStaff).subscribe(staff => {
      this.staffs.push(staff);
      this.showUserModal = false;
    });





  }


  showUpdateModal = false;

  toggleUpdateModal(show: boolean): void {
    this.showUpdateModal = show;
  }



}

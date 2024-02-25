import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { InventoryManagementComponent } from './inventory-management/inventory-management.component';
import { OrderManagementComponent } from './order-management/order-management.component';
import { ReportingComponent } from './reporting/reporting.component';
import { UserAccessManagementComponent } from './user-access-management/user-access-management.component';
import { SupplierManagementComponent } from './supplier-management/supplier-management.component';
import { SupplierOrderManagementComponent } from './supplier-order-management/supplier-order-management.component';
import { CustomerManagementComponent } from './customer-management/customer-management.component';

export const routes: Routes = [
    { path: 'inventory-management', component: InventoryManagementComponent },
    { path: 'order-management', component: OrderManagementComponent },
    { path: 'reporting', component: ReportingComponent },
    { path: 'login-page', component: LoginPageComponent },
    { path: 'user-access-management', component: UserAccessManagementComponent },
    { path: 'customer-management', component: CustomerManagementComponent },
    { path: 'supplier-management', component: SupplierManagementComponent },
    { path: 'supplier-order-management', component: SupplierOrderManagementComponent },
    { path: '', redirectTo: '/login-page', pathMatch: 'full' } // Redirect to login page as the default route
];

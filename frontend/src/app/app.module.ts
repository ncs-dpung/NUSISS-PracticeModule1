
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

import { InventoryManagementComponent } from './inventory-management/inventory-management.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { OrderManagementComponent } from './order-management/order-management.component';
import { ReportingComponent } from './reporting/reporting.component';
import { SupplierManagementComponent } from './supplier-management/supplier-management.component';
import { SupplierOrderManagementComponent } from './supplier-order-management/supplier-order-management.component';
import { CustomerManagementComponent } from './customer-management/customer-management.component';
import { UserAccessManagementComponent } from './user-access-management/user-access-management.component';
import { HttpClientModule } from '@angular/common/http';
import { routes } from './app.routes';
import { ChartsModule } from 'ng2-charts';
import { FormsModule }    from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    InventoryManagementComponent,
    LoginPageComponent,
    OrderManagementComponent,
    ReportingComponent,
    UserAccessManagementComponent,
    SupplierManagementComponent,
    SupplierOrderManagementComponent,
    CustomerManagementComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    routes,
    FormsModule,
    ChartsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

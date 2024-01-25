
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

import { InventoryManagementComponent } from './inventory-management/inventory-management.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { OrderManagementComponent } from './order-management/order-management.component';
import { ReportingComponent } from './reporting/reporting.component';
import { UserAccessManagementComponent } from './user-access-management/user-access-management.component';

import { routes } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    InventoryManagementComponent,
    LoginPageComponent,
    OrderManagementComponent,
    ReportingComponent,
    UserAccessManagementComponent,
  ],
  imports: [
    BrowserModule,
    routes

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductService } from './product.service';
import { FormsModule } from '@angular/forms';
import { ProductsListComponent } from './products-list/products-list.component';


import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AppRoutingModule } from './app-routing.module';
import { AddProductComponent } from './add-product/add-product.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsListComponent,
    AdminPanelComponent,
    AddProductComponent,
    LoginComponent,
  ],
  imports: [BrowserModule, HttpClientModule, FormsModule, AppRoutingModule],

  providers: [ProductService],
  bootstrap: [AppComponent],
})
export class AppModule {}

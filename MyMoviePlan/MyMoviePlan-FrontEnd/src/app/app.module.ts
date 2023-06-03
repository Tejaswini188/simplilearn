import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlertComponent } from './_components/alert/alert.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { FilterPipe } from './filter.pipe';
import { HighlightDirective } from './highlight.directive';
import { LinkPipe } from './link.pipe';
import { AdminComponent } from './admindashboard/admin/admin.component';
import { AddmovieComponent } from './admindashboard/addmovie/addmovie.component';
import { EditmovieComponent } from './admindashboard/editmovie/editmovie.component';
import { AddgenreComponent } from './admindashboard/addgenre/addgenre.component';
import { EditgenreComponent } from './admindashboard/editgenre/editgenre.component';
import { AddshowComponent } from './admindashboard/addshow/addshow.component';
import { EditshowComponent } from './admindashboard/editshow/editshow.component';
import { CommonModule } from '@angular/common';
import { ListgenreComponent } from './admindashboard/listgenre/listgenre.component';
import { ListmovieComponent } from './admindashboard/listmovie/listmovie.component';
import { ListshowComponent } from './admindashboard/listshow/listshow.component';
import { ResetpasswordComponent } from './admindashboard/resetpassword/resetpassword.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { NgbDateAdapter, NgbDateParserFormatter, NgbModule, NgbTimeAdapter } from '@ng-bootstrap/ng-bootstrap';
import { CustomadapterService } from './_helpers/customadapter.service';
import { CustomdateparserformatterService } from './_helpers/customdateparserformatter.service';
import { NgbTimeStringAdapterService } from './_helpers/ngb-time-string-adapter.service';
import { ImageuploadComponent } from './_test/imageupload/imageupload.component';
import { ListshowsComponent } from './shows/listshows/listshows.component';
import { CartComponent } from './moviecart/cart/cart.component';
import { ShowListComponent } from './moviecart/show-list/show-list.component';
import { ShowItemComponent } from './moviecart/show-list/show-item/show-item.component';
import { CartItemComponent } from './moviecart/cart/cart-item/cart-item.component';
import { MoviecartComponent } from './moviecart/moviecart.component';
import { PaymentComponent } from './payment/payment.component';
import { ReviewpaymentComponent } from './reviewpayment/reviewpayment.component';
import { PaymentcanceledComponent } from './paymentcanceled/paymentcanceled.component';
import { PaymentreceiptComponent } from './paymentreceipt/paymentreceipt.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { DashboardComponent } from './userdashboard/dashboard/dashboard.component';
import { PaymentdetailsComponent } from './paymentdetails/paymentdetails.component';
import { OrderdetailsComponent } from './orderdetails/orderdetails.component';
import { CustomerordersComponent } from './admindashboard/customerorders/customerorders.component';
import { CustomerpaydetailsComponent } from './admindashboard/customerpaydetails/customerpaydetails.component';
import { CustomerdetailsComponent } from './admindashboard/customerdetails/customerdetails.component';
import { UserorderdetailsComponent } from './userdashboard/userorderdetails/userorderdetails.component';
import { AboutusComponent } from './aboutus/aboutus.component';


@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    FilterPipe,
    HighlightDirective,
    LinkPipe,
    AdminComponent,
    AddmovieComponent,
    EditmovieComponent,
    AddgenreComponent,
    EditgenreComponent,
    AddshowComponent,
    EditshowComponent,
    ListgenreComponent,
    ListmovieComponent,
    ListshowComponent,
    ResetpasswordComponent,
    ImageuploadComponent,
    ListshowsComponent,
    ShowListComponent,
    ShowItemComponent,
    CartItemComponent,
    CartComponent,
    MoviecartComponent,
    PaymentComponent,
    ReviewpaymentComponent,
    PaymentcanceledComponent,
    PaymentreceiptComponent,
    DashboardComponent,
    PaymentdetailsComponent,
    OrderdetailsComponent,
    CustomerordersComponent,
    CustomerpaydetailsComponent,
    CustomerdetailsComponent,
    UserorderdetailsComponent,
    AboutusComponent
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    SlickCarouselModule,
    CommonModule,
    MatDatepickerModule,
    MatInputModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    BrowserAnimationsModule,
    NgbModule 
  ],
  providers: [
    {provide: NgbDateAdapter, useClass: CustomadapterService},
    {provide: NgbDateParserFormatter, useClass: CustomdateparserformatterService},
    {provide: NgbTimeAdapter, useClass: NgbTimeStringAdapterService},
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './aboutus/aboutus.component';
import { AddgenreComponent } from './admindashboard/addgenre/addgenre.component';
import { AddmovieComponent } from './admindashboard/addmovie/addmovie.component';
import { AddshowComponent } from './admindashboard/addshow/addshow.component';
import { AdminComponent } from './admindashboard/admin/admin.component';
import { CustomerdetailsComponent } from './admindashboard/customerdetails/customerdetails.component';
import { CustomerordersComponent } from './admindashboard/customerorders/customerorders.component';
import { CustomerpaydetailsComponent } from './admindashboard/customerpaydetails/customerpaydetails.component';
import { EditgenreComponent } from './admindashboard/editgenre/editgenre.component';
import { EditmovieComponent } from './admindashboard/editmovie/editmovie.component';
import { EditshowComponent } from './admindashboard/editshow/editshow.component';
import { ListgenreComponent } from './admindashboard/listgenre/listgenre.component';
import { ListmovieComponent } from './admindashboard/listmovie/listmovie.component';
import { ListshowComponent } from './admindashboard/listshow/listshow.component';
import { ResetpasswordComponent } from './admindashboard/resetpassword/resetpassword.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MoviecartComponent } from './moviecart/moviecart.component';
import { ShowListComponent } from './moviecart/show-list/show-list.component';
import { PaymentComponent } from './payment/payment.component';
import { PaymentcanceledComponent } from './paymentcanceled/paymentcanceled.component';
import { PaymentdetailsComponent } from './paymentdetails/paymentdetails.component';
import { PaymentreceiptComponent } from './paymentreceipt/paymentreceipt.component';
import { RegisterComponent } from './register/register.component';
import { ReviewpaymentComponent } from './reviewpayment/reviewpayment.component';
import { ListshowsComponent } from './shows/listshows/listshows.component';
import { DashboardComponent } from './userdashboard/dashboard/dashboard.component';
import { UserorderdetailsComponent } from './userdashboard/userorderdetails/userorderdetails.component';
import { AuthGuard } from './_helpers/auth.guard';


const routes: Routes = [
  {"path":"login",component:LoginComponent},
  {"path":"register",component:RegisterComponent},
  {"path":"home",component:HomeComponent},
  {"path":"admin",component:AdminComponent,canActivate: [AuthGuard]},
  {"path":"addmovie",component:AddmovieComponent,canActivate: [AuthGuard]},
  {"path":"editmovie/:id",component:EditmovieComponent,canActivate: [AuthGuard]},
  {"path":"listmovie",component:ListmovieComponent,canActivate: [AuthGuard]},
  {"path":"addgenre",component:AddgenreComponent,canActivate: [AuthGuard]},
  {"path":"editgenre/:id",component:EditgenreComponent,canActivate: [AuthGuard]},
  {"path":"listgenre",component:ListgenreComponent,canActivate: [AuthGuard]},
  {"path":"addshow",component:AddshowComponent,canActivate: [AuthGuard]},
  {"path":"editshow/:id",component:EditshowComponent,canActivate: [AuthGuard]},
  {"path":"listshow",component:ListshowComponent,canActivate: [AuthGuard]},
  {"path":"moviedetails/:moviename",component:ListshowsComponent},
  {"path":"listshows",component:ShowListComponent,canActivate: [AuthGuard]}, 
  {"path":"listshows/:id",component:ShowListComponent},
  {"path":"resetpass",component:ResetpasswordComponent,canActivate: [AuthGuard]},
  {"path":"moviecart",component:MoviecartComponent,canActivate: [AuthGuard]},
  {"path":"payment",component:PaymentComponent,canActivate: [AuthGuard]},  
  {"path":"reviewpayment/:paymentId/:token/:PayerId",component:ReviewpaymentComponent,canActivate: [AuthGuard]},  
  {"path":"reviewpayment",component:ReviewpaymentComponent,canActivate: [AuthGuard]}, 
  {"path":"cancelpayment",component:PaymentcanceledComponent,canActivate: [AuthGuard]}, 
  {"path":"receiptpayment/:paymentId/:token/:PayerId",component:PaymentreceiptComponent,canActivate: [AuthGuard]},
  {"path":"user",component:DashboardComponent,canActivate: [AuthGuard]}, 
  {"path":"paydetails/:id",component:PaymentdetailsComponent,canActivate: [AuthGuard]},
  {"path":"listorders",component:CustomerordersComponent,canActivate: [AuthGuard]}, 
  {"path":"listpayments",component:CustomerpaydetailsComponent,canActivate: [AuthGuard]}, 
  {"path":"listusers",component:CustomerdetailsComponent,canActivate: [AuthGuard]},  
  {"path":"orderdetails",component:UserorderdetailsComponent,canActivate: [AuthGuard]},
  {"path":"aboutus",component:AboutusComponent},
  
  
  
  {"path": '', redirectTo: '/home', pathMatch: 'full'} 

  

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

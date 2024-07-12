import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { LeaveBalanceComponent } from './components/leave-balance/leave-balance.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: '/employees', component: EmployeeComponent },
  { path: 'leave-requests', component: LeaveRequestComponent },
  { path: 'leave-balances', component: LeaveBalanceComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

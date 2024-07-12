import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { InitLeaveRequestComponent } from './initLeaveRequest.component';
import { LeaveRequestComponent } from './LeaveRequest.component';

const routes: Routes = [
  {
    path: '',
    component: LeaveRequestComponent,
  },
  {
    path: 'init',
    component: InitLeaveRequestComponent,
  },
];

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
  ],
  declarations: [LeaveRequestComponent],
})
export class LeaveRequestModule {}

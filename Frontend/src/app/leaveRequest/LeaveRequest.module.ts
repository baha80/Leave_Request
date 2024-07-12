import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { InitLeaveRequestComponent } from './initLeaveRequest.component';

const routes: Routes = [
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
  declarations: [],
})
export class LeaveRequestModule {}

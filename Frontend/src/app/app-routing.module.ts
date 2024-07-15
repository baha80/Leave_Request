import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';
import { CreateLeaveRequestComponent } from './create-leave-request/create-leave-request.component';

export const Approutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        loadChildren: () =>
          import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
      },
      { path: 'create-leave-request', component: CreateLeaveRequestComponent },

      {
        path: 'leaveRequest',
        loadChildren: () =>
          import('./leaveRequest/LeaveRequest.module').then(
            (m) => m.LeaveRequestModule
          ),
      },
      {
        path: 'component',
        loadChildren: () =>
          import('./component/component.module').then(
            (m) => m.ComponentsModule
          ),
      },
    ],
  },
  {
    path: '**',
    redirectTo: '/starter',
  },
];

import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../core/services/LeaveRequest.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './initLeaveRequest.component.html',
})
export class InitLeaveRequestComponent implements OnInit {
  employees: any[] = [];
  selectedUserId: string = '';

  vacationDays: number = 5;
  sickDays: number = 5;
  personalDays: number = 5;

  successMessage: string = '';
  errorMessage: string = '';


  constructor(private leaveRequestService: LeaveRequestService ,private router: Router) {}

  ngOnInit(): void {
    this.leaveRequestService.getAllEmployees().subscribe((data) => {
      this.employees = data;
    });
  }

  onInitializeLeaveBalance() {
    if (this.selectedUserId) {
      this.leaveRequestService
        .initializeLeaveBalance(
          this.selectedUserId,
          this.vacationDays,
          this.sickDays,
          this.personalDays
        )
        .subscribe(
          (response) => {
            this.successMessage = 'Leave balance initialized successfully';
            this.errorMessage = '';
          },
          (error) => {
            this.errorMessage = 'Error initializing leave balance';
            this.successMessage = '';
          }
        );
    } else {
      alert('Please select a user');
    }
  }

  closeAlert() {
    this.successMessage = '';
    this.errorMessage = '';
  }

  navigateToCreateLeaveRequest() {
    this.router.navigate(['/create-leave-request']);
  }
}

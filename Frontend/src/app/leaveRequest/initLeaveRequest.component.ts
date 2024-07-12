import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../core/services/LeaveRequest.service';

@Component({
  templateUrl: './initLeaveRequest.component.html',
})
export class InitLeaveRequestComponent implements OnInit {
  employees: any[] = [];
  selectedUserId: string = '';

  vacationDays: number = 7;
  sickDays: number = 7;
  personalDays: number = 8;

  successMessage: string = '';
  errorMessage: string = '';

  constructor(private leaveRequestService: LeaveRequestService) {}

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
}

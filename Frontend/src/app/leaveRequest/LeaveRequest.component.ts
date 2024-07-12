import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../core/services/LeaveRequest.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './LeaveRequest.component.html',
})
export class LeaveRequestComponent implements OnInit {
  employees: any[] = []; // Assuming you have a list of employees
  selectedEmployee: any; // Selected employee object
  leaveRequests: any[] = [];

  constructor(
    private leaveRequestsService: LeaveRequestService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Fetch list of employees (assuming you have a service for this)
    this.leaveRequestsService
      .getAllEmployees()
      .subscribe((data) => (this.employees = data));
  }

  onEmployeeSelect() {
    if (this.selectedEmployee) {
      this.leaveRequestsService
        .getLeaveRequestsForEmployee(this.selectedEmployee.id)
        .subscribe((data) => {
          this.leaveRequests = data;
        });
    } else {
      this.leaveRequests = []; // Clear leave requests if no employee is selected
    }
  }

  initializeLeaveRequest() {
    // Navigate to the leaveRequest/init route
    this.router.navigate(['/leaveRequest/init']);
  }
}

import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../core/services/LeaveRequest.service';
import { Router } from '@angular/router';

interface Employee {
  id: string;
  username: string;
  // Add other properties if needed
}

@Component({
  selector: 'app-create-leave-request',
  templateUrl: './create-leave-request.component.html',
  styleUrls: ['./create-leave-request.component.css']
})
export class CreateLeaveRequestComponent implements OnInit {
  employees: Employee[] = [];
  leaveRequest = {
    employee: null as Employee | null,
    startDate: '',
    endDate: '',
    reason: '',
    leaveType: ''
  };
  errorMessage = '';
  successMessage = '';

  constructor(
    private leaveRequestService: LeaveRequestService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.leaveRequestService.getAllEmployees().subscribe(
      (data: Employee[]) => {
        this.employees = data;
      },
      (error) => {
        console.error('Error fetching employees:', error);
        this.errorMessage = 'Failed to load employees. Please try again.';
      }
    );
  }

  onSubmit() {
    if (!this.leaveRequest.employee) {
      this.errorMessage = 'Please select an employee.';
      return;
    }

    const payload = {
      ...this.leaveRequest,
      employee: { id: this.leaveRequest.employee.id }
    };

    this.leaveRequestService.createLeaveRequest(payload).subscribe(
      (response) => {
        console.log('Leave request created successfully', response);
        this.successMessage = 'Leave request created successfully!';
        this.errorMessage = '';
        setTimeout(() => this.router.navigate(['/leave-requests']), 2000);
      },
      (error) => {
        console.error('Insufficient leave balance', error);
        if (error.error && error.error.message) {
          this.errorMessage = error.error.message;
        } else if (error.status === 400 && error.error.includes('Insufficient leave balance')) {
          this.errorMessage = 'Insufficient leave balance. Please check your available leave days.';
        } else {
          this.errorMessage = 'Failed to create leave request. Please try again.';
        }
      }
    );
  }
}
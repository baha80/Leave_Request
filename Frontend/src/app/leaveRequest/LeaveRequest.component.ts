import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../core/services/LeaveRequest.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './LeaveRequest.component.html',
  styleUrls: ['./leave-request.component.css'],

})
export class LeaveRequestComponent implements OnInit {
  employees: any[] = [];
  selectedEmployee: any;
  leaveRequests: any[] = [];
  vacationDays: number | null = null;
  sickDays: number | null = null;
  personalDays: number | null = null;
  showDetailedBalance: boolean = false;

  constructor(
    private leaveRequestService: LeaveRequestService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees() {
    this.leaveRequestService.getAllEmployees().subscribe(
      (data) => {
        this.employees = data;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }

  onEmployeeSelect() {
    if (this.selectedEmployee) {
      this.loadLeaveRequests();
      this.loadVacationDays();
    } else {
      this.leaveRequests = [];
      this.vacationDays = null;
    }
  }

  loadLeaveRequests() {
    this.leaveRequestService.getLeaveRequestsForEmployee(this.selectedEmployee.id).subscribe(
      (data) => {
        this.leaveRequests = data;
      },
      (error) => {
        console.error('Error fetching leave requests:', error);
      }
    );
  }

  loadVacationDays() {
    this.leaveRequestService.getVacationDays(this.selectedEmployee.id).subscribe(
      (days) => {
        this.vacationDays = days;
      },
      (error) => {
        console.error('Error fetching vacation days:', error);
      }
    );
  }

  approveLeaveRequest(leaveRequestId: number) {
    const approverId = this.selectedEmployee.id;
   
    if (!approverId) {
      console.error('Approver ID is missing');
      alert('Cannot approve: Approver ID is missing');
      return;
    }
 
    this.leaveRequestService.approveLeaveRequest(leaveRequestId, approverId).subscribe(
      () => {
        alert('Leave request approved successfully');
        this.updateAfterAction();
      },
      (error) => {
        console.error('Error approving leave request:', error);
        alert('Failed to approve leave request');
      }
    );
  }

  rejectLeaveRequest(leaveRequestId: number) {
    const approverId = this.selectedEmployee.id;
   
    if (!approverId) {
      console.error('Approver ID is missing');
      alert('Cannot reject: Approver ID is missing');
      return;
    }
 
    this.leaveRequestService.rejectLeaveRequest(leaveRequestId, approverId).subscribe(
      () => {
        alert('Leave request rejected successfully');
        this.updateAfterAction();
      },
      (error) => {
        console.error('Error rejecting leave request:', error);
        alert('Failed to reject leave request');
      }
    );
  }

  updateAfterAction() {
    this.loadLeaveRequests();
    this.loadVacationDays();
  }

  loadDetailedBalance() {
    if (this.selectedEmployee) {
      this.leaveRequestService.getSickDays(this.selectedEmployee.id).subscribe(
        (days) => {
          this.sickDays = days;
        },
        (error) => {
          console.error('Error fetching sick days:', error);
        }
      );

      this.leaveRequestService.getPersonalDays(this.selectedEmployee.id).subscribe(
        (days) => {
          this.personalDays = days;
        },
        (error) => {
          console.error('Error fetching personal days:', error);
        }
      );
    }
  }

  toggleDetailedBalance() {
    this.showDetailedBalance = !this.showDetailedBalance;
    if (this.showDetailedBalance) {
      this.loadDetailedBalance();
    }
  }
  
}
import { Component, OnInit } from '@angular/core';
import { LeaveRequestService } from '../../services/leave-request.service';

@Component({
  selector: 'app-leave-request',
  templateUrl: './leave-request.component.html',
  //styleUrls: ['./leave-request.component.css']
})
export class LeaveRequestComponent implements OnInit {
  leaveRequests: any[] = [];

  constructor(private leaveRequestService: LeaveRequestService) {}

  ngOnInit(): void {
    this.leaveRequestService.getLeaveRequests().subscribe((data: any[]) => {
      this.leaveRequests = data;
    });
  }
}

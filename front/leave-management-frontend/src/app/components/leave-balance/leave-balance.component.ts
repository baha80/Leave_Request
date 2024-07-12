import { Component, OnInit } from '@angular/core';
import { LeaveBalanceService } from '../../services/leave-balance.service';

@Component({
  selector: 'app-leave-balance',
  templateUrl: './leave-balance.component.html',
  //styleUrls: ['./leave-balance.component.css']
})
export class LeaveBalanceComponent implements OnInit {
  leaveBalances: any[] = [];

  constructor(private leaveBalanceService: LeaveBalanceService) {}

  ngOnInit(): void {
    this.leaveBalanceService.getLeaveBalances().subscribe((data: any[]) => {
      this.leaveBalances = data;
    });
  }
}

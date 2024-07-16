// src/app/employee.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
interface LeaveRequestPayload {
  employee: {
    id: string;
  };
  startDate: string;
  endDate: string;
  reason: string;
  leaveType: string;
}
interface Employee {
  id: string;
  username: string;
  // Add other properties if needed
}
@Injectable({
  providedIn: 'root',
})
export class LeaveRequestService {
  private baseUrl = 'http://localhost:5050';

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllEmployee`);
  }

  initializeLeaveBalance(
    userId: string,
    vacationDays: number,
    sickDays: number,
    personalDays: number
  ): Observable<any> {
    const url = `${this.baseUrl}/api/leave-balances/initialize?userId=${userId}&vacationDays=${vacationDays}&sickDays=${sickDays}&personalDays=${personalDays}`;
    return this.http.post(url, {});
  }

  getLeaveRequestsForEmployee(employeeId: string): Observable<any[]> {
    const url = `${this.baseUrl}/employee/${employeeId}/leave-requests`;
    return this.http.get<any[]>(url);
  }

  getVacationDays(userId: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/leave-balances/${userId}/vacation-days`);
  }
  
  approveLeaveRequest(leaveRequestId: number, approverId: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/${leaveRequestId}/approve/${approverId}`, {});
  }

  createLeaveRequest(leaveRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, leaveRequest);
  }
  rejectLeaveRequest(leaveRequestId: number, approverId: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/${leaveRequestId}/reject/${approverId}`, {});
  }
  getSickDays(userId: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/leave-balances/${userId}/sick-days`);
  }

  getPersonalDays(userId: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/leave-balances/${userId}/personal-days`);
  }
}

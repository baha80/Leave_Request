// src/app/employee.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
}

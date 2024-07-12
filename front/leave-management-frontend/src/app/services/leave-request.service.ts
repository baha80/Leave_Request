import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LeaveRequestService {
  private apiUrl = `${environment.apiUrl}/leave-requests`;

  constructor(private http: HttpClient) {}

  getLeaveRequests(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  createLeaveRequest(leaveRequest: any): Observable<any> {
    return this.http.post(this.apiUrl, leaveRequest);
  }
}

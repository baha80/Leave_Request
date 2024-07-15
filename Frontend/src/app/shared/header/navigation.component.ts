import { Component, AfterViewInit, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NgbDropdownModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';

declare var $: any;

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports:[NgbDropdownModule],
  templateUrl: './navigation.component.html'
})
export class NavigationComponent  {
  @Output() toggleSidebar = new EventEmitter<void>();

  public showSearch = false;

  constructor(private modalService: NgbModal , private router: Router) {}
  

  


  initializeLeaveRequest() {
    this.router.navigate(['/leaveRequest/init']);
  }

  navigateToCreateLeaveRequest() {
    this.router.navigate(['/create-leave-request']);
  }
}

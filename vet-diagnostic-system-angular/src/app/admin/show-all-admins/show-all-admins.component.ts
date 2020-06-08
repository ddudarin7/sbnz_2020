import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../core/services/admin.service';
import {Admin} from '../../shared/model/admin';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-show-all-admins',
  templateUrl: './show-all-admins.component.html',
  styleUrls: ['./show-all-admins.component.css']
})
export class ShowAllAdminsComponent implements OnInit {

  admins: Admin[];

  constructor(private adminService: AdminService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadAdmins();
  }

  loadAdmins(): void {
    this.adminService.getAdmins().then(
      res => {
        this.admins = res;
      }
    );
  }

  adminInfoClicked(recordNumber: string): void {
    this.router.navigate(['admin/home/admin-info/' + recordNumber]);
  }

  newAdminClicked(): void {
    this.router.navigate(['admin/home/create-new-admin']);
  }

  adminDeleteClicked(adminId: number): void {

    if (!confirm('Are you sure you want to delete a admin?')){
      return;
    }

    this.adminService.delete(adminId).then(
      res => {
        this.toastr.success('Admin successfully deleted.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Admin not deleted.');
      }
    );
  }

}

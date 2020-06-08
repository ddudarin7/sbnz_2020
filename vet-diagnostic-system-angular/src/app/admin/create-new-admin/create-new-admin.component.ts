import { Component, OnInit } from '@angular/core';
import {Admin} from '../../shared/model/admin';
import {AdminService} from '../../core/services/admin.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-create-new-admin',
  templateUrl: './create-new-admin.component.html',
  styleUrls: ['./create-new-admin.component.css']
})
export class CreateNewAdminComponent implements OnInit {

  admin: Admin = new Admin(null, '', '', '', '', null);

  dropdownSettings = {};

  constructor(private adminService: AdminService, private activatedRoute: ActivatedRoute,
              private router: Router, private toastr: ToastrService) {
  }

  ngOnInit(): void {

  }


  submitChanges(): void {
    if(this.admin.username != '' && this.admin.firstName != '' && this.admin.lastName != ''){
      this.adminService.add(this.admin).then(
        res => {
          this.toastr.success('Admin successfully added.');
          this.router.navigate(['admin/home/show-all-admins/']);
        }
      ).catch(
        err => {
          this.toastr.error('Admin addition failed');
        }
      );
    }else{
      this.toastr.error('All the fields must be filled');
    }
    
  }

}

import { Component, OnInit } from '@angular/core';

import {Vet} from '../../shared/model/vet';
import {VetService} from '../../core/services/vet.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import { TOUCH_BUFFER_MS } from '@angular/cdk/a11y';


@Component({
  selector: 'app-create-new-vet',
  templateUrl: './create-new-vet.component.html',
  styleUrls: ['./create-new-vet.component.css']
})
export class CreateNewVetComponent implements OnInit {

  vet: Vet = new Vet(null, '', '', '', '', null);

  dropdownSettings = {};

  constructor(private vetService: VetService, private activatedRoute: ActivatedRoute,
              private router: Router, private toastr: ToastrService) {
  }

  ngOnInit(): void {

  }


  submitChanges(): void {
    if(this.vet.username != '' && this.vet.firstName != '' && this.vet.lastName != ''){
      this.vetService.add(this.vet).then(
        res => {
          this.toastr.success('Vet successfully added.');
        }
      ).catch(
        err => {
          this.toastr.error('Vet addition failed');
        }
      );
    }else{
      this.toastr.error('All the fields must be filled');
    }
    
  }

}

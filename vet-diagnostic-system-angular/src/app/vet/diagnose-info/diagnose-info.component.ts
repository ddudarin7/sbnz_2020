import { Component, OnInit } from '@angular/core';
import {Patient} from '../../shared/model/patient';
import {Diagnose} from '../../shared/model/diagnose';
import {Disease} from '../../shared/model/disease';
import {ActivatedRoute, Router} from '@angular/router';
import {DiagnoseService} from '../../core/services/diagnose.service';
import {ToastrService} from 'ngx-toastr';
import {Vet} from '../../shared/model/vet';

@Component({
  selector: 'app-diagnose-info',
  templateUrl: './diagnose-info.component.html',
  styleUrls: ['./diagnose-info.component.css']
})
export class DiagnoseInfoComponent implements OnInit {

  diagnose = new Diagnose(null, new Disease(null, '', '', [],
    [], []), new Patient(null, '', '', null, '',
  null, [], [], []), new Vet(null, '', '',
    '', '', null), [], [],
    null, null, [], null);

  constructor(private activatedRoute: ActivatedRoute, private diagnoseService: DiagnoseService,
              private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.loadDiagnose(this.activatedRoute.snapshot.params.id);
  }

  loadDiagnose(diagnoseId: number){
    this.diagnoseService.getDiagnoseById(diagnoseId).then(
      res => {
        this.diagnose = res;
      }
    );
  }

  delete(): void {

    if (!confirm('Are you sure you want to delete diagnose?')){
      return;
    }

    this.diagnoseService.delete(this.diagnose.id).then(
      res => {
        this.toastr.success('Diagnose successfully deleted.');
        this.router.navigate(['vet/home']);
      }
    ).catch(
      err => {
        this.toastr.error('Diagnose not deleted.');
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import {VetService} from '../../core/services/vet.service';
import {Vet} from '../../shared/model/vet';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-show-all-vets',
  templateUrl: './show-all-vets.component.html',
  styleUrls: ['./show-all-vets.component.css']
})
export class ShowAllVetsComponent implements OnInit {

  vets: Vet[];

  constructor(private vetService: VetService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadVets();
  }

  loadVets(): void {
    this.vetService.getVets().then(
      res => {
        this.vets = res;
      }
    );
  }

  vetInfoClicked(recordNumber: string): void {
    this.router.navigate(['vet/home/vet-info/' + recordNumber]);
  }

  newVetClicked(): void {
    this.router.navigate(['admin/home/create-new-vet']);
  }

  vetDeleteClicked(vetId: number): void {

    if (!confirm('Are you sure you want to delete a vet?')){
      return;
    }

    this.vetService.delete(vetId).then(
      res => {
        this.toastr.success('Vet successfully deleted.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Vet not deleted.');
      }
    );
  }

}

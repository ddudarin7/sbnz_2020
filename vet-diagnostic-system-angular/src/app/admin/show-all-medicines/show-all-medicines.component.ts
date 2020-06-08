import { Component, OnInit } from '@angular/core';
import {MedicineService} from '../../core/services/medicine.service';
import {Medicine} from '../../shared/model/medicine';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-show-all-medicines',
  templateUrl: './show-all-medicines.component.html',
  styleUrls: ['./show-all-medicines.component.css']
})
export class ShowAllMedicinesComponent implements OnInit {

  medicines: Medicine[];

  constructor(private medicineService: MedicineService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadMedicines();
  }

  loadMedicines(): void {
    this.medicineService.getMedicines().then(
      res => {
        this.medicines = res;
      }
    );
  }

  medicineInfoClicked(recordNumber: string): void {
    this.router.navigate(['medicine/home/medicine-info/' + recordNumber]);
  }

  newMedicineClicked(): void {
    this.router.navigate(['admin/home/create-new-medicine']);
  }

  medicineDeleteClicked(medicineId: number): void {

    if (!confirm('Are you sure you want to delete a medicine?')){
      return;
    }

    this.medicineService.delete(medicineId).then(
      res => {
        this.toastr.success('Medicine successfully deleted.');
        location.reload();
      }
    ).catch(
      err => {
        this.toastr.error('Medicine not deleted.');
      }
    );
  }

}

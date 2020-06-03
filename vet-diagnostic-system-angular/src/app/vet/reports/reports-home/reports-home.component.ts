import { Component, OnInit } from '@angular/core';
import {SelectItem} from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reports-home',
  templateUrl: './reports-home.component.html',
  styleUrls: ['./reports-home.component.css']
})
export class ReportsHomeComponent implements OnInit {

  types: SelectItem[];
  selectedType: string;

  constructor(private route: ActivatedRoute,
    private router: Router) { 
    this.types = [
      {label: 'Chronic diseases', value: '1'},
      {label: 'Weak immune system', value: '2'}
    ];

    this.selectedType="";
  }

  ngOnInit(): void {
  }

  onChangeReport($event){
    if($event.value==="1"){
      this.router.navigate(['/vet/home/reports/chronic-diseases']);
    }else if($event.value==="2"){
      console.log("slab imunitet");
    }
    
  }

}

import { Component, OnInit } from '@angular/core';
import {Rule} from '../../shared/model/rule';
import {RuleService} from '../../core/services/rule.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-rules-input',
  templateUrl: './rules-input.component.html',
  styleUrls: ['./rules-input.component.css']
})
export class RulesInputComponent implements OnInit {

  rule: Rule = new Rule('');

  constructor(private ruleService: RuleService, private activatedRoute: ActivatedRoute,
    private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadRules();
  }

  loadRules(): void {
    this.ruleService.getRules().then(
      res => {
        this.rule = res;
      }
    );
  }

  submitChanges(): void {
    if(this.rule.droolsRuleCode != ''){
      this.ruleService.add(this.rule).then(
        res => {
          this.toastr.success('Rule successfully added.');
          this.router.navigate(['admin/home/show-all-vets/']);

        }
      ).catch(
        err => {
          this.toastr.error('Rule addition failed');
        }
      );
    }else{
      this.toastr.error('Field must not be empty!');
    }
    
  }

}

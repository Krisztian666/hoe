import { Component, OnInit } from '@angular/core';
import {Hero} from '../../../../services/hero/model/hero';
import {Empire} from '../../../../services/empire/model/empire';
import {EmpireHandlerResourceService} from '../../../../services/empire/api/empireHandlerResource.service';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-new-empire',
  templateUrl: './new-empire.component.html',
  styleUrls: ['./new-empire.component.scss']
})
export class NewEmpireComponent implements OnInit {

  public empire= new Object() as Empire;
  constructor(private router: Router, private empireService: EmpireHandlerResourceService) { }

  ngOnInit(): void {
  }

  public save(){
    this.empireService.addEmpire(this.empire).subscribe(()=>{this.router.navigateByUrl("/empire/list")});
  }

}

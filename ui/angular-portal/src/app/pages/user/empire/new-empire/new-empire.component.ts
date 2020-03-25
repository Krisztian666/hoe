import { Component, OnInit } from '@angular/core';
import {Hero} from '../../../../services/hero/model/hero';
import {Empire} from '../../../../services/empire/model/empire';
import {EmpireHandlerResourceService} from '../../../../services/empire/api/empireHandlerResource.service';

@Component({
  selector: 'app-new-empire',
  templateUrl: './new-empire.component.html',
  styleUrls: ['./new-empire.component.scss']
})
export class NewEmpireComponent implements OnInit {

  public empire= new Object() as Empire;
  constructor(private empireService: EmpireHandlerResourceService) { }

  ngOnInit(): void {
  }

  public save(){
    this.empireService.addEmpire(this.empire).subscribe(()=>{});
  }

}

import {Component, Inject, OnInit} from '@angular/core';
import {Hero} from '../../../../../services/hero/model/hero';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {HeroResourceService} from '../../../../../services/hero/api/heroResource.service';
import {EmpireHandlerResourceService} from '../../../../../services/empire/api/empireHandlerResource.service';
import {Empire} from '../../../../../services/empire/model/empire';

@Component({
  selector: 'app-attack-empire-modal',
  templateUrl: './attack-empire-modal.component.html',
  styleUrls: ['./attack-empire-modal.component.scss']
})
export class AttackEmpireModalComponent implements OnInit {

  public empires :Array<Empire>;

  constructor(
    public dialogRef: MatDialogRef<AttackEmpireModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Object,
    private empireResourceService: EmpireHandlerResourceService) { }

  ngOnInit(): void {
    this.empireResourceService.getAllEmpires().subscribe(pData => {
      this.empires = pData;
    });
  }

  public attack(pEmpire: Empire){
    let hero = this.data['hero'];
    this.empireResourceService.attack(pEmpire.id, hero.id).subscribe(pSucces=>{alert(pSucces);});
  }

  public onClose(): void {
    this.dialogRef.close();
  }
}

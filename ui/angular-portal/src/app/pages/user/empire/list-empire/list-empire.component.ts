import { Component, OnInit } from '@angular/core';
import {EmpireHandlerResourceService} from '../../../../services/empire/api/empireHandlerResource.service';
import {Empire} from '../../../../services/empire/model/empire';
import {SpeciesDialogComponent} from '../../../index/species-dialog/species-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {ProtectEmpireModalComponent} from './protect-empire-modal/protect-empire-modal.component';
import {Hero} from '../../../../services/hero/model/hero';
import {SecurityGuard} from '../../../../services/empire/model/securityGuard';
import {ProtectHeroModalComponent} from './protect-hero-modal/protect-hero-modal.component';

@Component({
  selector: 'app-list-empire',
  templateUrl: './list-empire.component.html',
  styleUrls: ['./list-empire.component.scss']
})
export class ListEmpireComponent implements OnInit {
  public selectedHero= new Object() as Hero;
  public empires: Array<Empire>;
  constructor(private empireService: EmpireHandlerResourceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.empireService.getAllMyEmpires().subscribe(pData=>{this.empires = pData;});
  }


  public openAddProtectedDialog(pEmpire: Empire): void {
    const dialogRef = this.dialog.open(ProtectEmpireModalComponent, {
      width: '250px', data: {hero: this.selectedHero}});
    dialogRef.afterClosed().subscribe(result => {
      this.selectedHero = result;
      let guard = new Object() as SecurityGuard;
      guard.heroid = this.selectedHero.id;
      guard.empireid = pEmpire.id;
      this.empireService.setSecurityGuard(guard).subscribe(pEmpire=>{this.empireService.getAllMyEmpires().subscribe(pData=>{this.empires = pData;});});
    });
  }


  public openGetProtectedDialog(pEmpire: Empire): void {
    const dialogRef = this.dialog.open(ProtectHeroModalComponent, {
      width: '250px', data: {empire: pEmpire}});
    dialogRef.afterClosed().subscribe(result => {});
  }

  public doDelete(pEmpire: Empire): void {
    this.empireService.deleteEmpireById(pEmpire.id).subscribe(()=>{
      this.empireService.getAllMyEmpires().subscribe(pData=>{this.empires = pData;});
    })
  }

}

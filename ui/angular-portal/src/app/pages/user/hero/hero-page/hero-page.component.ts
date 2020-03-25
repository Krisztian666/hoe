import { Component, OnInit } from '@angular/core';
import {HeroResourceService} from '../../../../services/hero/api/heroResource.service';
import {Hero} from '../../../../services/hero/model/hero';
import {Empire} from '../../../../services/empire/model/empire';
import {ProtectHeroModalComponent} from '../../empire/list-empire/protect-hero-modal/protect-hero-modal.component';
import {AttackEmpireModalComponent} from './attack-empire-modal/attack-empire-modal.component';
import {EmpireHandlerResourceService} from '../../../../services/empire/api/empireHandlerResource.service';
import {MatDialog} from '@angular/material/dialog';
import {HeroInfoComponent} from './hero-info/hero-info.component';

@Component({
  selector: 'app-hero-page',
  templateUrl: './hero-page.component.html',
  styleUrls: ['./hero-page.component.scss']
})
export class HeroPageComponent implements OnInit {

  public heroes: Array<Hero>;

  constructor(private heroService: HeroResourceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.heroService.getAllHeroes().subscribe(pData=>{this.heroes=pData;});
  }


  public openAttackDialog(pHero: Hero): void {
    const dialogRef = this.dialog.open(AttackEmpireModalComponent, {
      width: '250px', data: {hero: pHero}});
    dialogRef.afterClosed().subscribe(result => {});
  }

  public openHeroInfoDialog(pHero: Hero): void {
    const dialogRef = this.dialog.open(HeroInfoComponent, {
      width: '250px', data: {hero: pHero}});
    dialogRef.afterClosed().subscribe(result => {});
  }

  public onDelete(pHero: Hero){
    this.heroService.removeHeroByiD(pHero.id).subscribe(()=>{
      this.heroService.getAllHeroes().subscribe(pData=>{this.heroes=pData;});});
  }

}

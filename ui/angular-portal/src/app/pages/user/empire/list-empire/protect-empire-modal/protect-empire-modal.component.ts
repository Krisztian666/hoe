import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {HeroResourceService} from '../../../../../services/hero/api/heroResource.service';
import {Hero} from '../../../../../services/hero/model/hero';

@Component({
  selector: 'app-protect-empire-modal',
  templateUrl: './protect-empire-modal.component.html',
  styleUrls: ['./protect-empire-modal.component.scss']
})
export class ProtectEmpireModalComponent implements OnInit {
  public heroes: Array<Hero>
  constructor(
    public dialogRef: MatDialogRef<ProtectEmpireModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Hero,
    private heroResourceService: HeroResourceService) { }

  ngOnInit(): void {
    this.heroResourceService.getAllHeroes().subscribe(pData=>{this.heroes= pData;});
  }

  public onClose(): void {
    this.dialogRef.close();
  }
}

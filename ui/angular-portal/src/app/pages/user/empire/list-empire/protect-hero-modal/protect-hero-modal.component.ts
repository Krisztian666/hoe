import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Hero} from '../../../../../services/hero/model/hero';
import {HeroResourceService} from '../../../../../services/hero/api/heroResource.service';
import {Empire} from '../../../../../services/empire/model/empire';

@Component({
  selector: 'app-protect-hero-modal',
  templateUrl: './protect-hero-modal.component.html',
  styleUrls: ['./protect-hero-modal.component.scss']
})
export class ProtectHeroModalComponent implements OnInit {

  public hero= new Object() as Hero;

  constructor(
    public dialogRef: MatDialogRef<ProtectHeroModalComponent>,
    @Inject(MAT_DIALOG_DATA) public empire: Object,
    private heroResourceService: HeroResourceService) { }

ngOnInit(): void {
    this.heroResourceService.getFullHeroById(this.empire['empire'].protect.heroid).subscribe(pData=>{this.hero= pData;});
}

public onClose(): void {
  this.dialogRef.close();
}
}

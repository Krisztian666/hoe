import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {EmpireHandlerResourceService} from '../../../../../services/empire/api/empireHandlerResource.service';
import {HeroResourceService} from '../../../../../services/hero/api/heroResource.service';
import {Hero} from '../../../../../services/hero/model/hero';

@Component({
  selector: 'app-hero-info',
  templateUrl: './hero-info.component.html',
  styleUrls: ['./hero-info.component.scss']
})
export class HeroInfoComponent implements OnInit {

  public hero: Hero;

  constructor(
    public dialogRef: MatDialogRef<HeroInfoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Object,
    private heroService: HeroResourceService) { }

  ngOnInit(): void {
    this.heroService.getFullHeroById(this.data['hero'].id).subscribe(pData=>{ this.hero = pData;});
  }


  public onClose(): void {
    this.dialogRef.close();
  }

}

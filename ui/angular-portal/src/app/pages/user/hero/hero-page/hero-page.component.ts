import { Component, OnInit } from '@angular/core';
import {HeroResourceService} from '../../../../services/hero/api/heroResource.service';
import {Hero} from '../../../../services/hero/model/hero';

@Component({
  selector: 'app-hero-page',
  templateUrl: './hero-page.component.html',
  styleUrls: ['./hero-page.component.scss']
})
export class HeroPageComponent implements OnInit {

  public heroes: Array<Hero>;

  constructor(private heroService: HeroResourceService) { }

  ngOnInit(): void {
    this.heroService.getAllHeroes().subscribe(pData=>{this.heroes=pData;});
  }

  public load(pHero: Hero){
    this.heroService.getFullHeroById(pHero.id).subscribe(pData=>{pHero.hybrid=pData.hybrid;})
  }
}

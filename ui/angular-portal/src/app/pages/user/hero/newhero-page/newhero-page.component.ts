import { Component, OnInit } from '@angular/core';
import {SpeciesResourceService} from '../../../../services/species/api/speciesResource.service';
import {HeroResourceService} from '../../../../services/hero/api/heroResource.service';
import {Hero} from '../../../../services/hero/model/hero';
import {Species} from '../../../../services/hero/model/species';
import {Hybrid} from '../../../../services/hero/model/hybrid';
import {Router} from '@angular/router';

@Component({
  selector: 'app-newhero-page',
  templateUrl: './newhero-page.component.html',
  styleUrls: ['./newhero-page.component.scss']
})
export class NewheroPageComponent implements OnInit {

  public hero= new Object() as Hero;
  public species: Array<Species>
  constructor(private router: Router, private speciesService: SpeciesResourceService, private heroSerice: HeroResourceService) { }

  ngOnInit(): void {
    this.hero.hybrid= new Array<Hybrid>();
    this.speciesService.getAllSpecies().subscribe(pData=>{
      this.species=pData;
      for(let tmp in this.species){
        let hybrid = new Object() as Hybrid;
        hybrid.species = this.species[tmp];
        this.hero.hybrid.push(hybrid);
      }
    });
  }

  public save(){

    this.heroSerice.addHero(this.hero).subscribe(()=>{});
  }
}

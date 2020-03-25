import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {SpeciesResourceService} from '../../../services/species/api/speciesResource.service';
import {Species} from '../../../services/species/model/species';

@Component({
  selector: 'app-species-dialog',
  templateUrl: './species-dialog.component.html',
  styleUrls: ['./species-dialog.component.scss']
})
export class SpeciesDialogComponent implements OnInit {

  public species: Array<Species>;

  constructor( public dialogRef: MatDialogRef<SpeciesDialogComponent>,
               private speciesService: SpeciesResourceService) { }

  ngOnInit(): void {
    this.speciesService.getAllSpecies().subscribe((data)=>{this.species=data;});

  }

  public onClose(): void {
    this.dialogRef.close();
  }
}

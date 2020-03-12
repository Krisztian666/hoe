import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {SpeciesDialogComponent} from './species-dialog/species-dialog.component';

@Component({
  selector: 'app-index-page',
  templateUrl: './index-page.component.html',
  styleUrls: ['./index-page.component.scss']
})
export class IndexPageComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }


  public openSpeciesDialog(): void {
    const dialogRef = this.dialog.open(SpeciesDialogComponent, {
      width: '250px'});

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}

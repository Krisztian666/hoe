import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-hero-toplist-dialog',
  templateUrl: './hero-toplist-dialog.component.html',
  styleUrls: ['./hero-toplist-dialog.component.scss']
})
export class HeroToplistDialogComponent implements OnInit {

  constructor( public dialogRef: MatDialogRef<HeroToplistDialogComponent>) { }

  ngOnInit(): void {
  }

  public onClose(): void {
    this.dialogRef.close();
  }
}

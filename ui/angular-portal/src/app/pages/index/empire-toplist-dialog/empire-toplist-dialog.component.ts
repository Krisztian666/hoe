import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-empire-toplist-dialog',
  templateUrl: './empire-toplist-dialog.component.html',
  styleUrls: ['./empire-toplist-dialog.component.scss']
})
export class EmpireToplistDialogComponent implements OnInit {

  constructor( public dialogRef: MatDialogRef<EmpireToplistDialogComponent>) { }

  ngOnInit(): void {
  }

  public onClose(): void {
    this.dialogRef.close();
  }
}

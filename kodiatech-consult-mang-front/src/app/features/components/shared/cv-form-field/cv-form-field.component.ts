import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cv-form-field',
  templateUrl: './cv-form-field.component.html',
  styleUrls: ['./cv-form-field.component.scss']
})
export class CvFormFieldComponent implements OnInit {

  @Input() displayedColumns:any;
  @Input() dataSource :any;;

  @Input() entete:any




  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor() { }

  ngOnInit(): void {
  }
public deleteElement(el:any){

}
}

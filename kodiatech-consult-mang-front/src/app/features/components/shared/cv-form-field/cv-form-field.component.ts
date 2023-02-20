import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cv-form-field',
  templateUrl: './cv-form-field.component.html',
  styleUrls: ['./cv-form-field.component.scss'],
  // Need to remove view encapsulation so that the custom tooltip style defined in
  // `tooltip-custom-class-example.css` will not be scoped to this component's view.
  encapsulation: ViewEncapsulation.None,
})
export class CvFormFieldComponent implements OnInit {
  @Input() displayedColumns: any;
  @Input() dataSource: any;
  @Input() columns: any;
  @Input() messageTooltip: any;
  @Input()titreTable:any;

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

//
@Output() addNewElement = new EventEmitter<string>();

@Output() modiffElement = new EventEmitter<string>();

openModalmodifElement(value: string) {
  console.warn("------------------------------------>value---"+value)
  this.modiffElement.emit(value);
}
openModalAddElement(){
  this.addNewElement.emit()
}

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {}

  public deleteElement(el: any) {}

}

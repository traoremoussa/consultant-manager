import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [ ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,

  ],
  exports:[
    // on a exporter vu que sharemodule, serait presque partout
    MaterialModule,
    ReactiveFormsModule,
  ]
})
export class SharedModule { }

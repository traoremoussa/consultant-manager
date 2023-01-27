import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { TimelineModule } from 'primeng/timeline';

@NgModule({
  declarations: [ ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,

    TimelineModule
  ],
  exports:[
    // on a exporter vu que sharemodule, serait presque partout
    MaterialModule,
    ReactiveFormsModule,
    //NGprimeng
    TimelineModule
  ]
})
export class SharedModule { }

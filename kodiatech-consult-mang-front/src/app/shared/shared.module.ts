import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { ReactiveFormsModule } from '@angular/forms';
// PrimeNG
import { TimelineModule } from 'primeng/timeline';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';


@NgModule({
  declarations: [ ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,

    TimelineModule,
    ToastModule
  ],
  exports:[
    // on a exporter vu que sharemodule, serait presque partout
    MaterialModule,
    ReactiveFormsModule,
    //NGprimeng
    TimelineModule,
    ToastModule
  ],
  providers: [
    MessageService
  ]
})
export class SharedModule { }

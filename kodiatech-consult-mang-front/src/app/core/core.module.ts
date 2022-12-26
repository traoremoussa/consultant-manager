import { SharedModule } from './../shared/shared.module';
import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';

import { RouterModule } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { httpInterceptorProviders } from './interceptors/multiple-interceptor';

import * as fr from '@angular/common/locales/fr';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,

  ],
  providers:[
    // la langue
    { provide: LOCALE_ID, useValue: 'fr-FR' },
  //intercepteur
  httpInterceptorProviders
 ],
  exports:[HeaderComponent,FooterComponent,BrowserAnimationsModule]
})
export class CoreModule {
  constructor() {
    //pour langue que j'ai deplacé dans le core module
    registerLocaleData(fr.default);
  }
}

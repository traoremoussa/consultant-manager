import { CoreRoutingModule } from './core-routing.module';
import { SharedModule } from './../shared/shared.module';
import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';

import { RouterModule } from '@angular/router';

import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { httpInterceptorProviders } from './interceptors/multiple-interceptor';

import * as fr from '@angular/common/locales/fr';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
// for HttpClient import:
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import { BoardUserComponent } from './components/board-user/board-user.component';

@NgModule({ declarations: [
        HeaderComponent,
        FooterComponent,
        PageNotFoundComponent,
        BoardUserComponent
    ],
    //n'oublie pas d'exporter
    exports: [
        HeaderComponent,
        FooterComponent,
        BrowserAnimationsModule,
        CoreRoutingModule,
        LoadingBarHttpClientModule
    ], imports: [CommonModule,
        SharedModule,
        RouterModule,
        BrowserAnimationsModule,
        CoreRoutingModule,
        LoadingBarHttpClientModule], providers: [
        // la langue
        { provide: LOCALE_ID, useValue: 'fr-FR' },
        //intercepteur
        httpInterceptorProviders,
        //provideHttpClient(withInterceptorsFromDi())
    ] })
export class CoreModule {
  constructor() {
    //pour langue que j'ai deplacé dans le core module
    registerLocaleData(fr.default);
  }
}

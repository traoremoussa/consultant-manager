import { FeaturesModule } from './features/features.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { provideAnimations } from '@angular/platform-browser/animations';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeng/themes/aura';

@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    CoreModule,
    SharedModule,
    FeaturesModule
  ],
  providers: [ provideAnimations(),
    providePrimeNG({
      theme: {
        preset: Aura,
        options: {
          cssLayer: false
        }
      }
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

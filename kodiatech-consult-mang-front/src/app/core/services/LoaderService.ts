import { Injectable, signal, computed } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LoaderService {

  // compteur (important pour requêtes multiples)
  private count = signal(0);

  // état lisible par UI
  readonly loading = computed(() => this.count() > 0);

  show() {
    this.count.update(v => v + 1);
  }

  hide() {
   this.count.update(v => Math.max(0, v - 1));
  }

  reset() {
    this.count.set(0);
  }
}

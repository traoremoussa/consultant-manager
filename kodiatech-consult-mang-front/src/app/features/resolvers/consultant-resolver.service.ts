import { Injectable } from '@angular/core';
import { ConsultantService } from '../services/consultant.service';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Consultant } from '../models/consultant-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConsultantResolverService implements Resolve<Consultant> {
  constructor(private consultantService: ConsultantService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Consultant | Observable<Consultant> | Promise<Consultant> {
    throw new Error('Method not implemented.');
  }

  //https://angular.fr/routing/resolver
  // https://www.youtube.com/watch?v=62ooGV-5kWc&ab_channel=LEARNINGPARTNER
}

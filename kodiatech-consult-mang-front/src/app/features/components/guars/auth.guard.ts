import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { StorageService } from '../../services/storage.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private storageService: StorageService,
              private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const token = this.storageService.getUser();
   //alert(token.id)
    if (token.id) {
      return true;
    } else {
      this.router.navigateByUrl('features/login');
      return false;
    }
  }
}


/*  return !!token;

Pour comprendre pourquoi, remarquez d'abord que  !token  retourne  true
 si le token n'existe pas (comme dans  if (!token)  ), et donc  !!token  retourne l'inverse, donc  true  si le token existe.

Le double bang  !!  est une technique pour transformer une valeur "truthy" en  true  .
*/

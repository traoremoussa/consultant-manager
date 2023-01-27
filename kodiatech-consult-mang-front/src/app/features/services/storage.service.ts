import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';
/**
 * ici on a utiliser pour windows juste fentetre quand tu change on perd donner
 *
 * ya pour cookies
 * ya localStorage
 * on a pas besion de grande chose
/**
 * Injectable
 */
@Injectable({
  providedIn: 'root'
})

export class StorageService {
  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }

  public remove(){
    window.sessionStorage.removeItem(USER_KEY);
  }
  public getToken(){
    const user = this.getUser();
    if (user) {
      return user.authenticationToken;
    }
    return '';
  }
}

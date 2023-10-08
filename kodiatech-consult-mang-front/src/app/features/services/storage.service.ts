import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';
const TOKEN_KEY = 'auth-token';
const REFRESHTOKEN_KEY = 'auth-refreshtoken';
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

  //---23/08/2023 ============================================
  signOut(): void {
    window.sessionStorage.clear();
  }
/**
 *
 * @param token
 */
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);

    const user = this.getUser();
    if (user.id) {
      //An alternative to using Object.assign()
      /*
      - on fusionne le contenu de l'objet user et attribut auth...
      */
      this.saveUser({ ...user, authenticationToken: token });
    }
  }
  /**
   *
   * @returns
   */
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
/**
 *
 * @param token
 */
  public saveRefreshToken(token: string): void {
    window.sessionStorage.removeItem(REFRESHTOKEN_KEY);
    window.sessionStorage.setItem(REFRESHTOKEN_KEY, token);
  }
/**
 *
 * @returns
 */
  public getRefreshToken(): string | null {
    return window.sessionStorage.getItem(REFRESHTOKEN_KEY);
  }
  //============================================================================

  //TODO OLD --------------------------------------------
   /*
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
  */

}

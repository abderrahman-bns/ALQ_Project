import { Injectable } from '@angular/core';
import { User } from './models';

const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root',
})
export class StorageService {
  saveUser(user: User): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  getUser(): User | null {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }

  isLoggedIn(): boolean {
    const user = this.getUser();
    if (user) {
      return true;
    }
    return false;
  }

  clear(): void {
    window.sessionStorage.clear();
    localStorage.clear();
  }

  getToken(): string {
    const user = this.getUser();
    if (user) {
      return user.token;
    }
    return '';
  }
}

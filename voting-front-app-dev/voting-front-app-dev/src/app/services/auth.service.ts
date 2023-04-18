import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginRequest, UserRegitrationRequest } from './models';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/v1/auth';
const API = 'http://localhost:8080/api/v1/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  register(userRegisterationRequest: UserRegitrationRequest): Observable<any> {
    return this.http.post(
      API + 'users',
      {
        firstName: userRegisterationRequest.firstName,
        lastName: userRegisterationRequest.lastName,
        email: userRegisterationRequest.email,
        password: userRegisterationRequest.password,
        roles: userRegisterationRequest.roles,
      },
      httpOptions
    );
  }

  login(userLoginRequest: UserLoginRequest): Observable<any> {
    return this.http.post(
      AUTH_API,
      {
        email: userLoginRequest.email,
        password: userLoginRequest.password,
      },
      httpOptions
    );
  }
}

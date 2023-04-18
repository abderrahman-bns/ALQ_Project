import { Component } from '@angular/core';
import { UserLoginModel } from '../models/ILoginForm';
import { LoginForm } from '../form/LoginForm';
import { AuthService } from 'src/app/services/auth.service';
import { IHttpResponses } from 'src/app/shared/models/IResponses';
import { StorageService } from 'src/app/services/storage.sevice';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  userLoginModel: UserLoginModel = {
    email: '',
    password: '',
  };

  loginForm: LoginForm = new LoginForm(this.userLoginModel);

  constructor(
    private authService: AuthService,
    private storageService: StorageService
  ) {}
  handleLogin() {
    if (this.loginForm.valid) {
      this.authService
        .login({
          email: this.loginForm.value.email,
          password: this.loginForm.value.password,
        })
        .subscribe((response: IHttpResponses) => {
          if (response.status === 'Success') {
            this.storageService.saveUser({
              id: response.data.id,
              firstName: response.data.firstName,
              lastName: response.data.lastName,
              email: response.data.email,
              roles: response.data.roles,
              token: response.data.jwt,
            });
          } else {
            alert(response.message);
          }
        });
    }
  }
}

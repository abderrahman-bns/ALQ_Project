import { Component } from '@angular/core';
import { UserRegisterModel } from '../models/IRegisterForm';
import { RegisterForm } from '../form/RegisterForm';
import { AuthService } from 'src/app/services/auth.service';
import { IHttpResponses } from 'src/app/shared/models/IResponses';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  userRegisterModel: UserRegisterModel = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    confirmPassword: '',
  };

  registerForm: RegisterForm = new RegisterForm(this.userRegisterModel);

  constructor(private authService: AuthService) {}

  handleRegistration() {
    if (this.registerForm.valid) {
      this.authService
        .register({
          firstName: this.registerForm.value.firstname,
          lastName: this.registerForm.value.lastname,
          email: this.registerForm.value.email,
          password: this.registerForm.value.password,
          roles: [],
        })
        .subscribe((response: IHttpResponses) => alert(response.message));
    }
  }
}

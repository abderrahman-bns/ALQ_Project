import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { UserRegisterModel } from '../models/IRegisterForm';

export class RegisterForm extends FormGroup {
  readonly firstname = this.get('firstname') as FormControl;
  readonly lastname = this.get('lastname') as FormControl;
  readonly email = this.get('email') as FormControl;
  readonly password = this.get('password') as FormControl;
  readonly confirmPassword = this.get('confirmPassword') as FormControl;
  constructor(
    readonly model: UserRegisterModel,
    readonly fb: FormBuilder = new FormBuilder()
  ) {
    super(
      fb.group({
        firstname: [
          model?.firstname,
          [
            Validators.required,
            Validators.minLength(2),
            Validators.maxLength(30),
          ],
        ],
        lastname: [
          model?.lastname,
          [
            Validators.required,
            Validators.minLength(2),
            Validators.maxLength(30),
          ],
        ],
        email: [model?.email, [Validators.required, Validators.email]],
        password: [
          model?.password,
          [
            Validators.required,
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(16),
          ],
        ],
        confirmPassword: [
          model?.confirmPassword,
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(16),
          ],
        ],
      }).controls
    );
  }
}

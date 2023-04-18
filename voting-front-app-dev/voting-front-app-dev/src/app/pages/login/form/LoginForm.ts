import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UserLoginModel } from '../models/ILoginForm';

export class LoginForm extends FormGroup {
  readonly email = this.get('email') as FormControl;
  readonly password = this.get('password') as FormControl;
  constructor(
    readonly model: UserLoginModel,
    readonly fb: FormBuilder = new FormBuilder()
  ) {
    super(
      fb.group({
        email: [model?.email, [Validators.required, Validators.email]],
        password: [
          model?.password,
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

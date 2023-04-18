import { Pipe, PipeTransform } from '@angular/core';
import { ValidationErrors } from '@angular/forms';

export const validationsMessage: ValidationErrors = {
  required: 'This field is required',
  email: 'This field must be a valid email',
  minlength: 'This field must be at least 3 characters',
  maxlength: 'This field must be less than 10 characters',
  date: 'This field must be a valid date',
};

@Pipe({
  name: 'errorsKeys',
})
export class ErrorsKeysPipe implements PipeTransform {
  transform(errors: ValidationErrors): string[] {
    if (!errors) {
      return [];
    }
    return Object.keys(errors);
  }
}

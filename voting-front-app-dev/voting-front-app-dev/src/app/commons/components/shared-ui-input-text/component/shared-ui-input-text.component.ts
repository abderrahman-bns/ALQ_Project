import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { validationsMessage } from 'src/app/shared/pipes/ErrorsKeys.pipe';

@Component({
  selector: 'app-shared-ui-input-text',
  templateUrl: './shared-ui-input-text.component.html',
  styleUrls: ['./shared-ui-input-text.component.css'],
})
export class SharedUiInputTextComponent {
  @Input() label = '';
  @Input() placeholder = '';
  @Input() messages = validationsMessage;
  @Input() control: FormControl = new FormControl();
  @Input() widthExp: number = 200;
  @Input() isPassword: boolean = false;
  hide: boolean = true;
}

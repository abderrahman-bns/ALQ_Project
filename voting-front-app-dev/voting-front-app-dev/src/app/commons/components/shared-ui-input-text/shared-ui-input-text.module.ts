import { NgModule } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from 'src/app/shared/shared.module';
import { SharedUiInputTextComponent } from './component/shared-ui-input-text.component';

@NgModule({
  declarations: [SharedUiInputTextComponent],
  imports: [SharedModule, MatFormFieldModule, MatInputModule, MatIconModule],
  exports: [SharedUiInputTextComponent],
})
export class SharedUiInputTextModule {}

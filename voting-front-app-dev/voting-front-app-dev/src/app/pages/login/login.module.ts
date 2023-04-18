import { NgModule } from '@angular/core';
import { LoginComponent } from './component/login.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { SharedUiInputTextModule } from 'src/app/commons/components/shared-ui-input-text/shared-ui-input-text.module';

@NgModule({
  imports: [
    MatButtonModule,
    SharedModule,
    MatCardModule,
    SharedUiInputTextModule,
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent],
})
export class LoginModule {}

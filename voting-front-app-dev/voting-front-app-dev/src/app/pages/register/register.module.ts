import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { RegisterComponent } from './component/register.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SharedUiInputTextModule } from 'src/app/commons/components/shared-ui-input-text/shared-ui-input-text.module';
@NgModule({
  imports: [
    SharedModule,
    MatCardModule,
    SharedUiInputTextModule,
    MatButtonModule,
  ],
  declarations: [RegisterComponent],
  exports: [RegisterComponent],
})
export class RegisterModule {}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorsKeysPipe } from './pipes/ErrorsKeys.pipe';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [ErrorsKeysPipe],
  imports: [CommonModule, ReactiveFormsModule, FormsModule, HttpClientModule],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ErrorsKeysPipe,
    HttpClientModule,
  ],
})
export class SharedModule {}

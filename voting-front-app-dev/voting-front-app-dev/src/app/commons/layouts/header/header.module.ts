import { NgModule } from '@angular/core';
import { HeaderComponent } from './component/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
@NgModule({
  exports: [HeaderComponent],
  declarations: [HeaderComponent],
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, MatMenuModule],
})
export class HeaderModule {}

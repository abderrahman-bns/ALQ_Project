import { NgModule } from '@angular/core';
import { PostUiComponent } from './component/post-ui.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [SharedModule, MatCardModule, MatButtonModule],
  exports: [PostUiComponent],
  declarations: [PostUiComponent],
})
export class PostUiModule {}

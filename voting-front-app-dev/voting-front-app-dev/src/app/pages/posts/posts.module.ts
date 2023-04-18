import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { PostsComponent } from './components/posts.component';
import { PostUiModule } from 'src/app/commons/components/post-ui/post-ui.module';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [SharedModule, PostUiModule, MatButtonModule],
  exports: [PostsComponent],
  declarations: [PostsComponent],
})
export class PostsModule {}

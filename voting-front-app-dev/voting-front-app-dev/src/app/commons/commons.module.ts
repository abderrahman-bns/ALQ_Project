import { NgModule } from '@angular/core';
import { SharedUiInputTextModule } from './components/shared-ui-input-text/shared-ui-input-text.module';
import { PostUiModule } from './components/post-ui/post-ui.module';
import { HeaderModule } from './layouts/header/header.module';
import { FooterModule } from './layouts/footer/footer.module';

@NgModule({
  imports: [SharedUiInputTextModule, PostUiModule, HeaderModule, FooterModule],
  exports: [SharedUiInputTextModule, PostUiModule, HeaderModule, FooterModule],
  declarations: [],
})
export class CommonsModule {}

import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-post-ui',
  templateUrl: './post-ui.component.html',
  styleUrls: ['./post-ui.component.css'],
})
export class PostUiComponent {
  @Input() postTitle: string = 'Post Title';
  @Input() postDescription: string = 'Post Description';
}

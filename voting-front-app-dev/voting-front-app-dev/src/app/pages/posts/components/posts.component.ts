import { Component, OnInit } from '@angular/core';
import { Posts } from 'src/app/services/models';
import { PostsService } from 'src/app/services/posts.service';
import { IHttpResponses } from 'src/app/shared/models/IResponses';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
})
export class PostsComponent implements OnInit {
  posts: Posts[] = [];
  constructor(private postsService: PostsService) {}
  ngOnInit(): void {
    this.postsService.getAllPosts().subscribe((response: IHttpResponses) => {
      if (response.status === 'Success') {
        this.posts = response.data;
      }
    });
  }
}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IHttpResponses } from '../shared/models/IResponses';

const API_PATH = 'http://localhost:8080/api/v1/posts';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  params: new HttpParams().append('page', '1').append('limite', '25'),
};

@Injectable({
  providedIn: 'root',
})
export class PostsService {
  constructor(private http: HttpClient) {}

  getAllPosts(): Observable<IHttpResponses> {
    return this.http.get<IHttpResponses>(API_PATH, httpOptions);
  }
}

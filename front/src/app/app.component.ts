import { Component } from '@angular/core';
import { ClientService } from './client.service';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {

  public foods;
  public books;

  constructor(private _clientService: ClientService) {}

  ngOnInit() {
    this.getBooks();
  }

  getFoods() {
    this._clientService.getFoods().subscribe(
      data => { 
        console.log('logged');
        this.foods = data },
      err => console.error(err),
      () => console.log('done loading foods')
    );
  }

  getBooks() {
    this._clientService.getBooks().subscribe(
      data => { this.books = data },
      err => console.error(err),
      () => console.log('done loading books'));
  }

}

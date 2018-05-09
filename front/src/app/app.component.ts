import { Component } from '@angular/core';
import { ClientService } from './client.service';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {

  public items;

  constructor(private _clientService: ClientService) { }

  ngOnInit() {
    this.getItems();
  }

  getItems() {
    this._clientService.getItems().subscribe(
      data => { this.items = data },
      err => console.error(err),
      () => console.log('done loading items')
    );
  }

}

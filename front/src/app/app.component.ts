import { Component } from '@angular/core';
import { ClientService } from './client.service';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {

  public foods;

  constructor(private _clientService: ClientService) {}

  ngOnInit() {
    this.getFoods();
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

}

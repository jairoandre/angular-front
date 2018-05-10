import { Component } from '@angular/core';
import { ClientService } from './client.service';
import { Observable } from 'rxjs/Rx';
import { Order } from './order';
import { OrderPayload } from './order-payload';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {

  public order: Order;

  public orderId: number;

  public payload: OrderPayload;

  public total: number;
  public exchange: number;

  public message: string;
  public messageColor: string;

  constructor(private _clientService: ClientService) {}

  createOrder() {
    this.message = null;
    this._clientService.createOrder().subscribe(
      data => {
        this.orderId = data as number;
        this.getOrder();
      },
      err => console.error(err),
      () => console.log('new order created')
    );
  }

  getOrder() {
    this.message = null;
    this._clientService.getOrder(this.orderId).subscribe(
      data => { this.order = data },
      err => console.error(err),
      () => console.log('order refreshed')
    )
  }

  getTotal() {
    this._clientService.getTotal(this.orderId).subscribe(
      data => this.total = data
    );
  }

  addProduct(code: string, quantity: number) {
    this.payload = new OrderPayload(this.orderId, code, quantity);
    this._clientService.addProductOnOrder(this.payload).subscribe(
      data => {
        this.getOrder();
        this.getTotal();
      }
    )
  }

  getExchange(amount: number) {

    this._clientService.getExchange(this.orderId, amount).subscribe(
      data => {
        this.exchange = data;
      }
    )
  }

  setSuccessMessage(message: string) {
    this.message = message;
    this.messageColor = 'is-success';
  }

  setWarningMessage(message: string) {
    this.message = message;
    this.messageColor = 'is-warning';
  }

}

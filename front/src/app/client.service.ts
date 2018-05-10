import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';
import { Order } from './order';
import { OrderPayload } from './order-payload';


const httpOptions = {
  headers: new HttpHeaders(
    { 'Content-Type': 'application/json' })
};

@Injectable()
export class ClientService {

  public apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiUrl;
  }

  createOrder() {
    return this.http.post(this.apiUrl + '/snackbar/order', null);
  }

  getOrder(orderId: number) {
    return this.http.get<Order>(this.apiUrl + `/snackbar/order/${orderId}`);
  }

  addProductOnOrder(payload: OrderPayload) {
    return this.http.put(environment.apiUrl + '/snackbar/order', payload);
  }

  getTotal(orderId: number) {
    return this.http.get<number>(environment.apiUrl + `/snackbar/order/${orderId}/total`);
  }

  getExchange(orderId: number, amount: number) {
    return this.http.get<number>(environment.apiUrl + `/snackbar/order/${orderId}/exchange?amount=${amount}`);
  }

}

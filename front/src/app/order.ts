import { OrderItem } from './order-item'

export class Order {
    constructor(
        public id: number,
        public items: Array<OrderItem>
    ) {}
}

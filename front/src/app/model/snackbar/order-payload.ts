export class OrderPayload {
    constructor(
        public order_id: number,
        public product_code: string,
        public quantity: number
    ) {}
}

export interface Order {
    order_id?: number | null | undefined;
    staff_id?: number | null | undefined;
    customer_id?: number | null | undefined;
    date_placed: Date;
    date_shipped: Date;
    order_status_id?: number | null | undefined;
  }

export interface Order {
  orderId?: number | null | undefined;
  customerId?: number | null | undefined;
  customerName: string;
  datePlaced: Date;
  dateShipped: Date;
  order_status_id?: number | null | undefined;
  items: OrderItem[];
  total: number;
  staffFirstName: string;
  staffLastName: string;
  status: OrderStatus[];
}

export interface OrderItem {
  productId: number;
  productName: string;
  quantity: number;
  price: number;
}

export interface OrderStatus {
  id: number;
  name: string;
}

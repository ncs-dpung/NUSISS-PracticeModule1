export interface Inventory {
    inventory_id?: number | null | undefined;
    product_id: number;
    quantity_available: number;
    reorder_level:number;
  }


export interface Product {
    id?: number | null | undefined;
    categoryId?: number | null | undefined;
    categoryName:  string;
    name: string;
    price: number;
    quantityAvailable:number;
    reorderLevel:number;
    stockLevel:string;
    batchNo:string;
    supplierName:string;
    supplierId:number;
  }

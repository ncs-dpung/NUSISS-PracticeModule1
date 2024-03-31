export interface MonthlyReport {
  totalSales: {
    year: number;
    month: number;
    totalOrders: number;
    totalRevenue: number;
    mostSoldProduct: string;
  };
  topSellingProducts: TopSellingProduct[];
  revenueByCategory: RevenueByCategory[];
}

export interface TopSellingProduct {
  productName: string;
  totalQuantity: number;
}

export interface RevenueByCategory {
  categoryName: string;
  revenue: number;
}

package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Fetch total sales and revenue by month
  @Query(
      value =
          "SELECT YEAR(o.date_placed) AS year, MONTH(o.date_placed) AS month, COUNT(*) AS"
              + " totalOrders, SUM(oi.price * oi.quantity) AS totalRevenue FROM tbl_order o INNER"
              + " JOIN tbl_order_items oi ON o.order_id = oi.order_id WHERE YEAR(o.date_placed) ="
              + " :year AND MONTH(o.date_placed) = :month GROUP BY YEAR(o.date_placed),"
              + " MONTH(o.date_placed)",
      nativeQuery = true)
  List<Object[]> findTotalSalesAndRevenueForSpecificMonth(
      @Param("year") int year, @Param("month") int month);

  // Fetch name of the most sold product for a given month and year
  @Query(
      value =
          "SELECT p.name, SUM(oi.quantity) AS totalQuantity "
              + "FROM tbl_order_items oi JOIN tbl_product p JOIN tbl_order o "
              + "WHERE YEAR(o.date_placed) = :year AND MONTH(o.date_placed) = :month "
              + "GROUP BY p.product_id "
              + "ORDER BY SUM(oi.quantity) DESC "
              + "LIMIT 1",
      nativeQuery = true)
  List<Object[]> findMostSoldProductForMonthAndYear(
      @Param("year") String year, @Param("month") String month);

  @Query(
      value =
          "SELECT o.order_id, o.customer_id, o.date_placed, o.date_shipped, o.order_status_id FROM"
              + " tbl_order o INNER JOIN tbl_order_status os ON o.order_status_id ="
              + " os.order_status_id WHERE os.status_name IN ('Pending', 'Processed') ORDER BY CASE"
              + " WHEN os.status_name = 'Pending' THEN 1 WHEN os.status_name = 'Processed' THEN 2"
              + " ELSE 3 END, o.order_id",
      nativeQuery = true)
  List<Order> findPendingAndProcessedOrdersSorted();

  List<Order> findByCustomerId(Long customerId);
}

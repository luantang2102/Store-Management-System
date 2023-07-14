package com.example.storemanagementsystemfx.dao.itface;

import com.example.storemanagementsystemfx.model.OrderDetail;
import com.example.storemanagementsystemfx.model.ui.BSPChart;

import java.util.List;

public interface IOrderDetailDao {
    List<OrderDetail> getByOrderId(String orderId);
    List<BSPChart> getProductTotal();
    int save(OrderDetail orderDetail);
    int update(OrderDetail orderDetail, String oldProductId);
    int delete(String orderId, String productId);
}

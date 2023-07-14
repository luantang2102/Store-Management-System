package com.example.storemanagementsystemfx.dao.itface;

import com.example.storemanagementsystemfx.model.Order;
import com.example.storemanagementsystemfx.model.ui.ICChart;
import com.example.storemanagementsystemfx.model.ui.NOOChart;

import java.util.List;

public interface IOrderDao {
    List<Order> getAll();
    Order getByOrderId(String orderId);
    List<NOOChart> numbersOfOrdersByDate();
    List<ICChart> incomesByDate();
    int save(Order order);
    int update(Order order);
    int delete(String orderId);

}

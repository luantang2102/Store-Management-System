package com.example.storemanagementsystemfx.service;

import com.example.storemanagementsystemfx.dao.itface.IOrderDao;
import com.example.storemanagementsystemfx.dao.itface.IOrderDetailDao;
import com.example.storemanagementsystemfx.model.Order;
import com.example.storemanagementsystemfx.model.OrderDetail;
import com.example.storemanagementsystemfx.model.Product;
import com.example.storemanagementsystemfx.model.holder.OrderHolder;
import com.example.storemanagementsystemfx.model.ui.BSPChart;
import com.example.storemanagementsystemfx.model.ui.ICChart;
import com.example.storemanagementsystemfx.model.ui.NOOChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderService {
    private IOrderDao orderDao;
    private IOrderDetailDao orderDetailDao;
    private CustomerService customerService;
    private ProductService productService;

    public OrderService(IOrderDao orderDao, IOrderDetailDao orderDetailDao, CustomerService customerService, ProductService productService) {
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
        this.customerService = customerService;
        this.productService = productService;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = orderDao.getAll();
        for(Order order : orderList) {
            order.setCustomerName(customerService.getByCustomerId(order.getCustomerId()).getCustomerName());//Get customer name from customer ID in all orders
        }
        return  orderList;
    }

    public List<Order> getByOrderDate(Date startDate, Date endDate) {
        List<Order> orderList = orderDao.getAll();
        List<Order> newOrderList = new ArrayList<>();
        for(Order order : orderList) {
            if(order.getOrderDate().getTime().compareTo(startDate) >= 0 && order.getOrderDate().getTime().compareTo(endDate) <= 0) {
                order.setCustomerName(customerService.getByCustomerId(order.getCustomerId()).getCustomerName());//Get customer name from customer ID in all orders
                newOrderList.add(order);
            }
        }
        return newOrderList;
    }

    public int saveOrder(Order order) {
        return orderDao.save(order);
    }

    public Order setOrderData(Order order, List<OrderDetail> orderDetailList) {
        double totalAmount = 0;
        int items= 0;
        for(OrderDetail orderDetail : orderDetailList) {
            totalAmount += (orderDetail.getProductPrice() * orderDetail.getQuantity());
            items += 1;
        }
        order.setAmount(totalAmount);
        order.setItems(items);
        OrderHolder.getInstance().setOrder(order);
        return order;
    }

    public int updateOrder(Order order) {
        OrderHolder.getInstance().setOrder(order);
        return orderDao.update(setOrderData(order, getOrderDetailsByOrder()));
    }

    public int deleteOrder(String orderId) {
        return orderDao.delete(orderId);
    }

    public List<NOOChart> numbersOfOrdersByDate() {
        return orderDao.numbersOfOrdersByDate();
    }

    public List<ICChart> incomesByDate() {
        return orderDao.incomesByDate();
    }
    public List<OrderDetail> setDetail(List<OrderDetail> orderDetailList) {
        for(OrderDetail orderDetail : orderDetailList) {
            orderDetail.setProductName(productService.getByProductId(orderDetail.getProductId()).getProductName()); //Get product Name from product ID in selected order
            orderDetail.setProductType(productService.getByProductId(orderDetail.getProductId()).getType());        //Get product Type from product ID in selected order
            orderDetail.setProductPrice(productService.getByProductId(orderDetail.getProductId()).getPrice()); //Get product Price from product ID in selected order
        }
        return orderDetailList;
    }

    public List<OrderDetail> getOrderDetailsByOrder() {
        List<OrderDetail> orderDetailList = orderDetailDao.getByOrderId(OrderHolder.getInstance().getOrder().getOrderId());
        return setDetail(orderDetailList);
    }

    public List<BSPChart> getProductTotal() {
        List<BSPChart> dataList = orderDetailDao.getProductTotal();
        for(BSPChart data : dataList) {
            Product check = productService.getByProductId(data.getProductId());
            if(check == null) return Collections.emptyList();
            else data.setProductName(check.getProductName());
        }
        return dataList;
    }

    public int saveOrderDetails(OrderDetail orderDetail) {
        int check = orderDetailDao.save(orderDetail);
        if(check == 1){
            Order currentOrder = OrderHolder.getInstance().getOrder();
            updateOrder(currentOrder);
        }
        return check;
    }

    public int updateOrderDetails(OrderDetail orderDetail, String oldProdId) {
        int check = orderDetailDao.update(orderDetail, oldProdId);
        if(check == 1){
            Order currentOrder = OrderHolder.getInstance().getOrder();
            updateOrder(currentOrder);
        }
        return check;
    }

    public int deleteOrderDetails(String prodId) {
        int check = orderDetailDao.delete(OrderHolder.getInstance().getOrder().getOrderId(), prodId);
        if(check == 1){
            Order currentOrder = OrderHolder.getInstance().getOrder();
            updateOrder(currentOrder);
        }
        return check;
    }
}

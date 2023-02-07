package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.exception.OrderException;
import com.GorgeousGlam.model.Orders;

public interface IOrderService {

	public Orders addOrder(Orders orders, Integer customerId) throws OrderException;

	public List<Orders> viewAllOrders() throws OrderException;

	public Orders viewOrderById(Integer order_Id) throws OrderException;

	public Orders deleteOrdrerById(Integer order_Id) throws OrderException;

//	public Orders updateOrder(Order orders) throws OrderException;
}

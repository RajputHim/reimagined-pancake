package com.GorgeousGlam.service;

import java.util.List;

import com.GorgeousGlam.exception.OrderException;
import com.GorgeousGlam.model.Orders;

public interface IOrderService {
	
	public Orders addOrder(Orders orders) throws OrderException;

	public List<Orders> viewAllOrders() throws OrderException;

	public Orders viewOrderById(Integer id) throws OrderException;

//	public Order deleteOrdrerById(Integer id) throws OrderException;
//
//	public Order updateOrder(Order orders) throws OrderException;
}

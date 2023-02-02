package com.GorgeousGlam.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GorgeousGlam.exception.OrderException;
import com.GorgeousGlam.model.Orders;
import com.GorgeousGlam.repository.OrderRepo;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Orders addOrder(Orders orders) throws OrderException {

//		orders.setOrderDateTime(LocalDateTime.now());

		Orders saveOrder = orderRepo.save(orders);

		if (saveOrder == null) {
			throw new OrderException("Add product to the Order first...");
		}

		else {
			return saveOrder;
		}

	}

	@Override
	public List<Orders> viewAllOrders() throws OrderException {

		List<Orders> allOrders = orderRepo.findAll();

		if (allOrders.isEmpty())
			throw new OrderException("No order found...");

		return allOrders;

	}

	@Override
	public Orders viewOrderById(Integer id) throws OrderException {

		return orderRepo.findById(id).orElseThrow(() -> new OrderException("No order found by id: " + id));

	}

}

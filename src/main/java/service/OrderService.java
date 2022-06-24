package service;

import domain.Order;

public interface OrderService {
    public void save(Order order) throws Exception;
}

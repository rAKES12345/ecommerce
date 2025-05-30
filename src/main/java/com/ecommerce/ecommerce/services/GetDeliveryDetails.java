package com.ecommerce.ecommerce.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.DelivererDeliveries;
import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.DelivererDeliveriesRepo;
import com.ecommerce.ecommerce.repo.ItemRepo;
import com.ecommerce.ecommerce.repo.OrderRepo;
import com.ecommerce.ecommerce.repo.SellerRepo;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class GetDeliveryDetails {

    @Autowired
    private DelivererDeliveriesRepo deliveriesRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private UserRepo userRepo;

    public Map<String, Object> getDeliveryDetails(String deliveryId) {
        Map<String, Object> result = new HashMap<>();

        // 1. Get delivery
        Optional<DelivererDeliveries> deliveryOpt = deliveriesRepo.findById(deliveryId);
        if (deliveryOpt.isEmpty()) {
            result.put("error", "Delivery not found");
            return result;
        }

        DelivererDeliveries delivery = deliveryOpt.get();
        result.put("delivery", delivery);

        // 2. Get order
        String orderId = delivery.getOrderId();
        Optional<Order> orderOpt = orderRepo.findById(orderId);
        if (orderOpt.isEmpty()) {
            result.put("error", "Order not found");
            return result;
        }

        Order order = orderOpt.get();
        result.put("order", order);

        // 3. Get item
        Optional<Item> itemOpt = itemRepo.findById(order.getItemId());
        itemOpt.ifPresent(item -> result.put("item", item));

        // 4. Get seller
        Optional<Seller> sellerOpt = sellerRepo.findById(order.getSellerId());
        sellerOpt.ifPresent(seller -> result.put("seller", seller));

        // 5. Get user (buyer) — assuming you have `order.getUserId()`
        Optional<User> userOpt = userRepo.findById(order.getId());
        userOpt.ifPresent(user -> result.put("user", user));

        return result;
    }
}

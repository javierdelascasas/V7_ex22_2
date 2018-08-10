package se.javierdlc;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements Repository {
    private List<Order> orders = new ArrayList<>();
    private int orderID = 0;

    public Order getOrder(int orderID, int customerID) {
        for (Order order : orders) {
            if (order.orderID == orderID && order.customerID == customerID) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAllOrderForCustomer(int customerID) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.customerID == customerID) {
                result.add(order);
            }
        }
        return result;
    }

    public void deleteOrder(int orderID, int customerID) {
        Order selection = getOrder(orderID, customerID);
        if(selection!=null){
            orders.remove(selection);
        }
        // Kanske ska man skicka både orderID och customerID för extra säkerhet?
        // Då kanske man kan använda sig av customerID för att försäkra sig om att bara ägaren kan ta bort sin order?
    }

    public int createNewOrder(Order order) {
        order.orderID = ++orderID;
        orders.add(order);
        return orderID;
    }
}

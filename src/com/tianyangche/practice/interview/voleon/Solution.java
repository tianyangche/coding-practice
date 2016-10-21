package com.tianyangche.practice.interview.voleon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by tianyangche on 9/17/16.
 */
public class Solution {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Prlong output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StockExchange stockExchange = new StockExchange();


        while (true) {
            String s = br.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }
            String[] strs = s.split("\\s+");
            Order order = OrderPlacer.generateOrder(strs[0], strs[1], Long.parseLong(strs[2]), Double.parseDouble(strs[3]));
            stockExchange.acceptOrder(order);
        }

    }
}

class OrderPlacer {

    private static AtomicLong ORDER_COUNTER = new AtomicLong();

    public static Order generateOrder(String orderType, String side, long volume, double price) {
        Order res = null;
        Side s = null;
        if (side.equals("buy") || side.equals("sell")) {
            s = Side.valueOf(side.toUpperCase());
        } else {
            s = Side.NA;
        }
        switch (orderType) {
            case "market":
                res = new MarketOrder(ORDER_COUNTER.incrementAndGet(), s, volume, price);
                break;
            case "limit":
                res = new LimitOrder(ORDER_COUNTER.incrementAndGet(), s, volume, price);
                break;
            case "stop":
                res = new StopOrder(ORDER_COUNTER.incrementAndGet(), s, volume, price);
                break;
            case "cancel":
                res = new CancelOrder(ORDER_COUNTER.incrementAndGet(), volume);
                break;
            default:
        }

        return res;
    }
}


class StockExchange {
    List<Order> orders;

    // Stop order is a special type. Store their references.
    List<Long> stopOrders;

    // Stop orders that have been triggered.
    Queue<Order> triggeredStopOrders;

    // price for last trading stock
    double marketPrice;

    public StockExchange() {
        orders = new ArrayList<>();
        stopOrders = new ArrayList<>();
        triggeredStopOrders = new LinkedList<>();
        marketPrice = 0.0;
    }

    public void acceptOrder(Order order) {
        orders.add(order);
        if (order instanceof StopOrder) {
            stopOrders.add(order.orderId);
        } else if (order instanceof CancelOrder) {
            executeCancelOrder((CancelOrder) order);
        } else if (order.side.equals(Side.BUY)) {
            executeBuyOrder(order, order.orderId);
        } else if (order.side.equals(Side.SELL)) {
            executeSellOrder(order, order.orderId);
        }
        triggerStopOrdersIfAny();
    }


    // Execute a buy order.
    private void executeBuyOrder(Order buyOrder, long range) {
        double price = Double.MAX_VALUE;
        PriorityQueue<Order> heap = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price > o2.price) {
                    return 1;
                }
                if (o2.price > o1.price) {
                    return -1;
                }
                return (int) (o1.orderId - o2.orderId);
            }
        });


        // 1. find all open sell orders that can match buy orders
        for (int i = 0; i < range - 1; i++) {
            Order currOrder = orders.get(i);

            if (!((currOrder instanceof CancelOrder) || (currOrder instanceof StopOrder)) && currOrder.side.equals(Side.SELL) && currOrder.isOpen()) {
                if (((buyOrder instanceof MarketOrder) || currOrder.price <= buyOrder.price)) {
                    heap.offer(currOrder);
                }
            }
        }

        // 2. execute
        while (!heap.isEmpty() && buyOrder.isOpen()) {
            Order sellOrder = heap.poll();
            long executedVolume = Math.min(buyOrder.openVolume, sellOrder.openVolume);
            price = sellOrder.price;
            marketPrice = price;
            DecimalFormat format = new DecimalFormat();
            format.setMaximumFractionDigits(2);
//            System.out.println("match " + buyOrder.orderId + " " + sellOrder.orderId + " " + executedVolume + " " + format.format(direction == 1 ? Math.min(buyOrder.price, sellOrder.price) : Math.max(buyOrder.price, sellOrder.price)));
            System.out.println("match " + buyOrder.orderId + " " + sellOrder.orderId + " " + executedVolume + " " + format.format(price));
            findTriggeredStopOrders();
            // TODO: update order status
            buyOrder.updateOpenVolume(executedVolume);
            sellOrder.updateOpenVolume(executedVolume);
        }

        if (buyOrder instanceof MarketOrder) {
            buyOrder.openVolume = 0;
            buyOrder.orderStatus = OrderStatus.FULLY_FILLED;
        }
    }

    // Execute a sell order.
    private void executeSellOrder(Order sellOrder, long range) {

        double price = Double.MIN_VALUE;
        PriorityQueue<Order> heap = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price < o2.price) {
                    return 1;
                }
                if (o2.price < o1.price) {
                    return -1;
                }
                return (int) (o1.orderId - o2.orderId);
            }
        });


        // 1. find all open sell orders that can match sell orders
        for (int i = 0; i < range - 1; i++) {
            Order currOrder = orders.get(i);
            if (!((currOrder instanceof CancelOrder) || (currOrder instanceof StopOrder)) && currOrder.side.equals(Side.BUY) && currOrder.isOpen()) {
                if (((sellOrder instanceof MarketOrder) || currOrder.price >= sellOrder.price)) {
                    heap.offer(currOrder);
                }
            }
        }

        // 2. execute
        while (!heap.isEmpty() && sellOrder.isOpen()) {
            Order buyOrder = heap.poll();
            long executedVolume = Math.min(sellOrder.openVolume, buyOrder.openVolume);
            price = buyOrder.price;
            marketPrice = price;
            DecimalFormat format = new DecimalFormat("#.00");
            findTriggeredStopOrders();
            System.out.println("match " + sellOrder.orderId + " " + buyOrder.orderId + " " + executedVolume + " " + format.format(price));

            sellOrder.updateOpenVolume(executedVolume);
            buyOrder.updateOpenVolume(executedVolume);
        }

        if (sellOrder instanceof MarketOrder) {
            sellOrder.openVolume = 0;
            sellOrder.orderStatus = OrderStatus.FULLY_FILLED;
        }

    }

    // Looking for stop orders that can be triggered.
    private void findTriggeredStopOrders() {
        for (int i = 0; i < stopOrders.size(); i++) {
            Order currOrder = orders.get((int) (stopOrders.get(i) - 1));
            if (currOrder.orderStatus.equals(OrderStatus.NOT_FILLED)) {
                if (currOrder.side.equals(Side.SELL)) {
                    if (currOrder.price >= marketPrice && !triggeredStopOrders.contains(currOrder)) {
                        triggeredStopOrders.offer(currOrder);
                    }
                } else {
                    if (currOrder.price <= marketPrice && !triggeredStopOrders.contains(currOrder)) {
                        triggeredStopOrders.offer(currOrder);
                    }
                }
            }
        }
    }

    // trigger stop orders if possible.
    private void triggerStopOrdersIfAny() {
        while (!triggeredStopOrders.isEmpty()) {
            Order order = triggeredStopOrders.poll();
            if (!order.isOpen()) {
                continue;
            }
            if (order.side.equals(Side.SELL)) {
                executeSellOrder(order, orders.size() + 1);
            } else {
                executeBuyOrder(order, orders.size() + 1);
            }
        }
    }

    private void executeCancelOrder(CancelOrder order) {
        Order orderToBeCancelled = orders.get((int) (order.target - 1));
        if (orderToBeCancelled.orderStatus == OrderStatus.FULLY_FILLED || orderToBeCancelled.orderStatus == OrderStatus.CANCELLED) {
            return;
        }
        orderToBeCancelled.orderStatus = OrderStatus.CANCELLED;
        orderToBeCancelled.openVolume = 0;
        order.orderStatus = OrderStatus.FULLY_FILLED;
    }
}

// Side for distinguish buy or sell
enum Side {
    BUY("buy"),
    SELL("sell"),
    NA("na");

    String value;

    Side(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
}

enum OrderStatus {
    NOT_FILLED,
    PARTIAL_FILLED,
    FULLY_FILLED,
    CANCELLED;
}

abstract class Order {
    long orderId;
    Side side;
    long volume;
    double price;
    OrderStatus orderStatus;

    // volume that hasn't been executed.
    long openVolume;

    public Order() {

    }

    public Order(long orderId, Side side, long volume, double price) {
        this.orderId = orderId;
        this.side = side;
        this.volume = volume;
        this.price = price;
        this.orderStatus = OrderStatus.NOT_FILLED;

        this.openVolume = volume;
    }

    public void updateOpenVolume(long executedOpenVolume) {
        openVolume = Math.max(openVolume - executedOpenVolume, 0);
        if (openVolume > 0) {
            orderStatus = OrderStatus.PARTIAL_FILLED;
        } else {
            orderStatus = OrderStatus.FULLY_FILLED;
        }
    }

    public boolean isOpen() {
        return (orderStatus.equals(OrderStatus.NOT_FILLED) || orderStatus.equals(OrderStatus.PARTIAL_FILLED)) && openVolume > 0;
    }
}

class MarketOrder extends Order {
    public MarketOrder() {

    }

    public MarketOrder(long orderId, Side side, long volume, double price) {
        super(orderId, side, volume, price);
    }
}

class LimitOrder extends Order {

    public LimitOrder(long orderId, Side side, long volume, double price) {
        super(orderId, side, volume, price);
    }


}

// StopOrder is a subclass of MarketOrder
class StopOrder extends MarketOrder {

    public StopOrder(long orderId, Side side, long volume, double price) {
        super(orderId, side, volume, price);
    }
}


class CancelOrder extends Order {
    long target;

    public CancelOrder(long orderId, long target) {
        this.orderId = orderId;
        this.target = target;
    }

}

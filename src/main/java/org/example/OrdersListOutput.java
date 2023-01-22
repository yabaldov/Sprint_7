package org.example;

import java.util.List;

public class OrdersListOutput {

    private List<OrderPlaced> orders;
    private PageInfo pageInfo;
    private List<AvailableStation> availableStations;

    public OrdersListOutput() {
    }

    public OrdersListOutput(List<OrderPlaced> orders, PageInfo pageInfo, List<AvailableStation> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public List<OrderPlaced> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderPlaced> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<AvailableStation> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<AvailableStation> availableStations) {
        this.availableStations = availableStations;
    }
}

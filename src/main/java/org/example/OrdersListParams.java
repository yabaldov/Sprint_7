package org.example;

import java.util.HashMap;

public class OrdersListParams {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;

    public OrdersListParams(Integer courierId, String nearestStation, Integer limit, Integer page) {
        this.courierId = courierId;
        this.nearestStation = nearestStation;
        this.limit = limit;
        this.page = page;
    }

    public OrdersListParams() {
    }

    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        if(courierId != null) {
            map.put("courierId", courierId.toString());
        }
        if(nearestStation != null) {
            map.put("nearestStation", nearestStation);
        }
        if(limit != null) {
            map.put("limit", limit.toString());
        }
        if(page != null) {
            map.put("page", page.toString());
        }
        return map;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}

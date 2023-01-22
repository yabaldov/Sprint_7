package org.example;

public class OrderPlaced extends Order {

    private Integer id;
    private Integer courierId;
    private Integer track;
    private String createdAt;
    private String updatedAt;
    private Integer status;

    public OrderPlaced() {
    }

    public OrderPlaced(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            Integer rentTime,
            String deliveryDate,
            String comment,
            String[] color,
            Integer id,
            Integer courierId,
            Integer track,
            String createdAt,
            String updatedAt,
            Integer status
    ) {
        super(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        this.id = id;
        this.courierId = courierId;
        this.track = track;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

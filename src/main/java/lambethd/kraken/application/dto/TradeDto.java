package lambethd.kraken.application.dto;

import portfolio.BuySell;
import portfolio.TradeStatus;

import java.time.LocalDateTime;

public class TradeDto {
    private String id;
    private Integer itemId;
    private Integer location;
    private Integer requestQuantity;
    private Float requestPrice;
    private String username;
    private LocalDateTime requestTime;
    private TradeStatus tradeStatus;
    private Integer currentQuantity;
    private Float currentTotalPrice;
    private LocalDateTime lastUpdated;
    private BuySell buySell;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(Integer requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public Float getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(Float requestPrice) {
        this.requestPrice = requestPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Float getCurrentTotalPrice() {
        return currentTotalPrice;
    }

    public void setCurrentTotalPrice(Float currentTotalPrice) {
        this.currentTotalPrice = currentTotalPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }
}

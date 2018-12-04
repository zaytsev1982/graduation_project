package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateJson {

    @JsonProperty(value = "ccy")
    private String ccy;
    @JsonProperty(value = "base_ccy")
    private String baseCcy;
    @JsonProperty(value = "buy")
    private String buy;
    @JsonProperty(value = "sale")
    private String sale;


    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return String
            .format("rate json [ccy: %s | base_ccy: %s | buy: %s | sale: %s]"
                , ccy, baseCcy, buy, sale);
    }
}

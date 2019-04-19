package in.codecorp.ssgcp.shop.Utils;

/**
 * Created by NavdeepAhuja on 2019-01-06.
 */

public class profileget {
    String orderid;
    String name;
    String date;
    String amount;
    String details;

    public profileget(String orderid, String date,String name, String amount, String details) {
        this.orderid = orderid;
        this.date = date;
        this.name=name;
        this.amount = amount;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

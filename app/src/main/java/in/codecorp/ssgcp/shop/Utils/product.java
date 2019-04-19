package in.codecorp.ssgcp.shop.Utils;

/**
 * Created by basleenkaur on 2018-12-20.
 */

public class product {
    String id;
    String name;
    String desc;
    String price;
    String regprice;
    String saleprice;
    String rating;

    public product(String id, String name, String desc, String price, String regprice, String saleprice, String rating) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.regprice = regprice;
        this.saleprice = saleprice;
        this.rating = rating;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegprice() {
        return regprice;
    }

    public void setRegprice(String regprice) {
        this.regprice = regprice;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

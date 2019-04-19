package in.codecorp.ssgcp.shop.Utils;

/**
 * Created by NavdeepAhuja on 2018-12-21.
 */

public class cart {
    String id;
    String name;
    String img;
    String sale_price;

    public cart(String id, String name, String img, String sale_price) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.sale_price = sale_price;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }
}

package commerce.modles;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    String productName;
    String productPrice;
    String productDate;
    String productTime;
    String productQuantity;
    int totalPrice;

    public MyCartModel() {
    }

    public MyCartModel(String productName, String productPrice, String productDate, String productTime, String productQuantity, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDate = productDate;
        this.productTime = productTime;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

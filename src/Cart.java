public class Cart {
    private String productName;
    private int Quantiy;
    private double price;

    private String category;

    public Cart(String productName, int quantiy, double price,String category) {
        this.productName = productName;
        Quantiy = quantiy;
        this.price = price;
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantiy() {
        return Quantiy;
    }

    public void setQuantiy(int quantiy) {
        Quantiy = quantiy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

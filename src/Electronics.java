public class Electronics extends Product{

    private String brandName;
    private double warentyPeriod;

    public Electronics() {
    }

    public Electronics(String productId, String productName, int noOfAvailableItems, double price, String brandName, double warentyPeriod) {
        super(productId, productName, noOfAvailableItems, price);
        this.brandName = brandName;
        this.warentyPeriod = warentyPeriod;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getWarentyPeriod() {
        return warentyPeriod;
    }

    public void setWarentyPeriod(double warentyPeriod) {
        this.warentyPeriod = warentyPeriod;
    }
}

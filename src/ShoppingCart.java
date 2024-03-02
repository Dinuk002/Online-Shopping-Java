import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Cart> products=new ArrayList<Cart>();

    public void add(Cart cart){
        products.add(cart);
    }

    public void remove(Cart cart){
        products.remove(cart);
    }

    public void totalCost(){
        //products.remove(product);
    }
}

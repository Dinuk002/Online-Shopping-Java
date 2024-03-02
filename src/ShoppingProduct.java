import java.util.ArrayList;

public class ShoppingProduct {

    ArrayList<Clothing> clothings=new ArrayList<Clothing>();
    ArrayList<Electronics> electronics=new ArrayList<Electronics>();

    public void addClothings(Clothing product){
        clothings.add(product);
    }

    public void addElectronics(Electronics product){
        electronics.add(product);
    }

    public void removeClothings(Clothing product){
        clothings.remove(product);
    }

    public void removeElectronics(Electronics product){
        electronics.remove(product);
    }

    public void calculateTotal(){

    }
}

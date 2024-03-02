import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class WestminsterShoppingManager implements IShoppingManager {
    ShoppingProduct shoppingProduct = new ShoppingProduct();

    public WestminsterShoppingManager(){
        readFile();
    }

    public void addNewProduct() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Which product you are going to add?(Clothing/Electronics)");
        String producttype = myObj.nextLine();

        Clothing clothing = new Clothing();
        Electronics electronics = new Electronics();

        if (shoppingProduct.electronics.size() + shoppingProduct.clothings.size() > 50) {
            System.out.println("You Can add maximum of 50 items");
            return;
        }


        if (producttype.toLowerCase().equals("clothing")) {

            boolean check = true;
            while (check) {
                System.out.println("Product Id:");
                clothing.setProductId(myObj.next());

                Clothing clothingCheck = shoppingProduct.clothings.stream()
                        .filter(x -> x.productId.equals(clothing.getProductId()))
                        .findFirst()
                        .orElse(null);

                if (clothingCheck == null) {
                    check = false;
                } else {
                    System.out.println("this product Id already added");
                }
            }


            System.out.println("Product Name:");
            clothing.setProductName(myObj.next());

            System.out.println("No Of Available Items:");
            clothing.setNoOfAvailableItems(myObj.nextInt());

            System.out.println("Price:");
            clothing.setPrice(myObj.nextDouble());

            System.out.println("Size:");
            clothing.setSize(myObj.nextDouble());

            System.out.println("Colour:");
            clothing.setColour(myObj.next());

            shoppingProduct.addClothings(clothing);
            System.out.println("Item Added Successfully");


        } else if (producttype.toLowerCase().equals("electronics")) {
            boolean check = true;

            while (check) {
                System.out.println("Product Id:");
                electronics.setProductId(myObj.next());

                Electronics electronicsCheck = shoppingProduct.electronics.stream()
                        .filter(x -> x.productId.equals(electronics.getProductId()))
                        .findFirst()
                        .orElse(null);

                if (electronicsCheck == null) {
                    check = false;
                } else {
                    System.out.println("this product Id already added");
                }
            }

            System.out.println("Product Name:");
            electronics.setProductName(myObj.next());

            System.out.println("No Of Available Items:");
            electronics.setNoOfAvailableItems(myObj.nextInt());

            System.out.println("Price:");
            electronics.setPrice(myObj.nextDouble());

            System.out.println("Brand Name:");
            electronics.setBrandName(myObj.next());

            System.out.println("Warenty Period:");
            electronics.setWarentyPeriod(myObj.nextDouble());
            shoppingProduct.addElectronics(electronics);
        } else {
            System.out.println("Not Valid product type");
        }

        menu();

    }

    public void deleteProduct() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Which products you are going to delete?(Clothing/Electronics)");
        String producttype = myObj.nextLine();

        System.out.println("Produt Id");
        String productId = myObj.next();

        if (producttype.toLowerCase().equals("clothing")) {

            Clothing clothing = shoppingProduct.clothings.stream()
                    .filter(x -> x.productId.equals(productId))
                    .findFirst()
                    .orElse(null);

            if (clothing != null) {
                System.out.println(clothing.getProductId() + "\t" + clothing.getProductName()
                        + "\t" + clothing.getNoOfAvailableItems() + String.valueOf(clothing.getPrice())
                        + "\t" + clothing.getColour() + "\t" + String.valueOf(clothing.getSize()));
                shoppingProduct.removeClothings(clothing);
                System.out.println("Item Deleted successfully");
                int count = shoppingProduct.clothings.size() + shoppingProduct.electronics.size();
                System.out.println("No of Items Left:" + count);

            } else {
                System.out.println("this product not exist");
            }
        } else if (producttype.toLowerCase().equals("electronics")) {
            Electronics electronics = shoppingProduct.electronics.stream()
                    .filter(x -> x.productId.equals(productId))
                    .findFirst()
                    .orElse(null);

            if (electronics != null) {
                System.out.println(electronics.getProductId() + "\t" + electronics.getProductName()
                        + "\t" + electronics.getNoOfAvailableItems() + String.valueOf(electronics.getPrice())
                        + "\t" + electronics.getBrandName() + "\t" + String.valueOf(electronics.getWarentyPeriod()));
                shoppingProduct.removeElectronics(electronics);
                System.out.println("Item Deleted successfully");
                int count = shoppingProduct.clothings.size() + shoppingProduct.electronics.size();
                System.out.println("No of Items Left:" + count);

            } else {
                System.out.println("this product not exist");
            }
        }
        menu();
    }

    public static void sortClothing(ArrayList<Clothing> list) {

        list.sort((o1, o2)  -> o1.getProductName().compareTo(
                o2.getProductName()));
    }

    public static void sortElectronics(ArrayList<Electronics> list) {

        list.sort((o1, o2)  -> o1.getProductName().compareTo(
                o2.getProductName()));
    }

    public void viewProduct() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Which products you are going to view?(Clothing/Electronics)");
        String producttype = myObj.nextLine();
        Formatter fmt = new Formatter();

        if (producttype.toLowerCase().equals("clothing")) {
                 sortClothing(shoppingProduct.clothings);
            fmt.format("%20s %20s %20s %20s %20s %20s\n", "Product Id", "Product Name", "No Of Available Items", "Price", "Size", "Colour");
            for (Clothing clothing : shoppingProduct.clothings) {
                fmt.format("%20s %20s %20s %20s %20s %20s\n", clothing.getProductId(), clothing.getProductName(),
                        clothing.getNoOfAvailableItems(), clothing.getPrice(), clothing.getSize(), clothing.getColour());
            }
            System.out.println(fmt);
        } else if (producttype.toLowerCase().equals("electronics")) {
            sortElectronics(shoppingProduct.electronics);
            fmt.format("%20s %20s %20s %20s %20s %20s\n", "Product Id", "Product Name", "No Of Available Items", "Price", "Brand Name", "Warenty Period");
            for (Electronics electronics : shoppingProduct.electronics) {
                fmt.format("%20s %20s %20s %20s %20s %20s\n", electronics.getProductId(), electronics.getProductName(),
                        electronics.getNoOfAvailableItems(), electronics.getPrice(), electronics.getBrandName(), electronics.getWarentyPeriod());
            }
            System.out.println(fmt);
        }
        menu();
    }

    public void saveToFile() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Do you want to save the items to the file?(Y/N)");
        String producttype = myObj.nextLine();
        StringBuilder clothingSb = new StringBuilder();
        StringBuilder electronicsSb = new StringBuilder();


        if (producttype.toLowerCase().equals("y")) {

            try {
                for (Clothing clothing : shoppingProduct.clothings) {
                    clothingSb.append(clothing.getProductId() + "," + clothing.getProductName() + "," +
                            clothing.getNoOfAvailableItems() + "," + clothing.getPrice() + ","
                            + clothing.getSize() + "," + clothing.getColour() + "\n");
                }
                FileWriter Writer
                        = new FileWriter("clothings.txt");
                Writer.write(
                        clothingSb.toString());
                Writer.close();
                System.out.println("Clothing datas Successfully written in to the file.");

                for (Electronics electronic : shoppingProduct.electronics) {
                    electronicsSb.append(electronic.getProductId() + "," + electronic.getProductName() + "," +
                            electronic.getNoOfAvailableItems() + "," + electronic.getPrice() + ","
                            + electronic.getBrandName() + "," + electronic.getWarentyPeriod() + "\n");
                }
                FileWriter Writer2
                        = new FileWriter("electronics.txt");
                Writer2.write(
                        electronicsSb.toString());
                Writer2.close();
                System.out.println("Electronics datas Successfully written in to the file.");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        menu();


    }

    public void readFile() {
        try {
            Scanner clothingReader = new Scanner(new File("clothings.txt"));
            while (clothingReader.hasNextLine()) {
                String data = clothingReader.nextLine();


                String[] clothingData = data.split(",");

                shoppingProduct.clothings.add(new Clothing(clothingData[0], clothingData[1], Integer.valueOf(clothingData[2].toString()),
                        Double.parseDouble(clothingData[3])
                        , Double.parseDouble(clothingData[4]), clothingData[5]));
            }
            clothingReader.close();

            Scanner electronicsReader = new Scanner(new File("electronics.txt"));

            while (electronicsReader.hasNextLine()) {
                String data = electronicsReader.nextLine();
                String[] electronicsData = data.split(",");

                shoppingProduct.electronics.add(new Electronics(electronicsData[0], electronicsData[1], Integer.valueOf(electronicsData[2].toString()),
                        Double.parseDouble(electronicsData[3])
                        , electronicsData[4], Double.parseDouble(electronicsData[5])));
            }
            electronicsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void openGui(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void menu() {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Select the menu to continue \n 1.Add a new product \n 2.Delete a product \n 3.Print the list of the products \n 4.Save in a file \n 5.Open GUI");
        int menuNo = myObj.nextInt();


        switch (menuNo) {
            case 1:
                addNewProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                viewProduct();
                break;
            case 4:
                saveToFile();
                break;

            case 5:
                openGui();
                break;

        }
    }

    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        westminsterShoppingManager.menu();
    }
}

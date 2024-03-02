import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Gui2 {

    public JFrame frame;

    public Gui2(){
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Shopping Cart");
        frame.setBounds(430, 200, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        DefaultTableModel model = new DefaultTableModel();

        JTable table = new JTable(model);

        Object[] colomns = {"Product", "Quantity", "Price"};
        model.setColumnIdentifiers(colomns);
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 464, 200);
        frame.getContentPane().add(scrollPane);
        Object[] row = new Object[3];

        double total=0;

        int clothingCount=0;
        int electronicsCount=0;

        for (Cart cart : Gui.shoppingCart.products) {
            row[0] = cart.getProductName();
            row[1] = cart.getQuantiy();
            row[2] = cart.getPrice();
            model.addRow(row);
            total=total+cart.getPrice();

            if(cart.getCategory().toLowerCase().equals("clothing")){
                clothingCount=clothingCount+cart.getQuantiy();
            }else if(cart.getCategory().toLowerCase().equals("electronics")){
                electronicsCount=electronicsCount+cart.getQuantiy();
            }
        }

        JLabel totallabel = new JLabel("Total :");
        totallabel.setBounds(10, 300, 200, 20);
        frame.getContentPane().add(totallabel);

        JLabel totallabel1 = new JLabel(String.valueOf(total));
        totallabel1.setBounds(220, 300, 200, 20);
        frame.getContentPane().add(totallabel1);
        JLabel discount1label1 = new JLabel("");
        if(electronicsCount>=3){
            JLabel discount1label = new JLabel("Items in same Category Discount :");
            discount1label.setBounds(10, 320, 200, 20);
            frame.getContentPane().add(discount1label);
            total=total*0.20;
            discount1label1.setText(String.valueOf(total));
            discount1label1.setBounds(220, 320, 200, 20);
            frame.getContentPane().add(discount1label1);


        }else if(clothingCount>=3){
            JLabel discount1label = new JLabel("Items in same Category Discount :");
            discount1label.setBounds(10, 320, 200, 20);
            frame.getContentPane().add(discount1label);
            total=total*0.20;
            discount1label1.setText(String.valueOf(total));
            discount1label1.setBounds(220, 320, 200, 20);
            frame.getContentPane().add(discount1label1);
        }else{
            JLabel discount1label = new JLabel("Items in same Category Discount :");
            discount1label.setBounds(10, 320, 200, 20);
            frame.getContentPane().add(discount1label);
            discount1label1.setText("0");
            discount1label1.setBounds(220, 320, 200, 20);
            frame.getContentPane().add(discount1label1);
        }

        double finalTotal=Double.parseDouble(totallabel1.getText().toString())-Double.parseDouble(discount1label1.getText().toString());
        JLabel finalTotallabel = new JLabel("Final Total :");
        finalTotallabel.setBounds(10, 340, 200, 20);
        frame.getContentPane().add(finalTotallabel);

        JLabel finalTotallabel1 = new JLabel(String.valueOf(finalTotal));
        finalTotallabel1.setBounds(220, 340, 200, 20);
        frame.getContentPane().add(finalTotallabel1);




        JLabel produtIdLabel = new JLabel("");
        produtIdLabel.setBounds(10, 330, 200, 20);
        frame.getContentPane().add(produtIdLabel);
        produtIdLabel.setVisible(false);

        JLabel categoryLabel = new JLabel("");
        categoryLabel.setBounds(10, 360, 200, 20);
        frame.getContentPane().add(categoryLabel);
        categoryLabel.setVisible(false);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }
}

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gui {

    public JFrame frame;
    private JComboBox productCategory;
    String categories[] = {"All", "Electronics", "Clothing"};
    ShoppingProduct shoppingProduct = new ShoppingProduct();
    public  static ShoppingCart shoppingCart = new ShoppingCart();

    JLabel count = new JLabel("0");


    public Gui() {
        initialize();
        readFile();
    }

    public void readFile() {
        try {
            Scanner clothingReader = new Scanner(new File("clothings.txt"));
            while (clothingReader.hasNextLine()) {
                String data = clothingReader.nextLine();


                String[] clothingData = data.split(",");

                shoppingProduct.clothings.add(new Clothing(clothingData[0], clothingData[1], Integer.valueOf(clothingData[2].toString()), Double.parseDouble(clothingData[3]), Double.parseDouble(clothingData[4]), clothingData[5]));
            }
            clothingReader.close();

            Scanner electronicsReader = new Scanner(new File("electronics.txt"));

            while (electronicsReader.hasNextLine()) {
                String data = electronicsReader.nextLine();
                String[] electronicsData = data.split(",");

                shoppingProduct.electronics.add(new Electronics(electronicsData[0], electronicsData[1], Integer.valueOf(electronicsData[2].toString()), Double.parseDouble(electronicsData[3]), electronicsData[4], Double.parseDouble(electronicsData[5])));
            }
            electronicsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    private void initialize() {

        frame = new JFrame("WestMinister Shopping Center");
        frame.setBounds(430, 200, 500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        DefaultTableModel model = new DefaultTableModel();

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);

                try {
                    if (getValueAt(rowIndex, 2).toString().toLowerCase().equals("clothing")) {
                        Clothing clothing = shoppingProduct.clothings.stream().filter(x -> x.productId.equals((String) getValueAt(rowIndex, 0))).findFirst().orElse(null);

                        if (clothing.getNoOfAvailableItems() > 3) {
                            component.setBackground(Color.white);
                        } else {
                            component.setBackground(Color.red);

                        }
                    } else {
                        Electronics electronics = shoppingProduct.electronics.stream().filter(x -> x.productId.equals((String) getValueAt(rowIndex, 0))).findFirst().orElse(null);

                        if (electronics.getNoOfAvailableItems() < 3) {
                            //System.out.println(rowIndex);
                            component.setBackground(Color.RED);
                        } else {
                            component.setBackground(Color.white);

                        }
                    }
                } catch (Exception ex) {

                }



                return component;
            }
        };





        Object[] colomns = {"Product ID", "Name", "Category", "Price"};
        model.setColumnIdentifiers(colomns);
        table.setModel(model);


        productCategory = new JComboBox(categories);
        productCategory.setBounds(120, 11, 133, 20);
        frame.getContentPane().add(productCategory);

        JLabel lblProductCateogoryLabel = new JLabel("Select Product Category");
        lblProductCateogoryLabel.setBounds(10, 11, 95, 20);
        frame.getContentPane().add(lblProductCateogoryLabel);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 464, 200);
        frame.getContentPane().add(scrollPane);

        JLabel heading = new JLabel("Selected Product Detail");
        heading.setBounds(10, 300, 200, 20);
        frame.getContentPane().add(heading);
        heading.setVisible(false);


        JLabel produtIdLabel = new JLabel("");
        produtIdLabel.setBounds(10, 330, 200, 20);
        frame.getContentPane().add(produtIdLabel);
        produtIdLabel.setVisible(false);

        JLabel categoryLabel = new JLabel("");
        categoryLabel.setBounds(10, 360, 200, 20);
        frame.getContentPane().add(categoryLabel);
        categoryLabel.setVisible(false);


        JLabel nameLabel = new JLabel("");
        nameLabel.setBounds(10, 390, 200, 20);
        frame.getContentPane().add(nameLabel);
        nameLabel.setVisible(false);


        JLabel sizeOrBrandLabel = new JLabel("");
        sizeOrBrandLabel.setBounds(10, 420, 200, 20);
        frame.getContentPane().add(sizeOrBrandLabel);
        sizeOrBrandLabel.setVisible(false);


        JLabel colorOrWarentyLabel = new JLabel("");
        colorOrWarentyLabel.setBounds(10, 450, 200, 20);
        frame.getContentPane().add(colorOrWarentyLabel);
        colorOrWarentyLabel.setVisible(false);


        JLabel availableItemsLabel = new JLabel("");
        availableItemsLabel.setBounds(10, 480, 200, 20);
        frame.getContentPane().add(availableItemsLabel);
        availableItemsLabel.setVisible(false);

        JLabel priceLabel = new JLabel("");
        priceLabel.setBounds(10, 510, 200, 20);
        frame.getContentPane().add(priceLabel);
        priceLabel.setVisible(false);

        JButton btnShoppingCart = new JButton("Shopping Cart");
        btnShoppingCart.setBounds(300, 10, 150, 23);
        frame.getContentPane().add(btnShoppingCart);


        JButton addPlusButton = new JButton("+");
        addPlusButton.setBounds(10, 540, 50, 40);
        frame.getContentPane().add(addPlusButton);
        addPlusButton.setVisible(false);

        JButton addMinusButton = new JButton("-");
        addMinusButton.setBounds(120, 540, 50, 40);
        frame.getContentPane().add(addMinusButton);
        addMinusButton.setVisible(false);


        count.setBounds(87, 540, 50, 40);
        frame.getContentPane().add(count);
        count.setVisible(false);

        JButton addToShoppingCartButton = new JButton("Add To Shopping Cart");
        addToShoppingCartButton.setBounds(10, 600, 200, 20);
        frame.getContentPane().add(addToShoppingCartButton);
        addToShoppingCartButton.setVisible(false);

        Object[] row = new Object[4];

        addToShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(count.getText().toString()) > 0) {
                    int a = JOptionPane.showConfirmDialog(null, "Do you want to add this item to the cart?");
                    if (a == JOptionPane.YES_OPTION) {
                        double price = Integer.valueOf(count.getText().toString()) * Double.parseDouble(priceLabel.getText().toString().split(":")[1]);
                        Cart cart = new Cart(nameLabel.getText().toString().split(":")[1], Integer.valueOf(count.getText().toString()), price,categoryLabel.getText().toString().split(":")[1]);
                        shoppingCart.add(cart);
                        heading.setVisible(false);
                        produtIdLabel.setVisible(false);
                        categoryLabel.setVisible(false);
                        nameLabel.setVisible(false);
                        sizeOrBrandLabel.setVisible(false);
                        colorOrWarentyLabel.setVisible(false);
                        availableItemsLabel.setVisible(false);
                        addPlusButton.setVisible(false);
                        addMinusButton.setVisible(false);
                        count.setVisible(false);
                        addToShoppingCartButton.setVisible(false);
                        priceLabel.setVisible(false);
                        count.setText("0");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The Item count is 0", "Error Message", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        addPlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count1 = Integer.valueOf(count.getText().toString());
                count1 = count1 + 1;

                String[] split = availableItemsLabel.getText().toString().split(":");
                int availableItem = Integer.valueOf(split[1]);

                if (availableItem < count1) {
                    JOptionPane.showMessageDialog(null, "You can't select more than availabe item", "Error Message", JOptionPane.ERROR_MESSAGE);

                } else {
                    count.setText(String.valueOf(count1));
                }


            }
        });

        addMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count1 = Integer.valueOf(count.getText().toString());
                count1 = count1 - 1;
                count.setText(count1 >= 0 ? String.valueOf(count1) : "0");


            }
        });


        addToShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });

        productCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] row = new Object[4];
                if (model.getRowCount() > 0) {
                    for (int i = model.getRowCount() - 1; i > -1; i--) {
                        model.removeRow(i);
                    }
                }

                if (productCategory.getSelectedItem().toString().toLowerCase().equals("clothing")) {

                    for (Clothing clothing : shoppingProduct.clothings) {
                        row[0] = clothing.getProductId();
                        row[1] = clothing.getProductName();
                        row[2] = "Clothing";
                        row[3] = clothing.getPrice();
                        model.addRow(row);


                    }
                } else if (productCategory.getSelectedItem().toString().toLowerCase().toString().equals("electronics")) {

                    for (Electronics electronics : shoppingProduct.electronics) {
                        row[0] = electronics.getProductId();
                        row[1] = electronics.getProductName();
                        row[2] = "Electronics";
                        row[3] = electronics.getPrice();
                        model.addRow(row);


                    }
                } else {
                    for (Clothing clothing : shoppingProduct.clothings) {
                        row[0] = clothing.getProductId();
                        row[1] = clothing.getProductName();
                        row[2] = "Clothing";
                        row[3] = clothing.getPrice();
                        model.addRow(row);


                    }

                    for (Electronics electronics : shoppingProduct.electronics) {
                        row[0] = electronics.getProductId();
                        row[1] = electronics.getProductName();
                        row[2] = "Electronics";
                        row[3] = electronics.getPrice();
                        model.addRow(row);

                    }
                }

                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);

                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);

            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                count.setText("0");
                heading.setVisible(true);
                addPlusButton.setVisible(true);
                addMinusButton.setVisible(true);
                count.setVisible(true);
                addToShoppingCartButton.setVisible(true);


                produtIdLabel.setVisible(true);
                produtIdLabel.setText("Product ID :" + (String) table.getValueAt(table.getSelectedRow(), 0));

                categoryLabel.setVisible(true);
                categoryLabel.setText("Category :" + (String) table.getValueAt(table.getSelectedRow(), 2));

                nameLabel.setVisible(true);
                nameLabel.setText("Product Name :" + (String) table.getValueAt(table.getSelectedRow(), 1));

                if (table.getValueAt(table.getSelectedRow(), 2).toString().toLowerCase().equals("clothing")) {
                    Clothing clothing = shoppingProduct.clothings.stream().filter(x -> x.productId.equals((String) table.getValueAt(table.getSelectedRow(), 0))).findFirst().orElse(null);

                    availableItemsLabel.setVisible(true);
                    availableItemsLabel.setText("Available Item :" + clothing.getNoOfAvailableItems());

                    sizeOrBrandLabel.setVisible(true);
                    sizeOrBrandLabel.setText("Size :" + clothing.getSize());

                    colorOrWarentyLabel.setVisible(true);
                    colorOrWarentyLabel.setText("Colour :" + clothing.getColour());

                } else {
                    Electronics electronics = shoppingProduct.electronics.stream().filter(x -> x.productId.equals((String) table.getValueAt(table.getSelectedRow(), 0))).findFirst().orElse(null);

                    availableItemsLabel.setVisible(true);
                    availableItemsLabel.setText("Available Item :" + electronics.getNoOfAvailableItems());

                    sizeOrBrandLabel.setVisible(true);
                    sizeOrBrandLabel.setText("Brand Name :" + electronics.getBrandName());

                    colorOrWarentyLabel.setVisible(true);
                    colorOrWarentyLabel.setText("Warenty Period :" + electronics.getWarentyPeriod());
                }

                priceLabel.setVisible(true);
                priceLabel.setText("Price :" + String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        btnShoppingCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
             //   System.out.println(shoppingCart.products.size());
//                EventQueue.invokeLater(new Runnable() {
//                    public void run() {
//                        try {
//                            Gui2 window = new Gui2();
//                            window.frame.setVisible(true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });

                Gui2 gui2=new Gui2();
                gui2.frame.setVisible(true);
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }


}
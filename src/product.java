import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class product{
    JFrame f;
    JButton b1,b2,bck;
    JLabel l1,bg;
    JComboBox jb;
    product(){
    f = new JFrame("Manage Products");
    ImageIcon img = new ImageIcon("bg7.jpg");
        bg = new JLabel(img,JLabel.CENTER);
        bg.setBounds(0,0,1400,750);
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                new AdminHome();
            }
        });
    jb = new JComboBox();
    jb.setBounds(250,200,300,40);
    jb.addItem("MOBILE PHONES");
    jb.addItem("LAPTOP / PC");
    jb.addItem("GAMING");
    jb.addItem("TVs");
    b2 = new JButton("OPEN");
    b2.setBounds(300,320,150,50);
    b2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(jb.getSelectedIndex()==0){
                f.dispose();
                new mobile();
            }
            else if(jb.getSelectedIndex()==1){
                f.dispose();
                new laptop();
            }
            else if(jb.getSelectedIndex()==2){
                f.dispose();
                new gaming();
            }
            else if(jb.getSelectedIndex()==3){
                f.dispose();
                new tv();
            }
        }
    });
    l1 = new JLabel("Select Product Category");
    l1.setFont(new Font("Verdana", Font.BOLD, 30));
    l1.setForeground(Color.WHITE);
    l1.setBounds(200,100,500,50);
    f.add(jb);
    f.add(l1);
    f.add(b2);
    f.add(bck);
    f.add(bg);
    f.setSize(850,480);
    f.setLayout(null);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    f.setResizable(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
class mobile{
    JFrame f2;
    JLabel id,name,brand,price,bg;
    JTextField t_id,t_name,t_brand,t_price;
    JButton b1,b2,b3,b4,bck,srch,clr;
    mobile(){
        JFrame f2 = new JFrame("Manage Mobile Phones");
        JTable tab = new JTable();
        JScrollPane sp = new JScrollPane(tab);
        sp.setBounds(650,10,500,400);
        ImageIcon img = new ImageIcon("bg7.jpg");
        bg = new JLabel(img,JLabel.CENTER);
        bg.setBounds(0,0,1400,750);
            id = new JLabel("Enter Device ID");
            id.setBounds(10,50,300,50);
            id.setFont(new Font("Verdana", Font.PLAIN, 20));
            id.setForeground(Color.WHITE);
            name = new JLabel("Enter Device Name");
            name.setBounds(10,100,300,50);
            name.setFont(new Font("Verdana", Font.PLAIN, 20));
            name.setForeground(Color.WHITE);
            brand = new JLabel("Enter Device Brand");
            brand.setBounds(10,150,300,50);
            brand.setFont(new Font("Verdana", Font.PLAIN, 20));
            brand.setForeground(Color.WHITE);
            price = new JLabel("Enter Device Price");
            price.setBounds(10,200,300,50);
            price.setFont(new Font("Verdana", Font.PLAIN, 20));
            price.setForeground(Color.WHITE);
            t_id = new JTextField("");
            t_id.setBounds(250,65,200,25);
            t_name = new JTextField("");
            t_name.setBounds(250,115,200,25);
            t_brand = new JTextField("");
            t_brand.setBounds(250,165,200,25);
            t_price = new JTextField("");
            t_price.setBounds(250,215,200,25);


        srch = new JButton("SEARCH");
        srch.setBounds(480,65,100,20);
        srch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aet){
                int id = Integer.parseInt(t_id.getText());
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                        PreparedStatement stm = (PreparedStatement) con.prepareStatement("Select * from mbl_phns where mbl_id=?");
                        stm.setInt(1, id);
                        ResultSet rs = stm.executeQuery();
                        if(rs.next()==true){
                            t_name.setText(rs.getString(2));
                            t_brand.setText(rs.getString(3));
                            t_price.setText(rs.getString(4));
                        }else{
                            JOptionPane.showMessageDialog(srch, "Data Not Found");
                        }
                        con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace(); 
            }catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            
         }
    });
    clr = new JButton("CLEAR");
    clr.setBounds(480,95,100,20);
    clr.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            t_id.setText(null);
            t_name.setText(null);
            t_brand.setText(null);
            t_price.setText(null);
        }
    });


        b1 = new JButton("ADD PRODUCT");
        b1.setBounds(10,400,150,40);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                int price = Integer.parseInt(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                Statement stm = con.createStatement();      
                stm.executeUpdate("INSERT INTO mbl_phns(mbl_id, mbl_name, mbl_brand, mbl_price) VALUES("+id+",'"+name+"','"+brand+"','"+price+"')");
                    JOptionPane.showMessageDialog(b1, "Product Added Successfuly");
                    f2.dispose();
                    new mobile();
                    con.close();                   
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    });

        b2 = new JButton("UPDATE PRODUCT");
        b2.setBounds(200,400,150,40);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                float price = Float.parseFloat(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE mbl_phns SET mbl_name='"+name+"' ,mbl_brand='"+brand+"',mbl_price='"+price+"' WHERE mbl_id='"+id+"'");
                stm.execute();                
                    JOptionPane.showMessageDialog(b1, "Product Updated Successfully");
                    f2.dispose();
                    new mobile();
                  
                    con.close();                   
            }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } 
        }
    });


        b3 = new JButton("DELETE PRODUCT");
        b3.setBounds(400,400,150,40);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                //String name = t_name.getText();
                //String brand = t_brand.getText();
                //float price = Float.parseFloat(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM mbl_phns WHERE mbl_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Deleted Successfuly");
                    f2.dispose();
                    new mobile();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
         }
    });
    
        b4 = new JButton("VIEW ALL PRODUCTS");
        b4.setBounds(185,500,200,40); 
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM mbl_phns");
                    ResultSetMetaData rsd = rs.getMetaData();
                    DefaultTableModel dft = (DefaultTableModel) tab.getModel();

                    int cols = rsd.getColumnCount();
                    String[] colName =new String[cols];
                    for(int i=0;i<cols;i++)
                    colName[i] = rsd.getColumnName(i+1);
                    dft.setColumnIdentifiers(colName);
                    String id,name,brand,price;
                    while(rs.next()){
                        id = rs.getString(1);
                        name = rs.getString(2);
                        brand = rs.getString(3);
                        price = rs.getString(4);
                        String[] row = {id,name,brand,price};
                        dft.addRow(row);
                    }
                
                    con.close(); 
                }
                catch (ClassNotFoundException | SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f2.dispose();
                new product();
            }
        });
        f2.add(id);
        f2.add(name);
        f2.add(brand);
        f2.add(price);
        f2.add(t_id);
        f2.add(t_name);
        f2.add(t_brand);
        f2.add(t_price);
        f2.add(b1);    
        f2.add(b2);
        f2.add(b3);
        f2.add(b4);
        f2.add(bck);
        f2.add(srch);
        f2.add(clr);
        f2.add(sp);
        f2.add(bg);
        f2.setSize(1200,650);
        f2.setLayout(new BorderLayout(10,5));
        f2.setLocationRelativeTo(null);
        f2.setVisible(true);
        f2.setResizable(true);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class laptop{
    JFrame f3;
    JLabel id,name,brand,price;
    JTextField t_id,t_name,t_brand,t_price;
    JButton b1,b2,b3,b4,bck,srch,clr;
    laptop(){
        JFrame f3 = new JFrame("Manage LAPTOP / PC");
        JTable tab = new JTable();
        JScrollPane sp = new JScrollPane(tab);
        sp.setBounds(650,10,500,400);
            id = new JLabel("Enter Device ID");
            id.setBounds(10,50,300,50);
            id.setFont(new Font("Verdana", Font.PLAIN, 20));
            id.setForeground(Color.WHITE);
            name = new JLabel("Enter Device Name");
            name.setBounds(10,100,300,50);
            name.setFont(new Font("Verdana", Font.PLAIN, 20));
            name.setForeground(Color.WHITE);
            brand = new JLabel("Enter Device Brand");
            brand.setBounds(10,150,300,50);
            brand.setFont(new Font("Verdana", Font.PLAIN, 20));
            brand.setForeground(Color.WHITE);
            price = new JLabel("Enter Device Price");
            price.setBounds(10,200,300,50);
            price.setFont(new Font("Verdana", Font.PLAIN, 20));
            price.setForeground(Color.WHITE);
            t_id = new JTextField("");
            t_id.setBounds(250,65,200,25);
            t_name = new JTextField("");
            t_name.setBounds(250,115,200,25);
            t_brand = new JTextField("");
            t_brand.setBounds(250,165,200,25);
            t_price = new JTextField("");
            t_price.setBounds(250,215,200,25);


        srch = new JButton("SEARCH");
        srch.setBounds(480,65,100,20);
        srch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aet){
                int id = Integer.parseInt(t_id.getText());
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                        PreparedStatement stm = (PreparedStatement) con.prepareStatement("Select * from lptp_pc where lptp_id=?");
                        stm.setInt(1, id);
                        ResultSet rs = stm.executeQuery();
                        if(rs.next()==true){
                            t_name.setText(rs.getString(2));
                            t_brand.setText(rs.getString(3));
                            t_price.setText(rs.getString(4));
                        }else{
                            JOptionPane.showMessageDialog(srch, "Data Not Found");
                        }
                        con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace(); 
            }catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            
         }
    });
    clr = new JButton("CLEAR");
    clr.setBounds(480,95,100,20);
    clr.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            t_id.setText(null);
            t_name.setText(null);
            t_brand.setText(null);
            t_price.setText(null);
        }
    });


        b1 = new JButton("ADD PRODUCT");
        b1.setBounds(10,400,150,40);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                int price = Integer.parseInt(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                Statement stm = con.createStatement();      
                stm.executeUpdate("INSERT INTO lptp_pc(lptp_id, lptp_name, lptp_brand, lptp_price) VALUES("+id+",'"+name+"','"+brand+"','"+price+"')");
                    JOptionPane.showMessageDialog(b1, "Product Added Successfuly");
                    f3.dispose();
                    new laptop();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    });

        b2 = new JButton("UPDATE PRODUCT");
        b2.setBounds(200,400,150,40);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                float price = Float.parseFloat(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE lptp_pc SET lptp_name='"+name+"' ,lptp_brand='"+brand+"',lptp_price='"+price+"' WHERE lptp_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Updated Successfuly");
                    f3.dispose();
                    new laptop();                    
            }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } 
        }
    });


        b3 = new JButton("DELETE PRODUCT");
        b3.setBounds(400,400,150,40);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM lptp_pc WHERE lptp_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Deleted Successfuly");
                    f3.dispose();
                    new laptop();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
         }
    });
    
        b4 = new JButton("VIEW ALL PRODUCTS");
        b4.setBounds(185,500,200,40); 
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM lptp_pc");
                    ResultSetMetaData rsd = rs.getMetaData();
                    DefaultTableModel dft = (DefaultTableModel) tab.getModel();

                    int cols = rsd.getColumnCount();
                    String[] colName =new String[cols];
                    for(int i=0;i<cols;i++)
                    colName[i] = rsd.getColumnName(i+1);
                    dft.setColumnIdentifiers(colName);
                    String id,name,brand,price;
                    while(rs.next()){
                        id = rs.getString(1);
                        name = rs.getString(2);
                        brand = rs.getString(3);
                        price = rs.getString(4);
                        String[] row = {id,name,brand,price};
                        dft.addRow(row);
                    }
                }
                catch (ClassNotFoundException | SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f3.dispose();
                new product();
            }
        });
        f3.add(id);
        f3.add(name);
        f3.add(brand);
        f3.add(price);
        f3.add(t_id);
        f3.add(t_name);
        f3.add(t_brand);
        f3.add(t_price);
        f3.add(b1);    
        f3.add(b2);
        f3.add(b3);
        f3.add(b4);
        f3.add(bck);
        f3.add(srch);
        f3.add(clr);
        f3.add(sp);
        f3.add(bg);
        f3.setSize(1200,650);
        f3.setLayout(new BorderLayout(10,5));
        f3.setLocationRelativeTo(null);
        f3.setVisible(true);
        f3.setResizable(true);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class gaming{
    JFrame f4;
    JLabel id,name,brand,price;
    JTextField t_id,t_name,t_brand,t_price;
    JButton b1,b2,b3,b4,bck,srch,clr;
    gaming(){
        JFrame f4 = new JFrame("Manage Gaming Consoles");
        JTable tab = new JTable();
        JScrollPane sp = new JScrollPane(tab);
        sp.setBounds(650,10,500,400);
            id = new JLabel("Enter Device ID");
            id.setBounds(10,50,300,50);
            id.setFont(new Font("Verdana", Font.PLAIN, 20));
            id.setForeground(Color.WHITE);
            name = new JLabel("Enter Device Name");
            name.setBounds(10,100,300,50);
            name.setFont(new Font("Verdana", Font.PLAIN, 20));
            name.setForeground(Color.WHITE);
            brand = new JLabel("Enter Device Brand");
            brand.setBounds(10,150,300,50);
            brand.setFont(new Font("Verdana", Font.PLAIN, 20));
            brand.setForeground(Color.WHITE);
            price = new JLabel("Enter Device Price");
            price.setBounds(10,200,300,50);
            price.setFont(new Font("Verdana", Font.PLAIN, 20));
            price.setForeground(Color.WHITE);
            t_id = new JTextField("");
            t_id.setBounds(250,65,200,25);
            t_name = new JTextField("");
            t_name.setBounds(250,115,200,25);
            t_brand = new JTextField("");
            t_brand.setBounds(250,165,200,25);
            t_price = new JTextField("");
            t_price.setBounds(250,215,200,25);


        srch = new JButton("SEARCH");
        srch.setBounds(480,65,100,20);
        srch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aet){
                int id = Integer.parseInt(t_id.getText());
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                        PreparedStatement stm = (PreparedStatement) con.prepareStatement("Select * from gmng where gmng_id=?");
                        stm.setInt(1, id);
                        ResultSet rs = stm.executeQuery();
                        if(rs.next()==true){
                            t_name.setText(rs.getString(2));
                            t_brand.setText(rs.getString(3));
                            t_price.setText(rs.getString(4));
                        }else{
                            JOptionPane.showMessageDialog(srch, "Data Not Found");
                        }
                        con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace(); 
            }catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }   
         }
    });
    clr = new JButton("CLEAR");
    clr.setBounds(480,95,100,20);
    clr.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            t_id.setText(null);
            t_name.setText(null);
            t_brand.setText(null);
            t_price.setText(null);
        }
    });


        b1 = new JButton("ADD PRODUCT");
        b1.setBounds(10,400,150,40);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                int price = Integer.parseInt(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                Statement stm = con.createStatement();      
                stm.executeUpdate("INSERT INTO gmng(gmng_id, gmng_name, gmng_brand, gmng_price) VALUES("+id+",'"+name+"','"+brand+"','"+price+"')");
                    JOptionPane.showMessageDialog(b1, "Product Added Successfuly");
                    f4.dispose();
                    new gaming();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    });

        b2 = new JButton("UPDATE PRODUCT");
        b2.setBounds(200,400,150,40);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                float price = Float.parseFloat(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE gmng SET gmng_name='"+name+"' ,gmng_brand='"+brand+"',gmng_price='"+price+"' WHERE gmng_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Updated Successfuly");
                    f4.dispose();
                    new gaming();                    
            }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } 
        }
    });


        b3 = new JButton("DELETE PRODUCT");
        b3.setBounds(400,400,150,40);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM gmng WHERE gmng_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Deleted Successfuly");
                    f4.dispose();
                    new gaming();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
         }
    });
    
        b4 = new JButton("VIEW ALL PRODUCTS");
        b4.setBounds(185,500,200,40); 
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM gmng");
                    ResultSetMetaData rsd = rs.getMetaData();
                    DefaultTableModel dft = (DefaultTableModel) tab.getModel();

                    int cols = rsd.getColumnCount();
                    String[] colName =new String[cols];
                    for(int i=0;i<cols;i++)
                    colName[i] = rsd.getColumnName(i+1);
                    dft.setColumnIdentifiers(colName);
                    String id,name,brand,price;
                    while(rs.next()){
                        id = rs.getString(1);
                        name = rs.getString(2);
                        brand = rs.getString(3);
                        price = rs.getString(4);
                        String[] row = {id,name,brand,price};
                        dft.addRow(row);
                    }
                }
                catch (ClassNotFoundException | SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f4.dispose();
                new product();
            }
        });
        f4.add(id);
        f4.add(name);
        f4.add(brand);
        f4.add(price);
        f4.add(t_id);
        f4.add(t_name);
        f4.add(t_brand);
        f4.add(t_price);
        f4.add(b1);    
        f4.add(b2);
        f4.add(b3);
        f4.add(b4);
        f4.add(bck);
        f4.add(srch);
        f4.add(clr);
        f4.add(sp);
        f4.add(bg);
        f4.setSize(1200,650);
        f4.setLayout(new BorderLayout(10,5));
        f4.setLocationRelativeTo(null);
        f4.setVisible(true);
        f4.setResizable(true);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class tv{
    JFrame f5;
    JLabel id,name,brand,price;
    JTextField t_id,t_name,t_brand,t_price;
    JButton b1,b2,b3,b4,bck,srch,clr;
    tv(){
        JFrame f5 = new JFrame("Manage TV Products");
        JTable tab = new JTable();
        JScrollPane sp = new JScrollPane(tab);
        sp.setBounds(650,10,500,400);
            id = new JLabel("Enter Device ID");
            id.setBounds(10,50,300,50);
            id.setFont(new Font("Verdana", Font.PLAIN, 20));
            id.setForeground(Color.WHITE);
            name = new JLabel("Enter Device Name");
            name.setBounds(10,100,300,50);
            name.setFont(new Font("Verdana", Font.PLAIN, 20));
            name.setForeground(Color.WHITE);
            brand = new JLabel("Enter Device Brand");
            brand.setBounds(10,150,300,50);
            brand.setFont(new Font("Verdana", Font.PLAIN, 20));
            brand.setForeground(Color.WHITE);
            price = new JLabel("Enter Device Price");
            price.setBounds(10,200,300,50);
            price.setFont(new Font("Verdana", Font.PLAIN, 20));
            price.setForeground(Color.WHITE);
            t_id = new JTextField("");
            t_id.setBounds(250,65,200,25);
            t_name = new JTextField("");
            t_name.setBounds(250,115,200,25);
            t_brand = new JTextField("");
            t_brand.setBounds(250,165,200,25);
            t_price = new JTextField("");
            t_price.setBounds(250,215,200,25);


        srch = new JButton("SEARCH");
        srch.setBounds(480,65,100,20);
        srch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent aet){
                int id = Integer.parseInt(t_id.getText());
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                        PreparedStatement stm = (PreparedStatement) con.prepareStatement("Select * from tv where tv_id=?");
                        stm.setInt(1, id);
                        ResultSet rs = stm.executeQuery();
                        if(rs.next()==true){
                            t_name.setText(rs.getString(2));
                            t_brand.setText(rs.getString(3));
                            t_price.setText(rs.getString(4));
                        }else{
                            JOptionPane.showMessageDialog(srch, "Data Not Found");
                        }
                        con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace(); 
            }catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            
         }
    });
    clr = new JButton("CLEAR");
    clr.setBounds(480,95,100,20);
    clr.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            t_id.setText(null);
            t_name.setText(null);
            t_brand.setText(null);
            t_price.setText(null);
        }
    });


        b1 = new JButton("ADD PRODUCT");
        b1.setBounds(10,400,150,40);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                int price = Integer.parseInt(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                Statement stm = con.createStatement();      
                stm.executeUpdate("INSERT INTO tv(tv_id, tv_name, tv_brand, tv_price) VALUES("+id+",'"+name+"','"+brand+"','"+price+"')");
                    JOptionPane.showMessageDialog(b1, "Product Added Successfuly");
                    f5.dispose();
                    new tv();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    });

        b2 = new JButton("UPDATE PRODUCT");
        b2.setBounds(200,400,150,40);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                String name = t_name.getText();
                String brand = t_brand.getText();
                float price = Float.parseFloat(t_price.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE tv SET tv_name='"+name+"' ,tv_brand='"+brand+"',tv_price='"+price+"' WHERE tv_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Updated Successfuly");
                    f5.dispose();
                    new tv();                    
            }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } 
        }
    });


        b3 = new JButton("DELETE PRODUCT");
        b3.setBounds(400,400,150,40);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(t_id.getText());
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM tv WHERE tv_id='"+id+"'");
                stm.execute();
                    JOptionPane.showMessageDialog(b1, "Product Deleted Successfuly");
                    f5.dispose();
                    new tv();                    
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
         }
    });
    
        b4 = new JButton("VIEW ALL PRODUCTS");
        b4.setBounds(185,500,200,40); 
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM tv");
                    ResultSetMetaData rsd = rs.getMetaData();
                    DefaultTableModel dft = (DefaultTableModel) tab.getModel();

                    int cols = rsd.getColumnCount();
                    String[] colName =new String[cols];
                    for(int i=0;i<cols;i++)
                    colName[i] = rsd.getColumnName(i+1);
                    dft.setColumnIdentifiers(colName);
                    String id,name,brand,price;
                    while(rs.next()){
                        id = rs.getString(1);
                        name = rs.getString(2);
                        brand = rs.getString(3);
                        price = rs.getString(4);
                        String[] row = {id,name,brand,price};
                        dft.addRow(row);
                    }
                }
                catch (ClassNotFoundException | SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f5.dispose();
                new product();
            }
        });
        f5.add(id);
        f5.add(name);
        f5.add(brand);
        f5.add(price);
        f5.add(t_id);
        f5.add(t_name);
        f5.add(t_brand);
        f5.add(t_price);
        f5.add(b1);    
        f5.add(b2);
        f5.add(b3);
        f5.add(b4);
        f5.add(bck);
        f5.add(srch);
        f5.add(clr);
        f5.add(sp);
        f5.add(bg);
        f5.setSize(1200,650);
        f5.setLayout(new BorderLayout(10,5));
        f5.setLocationRelativeTo(null);
        f5.setVisible(true);
        f5.setResizable(true);
        f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
    public static void main(String args[]){
        new product();
    }
}
 

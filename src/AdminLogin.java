import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class AdminLogin{
    JLabel l3,l4,bg;
    JButton bck,b1,b2;
    JTextField t1;
    JPasswordField jp;
    JFrame f;
    AdminLogin(){
        f = new JFrame("LoginPage");
        ImageIcon img = new ImageIcon("bg7.jpg");
        bg = new JLabel(img,JLabel.CENTER);
        bg.setBounds(0,0,1400,750);
        l3 = new JLabel("Username");
        l3.setBounds(50,60,160,50);
        l3.setFont(new Font("Verdana", Font.PLAIN, 30));
        l3.setForeground(Color.WHITE);
        t1 = new JTextField("");
        t1.setBounds(250,70,160,30);
        l4 = new JLabel("Password");
        l4.setBounds(50,120,160,50);
        l4.setFont(new Font("Verdana", Font.PLAIN, 30));
        l4.setForeground(Color.WHITE);
        jp = new JPasswordField("");
        jp.setBounds(250,130,160,30);
        ImageIcon ibck = new ImageIcon("back.png");
        bck = new JButton(ibck);
        bck.setBounds(20,0,40,40);
        bck.setOpaque(false);
        bck.setContentAreaFilled(false);
        bck.setBorderPainted(false);
        bck.setBorder(null);
        bck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==bck){
                    f.dispose();
                    new Eshop();
                }
            }
        });
        b1 = new JButton("LOGIN");
        b1.setBounds(190,200,80,30);
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String username = t1.getText();
                String password = String.valueOf(jp.getPassword());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mjdb","root","madmax@kd0820"); 
                    PreparedStatement stm = (PreparedStatement) con.prepareStatement("Select username,password from admin_login where username=? and password=?");
                    stm.setString(1, username);
                    stm.setString(2, password);
                    ResultSet rs = stm.executeQuery();
                    if(rs.next()){
                        f.dispose();
                        new AdminHome();                
                    }
                    else{
                        JOptionPane.showMessageDialog(b1, "Invalid username or password!!");   
                    }                
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                } 
            }        
        });
        
        f.add(l3);
        f.add(l4);
        f.add(t1);
        f.add(jp);
        f.add(bck);
        f.add(b1);
        f.add(bg);
        f.setSize(500,300);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);  
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    public static void main(String[] args){
        new AdminLogin();
   }
}
   
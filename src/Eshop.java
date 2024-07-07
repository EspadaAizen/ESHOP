import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;


public class Eshop{
    JFrame f;
    JLabel l1,l2,bg;
    JButton b1;
    Eshop(){
        f = new JFrame("StartPg");
        l1 = new JLabel("WELCOME TO");
        l1.setBounds(500,150,1000,200);
        l1.setFont(new Font("Verdana", Font.BOLD, 50));
        l1.setForeground(Color.WHITE);
        l2 = new JLabel("VALHALLA ELECTRONICS");
        l2.setBounds(380,230,1000,200);
        l2.setFont(new Font("Verdana", Font.BOLD, 50));
        l2.setForeground(Color.WHITE);
        b1 = new JButton("EXPLORE");
        b1.setBounds(600,450,200,50);
        b1.setFocusPainted(false);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                new AdminLogin();
            }
        });
        ImageIcon img = new ImageIcon("bg7.jpg");
        bg = new JLabel(img,JLabel.CENTER);
        bg.setBounds(0,0,1400,750);
        f.add(l1);
        f.add(l2);
        f.add(bg);
        f.add(b1);
        f.setSize(1400,750);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);  
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
public static void main(String[] args){
    new Eshop();
 }
}
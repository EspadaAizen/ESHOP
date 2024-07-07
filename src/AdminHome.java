import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.Border;

public class AdminHome{
      JLabel l,bg;
      JFrame f;
      JButton mp,ma;
      JMenu m;
      JMenuItem mi1,mi2,mi3;
      JMenuBar mb;
      Border br;
      AdminHome(){
            JFrame f = new JFrame("Home");
            ImageIcon img = new ImageIcon("bg7.jpg");
            bg = new JLabel(img,JLabel.CENTER);
            bg.setBounds(0,0,1400,750);
            ImageIcon prdct = new ImageIcon("product.png");
            mp = new JButton("Manage Products",prdct);
            mp.setFocusPainted(false);
            mp.setBounds(350,250,300,100);
            mp.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                      if(e.getSource()==mp){
                            f.dispose();
                            new product();
                      }
                }
          }); 
          ImageIcon usr = new ImageIcon("user.png");
            ma = new JButton("My Account",usr);
            ma.setBounds(850,250,400,100);
            ma.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(e.getSource()==ma){
                        f.dispose();
                        new profile();
                    }
                }
            });
            mb = new JMenuBar();
            m = new JMenu("Menu");
            mi1 = new JMenuItem("About us");
            mi2 = new JMenuItem("Setting");
            mi3 = new JMenuItem("Logout");
            mi3.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e){
                      if(e.getSource()==mi3){
                          f.dispose();
                          new Eshop();
                      }
                  }        
              });
            m.add(mi1);
            m.add(mi2);
            m.add(mi3);
            mb.add(m);
            f.add(mp);
            f.add(ma);
            f.setJMenuBar(mb);
            f.add(bg);
            f.setSize(1400,760);
            f.setLayout(null);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            f.setResizable(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
      }
    class profile {
        JTextField tf1, tf2, tf3;
        JLabel l1, l2, l3, bg,prImg;
        JButton bck;

        profile() {
            JFrame f = new JFrame("Profile");
            ImageIcon img = new ImageIcon("bg7.jpg");
            bg = new JLabel(img, JLabel.CENTER);
            bg.setBounds(0, 0, 480, 640);
            ImageIcon ibck = new ImageIcon("back.png");
            bck = new JButton(ibck);
            bck.setBounds(20, 0, 40, 40);
            bck.setOpaque(false);
            bck.setContentAreaFilled(false);
            bck.setBorderPainted(false);
            bck.setBorder(null);
            bck.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    new AdminHome();
                }
            });
            ImageIcon mImg = new ImageIcon("mUser.png");
            ImageIcon fImg = new ImageIcon("fUser.png");
            JComboBox jb = new JComboBox();
            jb.setBounds(250, 10, 200, 20);
            jb.addItem("MAYURESH JADHAV");
            jb.addItem("ANUSHKA KAMBLE");
            jb.addItem("ADITYA SINGH");
            jb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(jb.getSelectedIndex()==0){
                        tf1.setText("MAYURESH");
                        prImg.setIcon(mImg);
                    }
                    if(jb.getSelectedIndex()==1){
                        tf1.setText("ANUSHKA");
                        prImg.setIcon(fImg);
                    }
                    if(jb.getSelectedIndex()==2){
                        tf1.setText("ADITYA");
                        prImg.setIcon(mImg);
                    }
                }
            });
            prImg = new JLabel(mImg);
            prImg.setBounds(180, 80, 100, 100);
            l1 = new JLabel("Username");
            l1.setBounds(100, 200, 180, 50);
            l1.setFont(new Font("Verdana", Font.PLAIN, 20));
            l1.setForeground(Color.WHITE);
            l2 = new JLabel("Address");
            l2.setBounds(100, 250, 180, 50);
            l2.setFont(new Font("Verdana", Font.PLAIN, 20));
            l2.setForeground(Color.WHITE);
            l3 = new JLabel("Password");
            l3.setBounds(100, 300, 180, 50);
            l3.setFont(new Font("Verdana", Font.PLAIN, 20));
            l3.setForeground(Color.WHITE);
            tf1 = new JTextField("MAYURESH");
            tf1.setBounds(250, 210, 180, 30);
            tf1.setEditable(false);
            tf2 = new JTextField("Rasayani");
            tf2.setBounds(250, 260, 180, 30);
            tf2.setEditable(false);
            tf3 = new JTextField("********");
            tf3.setBounds(250, 310, 180, 30);
            tf3.setEditable(false);
            f.add(prImg);

            f.add(l1);
            f.add(l2);
            f.add(l3);
            f.add(tf1);
            f.add(tf2);
            f.add(tf3);
            f.add(jb);

            f.add(bck);
            f.add(bg);
            f.setSize(480, 640);
            f.setLayout(null);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            f.setResizable(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
      public static void main(String args[]){
        new AdminHome();
      }
 }
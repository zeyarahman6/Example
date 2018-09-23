package bs;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;














public class LoginPage
{
  private String user;
  private String pass;
  protected JFrame frame;
  private JTextField username;
  private JPasswordField password;
  private JLabel lblClock;
  private JLabel lblClock_1;
  public Connection con = null;
  public Connection scon = null;
  
  private JLabel imgLabel;
  
  private JPasswordField passwordField;
  
  private JTextField textField;
  
  JButton create;
  
  JPanel panel_1;
  
  private JPasswordField passwordField_1;
  
  DatabaseMetaData dmd;
  
  private void tableCheck()
  {
    try
    {
      dmd = con.getMetaData();
      ResultSet rs = dmd.getTables(null, null, "admintable", null);
      
      if (!rs.next())
      {





        String sql = "create table admintable (username varchar(50), password varchar(50))";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
        
        ps.close();
      }
    }
    catch (Exception localException) {}
    










    try
    {
      ResultSet rs = dmd.getTables(null, null, "company", null);
      if (!rs.next())
      {





        String sql = "create table COMPANY (COMPANY_name varchar(250), COMPANY_add varchar(250), COMPANY_phone varchar(40), company_email varchar(40))";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
      }
    }
    catch (Exception localException1) {}
    
















    try
    {
      ResultSet rs = dmd.getTables(null, null, "customers", null);
      if (!rs.next())
      {





        String sql = "create table  CUSTOMERS  (INVOICE_NO INTEGER(10) PRIMARY KEY auto_increment, cust_name varchar(50), cust_phone varchar(30), grand_total DECIMAL(10), TOTAL_ITEMS DECIMAL(7), date date, time time)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
      }
    }
    catch (Exception localException2) {}
    






















    try
    {
      ResultSet rs = dmd.getTables(null, null, "invoicetable", null);
      if (!rs.next())
      {





        String sql = "create table  INVOICETABLE (p_name varchar(50), p_price DECIMAL(7), P_QTY DECIMAL(5), p_DISC DECIMAL(5), p_AMT DECIMAL(7), I_NO INTEGER(10))";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
      }
    }
    catch (Exception localException3) {}
    















    try
    {
      ResultSet rs = dmd.getTables(null, null, "items", null);
      if (!rs.next())
      {





        String sql = "CREATE TABLE ITEMS (ITEM_NAME VARCHAR(50))";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
      }
    }
    catch (Exception localException4) {}
  }
  















  public void newAccount()
  {
    try
    {
      String sql = "select * from admintable";
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
      {
        create.setVisible(false);
      }
      else
      {
        create.setVisible(true);
      }
      
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, e);
    }
  }
  










  public Connection sharedb()
  {
    return scon;
  }
  
  public Connection dbcon()
  {
    try
    {
      Class.forName("org.h2.Driver");
      
      con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
      

      scon = con;


    }
    catch (ClassNotFoundException e)
    {


      JOptionPane.showMessageDialog(null, "Driver Not Loaded");
      System.exit(3);

    }
    catch (SQLException c)
    {

      JOptionPane.showMessageDialog(null, "connection not  established  wrong user name or password");
      System.exit(3);
    }
    
    return con;
  }
  

  public void Clock()
  {
    Thread Clock = new Thread()
    {

      public void run()
      {
        try
        {
          for (;;)
          {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            lblClock_1.setText(s.format(d));
            


            SimpleDateFormat s1 = new SimpleDateFormat("hh:mm:ss a");
            lblClock.setText(s1.format(d));
            

            sleep(1000L);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        
      }
      
    };
    Clock.start();
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          LoginPage window = new LoginPage();
          window.frame.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  



  public LoginPage()
  {
    dbcon();
    
    tableCheck();
    initialize();
    newAccount();
  }
  








  private void initialize()
  {
    frame = new JFrame();
    frame.setResizable(false);
    frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    frame.setBounds(200, 100, 1000, 600);
    frame.setDefaultCloseOperation(3);
    frame.getContentPane().setLayout(null);
    
    JLabel lblBillilngSystem = new JLabel("      Shop Billing System");
    lblBillilngSystem.setFont(new Font("Tahoma", 1, 37));
    lblBillilngSystem.setBounds(482, 33, 446, 71);
    frame.getContentPane().add(lblBillilngSystem);
    

    JLabel lblCreatedByMd = new JLabel("Created by: Md Zeyaur Rahman");
    lblCreatedByMd.setFont(new Font("Consolas", 0, 16));
    lblCreatedByMd.setBounds(659, 94, 259, 23);
    frame.getContentPane().add(lblCreatedByMd);
    

    username = new JTextField();
    username.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent evt)
      {

        if (evt.getKeyCode() == 10)
        {

          password.requestFocus();
        }
        
      }
    });
    username.setBounds(764, 265, 136, 20);
    frame.getContentPane().add(username);
    username.setColumns(10);
    

    JLabel lblUsername = new JLabel("Username");
    lblUsername.setFont(new Font("Tahoma", 1, 15));
    lblUsername.setBounds(659, 266, 83, 14);
    frame.getContentPane().add(lblUsername);
    
    JLabel lblPassword = new JLabel("Password");
    lblPassword.setFont(new Font("Tahoma", 1, 15));
    lblPassword.setBounds(659, 308, 83, 14);
    frame.getContentPane().add(lblPassword);
    
    JLabel lblAdminLogIn = new JLabel("Admin Log In");
    lblAdminLogIn.setFont(new Font("Tahoma", 1, 20));
    lblAdminLogIn.setBounds(764, 218, 136, 30);
    frame.getContentPane().add(lblAdminLogIn);
    
    password = new JPasswordField();
    password.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10)
        {
          try
          {
            String query = "select * from admintable where USERNAME=? and PASSWORD=?";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {

              pst.close();
              rs.close();
              
             Tasks t = new Tasks();
              
              frame.dispose();
              t.setVisible(true);
            }
            else
            {
              JOptionPane.showMessageDialog(null, "wrong username or passoword");
            }
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          
        }
        
      }
      
    });
    password.setBounds(764, 307, 136, 20);
    frame.getContentPane().add(password);
    

    imgLabel = new JLabel("");
    Image img = new ImageIcon(getClass().getResource("/bs1.png")).getImage();
    imgLabel.setIcon(new ImageIcon(img));
    imgLabel.setBounds(55, 157, 512, 338);
    frame.getContentPane().add(imgLabel);
    
    lblClock = new JLabel("Clock");
    lblClock.setFont(new Font("MS UI Gothic", 1, 24));
    lblClock.setBounds(25, 33, 247, 71);
    frame.getContentPane().add(lblClock);
    
    lblClock_1 = new JLabel("Clock");
    lblClock_1.setFont(new Font("MS UI Gothic", 1, 24));
    lblClock_1.setBounds(25, 115, 247, 71);
    frame.getContentPane().add(lblClock_1);
    
    final JLabel forgotPassword = new JLabel("Forgot password?");
    forgotPassword.setForeground(Color.BLACK);
    forgotPassword.addMouseListener(new MouseAdapter()
    {
      public void mouseEntered(MouseEvent arg0)
      {
        forgotPassword.setForeground(Color.WHITE);
      }
      

      public void mouseExited(MouseEvent e)
      {
        forgotPassword.setForeground(Color.BLACK);
      }
      

      public void mouseClicked(MouseEvent e)
      {
        passwordField_1.setVisible(true);
        passwordField_1.requestFocus();
      }
      
    });
    forgotPassword.setBackground(Color.DARK_GRAY);
    forgotPassword.setFont(new Font("Tahoma", 0, 13));
    forgotPassword.setBounds(803, 338, 106, 17);
    
    frame.getContentPane().add(forgotPassword);
    
    JPanel panel = new JPanel();
    panel.setBounds(633, 202, 295, 185);
    panel.setBorder(new SoftBevelBorder(1, null, null, null, null));
    panel.setBackground(SystemColor.activeCaption);
    frame.getContentPane().add(panel);
    panel.setLayout(null);
    
    passwordField_1 = new JPasswordField();
    passwordField_1.setVisible(false);
    passwordField_1.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          if (passwordField_1.getText().equals("iwant2bamsp"))
          {

            try
            {
              String query = "delete from  admintable ";
              
              PreparedStatement pst = con.prepareStatement(query);
              

              pst.execute();
              pst.close();
            }
            catch (Exception e1) {
              JOptionPane.showMessageDialog(null, e1);
            }
            



            create.setVisible(true);
          }
          
        }
        
      }
    });
    passwordField_1.setBounds(133, 159, 135, 18);
    panel.add(passwordField_1);
    
    create = new JButton("Create New Account");
    create.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        panel_1.setVisible(true);
        textField.requestFocus();
      }
      
    });
    create.setFont(new Font("Tahoma", 0, 12));
    create.setBounds(715, 404, 153, 23);
    create.setVisible(false);
    frame.getContentPane().add(create);
    
    panel_1 = new JPanel();
    panel_1.setBorder(new SoftBevelBorder(0, null, null, null, null));
    panel_1.setBackground(SystemColor.activeCaption);
    panel_1.setBounds(633, 438, 299, 107);
    frame.getContentPane().add(panel_1);
    panel_1.setLayout(null);
    panel_1.setVisible(false);
    
    passwordField = new JPasswordField();
    passwordField.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "INSERT INTO  admintable (username, password) values (?,?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, textField.getText());
            pst.setString(2, passwordField.getText());
            
            pst.execute();
            pst.close();
            

            JOptionPane.showMessageDialog(null, "USER CREATED!");
            panel_1.setVisible(false);
            create.setVisible(false);
            passwordField_1.setVisible(false);
            passwordField_1.setText("");
            textField.setText("");
            passwordField.setText("");
          }
          catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);

          }
          
        }
        
      }
      

    });
    passwordField.setBounds(129, 57, 136, 20);
    panel_1.add(passwordField);
    
    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {

          passwordField.requestFocus();
        }
        
      }
      
    });
    textField.setColumns(10);
    textField.setBounds(129, 26, 136, 20);
    panel_1.add(textField);
    
    JLabel label = new JLabel("Username");
    label.setFont(new Font("Tahoma", 1, 15));
    label.setBounds(24, 27, 83, 14);
    panel_1.add(label);
    
    JLabel label_1 = new JLabel("Password");
    label_1.setFont(new Font("Tahoma", 1, 15));
    label_1.setBounds(24, 63, 83, 14);
    panel_1.add(label_1);
    Clock();
  }
}

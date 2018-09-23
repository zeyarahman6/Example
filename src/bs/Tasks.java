package bs;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



























public class Tasks
  extends JFrame
{
  JButton btnNewButton_5;
  JButton btnNewButton_4;
  private JPanel contentPane;
  private JTextField textField_1;
  private JTextField P;
  private JTextField Q;
  private JTextField D;
  private JTextField A;
  private JTable table;
  JComboBox comboBox;
  JComboBox comboBox_1;
  Robot robot = null;
  JInternalFrame internalFrame;
  Connection con = null; Connection com = null;
  String cid = null;
  String zid = null;
  JDateChooser dateChooser;
  private JTextField textField;
  private JTextField invoice_no;
  private JTextField c_name;
  private JTextField c_contact;
  private JTextField TA;
  private JTextField TI;
  private int ti = 0;
  private double ta = 0.0D;
  int count = 1;
  private JTable table_1;
  private JTextField date;
  private JTable table_2;
  JButton btnNewButton_2;
  private JTextField time;
  double disc = 0.0D;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField timepmam;
  JTextPane textPane;
  int sno = 0;
  private JTextField textField_4;
  private JTextField textField_5;
  private JTextField textField_6;
  private JTextField textField_7;
  JLabel lblCompanyname;
  JLabel lblCompanyaddress;
  JLabel lblCompanyphone;
  JLabel lblCompanyemail;
  public String tempdate;
  public String temptime;
  private JTable table_3;
  private JTextField textField_8;
  private JTextField textField_9;
  private JTextField textField_10;
  private JTextField textField_11;
  private JTextField textField_12;
  private JTextField textField_13;
  private JTable table_4;
  
  public void cdatabase()
  {
    try {
      String sql = "select  company_name from company";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      if (rs.next())
      {
        btnNewButton_5.setVisible(true);
        textField_4.setVisible(false);
        textField_5.setVisible(false);
        textField_6.setVisible(false);
        textField_7.setVisible(false);
        lblCompanyname.setVisible(false);
        lblCompanyaddress.setVisible(false);
        lblCompanyphone.setVisible(false);
        lblCompanyemail.setVisible(false);
        btnNewButton_4.setVisible(false);

      }
      else
      {
        textField_4.setVisible(true);
        textField_5.setVisible(true);
        textField_6.setVisible(true);
        textField_7.setVisible(true);
        lblCompanyname.setVisible(true);
        lblCompanyaddress.setVisible(true);
        lblCompanyphone.setVisible(true);
        lblCompanyemail.setVisible(true);
        btnNewButton_4.setVisible(true);
        btnNewButton_5.setVisible(false);
      }
      

    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, e);
    }
  }
  



  public void printinvoice(String ino)
  {
    company();
    product(ino);
    try
    {
      textPane.print();
    }
    catch (PrinterException e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }
  

  public void product(String ino)
  {
    String tempta = null;
    try {
      String sql = "select to_char(date,'DD-MM-YYYY'),time, grand_total from customers where invoice_no='" + ino + "'";
      
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      table_4.setModel(DbUtils.resultSetToTableModel(rs));
      
      pst.close();
      rs.close();
      
      tempdate = table_4.getModel().getValueAt(0, 0).toString();
      temptime = table_4.getModel().getValueAt(0, 1).toString();
      tempta = table_4.getModel().getValueAt(0, 2).toString();
      

      table_4.setModel(new DefaultTableModel(
        new Object[0][], 
        
        new String[] {
        "d", "t", "ta" }));

    }
    catch (Exception e1)
    {

      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
    







    Document doc = textPane.getDocument();
    try {
      doc.insertString(doc.getLength(), "INVOICE No:" + ino + " \t\t\tCustomer Name:" + c_name.getText() + "\nDate:" + tempdate + "\t\t\tCustomer Phone:" + c_contact.getText() + "\n", null);
      doc.insertString(doc.getLength(), "Time:" + temptime + "\n", null);
      doc.insertString(doc.getLength(), "--------------------------------------------------------------------------\n", null);
      doc.insertString(doc.getLength(), "S.No  P_Name\t\tP_Qty\t P_Price\tP_Disc\tP_Amt\n", null);
      doc.insertString(doc.getLength(), "--------------------------------------------------------------------------\n", null);
    }
    catch (BadLocationException e) {
      e.printStackTrace();
    }
    




    try
    {
      String sql = "select  P_name, p_price ,p_qty ,p_disc ,p_amt from INVOICETABLE where I_no='" + ino + "'";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      


      while (rs.next())
      {



        String name = rs.getString("P_NAME");
        String price = rs.getString("P_PRICE");
        String qty = rs.getString("P_QTY");
        String disc = rs.getString("P_DISC");
        String amt = rs.getString("P_AMT");
        
        int n = name.length();
        
        if ((n <= 4) && (sno < 9))
        {
          sno += 1;
          
          try
          {
            doc.insertString(doc.getLength(), "  " + sno + "   " + name, null);
            doc.insertString(doc.getLength(), "\t\t\t" + qty + "\t " + price + "\t" + disc + "%\t" + amt + "\n", null);
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        


        if ((n < 4) && (sno > 9))
        {
          sno += 1;
          try
          {
            doc.insertString(doc.getLength(), "  " + sno + "   " + name, null);
            doc.insertString(doc.getLength(), "\t\t\t" + qty + "\t " + price + "\t" + disc + "%\t" + amt + "\n", null);
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        



        if (n > 15)
        {
          sno += 1;
          
          try
          {
            doc.insertString(doc.getLength(), "  " + sno + "   " + name, null);
            doc.insertString(doc.getLength(), "\t" + qty + "\t " + price + "\t" + disc + "%\t" + amt + "\n", null);
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        



        if ((n > 4) && (n < 16))
        {
          sno += 1;
          
          try
          {
            doc.insertString(doc.getLength(), "  " + sno + "   " + name, null);
            doc.insertString(doc.getLength(), "\t\t" + qty + "\t " + price + "\t" + disc + "%\t" + amt + "\n", null);
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        




        if ((n == 4) && (sno >= 9))
        {
          sno += 1;
          try
          {
            doc.insertString(doc.getLength(), "  " + sno + "   " + name, null);
            doc.insertString(doc.getLength(), "\t\t" + qty + "\t " + price + "\t" + disc + "%\t" + amt + "\n", null);
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
      }
      




      pst.close();
      rs.close();
      try
      {
        doc.insertString(doc.getLength(), "--------------------------------------------------------------------------\n", null);
        doc.insertString(doc.getLength(), "\t\t\t\t         Total Amount=" + tempta + "\n", null);
        doc.insertString(doc.getLength(), "\t\t\t\t         Type of Items=" + sno + "\n", null);
        doc.insertString(doc.getLength(), "--------------------------------------------------------------------------\n", null);
        doc.insertString(doc.getLength(), "\tThank You! For shopping with us. Please! visit again.", null);
        sno = 0;
      }
      catch (BadLocationException e) {
        e.printStackTrace();
      }
      
      return;
    }
    catch (SQLException e1)
    {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  





  public void company()
  {
    SimpleAttributeSet sas = new SimpleAttributeSet();
    StyleConstants.setBold(sas, true);
    


    StyledDocument doc1 = (StyledDocument)textPane.getDocument();
    MutableAttributeSet style = doc1.addStyle("Courier New", null);
    StyleConstants.setFontSize(style, 24);
    try
    {
      String sql = "select * from company";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
        String name = rs.getString("company_name");
        String add = rs.getString("company_add");
        String phone = rs.getString("company_phone");
        String email = rs.getString("company_email");
        





        try
        {
          int l = name.length();
          
          Document doc = textPane.getDocument();
          doc.insertString(doc.getLength(), "INVOICE RECEIPT\n", null);
          doc.insertString(doc.getLength(), name, style);
          doc.insertString(doc.getLength(), "\n" + add + "\nPh:" + phone + ", email:" + email + "\n\n\n", null);
          textPane.getStyledDocument().setCharacterAttributes(15, l + 1, sas, false);
        } catch (Exception exc) {
          exc.printStackTrace();
        }
      }
      


      pst.close();
      rs.close();

    }
    catch (SQLException e1)
    {

      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  
  public void run()
  {
    try
    {
      Tasks frame = new Tasks();
      frame.setVisible(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  protected void currentdate()
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
            date.setText(s.format(d));
            


            SimpleDateFormat s1 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat s2 = new SimpleDateFormat("hh:mm a");
            time.setText(s1.format(d));
            timepmam.setText(s2.format(d));
            

            sleep(59000L);
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
  








  protected void combobox_items_2()
  {
    LoginPage c = new LoginPage();
    
    com = c.dbcon();
    

    comboBox_1.removeAllItems();
    try {
      String sql = "select DISTINCT item_name from items order by item_name asc";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      while (rs.next())
      {

        String id = rs.getString("item_name");
        comboBox_1.addItem(id);
      }
      pst.close();
      rs.close();
      
      AutoCompleteDecorator.decorate(comboBox_1);
    }
    catch (SQLException e1)
    {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  




  protected void combobox_items()
  {
    LoginPage c = new LoginPage();
    
    com = c.dbcon();
    

    comboBox.removeAllItems();
    try {
      String sql = "select DISTINCT item_name from items order by item_name asc";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      while (rs.next())
      {

        String id = rs.getString("item_name");
        comboBox.addItem(id);
      }
      pst.close();
      rs.close();
      
      AutoCompleteDecorator.decorate(comboBox);
    }
    catch (SQLException e1)
    {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  








  public Tasks()
  {
    setDefaultCloseOperation(0);
    
    LoginPage c = new LoginPage();
    
    con = c.sharedb();
    




    setBounds(100, 100, 1240, 710);
    
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    
    JMenu mnOption = new JMenu("Option");
    menuBar.add(mnOption);
    
    JMenuItem mntmFocusInvoiceNo = new JMenuItem("Focus Invoice no");
    mntmFocusInvoiceNo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        invoice_no.requestFocus();
      }
    });
    mntmFocusInvoiceNo.setAccelerator(KeyStroke.getKeyStroke(90, 2));
    mnOption.add(mntmFocusInvoiceNo);
    contentPane = new JPanel();
    
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 78, 1204, 557);
    contentPane.add(tabbedPane);
    tabbedPane.setFont(new Font("Tahoma", 1, 12));
    



    Image imgadd = new ImageIcon(getClass().getResource("/add-item-icon74.png")).getImage();
    


    Image imggg = new ImageIcon(getClass().getResource("/submit32.png")).getImage();
    

    Image i = new ImageIcon(getClass().getResource("/add-item-icon74.png")).getImage();
    

    Image i2 = new ImageIcon(getClass().getResource("/delete-1-icon.png")).getImage();
    







    JPanel panel = new JPanel();
    panel.setBorder(new BevelBorder(0, null, null, null, null));
    panel.setBackground(SystemColor.controlHighlight);
    tabbedPane.addTab("BILLING       ", null, panel, null);
    panel.setLayout(null);
    
    JLabel lblProductprice_1 = new JLabel("Product_Price");
    lblProductprice_1.setFont(new Font("Tahoma", 1, 14));
    lblProductprice_1.setBounds(323, 129, 121, 20);
    panel.add(lblProductprice_1);
    
    JLabel lblProductquantity_1 = new JLabel("Quantity");
    lblProductquantity_1.setFont(new Font("Tahoma", 1, 15));
    lblProductquantity_1.setBounds(205, 129, 80, 20);
    panel.add(lblProductquantity_1);
    
    JLabel lblProductdate = new JLabel("Date");
    lblProductdate.setFont(new Font("Tahoma", 0, 15));
    lblProductdate.setBounds(536, 18, 36, 20);
    panel.add(lblProductdate);
    
    JLabel lblTotalAmount = new JLabel("Total ");
    lblTotalAmount.setFont(new Font("Tahoma", 1, 15));
    lblTotalAmount.setBounds(680, 129, 42, 20);
    panel.add(lblTotalAmount);
    
    JLabel lblDiscount = new JLabel("Discount");
    lblDiscount.setFont(new Font("Tahoma", 1, 15));
    lblDiscount.setBounds(485, 129, 109, 20);
    panel.add(lblDiscount);
    
    P = new JTextField();
    P.setHorizontalAlignment(4);
    P.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          Q.requestFocus();
        }
        



        if (e.getKeyCode() == 27)
        {
          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {
            try {
              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            





            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            ti = 0;
            ta = 0.0D;
            
            TI.setText("");
            TA.setText("");
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            
            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");
            
            c_name.setEnabled(false);
            c_contact.setEnabled(false);
            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.setEnabled(false);
            table.setModel(new DefaultTableModel(null, new String[] {
              "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" }));
          }
        }
        



        if (e.getKeyCode() == 10)
        {
          D.setEnabled(true);
          D.requestFocus();
        }
        
      }
    });
    P.setFont(new Font("Tahoma", 0, 18));
    P.setColumns(10);
    P.setBounds(324, 153, 99, 26);
    panel.add(P);
    
    Q = new JTextField();
    Q.setHorizontalAlignment(4);
    Q.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          comboBox.requestFocus();
        }
        


        if (e.getKeyCode() == 10)
        {


          P.setEnabled(true);
          P.requestFocus();
        }
        



        if (e.getKeyCode() == 27)
        {
          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {
            try {
              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            





            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            ti = 0;
            ta = 0.0D;
            
            TI.setText("");
            TA.setText("");
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            
            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");
            
            c_name.setEnabled(false);
            c_contact.setEnabled(false);
            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.setEnabled(false);
            table.setModel(new DefaultTableModel(null, new String[] {
              "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" }));


          }
          


        }
        


      }
      


    });
    Q.setFont(new Font("Tahoma", 0, 18));
    Q.setColumns(10);
    Q.setBounds(203, 153, 72, 26);
    panel.add(Q);
    
    D = new JTextField();
    D.setHorizontalAlignment(4);
    D.setText("0");
    D.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          P.requestFocus();
        }
        


        if (e.getKeyCode() == 27)
        {

          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {
            try
            {
              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            





            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            ti = 0;
            ta = 0.0D;
            
            TI.setText("");
            TA.setText("");
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            
            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");
            
            c_name.setEnabled(false);
            c_contact.setEnabled(false);
            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.setEnabled(false);
            table.setModel(new DefaultTableModel(null, new String[] {
              "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" }));
          }
        }
        








        if (e.getKeyCode() == 10)
        {
          try
          {

            double p = Double.parseDouble(P.getText());
            double q = Double.parseDouble(Q.getText());
            double d = Double.parseDouble(D.getText());
            
            double sum = q * (p - d * p / 100.0D);
            double newsum = Math.round(sum * 100.0D) / 100.0D;
            A.setText(Double.toString(newsum));
            

            A.setEnabled(true);
            A.requestFocus();

          }
          catch (Exception e2)
          {

            JOptionPane.showMessageDialog(null, "Enter valid number");
            P.requestFocus();

          }
          
        }
        
      }
      

    });
    D.setFont(new Font("Tahoma", 0, 18));
    D.setColumns(10);
    D.setBounds(485, 153, 72, 26);
    panel.add(D);
    
    A = new JTextField();
    A.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {}
    });
    A.setHorizontalAlignment(4);
    A.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          D.requestFocus();
        }
        

        if (e.getKeyCode() == 27)
        {
          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {
            try {
              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            





            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            ti = 0;
            ta = 0.0D;
            
            TI.setText("");
            TA.setText("");
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            
            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");
            
            c_name.setEnabled(false);
            c_contact.setEnabled(false);
            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.setEnabled(false);
            table.setModel(new DefaultTableModel(null, new String[] {
              "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" }));
          }
        }
        









        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "insert into invoicetable (p_name,p_price,p_qty,p_disc,p_amt , i_no) values (?,?,?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            



            pst.setString(1, (String)comboBox.getSelectedItem());
            pst.setString(2, P.getText());
            pst.setString(3, Q.getText());
            pst.setString(4, D.getText());
            pst.setString(5, A.getText());
            pst.setString(6, invoice_no.getText());
            




            pst.execute();
            
            pst.close();
            




            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.addRow(new String[] { comboBox.getSelectedItem().toString(), Q.getText(), P.getText(), D.getText(), A.getText() });
            

            double d = Double.parseDouble(A.getText());
            ta = (Math.round((ta + d) * 100.0D) / 100.0D);
            
            ti += 1;
            TI.setText(Integer.toString(ti));
            TA.setText(Double.toString(ta));
            




            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");



          }
          catch (SQLException e1)
          {


            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.requestFocus();
          }
        }
        







        if (e.getKeyCode() == 110)
        {
          try
          {

            double gt = Double.parseDouble(TA.getText());
            ti = Integer.parseInt(TI.getText());
            String in = invoice_no.getText();
            

            String query = " update customers set grand_total = '" + gt + "' , total_items='" + ti + "' where invoice_no='" + in + "'";
            
            PreparedStatement pst = con.prepareStatement(query);
            







            pst.execute();
            
            pst.close();
            printinvoice(in);
            textPane.setText("");
            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            ti = 0;
            ta = 0.0D;
            
            TI.setText("");
            TA.setText("");
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            
            P.setText("");
            Q.setText("");
            D.setText("0");
            A.setText("");
            
            c_name.setEnabled(false);
            c_contact.setEnabled(false);
            P.setEnabled(false);
            Q.setEnabled(false);
            D.setEnabled(false);
            A.setEnabled(false);
            comboBox.setEnabled(false);
            table.setModel(new DefaultTableModel(null, new String[] {
              "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" }));
          }
          catch (Exception e2)
          {
            JOptionPane.showMessageDialog(null, "Invoice number missing");


          }
          


        }
        

      }
      


    });
    A.setFont(new Font("Tahoma", 0, 18));
    A.setColumns(10);
    A.setBounds(623, 153, 99, 26);
    panel.add(A);
    



    comboBox = new JComboBox();
    comboBox.setFont(new Font("Tahoma", 0, 17));
    comboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}

    });
    comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if (keyChar == 10)
        {
          Q.requestFocus();
          Q.setEnabled(true);



        }
        


      }
      



    });
    JTextComponent editor = (JTextComponent)comboBox.getEditor().getEditorComponent();
    editor.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evt) {
        if (evt.getKeyChar() == '\n')
        {
          Q.requestFocus();
          Q.setEnabled(true);
        }
        

        if (evt.getKeyChar() == '\033')
        {
          internalFrame.setVisible(true);
          textField.requestFocus();
        }
        
      }
      

    });
    comboBox.setBounds(30, 153, 151, 26);
    panel.add(comboBox);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(30, 182, 692, 315);
    panel.add(scrollPane);
    
    table = new JTable();
    table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent ej) {
        int action = JOptionPane.showConfirmDialog(null, "Remove item?", "Remove", 0);
        if (action == 0)
        {

          try
          {
            int row = table.getSelectedRow();
            
            String name = table.getModel().getValueAt(row, 0).toString();
            String query = "Delete from invoicetable where i_no='" + invoice_no.getText() + "' and P_name='" + name + "'";
            
            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();
            ti -= 1;
            TI.setText(Integer.toString(ti));

          }
          catch (Exception e)
          {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
          }
          


          DefaultTableModel model = (DefaultTableModel)table.getModel();
          

          try
          {
            int SelectedRowIndex = table.getSelectedRow();
            
            double d = Double.parseDouble(TA.getText());
            double tempamt = Double.parseDouble(table.getModel().getValueAt(SelectedRowIndex, 4).toString());
            

            ta = (Math.round((d - tempamt) * 100.0D) / 100.0D);
            

            TA.setText(Double.toString(ta));
            
            model.removeRow(SelectedRowIndex);
            comboBox.requestFocus();
          }
          catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
          }
          
        }
      }
    });
    table.setFont(new Font("Tahoma", 0, 16));
    table.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "          P_Name", "            P_Quantity", "          P_Price", "          P_Discount", "          P_Amount" })
      {





        Class[] columnTypes = {
          String.class, String.class, String.class, String.class, String.class };
        
        public Class getColumnClass(int columnIndex) {
          return columnTypes[columnIndex];
        }
      });
    table.getColumnModel().getColumn(0).setPreferredWidth(87);
    table.getColumnModel().getColumn(0).setMinWidth(20);
    table.getColumnModel().getColumn(1).setPreferredWidth(109);
    table.getColumnModel().getColumn(1).setMinWidth(20);
    table.getColumnModel().getColumn(2).setPreferredWidth(86);
    table.getColumnModel().getColumn(2).setMinWidth(20);
    table.getColumnModel().getColumn(3).setPreferredWidth(93);
    table.getColumnModel().getColumn(3).setMinWidth(20);
    table.getColumnModel().getColumn(4).setMinWidth(20);
    scrollPane.setViewportView(table);
    


    JLabel lblCategoryname = new JLabel("Product_Name");
    lblCategoryname.setFont(new Font("Tahoma", 1, 15));
    lblCategoryname.setBounds(30, 132, 132, 20);
    panel.add(lblCategoryname);
    btnNewButton_2 = new JButton("");
    btnNewButton_2.setVisible(false);
    btnNewButton_2.setFont(new Font("Dialog", 1, 15));
    btnNewButton_2.setIcon(new ImageIcon(imggg));
    

    btnNewButton_2.setBounds(1007, 18, 50, 35);
    panel.add(btnNewButton_2);
    
    JLabel lblCustomerName = new JLabel("Customer Name");
    lblCustomerName.setFont(new Font("Tahoma", 0, 15));
    lblCustomerName.setBounds(717, 41, 121, 20);
    panel.add(lblCustomerName);
    
    JLabel lblInvoiceNo = new JLabel("Invoice no.");
    lblInvoiceNo.setFont(new Font("Tahoma", 0, 15));
    lblInvoiceNo.setBounds(717, 18, 121, 20);
    panel.add(lblInvoiceNo);
    
    invoice_no = new JTextField();
    invoice_no.setFont(new Font("Tahoma", 0, 17));
    invoice_no.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {

        if (e.getKeyCode() == 10)
        {

          if (invoice_no.getText().length() != 0)
          {
            try
            {
              String query = "INSERT INTO customers (invoice_no,date, time) VALUES (?,parsedatetime(?, 'dd-MM-yyyy'),parsedatetime(?, 'HH:mm'))";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              pst.setString(2, date.getText());
              pst.setString(3, time.getText());
              


              pst.execute();
              
              pst.close();
              
              btnNewButton_2.setVisible(true);
              c_name.setEnabled(true);
              c_name.requestFocus();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, "Invoice no.='" + invoice_no.getText() + "' Already Exist");
              invoice_no.setText("");

            }
            


          }
          else
          {

            try
            {

              String query = "INSERT INTO customers (date, time) VALUES (parsedatetime(?, 'dd-MM-yyyy'),parsedatetime(?, 'HH:mm'))";
              
              PreparedStatement pst = con.prepareStatement(query);
              




              pst.setString(1, date.getText());
              pst.setString(2, time.getText());
              


              pst.execute();
              
              pst.close();
              
              btnNewButton_2.setVisible(true);
              c_name.setEnabled(true);
              c_name.requestFocus();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, "unexpected error");
              invoice_no.setText("");
            }
            
            try
            {
              String sql = "select top 1 invoice_no  from customers order by grand_total asc, total_items asc";
              
              PreparedStatement pst = com.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
              
              while (rs.next())
              {
                invoice_no.setText(rs.getString("invoice_no"));
              }
              pst.close();
              rs.close();

            }
            catch (SQLException e1)
            {

              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);


            }
            


          }
          


        }
        

      }
      


    });
    invoice_no.setBounds(838, 18, 139, 20);
    panel.add(invoice_no);
    invoice_no.setColumns(10);
    
    JLabel lblCustomerContact = new JLabel("Customer contact");
    lblCustomerContact.setFont(new Font("Tahoma", 0, 15));
    lblCustomerContact.setBounds(717, 66, 121, 20);
    panel.add(lblCustomerContact);
    
    c_name = new JTextField();
    c_name.setFont(new Font("Tahoma", 0, 17));
    


    c_name.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 27)
        {

          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {

            try
            {

              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            






            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            


            c_name.setEnabled(false);
            c_contact.setEnabled(false);
          }
        }
        




        if (e.getKeyCode() == 38)
        {
          invoice_no.requestFocus();
        }
        






        if (e.getKeyCode() == 10)
        {
          c_contact.setEnabled(true);
          c_contact.requestFocus();

        }
        
      }
      

    });
    c_name.setColumns(10);
    c_name.setBounds(838, 41, 139, 20);
    panel.add(c_name);
    
    c_contact = new JTextField();
    c_contact.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          c_name.requestFocus();
        }
        



        if (e.getKeyCode() == 27)
        {

          int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
          if (action == 0)
          {

            try
            {

              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            






            btnNewButton_2.setVisible(false);
            invoice_no.requestFocus();
            
            invoice_no.setText("");
            c_name.setText("");
            c_contact.setText("");
            


            c_name.setEnabled(false);
            c_contact.setEnabled(false);
          }
        }
        






        if (e.getKeyCode() == 10)
        {


          try
          {


            String query = "update customers set cust_name = ?  , cust_phone= ? where invoice_no= '" + invoice_no.getText() + "' ";
            



            PreparedStatement pst = con.prepareStatement(query);
            




            pst.setString(1, c_name.getText());
            pst.setString(2, c_contact.getText());
            


            pst.execute();
            
            pst.close();
            comboBox.setEnabled(true);
            comboBox.requestFocus();

          }
          catch (SQLException e1)
          {
            JOptionPane.showMessageDialog(null, e1);

          }
          

        }
        
      }
      

    });
    c_contact.setFont(new Font("Tahoma", 0, 17));
    c_contact.setColumns(10);
    c_contact.setBounds(838, 66, 139, 20);
    panel.add(c_contact);
    







    JLabel lblTotalAmount_1 = new JLabel("Total Amount");
    lblTotalAmount_1.setFont(new Font("Tahoma", 0, 15));
    lblTotalAmount_1.setBounds(880, 423, 91, 19);
    panel.add(lblTotalAmount_1);
    
    TA = new JTextField();
    TA.setFont(new Font("Tahoma", 0, 17));
    TA.setColumns(10);
    TA.setBounds(981, 424, 139, 20);
    panel.add(TA);
    
    JLabel lblTotalItems = new JLabel("Total Items");
    lblTotalItems.setFont(new Font("Tahoma", 0, 15));
    lblTotalItems.setBounds(880, 453, 91, 20);
    panel.add(lblTotalItems);
    
    TI = new JTextField();
    TI.setFont(new Font("Tahoma", 0, 17));
    TI.setColumns(10);
    TI.setBounds(981, 455, 139, 20);
    panel.add(TI);
    





    date = new JTextField();
    date.setBackground(Color.WHITE);
    date.setDisabledTextColor(Color.BLACK);
    date.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {










          time.requestFocus();
        }
        
      }
      
    });
    date.setFont(new Font("Tahoma", 0, 14));
    date.setBounds(573, 18, 91, 20);
    panel.add(date);
    date.setColumns(10);
    
    time = new JTextField();
    time.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == 10)
        {










          invoice_no.requestFocus();

        }
        
      }
      

    });
    time.setBackground(Color.WHITE);
    time.setDisabledTextColor(Color.BLACK);
    
    time.setFont(new Font("Tahoma", 0, 14));
    time.setColumns(10);
    time.setBounds(573, 47, 91, 20);
    panel.add(time);
    


    JLabel lblTime = new JLabel("Time");
    lblTime.setFont(new Font("Tahoma", 0, 15));
    lblTime.setBounds(536, 47, 36, 20);
    panel.add(lblTime);
    
    timepmam = new JTextField();
    timepmam.setEditable(false);
    timepmam.setBackground(Color.WHITE);
    timepmam.setFont(new Font("Tahoma", 0, 14));
    timepmam.setColumns(10);
    timepmam.setBounds(573, 72, 91, 20);
    panel.add(timepmam);
    
    JPanel panel_5 = new JPanel();
    panel_5.setBackground(SystemColor.inactiveCaption);
    panel_5.setBorder(new BevelBorder(0, null, null, null, null));
    panel_5.setBounds(502, 8, 618, 93);
    panel.add(panel_5);
    
    JLabel lblNewLabel_1 = new JLabel("");
    lblNewLabel_1.setBounds(41, 11, 80, 93);
    panel.add(lblNewLabel_1);
    lblNewLabel_1.setIcon(new ImageIcon(imgadd));
    


    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new EtchedBorder(0, null, null));
    panel_1.setForeground(SystemColor.inactiveCaption);
    tabbedPane.addTab("ADD & DELETE PRODUCTS  ", null, panel_1, null);
    panel_1.setLayout(null);
    
    JLabel lblProductname = new JLabel("Product_Name");
    lblProductname.setFont(new Font("Tahoma", 1, 16));
    lblProductname.setBounds(35, 195, 120, 20);
    panel_1.add(lblProductname);
    
    textField_1 = new JTextField();
    textField_1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "INSERT INTO items (item_NAME) VALUES (?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            

            String lowercase = textField_1.getText();
            
            pst.setString(1, lowercase);
            


            pst.execute();
            
            pst.close();
            combobox_items();
            combobox_items_2();
          }
          catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          try
          {
            String sql = "select DISTINCT item_name from items ORDER BY item_name ASC;";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_2.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          textField_1.setText("");
        }
      }
    });
    textField_1.setColumns(10);
    textField_1.setBounds(165, 197, 214, 20);
    panel_1.add(textField_1);
    

    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(i));
    lblNewLabel.setBounds(320, 70, 66, 83);
    panel_1.add(lblNewLabel);
    




    JLabel lblDelete = new JLabel("");
    lblDelete.setIcon(new ImageIcon(i2));
    lblDelete.setBounds(986, 70, 72, 83);
    panel_1.add(lblDelete);
    
    comboBox_1 = new JComboBox();
    
    comboBox_1.setBounds(899, 197, 159, 20);
    panel_1.add(comboBox_1);
    comboBox_1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if (keyChar == 10)
        {

          String st = (String)comboBox_1.getSelectedItem();
          

          int action = JOptionPane.showConfirmDialog(null, st, "Delete", 0);
          if (action == 0)
          {

            try
            {
              String sql = " DELETE FROM items WHERE  item_name='" + st + "'";
              
              PreparedStatement pst = con.prepareStatement(sql);
              
              pst.execute();
              
              pst.close();
              
              combobox_items_2();
              combobox_items();
            }
            catch (SQLException e1) {
              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String sql = "select DISTINCT item_name from items ORDER BY item_name ASC;";
              
              PreparedStatement pst = com.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
              table_2.setModel(DbUtils.resultSetToTableModel(rs));
              
              pst.close();
              rs.close();

            }
            catch (SQLException e1)
            {

              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);


            }
            


          }
          

        }
        

      }
      


    });
    JLabel label_1 = new JLabel("Product_Name");
    label_1.setFont(new Font("Tahoma", 1, 16));
    label_1.setBounds(773, 197, 127, 20);
    panel_1.add(label_1);
    
    JPanel panel_3 = new JPanel();
    panel_3.setBackground(SystemColor.inactiveCaption);
    panel_3.setForeground(SystemColor.desktop);
    panel_3.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Add Items    ", 3, 2, null, new Color(0, 0, 0)));
    panel_3.setBounds(10, 56, 390, 269);
    panel_1.add(panel_3);
    
    JPanel panel_4 = new JPanel();
    panel_4.setForeground(Color.BLACK);
    panel_4.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Delete Items    ", 3, 2, null, new Color(0, 0, 0)));
    panel_4.setBackground(SystemColor.inactiveCaption);
    panel_4.setBounds(679, 56, 393, 269);
    panel_1.add(panel_4);
    
    JScrollPane scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(460, 56, 171, 397);
    panel_1.add(scrollPane_2);
    
    table_2 = new JTable();
    scrollPane_2.setViewportView(table_2);
    
    JButton btnNewButton_3 = new JButton("Items List");
    btnNewButton_3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        try {
          String sql = "select DISTINCT item_name from items ORDER BY item_name ASC;";
          
          PreparedStatement pst = com.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          table_2.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (SQLException e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);
        }
        
      }
    });
    btnNewButton_3.setFont(new Font("Tahoma", 1, 12));
    btnNewButton_3.setBounds(493, 22, 107, 23);
    panel_1.add(btnNewButton_3);
    
    JPanel panel_2 = new JPanel();
    tabbedPane.addTab("BILLS HISTORY ", null, panel_2, null);
    panel_2.setLayout(null);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(0, 110, 643, 365);
    panel_2.add(scrollPane_1);
    
    table_1 = new JTable();
    table_1.setFont(new Font("Tahoma", 0, 15));
    table_1.addMouseListener(new MouseAdapter()
    {

      public void mouseClicked(MouseEvent e)
      {
        int row = table_1.getSelectedRow();
        String sid = table_1.getModel().getValueAt(row, 0).toString();
        

        try
        {
          String sql = "select i_no ,p_name,p_qty,p_price,p_disc ,p_amt from invoicetable where i_no='" + sid + "'";
          
          PreparedStatement pst = com.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          table_3.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (SQLException e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);

        }
        

      }
      

    });
    table_1.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "Invoice_no", "Date", "Time", "Cust_Name", "Cust_Phone", "Total_Items", "Grand_Total" }));
    

    table_1.getColumnModel().getColumn(1).setPreferredWidth(92);
    scrollPane_1.setViewportView(table_1);
    
    JButton btnLoadDetaila = new JButton("Load Details");
    btnLoadDetaila.setFont(new Font("Tahoma", 0, 15));
    btnLoadDetaila.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        try {
          String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from customers order by date desc, time desc";
          
          PreparedStatement pst = com.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (SQLException e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);
        }
        




        table_3.setModel(new DefaultTableModel(
          new Object[0][], 
          
          new String[] {
          "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));



      }
      



    });
    btnLoadDetaila.setBounds(10, 31, 113, 59);
    panel_2.add(btnLoadDetaila);
    
    dateChooser = new JDateChooser();
    dateChooser.getCalendarButton().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {}

    });
    dateChooser.setBounds(533, 35, 128, 20);
    panel_2.add(dateChooser);
    
    dateChooser.setDateFormatString("dd-MM-yyy");
    dateChooser.setFont(new Font("Tahoma", 0, 13));
    
    JLabel searchby = new JLabel("Search By Invoice_no:");
    searchby.setFont(new Font("Tahoma", 0, 15));
    searchby.setBounds(145, 31, 153, 27);
    panel_2.add(searchby);
    
    textField_2 = new JTextField();
    textField_2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          try
          {
            int i = Integer.parseInt(textField_2.getText());
            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from  customers  where INvoice_no='" + i + "'  order by date desc,time desc";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();
            
            textField_2.setText("");
          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          



          table_3.setModel(new DefaultTableModel(
            new Object[0][], 
            
            new String[] {
            "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));

        }
        

      }
      


    });
    textField_2.setBounds(296, 35, 94, 20);
    panel_2.add(textField_2);
    textField_2.setColumns(10);
    
    JLabel lblSearchByDate = new JLabel("Search By Date:");
    lblSearchByDate.setFont(new Font("Tahoma", 0, 15));
    lblSearchByDate.setBounds(419, 31, 118, 27);
    panel_2.add(lblSearchByDate);
    
    JButton button = new JButton("Load");
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String s = df.format(dateChooser.getDate());
        

        try
        {
          String sql = "\r\nselect INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total  from  customers   where date=parsedatetime(?, 'dd-MM-yyyy') order by date asc,time desc";
          
          PreparedStatement pst = com.prepareStatement(sql);
          
          pst.setString(1, s);
          ResultSet rs = pst.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (SQLException e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);
        }
        



        table_3.setModel(new DefaultTableModel(
          new Object[0][], 
          
          new String[] {
          "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));


      }
      



    });
    button.setBounds(597, 70, 64, 20);
    panel_2.add(button);
    
    final JDateChooser dateChooser_1 = new JDateChooser();
    dateChooser_1.setFont(new Font("Tahoma", 0, 13));
    dateChooser_1.setDateFormatString("dd-MM-yyy");
    dateChooser_1.setBounds(846, 35, 100, 20);
    panel_2.add(dateChooser_1);
    
    final JDateChooser dateChooser_2 = new JDateChooser();
    dateChooser_2.setFont(new Font("Tahoma", 0, 13));
    dateChooser_2.setDateFormatString("dd-MM-yyy");
    dateChooser_2.setBounds(1003, 35, 100, 20);
    panel_2.add(dateChooser_2);
    
    JButton button_2 = new JButton("Load");
    button_2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String s1 = df.format(dateChooser_1.getDate());
        String s2 = df.format(dateChooser_2.getDate());
        


        try
        {
          String sql = "select INVOICe_no,TO_CHAR(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total  from  customers where   date  between parsedatetime(?, 'dd-MM-yyyy') and parsedatetime(?, 'dd-MM-yyyy') order by date desc,time desc";
          PreparedStatement pst = com.prepareStatement(sql);
          
          pst.setString(1, s1);
          pst.setString(2, s2);
          ResultSet rs = pst.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (SQLException e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);
        }
        



        table_3.setModel(new DefaultTableModel(
          new Object[0][], 
          
          new String[] {
          "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));

      }
      


    });
    button_2.setBounds(1039, 70, 64, 20);
    panel_2.add(button_2);
    
    JLabel lblSearchBwDate = new JLabel("Search B/W Date:");
    lblSearchBwDate.setFont(new Font("Tahoma", 0, 15));
    lblSearchBwDate.setBounds(714, 31, 126, 27);
    panel_2.add(lblSearchBwDate);
    
    JLabel lblAnd = new JLabel("AND");
    lblAnd.setFont(new Font("Tahoma", 0, 15));
    lblAnd.setBounds(956, 31, 37, 27);
    panel_2.add(lblAnd);
    
    JLabel lblDeleteInvoice = new JLabel("Delete Invoice:");
    lblDeleteInvoice.setFont(new Font("Tahoma", 0, 15));
    lblDeleteInvoice.setBounds(10, 486, 113, 27);
    panel_2.add(lblDeleteInvoice);
    
    textField_3 = new JTextField();
    textField_3.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          String s = textField_3.getText();
          int action = JOptionPane.showConfirmDialog(null, s, "Delete", 0);
          if (action == 0)
          {

            try
            {
              String query = "delete from customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, textField_3.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, textField_3.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            



            try
            {
              String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name ,CUST_phone,total_items,grand_total from  customers   order by date desc,time desc";
              
              PreparedStatement pst = com.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
              table_1.setModel(DbUtils.resultSetToTableModel(rs));
              
              pst.close();
              rs.close();

            }
            catch (SQLException e1)
            {

              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);
            }
          }
          

          textField_3.setText("");
          table_3.setModel(new DefaultTableModel(
            new Object[0][], 
            
            new String[] {
            "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));

        }
        
      }
      

    });
    textField_3.setColumns(10);
    textField_3.setBounds(113, 491, 94, 20);
    panel_2.add(textField_3);
    
    JPanel panel_7 = new JPanel();
    panel_7.setBackground(SystemColor.inactiveCaption);
    panel_7.setBorder(new BevelBorder(0, null, null, null, null));
    panel_7.setBounds(134, 11, 264, 87);
    panel_2.add(panel_7);
    
    JPanel panel_8 = new JPanel();
    panel_8.setBackground(SystemColor.inactiveCaption);
    panel_8.setBorder(new BevelBorder(0, null, null, null, null));
    panel_8.setBounds(408, 11, 265, 87);
    panel_2.add(panel_8);
    
    JPanel panel_9 = new JPanel();
    panel_9.setBackground(SystemColor.inactiveCaption);
    panel_9.setBorder(new BevelBorder(0, null, null, null, null));
    panel_9.setBounds(683, 11, 437, 87);
    panel_2.add(panel_9);
    
    JScrollPane scrollPane_3 = new JScrollPane();
    scrollPane_3.setBounds(653, 110, 446, 365);
    panel_2.add(scrollPane_3);
    
    table_3 = new JTable();
    table_3.setFont(new Font("Tahoma", 0, 15));
    table_3.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent me)
      {
        try
        {
          int row = table_3.getSelectedRow();
          String sid = table_3.getModel().getValueAt(row, 0).toString();
          String name = table_3.getModel().getValueAt(row, 1).toString();
          String query = "select P_Name, P_Qty, P_Price, P_Disc, P_Amt from invoicetable where i_no='" + sid + "' and P_name='" + name + "'";
          PreparedStatement pst = con.prepareStatement(query);
          ResultSet rs = pst.executeQuery();
          while (rs.next())
          {
            textField_8.setText(rs.getString("P_Name"));
            textField_9.setText(rs.getString("P_Qty"));
            textField_10.setText(rs.getString("P_Price"));
            textField_11.setText(rs.getString("P_Disc"));
            textField_12.setText(rs.getString("P_Amt"));
          }
          
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        
      }
      
    });
    table_3.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));
    

    scrollPane_3.setViewportView(table_3);
    
    textField_8 = new JTextField();
    textField_8.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {



          textField_9.requestFocus();
        }
        
      }
      

    });
    textField_8.setBounds(1109, 138, 80, 20);
    panel_2.add(textField_8);
    textField_8.setColumns(10);
    
    textField_9 = new JTextField();
    textField_9.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {



          textField_10.requestFocus();
        }
        


        if (arg0.getKeyCode() == 38)
        {
          textField_8.requestFocus();

        }
        
      }
      

    });
    textField_9.setColumns(10);
    textField_9.setBounds(1109, 184, 80, 20);
    panel_2.add(textField_9);
    
    textField_10 = new JTextField();
    textField_10.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {

        if (e.getKeyCode() == 10)
        {



          textField_11.requestFocus();
        }
        

        if (e.getKeyCode() == 38)
        {
          textField_9.requestFocus();

        }
        

      }
      

    });
    textField_10.setColumns(10);
    textField_10.setBounds(1109, 230, 80, 20);
    panel_2.add(textField_10);
    
    textField_11 = new JTextField();
    textField_11.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          try
          {
            double p = Double.parseDouble(textField_10.getText());
            double q = Double.parseDouble(textField_9.getText());
            double d = Double.parseDouble(textField_11.getText());
            
            double sum = q * (p - d * p / 100.0D);
            double newsum = Math.round(sum * 100.0D) / 100.0D;
            textField_12.setText(Double.toString(newsum));
            


            textField_12.requestFocus();



          }
          catch (Exception e2)
          {


            JOptionPane.showMessageDialog(null, "Enter valid number");
            P.requestFocus();
          }
        }
        







        if (e.getKeyCode() == 38)
        {
          textField_10.requestFocus();

        }
        
      }
      

    });
    textField_11.setColumns(10);
    textField_11.setBounds(1109, 277, 80, 20);
    panel_2.add(textField_11);
    
    textField_12 = new JTextField();
    textField_12.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          textField_11.requestFocus();
        }
        
      }
      

    });
    textField_12.setColumns(10);
    textField_12.setBounds(1109, 323, 80, 20);
    panel_2.add(textField_12);
    
    JLabel lblPname = new JLabel("P_Name");
    lblPname.setFont(new Font("Tahoma", 1, 11));
    lblPname.setBounds(1109, 122, 80, 14);
    panel_2.add(lblPname);
    
    JLabel lblP = new JLabel("P_Qty");
    lblP.setFont(new Font("Tahoma", 1, 11));
    lblP.setBounds(1109, 169, 80, 14);
    panel_2.add(lblP);
    
    JLabel lblPprice = new JLabel("P_Price");
    lblPprice.setFont(new Font("Tahoma", 1, 11));
    lblPprice.setBounds(1109, 213, 74, 14);
    panel_2.add(lblPprice);
    
    JLabel lblPdisc = new JLabel("P_Disc");
    lblPdisc.setFont(new Font("Tahoma", 1, 11));
    lblPdisc.setBounds(1109, 261, 64, 14);
    panel_2.add(lblPdisc);
    
    JButton btnUpdate = new JButton("Update");
    btnUpdate.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        int row = table_3.getSelectedRow();
        String sid = table_3.getModel().getValueAt(row, 0).toString();
        String name = table_3.getModel().getValueAt(row, 1).toString();
        double d = Double.parseDouble(table_3.getModel().getValueAt(row, 5).toString());
        try
        {
          String query = "update invoicetable set P_name='" + textField_8.getText() + "',P_Qty='" + textField_9.getText() + "'," + 
            " P_price='" + textField_10.getText() + "' , P_Disc='" + textField_11.getText() + "' ,P_Amt='" + textField_12.getText() + "' where i_no ='" + sid + "' and P_name ='" + name + "' ";
          PreparedStatement pst = con.prepareStatement(query);
          pst.execute();
          pst.close();
          



          double gt = 0.0D;
          

          String query1 = "select  grand_total from customers where invoice_no='" + sid + "' ";
          PreparedStatement pst1 = con.prepareStatement(query1);
          ResultSet rs = pst1.executeQuery();
          while (rs.next())
          {

            gt = Double.parseDouble(rs.getString("GRAND_TOTAL"));
          }
          
          pst1.close();
          rs.close();
          



          gt -= d;
          double amt = Double.parseDouble(textField_12.getText());
          gt = Math.round((gt + amt) * 100.0D) / 100.0D;
          





          String sql = "update customers set grand_total='" + gt + "' where invoice_no='" + sid + "'";
          
          PreparedStatement pst2 = com.prepareStatement(sql);
          pst2.execute();
          pst2.close();
          






          String sql1 = "select i_no ,p_name,p_qty,p_price,p_disc ,p_amt from invoicetable where i_no='" + sid + "'";
          
          PreparedStatement pst3 = com.prepareStatement(sql1);
          ResultSet rs1 = pst3.executeQuery();
          table_3.setModel(DbUtils.resultSetToTableModel(rs1));
          
          pst3.close();
          rs1.close();
          


          textField_8.setText("");
          textField_9.setText("");
          textField_10.setText("");
          textField_11.setText("");
          textField_12.setText("");
          




          String sql2 = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from customers where invoice_no='" + sid + "'";
          
          PreparedStatement pt = com.prepareStatement(sql2);
          ResultSet r = pt.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(r));
          
          pt.close();
          r.close();

        }
        catch (Exception e1)
        {

  //        e1.printStackTrace();
          JOptionPane.showMessageDialog(null, "Error something wrong");

        }
        

      }
      


    });
    btnUpdate.setFont(new Font("Tahoma", 1, 11));
    btnUpdate.setBounds(1109, 350, 80, 23);
    panel_2.add(btnUpdate);
    
    JButton btnDelete = new JButton("Delete");
    btnDelete.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        int row = table_3.getSelectedRow();
        String sid = table_3.getModel().getValueAt(row, 0).toString();
        String name = table_3.getModel().getValueAt(row, 1).toString();
        double d = Double.parseDouble(table_3.getModel().getValueAt(row, 5).toString());
        
        int action = JOptionPane.showConfirmDialog(null, "Remove item?", "Remove", 0);
        if (action == 0)
        {

          try
          {

            String query = "delete from invoicetable where i_no='" + sid + "' and p_name='" + name + "'";
            
            PreparedStatement pst = con.prepareStatement(query);
            

            pst.execute();
            
            pst.close();
            




            double gt = 0.0D;
            int t = 0;
            
            String query1 = "select  grand_total , total_items from customers where invoice_no='" + sid + "' ";
            PreparedStatement pst1 = con.prepareStatement(query1);
            ResultSet rs = pst1.executeQuery();
            while (rs.next())
            {

              gt = Double.parseDouble(rs.getString("GRAND_TOTAL"));
              t = Integer.parseInt(rs.getString("TOTAL_ITEMS"));
            }
            
            pst1.close();
            rs.close();
            





            gt = Math.round((gt - d) * 100.0D) / 100.0D;
            t--;
            




            String sql = "update customers set grand_total='" + gt + "' ,  total_items='" + t + "'where invoice_no='" + sid + "'";
            
            PreparedStatement pst2 = com.prepareStatement(sql);
            pst2.execute();
            pst2.close();
            


            String sql1 = "select i_no ,p_name,p_qty,p_price,p_disc ,p_amt from invoicetable where i_no='" + sid + "'";
            
            PreparedStatement pst3 = com.prepareStatement(sql1);
            ResultSet rs1 = pst3.executeQuery();
            table_3.setModel(DbUtils.resultSetToTableModel(rs1));
            
            pst3.close();
            rs1.close();
            

            textField_8.setText("");
            textField_9.setText("");
            textField_10.setText("");
            textField_11.setText("");
            textField_12.setText("");
            



            String sql2 = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from customers where invoice_no='" + sid + "'";
            
            PreparedStatement pt = com.prepareStatement(sql2);
            ResultSet r = pt.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(r));
            
            pt.close();
            r.close();


          }
          catch (SQLException e1)
          {

//            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error something wrong");
          }
          
        }
        
      }
    });
    btnDelete.setFont(new Font("Tahoma", 0, 11));
    btnDelete.setBounds(1115, 449, 74, 23);
    panel_2.add(btnDelete);
    
    JButton btnAdd = new JButton("Add");
    btnAdd.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String sid;
        try {
          sid = table_3.getModel().getValueAt(0, 0).toString();
        }
        catch (Exception e1) {
          try {
            
            int row = table_1.getSelectedRow();
            sid = table_1.getModel().getValueAt(row, 0).toString();
          } catch (Exception l) { 
            sid = table_1.getModel().getValueAt(0, 0).toString();
          }
        }
        try {
          String query = "insert into invoicetable (p_name,p_price,p_qty,p_disc,p_amt , i_no) values (?,?,?,?,?,?)";
          
          PreparedStatement pst = con.prepareStatement(query);
          



          pst.setString(1, textField_8.getText());
          pst.setString(2, textField_10.getText());
          pst.setString(3, textField_9.getText());
          pst.setString(4, textField_11.getText());
          pst.setString(5, textField_12.getText());
          pst.setString(6, sid);
          




          pst.execute();
          
          pst.close();
          

          double gt = 0.0D;
          int t = 0;
          
          String query1 = "select  grand_total , total_items from customers where invoice_no='" + sid + "' ";
          PreparedStatement pst1 = con.prepareStatement(query1);
          ResultSet rs1 = pst1.executeQuery();
          while (rs1.next())
          {

            gt = Double.parseDouble(rs1.getString("GRAND_TOTAL"));
            t = Integer.parseInt(rs1.getString("TOTAL_ITEMS"));
          }
          
          pst1.close();
          rs1.close();
          




          double amt = Double.parseDouble(textField_12.getText());
          gt = Math.round((gt + amt) * 100.0D) / 100.0D;
          
          t++;
          




          String sql = "update customers set grand_total='" + gt + "' ,  total_items='" + t + "'where invoice_no='" + sid + "'";
          
          PreparedStatement pst2 = com.prepareStatement(sql);
          pst2.execute();
          pst2.close();
          



          String sql2 = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from customers where invoice_no='" + sid + "'";
          
          PreparedStatement pt = com.prepareStatement(sql2);
          ResultSet r = pt.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(r));
          
          pt.close();
          r.close();
          


          String sql1 = "select i_no ,p_name,p_qty,p_price,p_disc ,p_amt from invoicetable where i_no='" + sid + "'";
          
          PreparedStatement pst3 = com.prepareStatement(sql1);
          ResultSet rs2 = pst3.executeQuery();
          table_3.setModel(DbUtils.resultSetToTableModel(rs2));
          
          pst3.close();
          rs2.close();
          

          textField_8.setText("");
          textField_9.setText("");
          textField_10.setText("");
          textField_11.setText("");
          textField_12.setText("");


        }
        catch (SQLException e1)
        {


          JOptionPane.showMessageDialog(null, "Enter Data carefully");

        }
        

      }
      


    });
    btnAdd.setFont(new Font("Tahoma", 1, 11));
    btnAdd.setBounds(1109, 388, 80, 23);
    panel_2.add(btnAdd);
    
    JLabel lblPamt_1 = new JLabel("P_Amt");
    lblPamt_1.setFont(new Font("Tahoma", 1, 11));
    lblPamt_1.setBounds(1109, 308, 64, 14);
    panel_2.add(lblPamt_1);
    



    JLabel lblPrintInvoice = new JLabel("Print Invoice:");
    lblPrintInvoice.setFont(new Font("Tahoma", 0, 15));
    lblPrintInvoice.setBounds(653, 486, 113, 27);
    panel_2.add(lblPrintInvoice);
    
    textField_13 = new JTextField();
    textField_13.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          String ino = textField_13.getText();
          printinvoice(ino);
          textPane.setText("");
          
          textField_13.setText("");
        }
        
      }
      

    });
    textField_13.setColumns(10);
    textField_13.setBounds(756, 491, 94, 20);
    panel_2.add(textField_13);
    

    JPanel panel_11 = new JPanel();
    panel_11.setBackground(SystemColor.inactiveCaption);
    panel_11.setBorder(new BevelBorder(0, null, null, null, null));
    panel_11.setBounds(1102, 110, 93, 365);
    panel_2.add(panel_11);
    


    JPanel panel_10 = new JPanel();
    tabbedPane.addTab("Company Details", null, panel_10, null);
    panel_10.setLayout(null);
    
    lblCompanyname = new JLabel("Company_Name");
    lblCompanyname.setFont(new Font("Tahoma", 1, 16));
    lblCompanyname.setBounds(85, 96, 144, 20);
    panel_10.add(lblCompanyname);
    
    textField_4 = new JTextField();
    textField_4.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {

          textField_5.requestFocus();
        }
        
      }
      
    });
    textField_4.setColumns(10);
    textField_4.setBounds(264, 98, 272, 20);
    panel_10.add(textField_4);
    
    lblCompanyaddress = new JLabel("Company_Address");
    lblCompanyaddress.setFont(new Font("Tahoma", 1, 16));
    lblCompanyaddress.setBounds(85, 139, 169, 20);
    panel_10.add(lblCompanyaddress);
    
    textField_5 = new JTextField();
    textField_5.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == 10)
        {

          textField_6.requestFocus();
        }
        
      }
      

    });
    textField_5.setColumns(10);
    textField_5.setBounds(264, 141, 272, 20);
    panel_10.add(textField_5);
    
    lblCompanyphone = new JLabel("Company_Phone");
    lblCompanyphone.setFont(new Font("Tahoma", 1, 16));
    lblCompanyphone.setBounds(85, 184, 144, 20);
    panel_10.add(lblCompanyphone);
    
    textField_6 = new JTextField();
    textField_6.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {

          textField_7.requestFocus();
        }
        
      }
      

    });
    textField_6.setColumns(10);
    textField_6.setBounds(264, 186, 272, 20);
    panel_10.add(textField_6);
    
    lblCompanyemail = new JLabel("Company_Email");
    lblCompanyemail.setFont(new Font("Tahoma", 1, 16));
    lblCompanyemail.setBounds(85, 229, 144, 20);
    panel_10.add(lblCompanyemail);
    
    textField_7 = new JTextField();
    textField_7.setColumns(10);
    textField_7.setBounds(264, 231, 272, 20);
    panel_10.add(textField_7);
    
    btnNewButton_4 = new JButton("Submit");
    btnNewButton_4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        try {
          String query = "INSERT INTO company (company_NAME,company_add,company_phone,company_email) VALUES (?,?,?,?)";
          
          PreparedStatement pst = con.prepareStatement(query);
          



          pst.setString(1, textField_4.getText());
          pst.setString(2, textField_5.getText());
          pst.setString(3, textField_6.getText());
          pst.setString(4, textField_7.getText());
          


          pst.execute();
          
          pst.close();
          
          JOptionPane.showMessageDialog(null, "Details Saved!");
          btnNewButton_5.setVisible(true);
          textField_4.setText("");
          textField_5.setText("");
          textField_6.setText("");
          textField_7.setText("");
          
          textField_4.setVisible(false);
          textField_5.setVisible(false);
          textField_6.setVisible(false);
          textField_7.setVisible(false);
          lblCompanyname.setVisible(false);
          lblCompanyaddress.setVisible(false);
          lblCompanyphone.setVisible(false);
          lblCompanyemail.setVisible(false);
          btnNewButton_4.setVisible(false);
        }
        catch (SQLException e1) {
          JOptionPane.showMessageDialog(null, e1);
        }
        
      }
      

    });
    btnNewButton_4.setFont(new Font("Tahoma", 0, 13));
    btnNewButton_4.setBounds(447, 283, 89, 23);
    panel_10.add(btnNewButton_4);
    
    btnNewButton_5 = new JButton("Reset Company Details");
    btnNewButton_5.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        int action = JOptionPane.showConfirmDialog(null, "All Company Details Will be Deleted!", "Delete", 0);
        if (action == 0) {
          try
          {
            String sql = " DELETE FROM company";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();
            
            textField_4.setVisible(true);
            textField_5.setVisible(true);
            textField_6.setVisible(true);
            textField_7.setVisible(true);
            lblCompanyname.setVisible(true);
            lblCompanyaddress.setVisible(true);
            lblCompanyphone.setVisible(true);
            lblCompanyemail.setVisible(true);
            btnNewButton_4.setVisible(true);
            btnNewButton_5.setVisible(false);
          }
          catch (SQLException e1)
          {
            JOptionPane.showMessageDialog(null, e1);
          }
          
        }
        
      }
      
    });
    btnNewButton_5.setFont(new Font("Tahoma", 0, 13));
    btnNewButton_5.setBounds(723, 46, 169, 31);
    panel_10.add(btnNewButton_5);
    


    btnNewButton_5.setVisible(false);
    

    JPanel panel_6 = new JPanel();
    tabbedPane.addTab("Reset Data Factory", null, panel_6, null);
    panel_6.setLayout(null);
    
    JButton deleteall = new JButton("Delete All Data!");
    Image da = new ImageIcon(getClass().getResource("/deleteall.png")).getImage();
    deleteall.setIcon(new ImageIcon(da));
    

    deleteall.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        int action = JOptionPane.showConfirmDialog(null, "All Data will be erased", "Delete All Data", 0);
        if (action == 0)
        {

          try
          {
            String sql = " DELETE FROM items ";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();
            
            combobox_items_2();
            combobox_items();
          }
          catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          

          try
          {
            String sql = " DELETE FROM  customers";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          

          try
          {
            String sql = " DELETE FROM invoicetable ";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          


          try
          {
            String sql = "select DISTINCT item_name from items ORDER BY item_name ASC;";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_2.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          





          try
          {
            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name ,CUST_phone,total_items,grand_total  from  customers order by date desc, time desc";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);

          }
          
        }
        
      }
      

    });
    deleteall.setFont(new Font("Tahoma", 1, 15));
    deleteall.setBounds(706, 304, 205, 60);
    panel_6.add(deleteall);
    





    JButton btnDeleteAllItems = new JButton("Delete All Bills");
    Image dab = new ImageIcon(getClass().getResource("/deletebills.png")).getImage();
    btnDeleteAllItems.setIcon(new ImageIcon(dab));
    btnDeleteAllItems.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int action = JOptionPane.showConfirmDialog(null, "All Billing History will be erased", "Delete All Data", 0);
        if (action == 0)
        {
          try
          {
            String sql = " DELETE FROM  customers";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          


          try
          {
            String sql = " DELETE FROM invoicetable ";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          try
          {
            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name ,CUST_phone,total_items,grand_total from  customers  order by date desc,time desc";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
        }
        
      }
      

    });
    btnDeleteAllItems.setFont(new Font("Tahoma", 0, 17));
    btnDeleteAllItems.setBounds(122, 99, 205, 60);
    panel_6.add(btnDeleteAllItems);
    
    JButton button_1 = new JButton("Delete All Items");
    
    Image dai = new ImageIcon(getClass().getResource("/deleteitems.png")).getImage();
    button_1.setIcon(new ImageIcon(dai));
    button_1.addActionListener(new ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        int action = JOptionPane.showConfirmDialog(null, "All Items will be erased", "Delete All Data", 0);
        if (action == 0)
        {

          try
          {
            String sql = " DELETE FROM items ";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();
            
            combobox_items_2();
            combobox_items();
          }
          catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          try
          {
            String sql = "select DISTINCT item_name from items ORDER BY item_name ASC;";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table_2.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);

          }
          
        }
        
      }
      

    });
    button_1.setFont(new Font("Tahoma", 0, 17));
    button_1.setBounds(453, 99, 208, 60);
    panel_6.add(button_1);
    







    Image imglogout = new ImageIcon(getClass().getResource("/Logout24.png")).getImage();
    

    Image imgchangepass = new ImageIcon(getClass().getResource("/changeDetails.png")).getImage();
    



    JButton btnNewButton = new JButton("Change Password");
    
    btnNewButton.setIcon(new ImageIcon(imgchangepass));
    btnNewButton.setFont(new Font("Tahoma", 1, 12));
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ChangePassword cp = new ChangePassword();
        cp.setVisible(true);
      }
      
    });
    JButton logout = new JButton("Logout");
    logout.setIcon(new ImageIcon(imglogout));
    logout.setFont(new Font("Tahoma", 0, 12));
    logout.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new LoginPage().frame.setVisible(true);
      }
    });
    logout.setBounds(1095, 11, 108, 32);
    contentPane.add(logout);
    btnNewButton.setBounds(1025, 51, 178, 32);
    contentPane.add(btnNewButton);
    
    internalFrame = new JInternalFrame("Fast Add Items");
    internalFrame.getContentPane().setBackground(SystemColor.inactiveCaption);
    internalFrame.setBorder(new EtchedBorder(0, null, null));
    internalFrame.setForeground(SystemColor.textText);
    internalFrame.setBackground(SystemColor.activeCaption);
    internalFrame.setClosable(true);
    internalFrame.setBounds(598, 0, 332, 67);
    contentPane.add(internalFrame);
    internalFrame.getContentPane().setLayout(null);
    
    JLabel label = new JLabel("Product_Name");
    label.setFont(new Font("Tahoma", 1, 18));
    label.setBounds(10, 12, 135, 20);
    internalFrame.getContentPane().add(label);
    
    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "INSERT INTO items (item_NAME) VALUES (?)";
            


            PreparedStatement pst = con.prepareStatement(query);
            

            String lowercase = textField.getText();
            
            pst.setString(1, lowercase);
            


            pst.execute();
            
            pst.close();
            combobox_items();
            combobox_items_2();
            textField.setText("");
            internalFrame.setVisible(false);
            comboBox.requestFocus();
          }
          catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
        }
        
      }
      
    });
    textField.setColumns(10);
    textField.setBounds(155, 11, 135, 20);
    internalFrame.getContentPane().add(textField);
    






    c_name.setEnabled(false);
    c_contact.setEnabled(false);
    P.setEnabled(false);
    Q.setEnabled(false);
    D.setEnabled(false);
    A.setEnabled(false);
    comboBox.setEnabled(false);
    
    textPane = new JTextPane();
    textPane.setFont(new Font("Courier New", 1, 11));
    textPane.setBounds(777, 182, 332, 199);
    panel.add(textPane);
    
    table_4 = new JTable();
    table_4.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "d", "time" }));
    

    table_4.setBounds(75, 42, 1, 1);
    contentPane.add(table_4);
    

    combobox_items_2();
    internalFrame.setVisible(false);
    
    combobox_items();
    currentdate();
    cdatabase();
  }
}

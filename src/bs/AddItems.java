package bs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;








public class AddItems
  extends JFrame
{
  private JPanel contentPane;
  Connection con = null;
  
  private JTextField textField;
  
  public void run()
  {
    try
    {
      AddItems frame = new AddItems();
      frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  





  public AddItems()
  {
    setBounds(100, 100, 450, 322);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JDesktopPane desktopPane = new JDesktopPane();
    desktopPane.setBackground(new Color(106, 90, 205));
    desktopPane.setBounds(26, 11, 398, 179);
    contentPane.add(desktopPane);
    
    JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
    internalFrame.setBounds(10, 11, 378, 157);
    desktopPane.add(internalFrame);
    internalFrame.getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBorder(new EtchedBorder(0, null, null));
    panel.setBackground(SystemColor.inactiveCaption);
    panel.setBounds(10, 24, 318, 92);
    internalFrame.getContentPane().add(panel);
    
    JLabel label = new JLabel("Product_Name");
    label.setFont(new Font("Tahoma", 1, 18));
    panel.add(label);
    
    textField = new JTextField();
    textField.setColumns(10);
    panel.add(textField);
    
    JButton button = new JButton("Submit");
    button.setFont(new Font("Tahoma", 1, 18));
    button.setBounds(new Rectangle(70, 70, 70, 70));
    panel.add(button);
    internalFrame.setVisible(true);
  }
}

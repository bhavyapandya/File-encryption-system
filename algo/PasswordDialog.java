/**
 * Title:        Algo<p>
 * Description:  A Java Based BlowFish Implementation<p>
 * Copyright:    Copyright (c) GSS<p>
 * Company:      Galaxy Software<p>
 * @author GSS
 * @version 1.0
 */

package algo;

//  Classes/Interfaces from java.awt . . . 

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;

//  Classes/Interfaces from java.awt.event . . . 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//  Classes/Interfaces from javax.swing . . . 

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**<Font Color = "Green"><B>Accessed from <Font Color = "Red">AlgoFrame</Font>.   This allows the user to 
 *specify the password, using which this algorithm prepares
 *the round keys for performing encryption and decryption.
 *</B></Font>
 *@see MsgBox
 */

public class PasswordDialog extends JDialog
{
    private JLabel jlPassword = new JLabel();
    private JLabel jlConfirmPassword = new JLabel();
    private Button butOk = new Button();//ipf
    private Button butCancel = new Button();//opf
    public TextField tfPassword = new TextField();
    public TextField tfConfirmPassword = new TextField();
    public boolean cancel = true;

    public PasswordDialog(JFrame f)
    {
    super(f,true) ;
    setResizable(false);
    setSize(300,200);
    int x=0,y=0;
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
x = d.width /2 - 300/2;
y = d.height /2 - 200/2;

setLocation( x,y);
try {
  jbInit();
}
catch(Exception e) {
  e.printStackTrace();
}
}
 
    /**Initializes the user interface.   The controls will
     *be displayed for the user through which the user can
     *enter the password and confirmation password.
     */
        
    private void jbInit() throws Exception
    {
    this.getContentPane().setLayout(null);
    jlPassword.setText("Password");
    jlPassword.setBounds(new Rectangle(37, 23, 72, 25));
    jlConfirmPassword.setBounds(new Rectangle(11, 52, 116, 31));
    jlConfirmPassword.setText("Confirm Password");
    butOk.setBounds(new Rectangle(65, 98, 57, 21));
    butOk.setLabel("OK");
    butOk.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        butOkActionPerformed(e);
      }
    });
    butCancel.setBounds(new Rectangle(134, 98, 69, 20));
    butCancel.setLabel("Cancel");
    butCancel.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        butCancelActionPerformed(e);
      }
    });
    tfPassword.setBackground(Color.white);
    tfPassword.setBounds(new Rectangle(125, 26, 153, 21));
    tfPassword.setEchoChar('?');
    tfConfirmPassword.setBackground(SystemColor.controlLtHighlight);
    tfConfirmPassword.setBounds(new Rectangle(125, 55, 153, 21));
    tfConfirmPassword.setEchoChar('?');
    this.setTitle("JFSecurity Password");
    this.getContentPane().add(jlPassword, null);
    this.getContentPane().add(jlConfirmPassword, null);
    this.getContentPane().add(butOk, null);
    this.getContentPane().add(butCancel, null);
    this.getContentPane().add(tfPassword, null);
    this.getContentPane().add(tfConfirmPassword, null);
    }

    /**Raised when the user clicks on Ok button.
     *It checks whether the values in password and 
     *confirm password text boxes are matched or not.
     *If matched it disposes the dialog box and if not
     *provides the message box.
     *@param e The button click event
     */
  
    private void butOkActionPerformed(ActionEvent e)
    {
        if( tfPassword.getText().compareTo(tfConfirmPassword.getText())==0)
        {
            cancel = false;
            setVisible(false);
            return;
        }
        algo.MsgBox.show("Invalid Confirmation");
        tfConfirmPassword.selectAll();
        cancel = true;
    }
    
    /**Raised when Cancel button was pressed.   Simply
     *disposes the dialog box without taking password.
     *@param e The button click event
     */
  
    private void butCancelActionPerformed(ActionEvent e)
    {
        tfPassword.setText("");
        tfConfirmPassword.setText("");
        cancel = true;
        dispose();
    }
}
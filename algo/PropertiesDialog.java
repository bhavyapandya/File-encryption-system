
/**
 * Title:        Algo<p>
 * Description:  A Java Base BlowFish Implementation<p>
 * Copyright:    Copyright (c) GSS<p>
 * Company:      Galaxy Software<p>
 * @author GSS
 * @version 1.0
 */
package algo;



import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**<Font Color = "Green"><B>Accessed from <Font Color = "Red">AlgoFrame</Font>.   This allows the user to change the default settings of
 *block and key sizes.   The sizes may be one of 128 or 192
 *or 256 bytes.</B></Font>
 */

public class PropertiesDialog extends JDialog
{
    private Label lblBlockSize = new Label();
    private Label lblKeySize = new Label();
    private Button butOk = new Button();
    private Button butCancel = new Button();
    Choice chBlockSize = new Choice();
    Choice chKeySize = new Choice();
    public int BLOCK_SIZE=128;
    public int KEY_SIZE=128;

    public PropertiesDialog(javax.swing.JFrame f) 
    {
        super(f,true);
        setResizable(false);
        setSize(300,200);
        chBlockSize.add("128");
        chBlockSize.add("192");
        chBlockSize.add("256");
        chKeySize.add("128");
        chKeySize.add("192");
        chKeySize.add("256");
        int x=0,y=0;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        x = d.width /2 - 300/2;
        y = d.height /2 - 200/2;
        setLocation( x,y);
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**Initializes the user interface.   The controls will
     *be displayed for the user through which the user can
     *select the block and key sizes.
     */
    
    private void jbInit() throws Exception 
    {
        lblBlockSize.setBounds(new Rectangle(23, 17, 65, 29));
        lblBlockSize.setFont(new java.awt.Font("Serif", 1, 12));
        lblBlockSize.setText("Block Size");
        this.setTitle("Algo Encryption Settings");
        this.getContentPane().setLayout(null);
        lblKeySize.setText("Key Size");
        lblKeySize.setBounds(new Rectangle(22, 54, 65, 29));
        lblKeySize.setFont(new java.awt.Font("Serif", 1, 12));
        butOk.setBounds(new Rectangle(92, 125, 71, 31));
        butOk.setLabel("OK");
        butOk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button1_actionPerformed(e);
            }
        });
        butCancel.setLabel("Close");
        butCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                button2_actionPerformed(e);
            }
        });
        butCancel.setBounds(new Rectangle(173, 126, 71, 31));
        chBlockSize.setBounds(new Rectangle(94, 20, 111, 21));
        chBlockSize.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                choice1_itemStateChanged(e);
            }
        });
        chKeySize.setBounds(new Rectangle(93, 62, 111, 22));
        chKeySize.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                choice2_itemStateChanged(e);
            }
        });
        this.getContentPane().add(lblBlockSize, null);
        this.getContentPane().add(lblKeySize, null);
        this.getContentPane().add(butOk, null);
        this.getContentPane().add(butCancel, null);
        this.getContentPane().add(chBlockSize, null);
        this.getContentPane().add(chKeySize, null);
    }
    
    /**Raises when Ok button was clicked.   Stores the selected
     *block and key sizes into the static members.
     *@param e The button click event
     */
    
    void button1_actionPerformed(ActionEvent e)
    {
        // OK
        if(chBlockSize.getSelectedIndex() >0)
            BLOCK_SIZE = Integer.parseInt( chBlockSize.getItem(chBlockSize.getSelectedIndex() ) );
        if(chKeySize.getSelectedIndex() >0)
            KEY_SIZE = Integer.parseInt( chKeySize.getItem(chKeySize.getSelectedIndex() ) );
        this.dispose();
    }

    /**Raised when Cancel button was pressed.   Simply
     *disposes the dialog box and takes the default
     *block and key sizes.
     *@param e The button click event
     */
    
    void button2_actionPerformed(ActionEvent e) 
    {
        //  Cancel
        this.dispose();
    }

    /**Raised when the user changes the value of 
     *block size in the combo box.   Sets the block size
     *to the selected value.
     *@param e The item selection event
     */
    
    void choice1_itemStateChanged(ItemEvent e) 
    {
        BLOCK_SIZE = Integer.parseInt( chBlockSize.getSelectedItem());
    }
    
    /**Raised when the user changes the value of 
     *key size in the combo box.   Sets the key size
     *to the selected value.
     *@param e The item selection event
     */
    
    void choice2_itemStateChanged(ItemEvent e) 
    {
        KEY_SIZE = Integer.parseInt( chKeySize.getSelectedItem());
    }
}
package com.UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.crypto.CipherOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.xml.ws.spi.http.HttpHandler;

public class Options implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField Port;
	private JTextField ProxyURL;
	private JLabel lblTimeShyncronisation;
	private JButton Ok;
	private JButton Cancel;
	private JCheckBox chckbxLaunchChrometaAt;
	private JPasswordField Pass;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options window = new Options();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Options() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel UserName = new JLabel("User Name :");
		UserName.setBounds(10, 46, 80, 14);
		frame.getContentPane().add(UserName);
		
		JLabel Password = new JLabel("Password :");
		Password.setBounds(10, 80, 75, 14);
		frame.getContentPane().add(Password);
		
		textField = new JTextField();
		textField.setBounds(100, 46, 248, 14);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		/*Pass = new JPasswordField();
		Pass.setEchoChar("*");
		textField_1.setBounds(100, 80, 210, 14);*/
		
	/*	frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);*/
		
		JCheckBox chckbxProxyUrl = new JCheckBox("Proxy URL");
		chckbxProxyUrl.setBounds(10, 116, 86, 14);
		frame.getContentPane().add(chckbxProxyUrl);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(217, 116, 46, 14);
		frame.getContentPane().add(lblPort);
		
		Port = new JTextField();
		Port.setBounds(273, 116, 75, 14);
		frame.getContentPane().add(Port);
		Port.setColumns(10);
		
		ProxyURL = new JTextField();
		ProxyURL.setBounds(100, 116, 86, 14);
		frame.getContentPane().add(ProxyURL);
		ProxyURL.setColumns(10);
		
		lblTimeShyncronisation = new JLabel("Time Shyncronisation");
		lblTimeShyncronisation.setBounds(10, 21, 107, 14);
		frame.getContentPane().add(lblTimeShyncronisation);
		
		Ok = new JButton("OK");
		Ok.setBounds(76, 202, 86, 28);
		frame.getContentPane().add(Ok);
		
		Ok.addActionListener(this);
		Cancel.addActionListener(this);
		
		
		Cancel = new JButton("Cancel");
		Cancel.setBounds(221, 202, 99, 28);
		frame.getContentPane().add(Cancel);
		
		chckbxLaunchChrometaAt = new JCheckBox("Launch ApplicationTracker At Startup");
		chckbxLaunchChrometaAt.setBounds(10, 157, 258, 23);
		frame.getContentPane().add(chckbxLaunchChrometaAt);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 80, 238, 14);
		frame.getContentPane().add(passwordField);
		
		//Create File
	
		
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		try{
			
			if(Ok.getText().equalsIgnoreCase("Ok"))
			{
				
				File file = new File("D://Log.txt");
				 if(!file.exists())
				 {
					 file.createNewFile();
				 }
				 String s1 = textField.getText();
				 FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
				 
			/*	 BufferedOutputStream os=  new BufferedOutputStream(new CipherOutputStream(arg0, arg1))
				 BufferedWriter bw = new BufferedWriter(fw);
				 fw.write(textField.getToolTipText());	*/
			}
			 
			 
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		
		
	}
	/*public void ActionPerformed(ActionEvent ae)
	{
		try
		{
			if(Cancel.getText().equalsIgnoreCase("Cancel"))
			{
				System.exit(0);
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/
}

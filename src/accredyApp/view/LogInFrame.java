package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import accredyApp.controller.*;
import accredyApp.model.*;


public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogInUsername;
	private JTextField textFieldLogInPassword;
	private JLabel lblFeedback;
	private JButton btnLogIn;
	private JButton btnCreateAccount;
	private Model model;
	

	boolean updated = false;
	
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
	public void setFeedback(String feedback) { lblFeedback.setText(feedback);}
	public JTextField getUsernameField() { return textFieldLogInUsername; }
	public JTextField getPasswordField() { return textFieldLogInPassword; }
	public JButton getLogInButton() { return btnLogIn; }
	public JButton getCreateButton() { return btnCreateAccount; }
	public boolean wasUpdated() { return updated; }
//	public void setVisible(boolean status) {this.setVisible(status);} //true if we need user to retry input. False if we need it to suddenly disappear.
	public JLabel getFeedback() { return lblFeedback;}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame(new Model());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInFrame(Model m) {
		this.model = m;
		
		
		setTitle("Accredy - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lblInstruction = new JLabel("Please enter your login information below:");
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		textFieldLogInUsername = new JTextField();
		textFieldLogInUsername.setColumns(10);
		
//		textFieldLogInPassword = new JTextField();
		textFieldLogInPassword = new JPasswordField();
		textFieldLogInPassword.setColumns(10);
		
		// User tried to log in, now we need to verify account
		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				new LogInController(model, LogInFrame.this).process();
//				lblFeedback.setText("Submitted.");
			}
		});
		
		getRootPane().setDefaultButton(btnLogIn); //If we press enter, it automatically clicks login for us (make sure that this DefaultButton appears after the button is defined)

		
		// create new account
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateAccountController(model, LogInFrame.this).process();
			}
		});
		
		JLabel lblFeedback = new JLabel(""); //I envision using a little label at the bottom of most windows that involve "saving" or updating info, where we could tell the user if the thing worked without spawning a whole extra window.
		//For instance, this one could tell the user if they created a new account successfully, OR could warn them if the login failed.

		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblPassword))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLogIn)
								.addComponent(textFieldLogInPassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addComponent(textFieldLogInUsername, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblFeedback, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateAccount))
						.addComponent(lblInstruction))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblInstruction)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldLogInUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldLogInPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogIn)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateAccount)
						.addComponent(lblFeedback))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}


}

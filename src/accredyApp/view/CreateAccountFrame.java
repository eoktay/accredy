package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateAccountFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JLabel lblFeedback;
	private JTextArea skillField;

	boolean updated = false;
	private JLabel lblseparatedByComma;
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
	public void setFeedback(String feedback) { lblFeedback.setText(feedback);}
	public JTextField getUserNameField() { return textFieldUsername; }
	public JTextField getPasswordField() { return textFieldPassword; }
	public JTextField getFirstNameField() { return textFieldFirstName; }
	public JTextField getLastNameField() { return textFieldLastName; }
	public JTextArea getSkillField() { return skillField; }
	public JButton getSubmitButton() { return btnSubmit; }
	public JButton getCancelButton() { return btnCancel; }
//	public void setVisible(boolean status) {this.setVisible(status);} //true if we need user to retry input. False if we need it to suddenly disappear.
	public boolean wasUpdated() { return updated; }

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountFrame frame = new CreateAccountFrame();
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
	
	
	public CreateAccountFrame() {
		setTitle("Accredy - Create New Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblInstruction = new JLabel("Please enter your account information below:");
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblFirstName = new JLabel("First Name:");
		
		JLabel lblLastName = new JLabel("Last Name:");
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setColumns(10);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		
		btnSubmit = new JButton("Submit");		
		getRootPane().setDefaultButton(btnSubmit); //If we press enter, it automatically clicks this button for us (make sure that this DefaultButton appears after the button is defined)

		btnCancel = new JButton("Cancel");
		
		lblFeedback = new JLabel("FeedbackLabel"); //this would be used to give feedback like whether the username is taken.
		
		skillField = new JTextArea();
		skillField.setWrapStyleWord(true);
		skillField.setLineWrap(true);
		
		JLabel lblYourSkillsSeparated = new JLabel("Your Skills:");
		
		lblseparatedByComma = new JLabel("(separated by comma)");
		lblseparatedByComma.setFont(new Font("Tahoma", Font.PLAIN, 11));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblFeedback, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName)
								.addComponent(lblUsername)
								.addComponent(lblPassword)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblseparatedByComma, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
										.addComponent(lblYourSkillsSeparated, GroupLayout.PREFERRED_SIZE, 121, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldUsername, Alignment.LEADING)
										.addComponent(textFieldLastName, Alignment.LEADING)
										.addComponent(textFieldFirstName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
								.addComponent(skillField, 260, 260, 260)))
						.addComponent(lblInstruction))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblInstruction)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(textFieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(textFieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblYourSkillsSeparated)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblseparatedByComma))
						.addComponent(skillField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSubmit)
						.addComponent(lblFeedback))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	
	
		// User tried to submit, so tell the controller to proceed.
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				lblFeedback.setText("Submitted.");
				CreateAccountFrame.this.setVisible(false);
			}
		});
		
		// Close dialog without changes
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				CreateAccountFrame.this.setVisible(false);
				CreateAccountFrame.this.dispose(); //This might only belong in controllers, but it's worth a try.
			}
		});
		
	}
}

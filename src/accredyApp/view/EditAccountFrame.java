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

public class EditAccountFrame extends JDialog {

	private JPanel contentPane;
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
	public JTextField getPasswordField() { return textFieldPassword; }
	public JTextField getFirstNameField() { return textFieldFirstName; }
	public JTextField getLastNameField() { return textFieldLastName; }
	public JButton getSubmitButton() { return btnSubmit; }
	public JButton getCancelButton() { return btnCancel; }
	public JTextArea getSkillField() { return skillField; }
//	public void setVisible(boolean status) {this.setVisible(status);} //true if we need user to retry input. False if we need it to suddenly disappear.
	public boolean wasUpdated() { return updated; }
	public boolean cancel = false;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAccountFrame frame = new EditAccountFrame();
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
	
	
	public EditAccountFrame() {
		setTitle("Accredy - Edit Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblInstruction = new JLabel("Please enter your account information below:");
		
		JLabel lblPassword = new JLabel("Reset Password to:");
		
		JLabel lblFirstName = new JLabel("First Name:");
		
		JLabel lblLastName = new JLabel("Last Name:");
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setColumns(10);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		
		btnSubmit = new JButton("Save");	
		
		getRootPane().setDefaultButton(btnSubmit); //If we press enter, it automatically clicks this button for us (make sure that this DefaultButton appears after the button is defined)

		btnCancel = new JButton("Close");
		
		lblFeedback = new JLabel(""); //this would be used to give feedback like whether the username is taken.
		
		skillField = new JTextArea();
		skillField.setLineWrap(true);
		skillField.setWrapStyleWord(true);
		
		JLabel lblYourSkillsSeparated = new JLabel("Your Skills:");
		
		lblseparatedByComma = new JLabel("(separated by comma)");
		lblseparatedByComma.setFont(new Font("Tahoma", Font.PLAIN, 11));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFeedback, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
									.addGap(55)
									.addComponent(btnSubmit)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancel))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblFirstName)
												.addComponent(lblLastName)
												.addComponent(lblPassword))
											.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblYourSkillsSeparated, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblseparatedByComma, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(skillField, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(textFieldFirstName)
											.addComponent(textFieldLastName)
											.addComponent(textFieldPassword, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))))
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblInstruction)
							.addContainerGap(196, Short.MAX_VALUE))))
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
						.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(skillField, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblYourSkillsSeparated)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblseparatedByComma)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFeedback)
						.addComponent(btnSubmit)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	
	
		// User tried to submit, so tell the controller to proceed.
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				lblFeedback.setText("Profile Updated.");//TODO this just blindly goes off. Wanna add some checks at some point.
				EditAccountFrame.this.setVisible(false);
	} //TODO I think this would be more efficient if I SPAWN CreateAccountFrame here instead of earlier
		});
		
		// Close dialog without changes
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = true;
				EditAccountFrame.this.setVisible(false);
				cancel = true;
			}
		});
		
	}
}

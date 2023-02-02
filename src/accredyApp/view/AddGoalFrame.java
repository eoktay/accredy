package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGoalFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldGoalDescription;
	private JTextField textFieldGoalKeywords;
	
	boolean updated = false;
	
	public JTextField getGoalDescriptionField() { return textFieldGoalDescription; }
	public JTextField getGoalKeywordsField() {return textFieldGoalKeywords;}
	public boolean wasUpdated() { return updated; }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoalFrame frame = new AddGoalFrame();
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
	public AddGoalFrame() {
		setTitle("Accredy - Create New Goal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddGoal = new JLabel("Pleaase add your goal below:");
		
		JLabel lblGoalDescription = new JLabel("Goal Description:");
		
		JLabel lblGoalKeywords = new JLabel("Goal Keywords:");
		
		textFieldGoalDescription = new JTextField();
		textFieldGoalDescription.setColumns(10);
		
		textFieldGoalKeywords = new JTextField();
		textFieldGoalKeywords.setColumns(10);
		
		JButton btnSubmitGoal = new JButton("Submit");
		btnSubmitGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				AddGoalFrame.this.setVisible(false);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = false;
				AddGoalFrame.this.setVisible(false);
				AddGoalFrame.this.dispose(); //This might only belong in controllers, but it's worth a try.
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAddGoal)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblGoalDescription)
										.addComponent(lblGoalKeywords))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldGoalKeywords)
										.addComponent(textFieldGoalDescription, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(106)
							.addComponent(btnSubmitGoal)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(lblAddGoal)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGoalDescription)
						.addComponent(textFieldGoalDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGoalKeywords)
						.addComponent(textFieldGoalKeywords, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmitGoal)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}

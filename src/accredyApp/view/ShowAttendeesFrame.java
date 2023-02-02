package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowAttendeesFrame extends JDialog {

	private JPanel contentPane;
	private JButton btnShowAttendees;
	private JButton btnMainMenu;
	private JList resourceList;
	
	boolean updated = false;
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
//	public void setFeedback(String feedback) { lblFeedback.setText(feedback);}
	public JButton getShowAttendeesBtn() { return btnShowAttendees; }
	public JButton getMainMenuBtn() { return btnMainMenu; }
	public JList getResourceList() { return resourceList; }
	public boolean wasUpdated() { return updated; }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAttendeesFrame frame = new ShowAttendeesFrame();
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
	public ShowAttendeesFrame() {
		setTitle("Accredy - View Resource Attendees");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblInstructions = new JLabel("Please select from the list of resources below:");
		
		JButton btnShowAttendees = new JButton("Show Attendees");
		btnShowAttendees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				ShowAttendeesFrame.this.setVisible(false);
			}
		});
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				ShowAttendeesFrame.this.setVisible(false);
			}
		});

				
		resourceList = new JList();
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(resourceList, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnShowAttendees)
							.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
							.addComponent(btnMainMenu))
						.addComponent(lblInstructions))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblInstructions)
					.addGap(18)
					.addComponent(resourceList, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowAttendees)
						.addComponent(btnMainMenu))
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

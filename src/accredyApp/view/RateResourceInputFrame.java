package accredyApp.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RateResourceInputFrame extends JDialog {

	private JPanel contentPane;
	private JComboBox ratingBox;
	private JTextArea txtComment;
	private JFormattedTextField txtCompletedDate;
	private JLabel lblResourceName;

	boolean updated = false;
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
	public JComboBox getRatingBox() { return ratingBox; }
	public JTextArea getTxtComment() { return txtComment; }
	public JFormattedTextField getTxtCompletedDate() { return txtCompletedDate; }
	public JLabel getLblResourceName() { return lblResourceName; }
	public boolean wasUpdated() { return updated; }

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RateResourceInputFrame frame = new RateResourceInputFrame();
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
	public RateResourceInputFrame() {
		setTitle("Accredy - Rate Resource");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblInstruction = new JLabel("Please rate the resource below:");
		
		JLabel lblRating = new JLabel("Rating:");
		
		JLabel lblCompletedOn = new JLabel("Completed on:");
		
		JLabel lblComments = new JLabel("Comments:");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				RateResourceInputFrame.this.setVisible(false);
			}
		});

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				RateResourceInputFrame.this.setVisible(false);
			}
		});


		ratingBox = new JComboBox();
		ratingBox.setModel(new DefaultComboBoxModel(new String[] {"-Select one-", "5", "4", "3", "2", "1"}));
		ratingBox.setMaximumRowCount(6);
		
		SimpleDateFormat dateformat = new SimpleDateFormat("mm/dd/yyyy");
		txtCompletedDate = new JFormattedTextField(dateformat);
		
		txtComment = new JTextArea();
		
		JLabel format = new JLabel("MM/DD/YYYY");
		format.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblResourceName = new JLabel("Resource Name:");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
							.addComponent(btnMainMenu))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCompletedOn)
										.addComponent(lblRating)
										.addComponent(lblComments))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(ratingBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtComment, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addComponent(txtCompletedDate, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(format))))
								.addComponent(lblInstruction)
								.addComponent(lblResourceName, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstruction)
					.addGap(4)
					.addComponent(lblResourceName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRating)
						.addComponent(ratingBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompletedOn)
						.addComponent(txtCompletedDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(format))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComments)
						.addComponent(txtComment, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnMainMenu))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}

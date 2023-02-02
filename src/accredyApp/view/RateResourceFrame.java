package accredyApp.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RateResourceFrame extends JDialog {

	private JPanel contentPane;
	private JList resourceList;
	
	
	boolean updated = false;
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
	public JList getResourceList() { return resourceList; }
	public boolean wasUpdated() { return updated; }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RateResourceFrame frame = new RateResourceFrame();
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
	public RateResourceFrame() {
		setTitle("Accredy - Rate Resource");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPleaseSelectFrom = new JLabel("Please select from the list of resources below:");
		
		resourceList = new JList();
		
		JButton btnRate = new JButton("Rate");
		btnRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				RateResourceFrame.this.setVisible(false);
			}
		});
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				RateResourceFrame.this.setVisible(false);
			}
		});
				
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPleaseSelectFrom)
							.addContainerGap(130, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRate)
							.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
							.addComponent(btnMainMenu)
							.addGap(17))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(resourceList, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblPleaseSelectFrom)
					.addGap(18)
					.addComponent(resourceList, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRate)
						.addComponent(btnMainMenu))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}

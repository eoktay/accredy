package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accredyApp.controller.SaveSearchResourceController;
import accredyApp.controller.SearchResourceController;
import accredyApp.controller.SearchSearchResourceController;
import accredyApp.model.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

public class SearchResourcesFrame extends JDialog {

	private JPanel contentPane;
	private JList listGoalList;
	private JList listResourceList;
	private JLabel feedbacklabel;

	boolean updated = false;
	
	public JList getGoalList() {return listGoalList;}
	public JList getResourceList() {return listResourceList;}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResourcesFrame frame = new SearchResourcesFrame(null);
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
	public SearchResourcesFrame(Model m) {
		setTitle("Accredy - Search Resources"); //btw, I keep passing around references to the model so anything can access it if it needs, even if it's 5 layers-of-frames-deep.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		feedbacklabel = new JLabel("");

		
		JLabel lblPleaseSelectGoals = new JLabel("Please select goals to see resources:");
		lblPleaseSelectGoals.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchSearchResourceController(m, SearchResourcesFrame.this, listGoalList.getSelectedIndex()).process();
			}
		});

		
		JLabel lblPleaseSaveResources = new JLabel("Please save resources:");
		lblPleaseSaveResources.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveSearchResourceController(m, SearchResourcesFrame.this, listResourceList.getSelectedIndices()).process();
				feedbacklabel.setText("Updated your resources successfully.");	
			}
		});

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				SearchResourcesFrame.this.setVisible(false);
			}
		});
		
		listGoalList = new JList();
		
		listResourceList = new JList();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(233)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMainMenu, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(feedbacklabel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPleaseSaveResources)
							.addPreferredGap(ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(listResourceList, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addComponent(listGoalList, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPleaseSelectGoals, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblPleaseSelectGoals, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listGoalList, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPleaseSaveResources)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(listResourceList, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnMainMenu)
							.addComponent(btnSave))
						.addComponent(feedbacklabel))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}

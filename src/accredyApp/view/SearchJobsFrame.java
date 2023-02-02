package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accredyApp.controller.SaveSearchJobController;
import accredyApp.controller.SaveSearchResourceController;
import accredyApp.controller.SearchSearchJobController;
import accredyApp.controller.SearchSearchResourceController;
import accredyApp.model.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class SearchJobsFrame extends JDialog {

	private JPanel contentPane;
	private JList listGoals;
	private JList listJobs;
	private JLabel feedbacklabel;
	
	boolean updated = false;
	
	public JList getGoalList() {return listGoals;}
	public JList getJobList() {return listJobs;}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchJobsFrame frame = new SearchJobsFrame(new Model());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param m 
	 */
	public SearchJobsFrame(Model m) {
		setTitle("Accredy - Search Jobs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		feedbacklabel = new JLabel("");

		listGoals = new JList();
		
		listJobs = new JList();
		
		JLabel lblPleaseSelectGoals = new JLabel("Please select goals to see related jobs:");
		lblPleaseSelectGoals.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JLabel lblPleaseSelectJobs = new JLabel("Please select jobs:");
		lblPleaseSelectJobs.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchSearchJobController(m, SearchJobsFrame.this, listGoals.getSelectedIndex()).process();
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveSearchJobController(m, SearchJobsFrame.this, listJobs.getSelectedIndices()).process();
				feedbacklabel.setText("Updated your jobs successfully.");
			}
		});

		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updated = false;
				SearchJobsFrame.this.setVisible(false);
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPleaseSelectJobs)
									.addGap(57)
									.addComponent(btnSearch))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPleaseSelectGoals)
											.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(feedbacklabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
											.addGap(49)
											.addComponent(btnSave)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnMainMenu)
											.addGap(25)))
									.addGap(83))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(listJobs, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(listGoals, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblPleaseSelectGoals)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(listGoals, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPleaseSelectJobs)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(listJobs, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(feedbacklabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnMainMenu))))
					.addGap(174))
		);
		contentPane.setLayout(gl_contentPane);
	}

}

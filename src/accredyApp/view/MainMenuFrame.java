package accredyApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import accredyApp.controller.*;
import accredyApp.model.Model;
import util.Util;


public class MainMenuFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnLogOut;
	private JButton btnCreateGoal;
	private JButton btnViewGoals;
	private JButton btnSearchJobs;
	private JButton btnSearchResources;
	private JButton btnViewResources;
	private JButton btnViewJobs;
	private JButton btnEditAccount;
	private JButton btnShowAttendees;
	private JButton btnRateResources;
	private JLabel lblFeedback;
	private JLabel lblUserName;

	boolean updated = false;
	
	
	//ACCESS (Add get/set methods here for everything a controller needs to access)//
//	public void setFeedback(String feedback) { lblFeedback.setText(feedback);}
	
	public JButton getLogOutButton() { return btnLogOut; }
	public JButton getCreateGoalButton() { return btnCreateGoal; }
	public JButton getViewGoalsButton() { return btnViewGoals; }
	public JButton getSearchJobsButton() { return btnSearchJobs; }
	public JButton getSearchResourcesButton() { return btnSearchResources; }
	public JButton getViewResourcesButton() { return btnViewResources; }
	public JButton getViewJobsButton() { return btnViewJobs; }
	public JButton getEditAccountButton() { return btnEditAccount; }
	public JButton getShowAttendeesButton() { return btnShowAttendees; }
	public JButton getRateResourcesButton() { return btnRateResources; }
	public JLabel getUserNameLabeln() { return lblUserName; }
	public boolean wasUpdated() { return updated; }
//	public void setVisible(boolean status) {this.setVisible(status);} //true if we need user to retry input. False if we need it to suddenly disappear.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame(new Model());
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
	public MainMenuFrame(Model m) {
		setTitle("Accredy - Main Menu");
		int btnSize = 140; //I am STRUGGLING with resizing all 50 buttons at once. this should make consistency easier.
		int btnMin = 140; //for now I am going to just force buttons to be a single size, though we could tweak that later.
		int btnMax = 140;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnEditAccount = new JButton("Edit Account");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditAccountController(m, MainMenuFrame.this).process();
				lblUserName.setText("Welcome, " + Model.profile.getName());
				MainMenuFrame.this.repaint();
			}
		});

		
		btnShowAttendees = new JButton("Show Attendees");
		btnShowAttendees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowAttendeesController(m, MainMenuFrame.this).process();
//				ShowAttendeesFrame showAttendeesFrame = new ShowAttendeesFrame();
//				showAttendeesFrame.setVisible(true);
			}
		});
		
		btnRateResources = new JButton("Rate Resources");
		btnRateResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RateResourceController(m, MainMenuFrame.this).process();
//				RateResourceFrame rateResourceFrame = new RateResourceFrame();
//				rateResourceFrame.setVisible(true);
			}
		});
		
		lblUserName = new JLabel("Welcome, " + Model.profile.getName());
		
		btnLogOut = new JButton("Log Out");
		// Close dialog without changes
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame.this.setVisible(false);
				LogInFrame loginFrame = new LogInFrame(m);
				Util.centerFrame(loginFrame);
				loginFrame.setVisible(true);
			}
		});

		
		btnViewGoals = new JButton("View Goals");
		btnViewGoals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowAllGoalsController(m, MainMenuFrame.this).process();
			}
		});
		
		btnSearchJobs = new JButton("Search Jobs");
		btnSearchJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchJobController(m, MainMenuFrame.this).process();
			}
		});
		
		btnSearchResources = new JButton("Search Resources");
		btnSearchResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchResourceController(m, MainMenuFrame.this).process();
			}
		});
		
		btnViewResources = new JButton("View Resources");
		btnViewResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowResourcesController(m, MainMenuFrame.this).process();
			}
		});
		
		btnViewJobs = new JButton("View Jobs");
		btnViewJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowJobsController(m, MainMenuFrame.this).process();
			}
		});
		
		lblFeedback = new JLabel("");
		
		JButton btnCreateGoal = new JButton("Create Goal");
		btnCreateGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AddGoalFrame addGoalFrame = new AddGoalFrame();
//				addGoalFrame.setVisible(true);
				new AddGoalController(m, MainMenuFrame.this).process();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUserName)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblFeedback, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnViewResources, 140, 140, 140)
										.addComponent(btnShowAttendees, 140, 140, 140)
										.addComponent(btnRateResources, 140, 140, 140)
										.addComponent(btnSearchResources, 140, 140, 140))
									.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnViewGoals, 140, 140, 140)
										.addComponent(btnCreateGoal, 140, 140, 140)))
								.addGap(122)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnLogOut, 140, 140, 140)
									.addComponent(btnEditAccount, 140, 140, 140)
									.addComponent(btnSearchJobs, 140, 140, 140)
									.addComponent(btnViewJobs, 140, 140, 140)))))
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblUserName)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(29)
									.addComponent(btnViewGoals))
								.addComponent(btnCreateGoal))
							.addGap(31)
							.addComponent(btnSearchResources)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewResources)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRateResources)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnShowAttendees))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnEditAccount)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnLogOut))
							.addGap(31)
							.addComponent(btnSearchJobs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewJobs)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFeedback)
					.addGap(13))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

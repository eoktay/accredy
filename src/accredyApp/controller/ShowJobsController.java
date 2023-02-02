package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class ShowJobsController {
	Model model;
	MainMenuFrame frame;
	
	public ShowJobsController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		JobDAO jdao = new JobDAO();
		ShowAllJobsFrame sajf = new ShowAllJobsFrame(model);
		sajf.setModal(true);
		Util.centerFrame(sajf);

		try {
			List<Job> jlist = jdao.getSavedJobs();
			int numJobs = jlist.size();
			String[] dispjlist = new String[numJobs]; //this is the "list of jobs" that will appear in the window
			for (int i = 0; i < numJobs; i++) {
				dispjlist[i] = jlist.get(i).toString();
			}

			sajf.getJobList().setListData(dispjlist);

			sajf.setVisible(true);

			
		} catch (Exception e) {
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}
		
		sajf.dispose();

		// refresh
		frame.repaint();
	}
	

}

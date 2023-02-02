package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class DeleteJobController {
	Model model;
	ShowAllJobsFrame frame;
	int jobId;

	public DeleteJobController(Model m, ShowAllJobsFrame frame, int jobId) {
		this.model = m;
		this.frame = frame;
		this.jobId = jobId;
	}

	public void process() {
		
		JobDAO jdao = new JobDAO();
		
		try { 
			jdao.deleteJob(model.getSavedJobs().get(jobId).getJID()); 
			model.setSavedJobs(jdao.getSavedJobs());
			
			List<Job> glist = model.getSavedJobs(); 
			int numJobs = glist.size();
			String[] dispjlist = new String[numJobs]; //this is the "list of jobs" that will appear in the window
			for (int i = 0; i < numJobs; i++) {
				dispjlist[i] = glist.get(i).toString();
			}

			frame.getJobList().setListData(dispjlist);
			frame.setVisible(true);
			
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}
		
		// refresh
		frame.repaint();
				
	}

}

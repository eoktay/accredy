package accredyApp.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.MainMenuFrame;
import accredyApp.view.RateResourceFrame;
import accredyApp.view.RateResourceInputFrame;
import accredyApp.view.SearchJobsFrame;
import accredyApp.view.SearchResourcesFrame;
//import accredyApp.view.*;
import util.Util;

public class SaveSearchJobController {

	Model model;
	SearchJobsFrame frame;
	int goalidx;
	int[] jobIndices;

	public SaveSearchJobController(Model m, SearchJobsFrame searchJobsFrame, int[] selectedIndices) {
		//goal idx refers to the index of the selected goal, so we can pull that out of the model's SavedGoals
		this.model = m;
		this.frame = searchJobsFrame;
		this.jobIndices = selectedIndices;
	}

	public void process() {

		JobDAO jdao = new JobDAO();
		
		int numJobs = jobIndices.length;
		List<Job> joblist = new ArrayList<>();
		for (int idx : jobIndices) {
			Job newjob = model.getSavedJobs().get(idx);
			joblist.add(newjob);
		}
		
		try {
			jdao.saveJob(joblist);
			
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		// refresh
		frame.repaint();
	}
}

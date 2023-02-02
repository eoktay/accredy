package accredyApp.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

public class SearchSearchJobController {

	Model model;
	SearchJobsFrame frame;
	int goalidx;

	//haha bad name I know... SearchResourceController was named after the general use case, then I realized I needed a specific sub-controller that would activate when someone hit the "search" button on the SearchResourceFrame. oh well.
	public SearchSearchJobController(Model m, SearchJobsFrame searchJobsFrame, int goalidx) {
		//goal idx refers to the index of the selected goal, so we can pull that out of the model's SavedGoals
		this.model = m;
		this.frame = searchJobsFrame;
		this.goalidx = goalidx;
	}

	public void process() {

		JobDAO jdao = new JobDAO();

		try {
			List<Job> jlist = jdao.getJobsByGoal(model.getSavedGoals().get(goalidx).getGID()); //This is the list of Resources returned from the database, based on the single input goal
			int numJobs = jlist.size();
			String[] disprlist = new String[numJobs]; //this is the "list of resources" that will appear in the window
			for (int i = 0; i < numJobs; i++) {
				disprlist[i] = jlist.get(i).toString();
			}

			frame.getJobList().setListData(disprlist);
			model.setSavedJobs(jlist);

		
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		// refresh
		frame.repaint();
	}
}

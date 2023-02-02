package accredyApp.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.MainMenuFrame;
import accredyApp.view.RateResourceFrame;
import accredyApp.view.RateResourceInputFrame;
import accredyApp.view.SearchResourcesFrame;
//import accredyApp.view.*;
import util.Util;

public class SearchSearchResourceController {

	Model model;
	SearchResourcesFrame frame;
	int goalidx;

	//haha bad name I know... SearchResourceController was named after the general use case, then I realized I needed a specific sub-controller that would activate when someone hit the "search" button on the SearchResourceFrame. oh well.
	public SearchSearchResourceController(Model m, SearchResourcesFrame frame, int goalidx) {
		//goal idx refers to the index of the selected goal, so we can pull that out of the model's SavedGoals
		this.model = m;
		this.frame = frame;
		this.goalidx = goalidx;
	}

	public void process() {

		ResourceDAO rdao = new ResourceDAO();

		try {
			List<Resource> rlist = rdao.getResouresByGoal(model.getSavedGoals().get(goalidx).getGID()); //This is the list of Resources returned from the database, based on the single input goal
			int numResources = rlist.size();
			String[] disprlist = new String[numResources]; //this is the "list of resources" that will appear in the window
			for (int i = 0; i < numResources; i++) {
				disprlist[i] = rlist.get(i).toString();
			}

			frame.getResourceList().setListData(disprlist);
			model.setSavedResources(rlist);

		
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		// refresh
		frame.repaint();
	}
}

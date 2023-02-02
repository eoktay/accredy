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

public class SearchJobController {

	Model model;
	MainMenuFrame frame;

	public SearchJobController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}

	public void process() {

		GoalDAO gdao = new GoalDAO();
		SearchJobsFrame sjf = new SearchJobsFrame(model);
		sjf.setModal(true);
		Util.centerFrame(sjf);

		try {
		List<Goal> glist = gdao.getAllGoals(); //This is the list of goals returned from the database
		int numgoals = glist.size();
		String[] dispglist = new String[numgoals]; //this is the "list of resources" that will appear in the window
		for (int i = 0; i < numgoals; i++) {
			dispglist[i] = glist.get(i).toString(); //TODO probably want to change what is printed for each goal
		}

		sjf.getGoalList().setListData(dispglist);
		
		model.setSavedGoals(glist);

		sjf.setVisible(true);
		
		
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		sjf.dispose(); // otherwise thread lingers

		// refresh
		frame.repaint();
	}
}

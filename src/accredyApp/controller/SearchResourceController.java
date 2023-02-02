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

public class SearchResourceController {

	Model model;
	MainMenuFrame frame;

	public SearchResourceController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}

	public void process() {

		// Before srf Dialog
		GoalDAO gdao = new GoalDAO();
		SearchResourcesFrame srf = new SearchResourcesFrame(model);
		srf.setModal(true);
		Util.centerFrame(srf);

		try {
		List<Goal> glist = gdao.getAllGoals(); //This is the list of goals returned from the database
		int numgoals = glist.size();
		String[] dispglist = new String[numgoals]; //this is the "list of resources" that will appear in the window
		for (int i = 0; i < numgoals; i++) {
			dispglist[i] = glist.get(i).toString(); //TODO probably want to change what is printed for each resource
		}

		srf.getGoalList().setListData(dispglist);
		
		model.setSavedGoals(glist);

		srf.setVisible(true);
		
		
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		srf.dispose(); // otherwise thread lingers

		// refresh
		frame.repaint();
	}
}

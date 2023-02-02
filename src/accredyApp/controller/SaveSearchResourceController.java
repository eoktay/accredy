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
import accredyApp.view.SearchResourcesFrame;
//import accredyApp.view.*;
import util.Util;

public class SaveSearchResourceController {

	Model model;
	SearchResourcesFrame frame;
	int goalidx;
	int[] resourceIndices;

	public SaveSearchResourceController(Model m, SearchResourcesFrame frame, int[] selectedIndices) {
		//goal idx refers to the index of the selected goal, so we can pull that out of the model's SavedGoals
		this.model = m;
		this.frame = frame;
		this.resourceIndices = selectedIndices;
	}

	public void process() {

		ResourceDAO rdao = new ResourceDAO();
		
		int numResources = resourceIndices.length;
		List<Resource> reclist = new ArrayList<>();
		for (int idx : resourceIndices) {
			Resource newrec = model.getSavedResources().get(idx);
			reclist.add(newrec);
		}
		
		try {
			rdao.saveResource(reclist);
			
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		// refresh
		frame.repaint();
	}
}

package accredyApp.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.MainMenuFrame;
import accredyApp.view.RateResourceFrame;
import accredyApp.view.RateResourceInputFrame;
//import accredyApp.view.*;
import util.Util;

public class RateResourceController {

	Model model;
	MainMenuFrame frame;

	public RateResourceController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}

	public void process() {

		// Before rrf Dialog
		ResourceDAO rdao = new ResourceDAO(); // rearrange this order if you have issues
		RateResourceFrame rrf = new RateResourceFrame();
		RateResourceInputFrame rrif = new RateResourceInputFrame();
		rrf.setModal(true);
		Util.centerFrame(rrf);

		try {
		List<Resource> rlist = rdao.getSavedResources(); //This is the list of Resources returned from the database
		int numResources = rlist.size();
		String[] disprlist = new String[numResources]; //this is the "list of resources" that will appear in the window
		for (int i = 0; i < numResources; i++) {
			disprlist[i] = rlist.get(i).toString();
		}

		rrf.getResourceList().setListData(disprlist);
		
		rrf.setVisible(true);


			// only get here if user hit submit (instead of cancel).
			if (rrf.wasUpdated()) {

				// After Dialog

				Resource selected = rlist.get(rrf.getResourceList().getSelectedIndex()); //TODO we COULD allow multi-selection if you change this to getSelectedIndices. It matches the use case better, but I think the processing would have to happen in Java since the database only gives us attendees of one resource, so there could be duplicates. IF we want that, I think this line is the first step.

				rrif.setModal(true);
				Util.centerFrame(rrif);
//				rrif.getLblResourceName().setText("Resource Name: " + selected.toString());
//				rrif.getLblResourceName().setText("Rate!");
				rrif.setVisible(true);

				// Get here when dialog closed (for now...)
				if (rrif.wasUpdated()) {

					// TODO I DO 0 error-checking, and that needs to change.
					String rating = (String) rrif.getRatingBox().getSelectedItem();
					String dateString = rrif.getTxtCompletedDate().getText();
					String comment = rrif.getTxtComment().getText();

					SimpleDateFormat dateformat = new SimpleDateFormat("mm/dd/yyyy");
					Date completedDate = new Date((dateformat.parse(dateString)).getTime());
					// Literally, the line above converts a "mm/dd/yyyy" string to a Java.util.date,
					// to an epoch long, to an SQL Date :P

					rdao.addRating(selected, Integer.parseInt(rating), comment, completedDate);
					// TODO I should affirm to the user if the rating was submitted.
					System.out.println("Rating successfully submitted.");
				}
			}

		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		rrf.dispose(); // otherwise thread lingers
		rrif.dispose(); // otherwise thread lingers

		// refresh
		frame.repaint();
	}
}

package accredyApp.controller;


import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;

public class ShowAttendeesController {
	
	Model model;
	MainMenuFrame frame;
	
	public ShowAttendeesController (Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		
		//Before SAF Dialog
		ResourceDAO rdao = new ResourceDAO(); //rearrange this order if you have issues
		ShowAttendeesFrame saf = new ShowAttendeesFrame();
		ShowAttendeeListFrame salf = new ShowAttendeeListFrame();
		saf.setModal(true);
		Util.centerFrame(saf);

		try {
		List<Resource> rlist = rdao.getSavedResources(); //This is the list of Resources returned from the database
		int numResources = rlist.size();
		String[] disprlist = new String[numResources]; //this is the "list of resources" that will appear in the window
		for (int i = 0; i < numResources; i++) {
			disprlist[i] = rlist.get(i).toString();
		}

		saf.getResourceList().setListData(disprlist);
		
		saf.setVisible(true);
		
		// only get here if user hit submit (instead of cancel).
		if (saf.wasUpdated()) {
			
			//After Dialog
			
			Resource selected = rlist.get(saf.getResourceList().getSelectedIndex()); //TODO we COULD allow multi-selection if you change this to getSelectedIndices. It matches the use case better, but I think the processing would have to happen in Java since the database only gives us attendees of one resource, so there could be duplicates. IF we want that, I think this line is the first step.
			
			List<UserInfo> attendees = rdao.getAttendees(selected);
			System.out.println(attendees.size() + " attendees");
			System.out.println("show attendees clicked");
			salf.setModal(true);
			Util.centerFrame(salf);
			String text = attendees.toString();
			System.out.println(text);
			
			salf.getTextArea().setText(text.substring(2, text.length() - 1));
			salf.setVisible(true);

		}
		
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}
		
		saf.dispose();  // otherwise thread lingers
		salf.dispose();  // otherwise thread lingers

		
		// refresh
		frame.repaint();
	}
}

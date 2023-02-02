package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class ShowResourcesController {
	Model model;
	MainMenuFrame frame;
	
	public ShowResourcesController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		ResourceDAO rdao = new ResourceDAO();
		ShowResourcesFrame srf = new ShowResourcesFrame(model);
		srf.setModal(true); //AFAIK this is the only way to freeze the controller so the window stays open as long as we want it to.
		Util.centerFrame(srf);

		try {
			List<Resource> rlist = rdao.getSavedResources();
			int numResources = rlist.size();
			String[] disprlist = new String[numResources]; //this is the "list of resources" that will appear in the window
			for (int i = 0; i < numResources; i++) {
				disprlist[i] = rlist.get(i).toString();
			}

			srf.getSavedResourcesList().setListData(disprlist);
			model.setSavedResources(rdao.getSavedResources());
			srf.setVisible(true);

			
		} catch (Exception e) {
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}
		
		srf.dispose();

		// refresh
		frame.repaint();
	}
	

}

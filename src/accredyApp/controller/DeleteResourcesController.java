package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class DeleteResourcesController {
	Model model;
	ShowResourcesFrame frame;
	int resourceId;

	public DeleteResourcesController(Model m, ShowResourcesFrame frame, int resourceId) {
		this.model = m;
		this.frame = frame;
		this.resourceId = resourceId;
	}

	public void process() {
		
		ResourceDAO rdao = new ResourceDAO();
		
		try { 
			rdao.deleteResource(model.getSavedResources().get(resourceId).getRID()); //This name is confusing, it looks like the resource primary key but it's actually the index of the resource to delete in the list.
			model.setSavedResources(rdao.getSavedResources());
			// We should probably just call the ShowResourcesController directly to avoid code duplication; just wanted to stay
			// on the safe side code-architecture wise until I feel safe making inter-Controller calls
//			List<Resource> rlist = rdao.getSavedResources(); //we should minimize DAO calls because it's really expensive (network connection, query, nondeterministic, etc), so since we store the getSavedResources results to the model's SavedResources, we could set rlist to that instead.
			List<Resource> rlist = model.getSavedResources();
			int numResources = rlist.size();
			String[] disprlist = new String[numResources]; //this is the "list of resources" that will appear in the window
			for (int i = 0; i < numResources; i++) {
				disprlist[i] = rlist.get(i).toString();
			}

			frame.getSavedResourcesList().setListData(disprlist);
//			model.setSavedResources(rdao.getSavedResources()); //again, no need for the extra DAO call, as nothing's changed since we called getSavedResources() ~10 lines ago
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

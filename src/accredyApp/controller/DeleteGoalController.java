package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class DeleteGoalController {
	Model model;
	ShowAllGoalsFrame frame;
	int goalId;

	public DeleteGoalController(Model m, ShowAllGoalsFrame frame, int goalId) {
		this.model = m;
		this.frame = frame;
		this.goalId = goalId;
	}

	public void process() {
		
		GoalDAO gdao = new GoalDAO();
		
		try { 
			gdao.deleteGoal(model.getSavedGoals().get(goalId).getGID()); 
			model.setSavedGoals(gdao.getAllGoals());
			
			//List<Goal> rlist = gdao.getSavedResources(); //yikes, we don't want to send this query to the database twice...
			List<Goal> glist = model.getSavedGoals(); 
			int numGoals = glist.size();
			String[] dispglist = new String[numGoals];
			for (int i = 0; i < numGoals; i++) {
				dispglist[i] = glist.get(i).toString();
			}

			frame.getGoalListField().setListData(dispglist);
//			model.setSavedGoals(gdao.getAllGoals()); //I don't think anything's changed since the last time we called dao.getAllGoals. Plus, it's an expensive function so we should keep DAO calls to a minimum.
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

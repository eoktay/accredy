package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class ShowAllGoalsController {
	Model model;
	MainMenuFrame frame;
	
	public ShowAllGoalsController(Model m,  MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		GoalDAO gdao = new GoalDAO();
		ShowAllGoalsFrame sagf = new ShowAllGoalsFrame(model);
		sagf.setModal(true);
		Util.centerFrame(sagf);

		
		try {
			
			List<Goal> allGoals = gdao.getAllGoals();
			int numgoals = allGoals.size();
			String[] dispglist = new String[numgoals];
			for (int i = 0; i < numgoals; i++)
			{
				dispglist[i] = allGoals.get(i).toString();
			}
			sagf.getGoalListField().setListData(dispglist);
			model.setSavedGoals(allGoals);
			sagf.setVisible(true);
			
			
			
		} catch (Exception e) {
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}
		
		sagf.dispose();

		// refresh
		frame.repaint();
	}
	

}

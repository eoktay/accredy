package accredyApp.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;


public class AddGoalController {
	Model model;
	MainMenuFrame frame;
	
	public AddGoalController(Model m,  MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		GoalDAO gdao = new GoalDAO();
		SkillDAO skdao = new SkillDAO();
		AddGoalFrame agf = new AddGoalFrame();
		agf.setModal(true);
		Util.centerFrame(agf);
		agf.setVisible(true);
		
		// Convert the comma separated keyword to list, untested in terms of the regex for the split function
		if (agf.wasUpdated())
		{
			try {
				skdao.addGoalKeywords(Arrays.asList(agf.getGoalKeywordsField().getText().toString().split("\\s*,\\s*")), 
						gdao.addGoal(agf.getGoalDescriptionField().getText().toString()));
			
			} catch (Exception e) {
				System.out.println("Encountered unexpected DB access exception.");
				e.printStackTrace();
			}
			
		}
		
		
		agf.dispose();

		// refresh
		frame.repaint();

	}
	
	

}



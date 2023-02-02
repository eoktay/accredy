package accredyApp.controller;


import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.CreateAccountFrame;
import accredyApp.view.LogInFrame;
//import accredyApp.view.*; //idk why but imports for the CreateAccountFrame are suddenly messed up. Hope this helps.
import util.Util;

public class CreateAccountController {
	
	Model model;
	LogInFrame frame;
	
	public CreateAccountController (Model m, LogInFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		
		//Before Dialog
		CreateAccountFrame caf = new CreateAccountFrame();
		caf.setModal(true);
		Util.centerFrame(caf);
		caf.setFeedback("");
		ProfileDAO pdao = new ProfileDAO();
		SkillDAO sdao = new SkillDAO();
		caf.setVisible(true);
		
		while (caf.wasUpdated()) {
			
			//After Dialog
			
			String uname = caf.getUserNameField().getText();
			String pass = caf.getPasswordField().getText();
			String fname = caf.getFirstNameField().getText();
			String lname = caf.getLastNameField().getText();
			String skills = caf.getSkillField().getText();
			
			if (uname.equals("") || pass.equals("") || fname.equals("") || lname.equals("")) { //didn't enter every field
				caf.setFeedback("Please enter all fields.");
				System.out.println("Please enter all fields.");
				caf.setVisible(true);
				continue;
			}
			
			
			Model.profile.setFirstName(fname);
			Model.profile.setLastName(lname);
			Model.profile.setUserID(uname);
			try {
				if (pass.length() < 5) { //password is too short
					caf.setFeedback("Password must be at least 5 characters long.");
					System.out.println("Password must be at least 5 characters long.");
					caf.setVisible(true);
					continue;
				}
				else if (!pdao.addUser(pass)) { // userID already present in database
					caf.setFeedback("Username " + uname + " is already in use.");
					System.out.println("Username " + uname + " is already in use.");
					caf.setVisible(true);
					continue;
				}
				else { //new account created.
					caf.setVisible(false);
//					frame.setFeedback("Account created successfully."); //I don't know why, but I get a null pointer exception any time I try to access the feedback label of any frame that calls a controller.
					System.out.println("Account created successfully.");
					
					//Now we want to add the skills to the user.
					List<String> skillList = Arrays.asList(skills.split(",[ ]*")); //separate the list by comma
					sdao.addUserKeywords(skillList);
					System.out.println("Keywords added successfully.");
					
					
				}
			} catch (Exception e) {
//				caf.setFeedback("Encountered unexpected DB access exception.");
				System.out.println("Encountered unexpected DB access exception.");
				e.printStackTrace();
			}
			
			break;
			
		}
		
		caf.dispose();  // otherwise thread lingers
		
		// refresh
		frame.repaint();
	}
}

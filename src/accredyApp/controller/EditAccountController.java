package accredyApp.controller;

import java.util.Arrays;
import java.util.List;

import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.EditAccountFrame;
import accredyApp.view.MainMenuFrame;
//import accredyApp.view.*;
import util.Util;

public class EditAccountController {

	Model model;
	MainMenuFrame frame;

	public EditAccountController(Model m, MainMenuFrame frame) {
		this.model = m;
		this.frame = frame;
	}

	public void process() {

		// Before Dialog
		ProfileDAO pdao = new ProfileDAO();
		SkillDAO sdao = new SkillDAO();
		EditAccountFrame eaf = new EditAccountFrame();
		eaf.getFirstNameField().setText(model.profile.getFirstName());
		eaf.getLastNameField().setText(model.profile.getLastName());
		eaf.getSkillField().setText("skills go here"); // TODO IMPLEMENT (Also, you cannot just get them from model, you
														// NEED to access database)

		try {

			eaf.setModal(true);
			Util.centerFrame(eaf);
//			caf.setFeedback("");
			List<String> oldSkillList = sdao.getAllUserKeywords();
			
			
			String oldSkillsString = oldSkillList.toString(); //toString makes a single string with all of the values that looks like "[a, b, c]"
			oldSkillsString = oldSkillsString.substring(1, oldSkillsString.length() - 1); //remove the first and last character (the [])
			eaf.getSkillField().setText(oldSkillsString); //put that into the skill field
			eaf.setVisible(true);

			// only get here when user properly completes dialog
			// I CHANGED THIS FROM IF TO WHILE. IF IT BREAKS TRY CHANGING IT BACK TO IF, AND
			// REMOVING THE CONTINUE AND BREAKS.
			while (eaf.wasUpdated()) {

				// After Dialog
				//caf.setFeedback("You clicked Submit.");
				if (eaf.cancel) {
					eaf.cancel = false;
					break;
				}

				// Step 1: update name
				Model.profile.setFirstName(eaf.getFirstNameField().getText());
				Model.profile.setLastName(eaf.getLastNameField().getText());
				pdao.updateProfile();

				// Step 2: update skills
				// Now we want to add the skills to the user.
				String skills = eaf.getSkillField().getText();
				if (!skills.equals(oldSkillsString)) { //don't mess with it if the skills section hasn't changed
					List<String> skillList = Arrays.asList(skills.split(",[ ]*")); //split the string by ,
					sdao.deleteUserKeywords(); //delete all of the user's old skills
					sdao.addUserKeywords(skillList); //add all of the new skills
					System.out.println("Keywords added successfully.");
				}

				// Step 3: update password (if they changed it)
				String pass = eaf.getPasswordField().getText();
				if (pass.length() > 0) {// user wants to update password
					if (pass.length() > 5) { // new password long enough
						pdao.updatePassword(pass);
					} else { // new password too short
						System.out.println("Password must be at least 5 characters long");
					}
				}
				// TODO give feedback whether things were updated
				System.out.println("Finished updating.");
				eaf.setVisible(true);

				// this configuration might give a constant loop. If it does, try setting update
				// back to false once we get here.
//			break;

			}
		} catch (Exception e) {
//			caf.setFeedback("Encountered unexpected DB access exception.");
			System.out.println("Encountered unexpected DB access exception.");
			e.printStackTrace();
		}

		eaf.dispose(); // otherwise thread lingers

		// refresh
		frame.repaint();
	}
}

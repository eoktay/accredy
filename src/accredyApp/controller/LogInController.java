package accredyApp.controller;


import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;

public class LogInController {
	
	Model model;
	LogInFrame frame;
	
	public LogInController (Model m, LogInFrame frame) {
		this.model = m;
		this.frame = frame;
	}
	
	public void process() {
		
		//Before Dialog
		ProfileDAO pdao = new ProfileDAO(); //rearrange this order if you have issues
		
		if (frame.wasUpdated()) {
			
			//After Dialog
			
			String uname = frame.getUsernameField().getText();
			String pass = frame.getPasswordField().getText();
			
			if (uname.equals("") || pass.equals("")) { //didn't enter every field
				System.out.println("Please enter both username and password.");
				frame.setVisible(true);
//				continue;
			}			
			
			Model.profile.setUserID(uname);
			try {
				if (pdao.logIn(pass)) { // userID matches password
					frame.setVisible(false);
					MainMenuFrame mmf = new MainMenuFrame(model);
					Util.centerFrame(mmf);
					mmf.setVisible(true);
					frame.dispose();  // otherwise thread lingers
				}
				else { //username does not match password
					frame.setFeedback("Username and Password do not match.");
					System.out.println("Username and Password do not match.");
					frame.setVisible(true);
//					continue;
				}
			} catch (Exception e) {
//				frame.setFeedback("Encountered unexpected DB access exception.");
				System.out.println("Encountered unexpected DB access exception.");
				e.printStackTrace();
			}
			
//			originframe.updateMonthText();
//			break;
		}
		
		
		// refresh
		frame.repaint();
	}
}

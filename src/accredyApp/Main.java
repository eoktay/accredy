package accredyApp;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import accredyApp.controller.*;
import accredyApp.db.*;
import accredyApp.model.*;
import accredyApp.view.*;
import util.Util;

public class Main {
	public static void main(String[] args) {
		Model m = new Model();

		//if we need to do any initialization, it happens here

/*		//testing out the database connection, not sure if it should happen right here in the working version.
		UserDAO ud = new UserDAO();
		try {
			ud.testConnection();
		} catch (Exception e1) {
			System.out.println("Error.");
			e1.printStackTrace();
		}
    	//end db testing
	
//		DatabaseUtil connect
*/
		LogInFrame frame = new LogInFrame(m);
		
		frame.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				 if (new QuitController().confirm(frame)) {
					 //do any other "closing" processes, like saving things from memory if necessary?
					 frame.dispose();
             }
			}
		});
		
		Util.centerFrame(frame);
		frame.setVisible(true);
	}
}

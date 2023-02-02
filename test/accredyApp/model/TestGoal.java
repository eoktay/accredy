package accredyApp.model;

import java.util.Collections;

import accredyApp.model.Goal;
import junit.framework.TestCase;

public class TestGoal extends TestCase {

	public void testSkillList() {
		Goal testGoal = new Goal("desc", "id");
		System.out.println(testGoal.skillList());
		testGoal.addSkill("Java");
		testGoal.addSkill("C++");
		testGoal.addSkill("Programming");
		System.out.println(testGoal.skillList());
		testGoal.removeSkill("C++");
		System.out.println(testGoal.skillList());
	}	


}

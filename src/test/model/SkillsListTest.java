package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillsListTest {
    private SkillsList skillsList;
    private Skill skill1;
    private Skill skill2;
    private Skill skill3;
    private Employee employee;

    @BeforeEach
    public void runBefore() {
        skillsList = new SkillsList();
        skill1 = new Skill("Line 5 Gatorade");
        skill2 = new Skill("Line 5 Dole");
        skill3 = new Skill("Batching");
        employee = new Employee("test");
    }

    @Test
    public void testAddRemoveSkills() {
        skillsList.addSkill(skill1, employee);
        skillsList.addSkill(skill2, employee);

        assertEquals(skill1, skillsList.getSkill(0));
        assertEquals(skill2, skillsList.getSkill(1));

        skillsList.removeSkill(skill1, employee);

        assertEquals(skill2, skillsList.getSkill(0));

        skillsList.removeSkill(skill1, employee);

        assertEquals(skill2, skillsList.getSkill(0));
    }

    @Test
    public void testSkillsSize() {
        skillsList.addSkill(skill1, employee);
        skillsList.addSkill(skill2, employee);

        assertEquals(2,skillsList.skillsListSize());
    }

    @Test
    public void testQCList() {
        skillsList.qcSkillsList();

        assertEquals(10,skillsList.skillsListSize());
    }

    @Test
    public void testHasSkill() {
        skillsList.addSkill(skill1, employee);
        skillsList.addSkill(skill2, employee);

        assertTrue(skillsList.hasSkill(skill1));
        assertTrue(skillsList.hasSkill(skill2));
        assertFalse(skillsList.hasSkill(skill3));
    }

}

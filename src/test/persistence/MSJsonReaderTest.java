package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.MSJsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Templated from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class MSJsonReaderTest {
    protected void checkEmployee(String name, Boolean bl, String id, List<Skill> sk, Employee employee) {
        assertEquals(name, employee.getEmployeeName());
        assertEquals(bl, employee.hasPosition());
        assertEquals(id, employee.getEmployeeID());
        for (int i = 0; i < sk.size(); i++) {
            assertEquals(sk.get(i).getSkillName(), employee.getSkills().getSkill(i).getSkillName());
        }
    }

    protected void checkPosition(String name, Boolean bl, String skill, String employeeID,
                                 Position position, EmployeeRoster er) {
        Employee positionEmployee = null;
        String id;

        for (Employee e: er.getRoster()) {
            id = e.getEmployeeID();
            if (id.equals(employeeID)) {
                positionEmployee = e;
            }
        }

        assertEquals(name, position.getPositionName());
        assertEquals(bl, position.isFull());
        assertEquals(skill, position.getPositionSkill().getSkillName());
        assertEquals(positionEmployee, position.getPositionEmployee());
    }

    @Test
    void testReaderNonExistentFile() {
        MSJsonReader reader = new MSJsonReader("./data/invalid.json");
        Schedule sch;
        try {
            sch = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Templated from JsonSerializationDemo
    @Test
    void testReadEmployees() {
        MSJsonReader reader = new MSJsonReader("./data/jsonTestData.json");
        Schedule sch;
        EmployeeRoster er;
        SkillsList qcSkills;
        try {
            sch = reader.read();
            er = sch.getRoster();
            qcSkills = new SkillsList();
            qcSkills.qcSkillsList();
            List<Skill> mattSkills = new ArrayList<>();
            List<Skill> kaylaSkills = new ArrayList<>();

            mattSkills.add(qcSkills.getSkill(0));
            mattSkills.add(qcSkills.getSkill(1));
            mattSkills.add(qcSkills.getSkill(2));

            assertEquals(2, er.rosterSize());

            checkEmployee("Matt", true, "451cd678-e10f-4b73-9753-3835fb24eb8d", mattSkills,
                    er.getEmployee(0));
            checkEmployee("Kayla", false, "3143d5e2-9237-40b2-a2ea-cb4f52bf3503", kaylaSkills,
                    er.getEmployee(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // Templated from JsonSerializationDemo
    @Test
    void testReadPositions() {
        MSJsonReader reader = new MSJsonReader("./data/jsonTestData.json");
        Schedule sch;
        PositionList pl;
        EmployeeRoster er;
        try {
            sch = reader.read();
            pl = sch.getPositionList();
            er = sch.getRoster();

            assertEquals(2, pl.positionListSize());

            checkPosition("AM Rockstar Batcher", true, "Cold Fill CSD",
                    "451cd678-e10f-4b73-9753-3835fb24eb8d", pl.getPosition(0), er);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

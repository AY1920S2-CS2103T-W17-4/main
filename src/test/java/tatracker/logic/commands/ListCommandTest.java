package tatracker.logic.commands;

import static tatracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tatracker.logic.commands.CommandTestUtil.showStudentAtIndex;
import static tatracker.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static tatracker.testutil.TypicalStudents.getTypicalTaTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tatracker.model.Model;
import tatracker.model.ModelManager;
import tatracker.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaTracker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
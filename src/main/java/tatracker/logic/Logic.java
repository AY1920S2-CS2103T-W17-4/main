package tatracker.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;

import tatracker.commons.core.GuiSettings;
import tatracker.logic.commands.CommandResult;
import tatracker.logic.commands.exceptions.CommandException;
import tatracker.logic.parser.exceptions.ParseException;
import tatracker.model.ReadOnlyTaTracker;
import tatracker.model.module.Module;
import tatracker.model.session.Session;
import tatracker.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TaTracker.
     *
     * @see tatracker.model.Model#getTaTracker()
     */
    ReadOnlyTaTracker getTaTracker();

    /**
     * Returns the user prefs' ta-tracker file path.
     */
    Path getTaTrackerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ObservableList<Session> getFilteredSessionList();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();
}

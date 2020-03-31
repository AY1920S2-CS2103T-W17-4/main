package tatracker.model.statistic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.util.Pair;

import tatracker.model.ReadOnlyTaTracker;
import tatracker.model.module.Module;
import tatracker.model.rating.Rating;
import tatracker.model.session.SessionType;
import tatracker.model.session.UniqueSessionList;
import tatracker.model.student.Student;
import tatracker.model.student.UniqueStudentList;
import tatracker.ui.StatisticWindow;

public class Statistic {

    public static final String ALL_MODULES_STRING = "ALL MODULES";

    public final String targetModuleCode;
    public final int[] numHoursPerCategory;
    public final int[] studentRatingBinValues;
    public final Pair<String, Integer>[] worstStudents;

    public Statistic(ReadOnlyTaTracker taTracker, Module targetModule) {

        UniqueSessionList fList = new UniqueSessionList();
        UniqueStudentList sList = new UniqueStudentList();

        fList.setSessions(taTracker.getSessionList());
        sList.setStudents(taTracker.getStudentList());

        // If targetModule is not null, filter by target module.
        if (targetModule != null) {
            targetModuleCode = targetModule.getIdentifier();
            fList = fList.getSessionsOfModuleCode(targetModuleCode);
        } else {
            targetModuleCode = ALL_MODULES_STRING;
        }

        this.numHoursPerCategory = new int[SessionType.NUM_SESSION_TYPES];
        for (int i = 0; i < SessionType.NUM_SESSION_TYPES; ++i) {
            this.numHoursPerCategory[i] = fList.getSessionsOfType(SessionType.getSessionTypeById(i))
                    .getTotalDuration().toHoursPart();
        }

        this.studentRatingBinValues = new int[Rating.getRatingRange()];
        for (int i = Rating.MIN_RATING; i < Rating.MIN_RATING + Rating.getRatingRange(); ++i) {
            this.studentRatingBinValues[i - Rating.MIN_RATING] = sList.getStudentsOfRating(new Rating(i)).size();
        }

        // Setup worst students
        List<Student> students = new ArrayList(taTracker.getStudentList());
        students.sort(Comparator.comparingInt((Student a) -> a.rating.value));
        worstStudents = new Pair[StatisticWindow.NUM_STUDENTS_TO_DISPLAY];
        for (int i = 0; i < StatisticWindow.NUM_STUDENTS_TO_DISPLAY; ++i) {
            if (i < students.size()) {
                worstStudents[i] = new Pair<>(students.get(i).getName().fullName, students.get(i).rating.value);
            } else {
                worstStudents[i] = new Pair<>("", 0);
            }
        }
    }

    public int getTotalHours() {
        int total = 0;
        for (int h : this.numHoursPerCategory) {
            total += h;
        }
        return total;
    }

    public double getTotalPay() {
        return getTotalHours() * 40; //TODO: Change 40 to rate
    }
}


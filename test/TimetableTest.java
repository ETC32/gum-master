import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TimetableTest {

  @Test
  void testGetTrainingSessionsForDaySingleSession() {
    Timetable timetable = new Timetable();

    Group group = new Group("Акробатика для детей", Age.CHILD, 60);
    Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
    TrainingSession singleTrainingSession = new TrainingSession(group, coach,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));

    timetable.addNewTrainingSession(singleTrainingSession);

    ArrayList<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.MONDAY);
    assertEquals(1, mondaySessions.size());

    ArrayList<TrainingSession> tuesdaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.TUESDAY);
    assertEquals(0, tuesdaySessions.size());

  }


  @Test
  void testGetTrainingSessionsForDayMultipleSessions() {
    Timetable timetable = new Timetable();

    Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

    Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
    TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
        DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

    timetable.addNewTrainingSession(thursdayAdultTrainingSession);

    Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
    TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
        DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
    TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
        DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

    timetable.addNewTrainingSession(mondayChildTrainingSession);
    timetable.addNewTrainingSession(thursdayChildTrainingSession);
    timetable.addNewTrainingSession(saturdayChildTrainingSession);


    ArrayList<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.MONDAY);
    assertEquals(1, mondaySessions.size());

    ArrayList<TrainingSession> thursdaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.THURSDAY);
    assertEquals(2, thursdaySessions.size());
    assertEquals(13, thursdaySessions.get(0).getTimeOfDay().getHours());
    assertEquals(0, thursdaySessions.get(0).getTimeOfDay().getMinutes());
    assertEquals(20, thursdaySessions.get(1).getTimeOfDay().getHours());
    assertEquals(0, thursdaySessions.get(1).getTimeOfDay().getMinutes());

    ArrayList<TrainingSession> tuesdaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.TUESDAY);
    assertEquals(0, tuesdaySessions.size());

  }

  @Test
  void testGetTrainingSessionsForDayAndTime() {
    Timetable timetable = new Timetable();

    Group group = new Group("Акробатика для детей", Age.CHILD, 60);
    Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
    TrainingSession singleTrainingSession = new TrainingSession(group, coach,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    timetable.addNewTrainingSession(singleTrainingSession);

    TimeOfDay timeMonday13 = new TimeOfDay(13, 0);
    ArrayList<TrainingSession> mondaySessions13 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, timeMonday13);
    assertEquals(1, mondaySessions13.size());

    TimeOfDay timeMonday14 = new TimeOfDay(14, 0);
    ArrayList<TrainingSession> mondaySessions14 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, timeMonday14);
    assertEquals(0, mondaySessions14.size());
  }

}

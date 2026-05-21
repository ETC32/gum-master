import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimetableTest {

  Timetable timetable;

  Coach coachOne;
  Coach coachTwo;

  Group groupAdult;
  Group groupChild;


  @BeforeEach
  void SetUp() {
    timetable = new Timetable();

    coachOne = new Coach("Васильев", "Николай", "Сергеевич");
    coachTwo = new Coach("Васильева", "Ольга", "Сергеевна");

    groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
    groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
  }

  @Test
  void testGetTrainingSessionsForDaySingleSession() {

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0)));

    ArrayList<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.MONDAY);
    assertEquals(1, mondaySessions.size());

    ArrayList<TrainingSession> tuesdaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.TUESDAY);
    assertEquals(0, tuesdaySessions.size());

  }

  @Test
  void testGetTrainingSessionsForDayMultipleSessions() {

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.THURSDAY, new TimeOfDay(20, 0)));

    TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coachOne,
        DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
    TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coachOne,
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

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0)));

    ArrayList<TrainingSession> mondaySessions13 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    assertEquals(1, mondaySessions13.size());

    ArrayList<TrainingSession> mondaySessions14 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, new TimeOfDay(14, 0));
    assertEquals(0, mondaySessions14.size());
  }

  @Test
  void testGetTrainingSessionsForDayAndTimeForMultipleSessions() {

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
    timetable.addNewTrainingSession(new TrainingSession(groupChild, coachTwo,
        DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
    timetable.addNewTrainingSession(new TrainingSession(groupChild, coachTwo,
        DayOfWeek.MONDAY, new TimeOfDay(14, 0)));

    ArrayList<TrainingSession> mondaySessions13 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    assertEquals(2, mondaySessions13.size());

    ArrayList<TrainingSession> mondaySessions14 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.MONDAY, new TimeOfDay(14, 0));
    assertEquals(1, mondaySessions14.size());

    ArrayList<TrainingSession> fridaySessions15 = timetable.getTrainingSessionsForDayAndTime(
        DayOfWeek.FRIDAY, new TimeOfDay(15, 0));
    assertEquals(0, fridaySessions15.size());
  }

  @Test
  void testGetTrainingSessionsForDayEmptyTimetable() {

    ArrayList<TrainingSession> sessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
    assertEquals(0, sessions.size());
  }

  @Test
  void testSessionsAreSortedByTime() {

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(15, 0)));
    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(12, 0)));

    ArrayList<TrainingSession> sessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
    assertEquals(3, sessions.size());
    assertEquals(10, sessions.get(0).getTimeOfDay().getHours());
    assertEquals(12, sessions.get(1).getTimeOfDay().getHours());
    assertEquals(15, sessions.get(2).getTimeOfDay().getHours());
  }

  @Test
  void testGetTrainingSessionsForNonExistentDay() {

    timetable.addNewTrainingSession(new TrainingSession(groupAdult, coachOne,
        DayOfWeek.MONDAY, new TimeOfDay(10, 0)));

    ArrayList<TrainingSession> wednesdaySessions = timetable.getTrainingSessionsForDay(
        DayOfWeek.WEDNESDAY);
    assertEquals(0, wednesdaySessions.size());
  }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Timetable {

  private Map<String, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();



  public void addNewTrainingSession(TrainingSession trainingSession) {

    String dayName = trainingSession.getDayOfWeek().name();
    TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingDay;
    ArrayList<TrainingSession> trainingSessions;

    if(timetable.containsKey(dayName)){
      trainingDay = timetable.get(dayName);
    } else{
      trainingDay = new TreeMap<>();
    }

    if(trainingDay.containsKey(trainingSession.getTimeOfDay())){
      trainingSessions = trainingDay.get(trainingSession.getTimeOfDay());
    } else{
      trainingSessions = new ArrayList<>();
    }

    trainingSessions.add(trainingSession);
    trainingDay.put(trainingSession.getTimeOfDay(), trainingSessions);
    timetable.put(dayName,trainingDay);
  }

  public ArrayList<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {

    TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule = timetable.get(dayOfWeek.name());
    if (daySchedule == null) {
      return new ArrayList<>();
    }

    ArrayList<TrainingSession> result = new ArrayList<>();
    for (ArrayList<TrainingSession> sessions : daySchedule.values()) {
      result.addAll(sessions);
    }
    return result;
  }


  public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {

    TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule = timetable.get(dayOfWeek.name());

    if (daySchedule == null || !daySchedule.containsKey(timeOfDay)) {
      return new ArrayList<>();
    }

    ArrayList<TrainingSession> result = daySchedule.get(timeOfDay);
    //for (ArrayList<TrainingSession> sessions : daySchedule.values()) {
      //if(daySchedule.)
      //result.addAll(sessions);
    //}
    return result;
  }

}
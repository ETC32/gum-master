public class CounterOfTrainings {

  Coach coach;
  int trainingCount;

  public CounterOfTrainings(int trainingCount, Coach coach) {
    this.trainingCount = trainingCount;
    this.coach = coach;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return coach.getSurname() + " " + coach.getName() + " " + coach.getMiddleName()
        + " - " + trainingCount + " тренировок";
  }
}

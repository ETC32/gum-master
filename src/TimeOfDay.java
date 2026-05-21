public class TimeOfDay implements Comparable<TimeOfDay> {

  private final int hours;
  private final int minutes;

  public TimeOfDay(int hours, int minutes) {
    this.hours = hours;
    this.minutes = minutes;
  }

  public int getHours() {
    return hours;
  }

  public int getMinutes() {
    return minutes;
  }

  @Override
  public int compareTo(TimeOfDay o) {
    return (this.hours - o.hours) + (this.minutes - o.minutes);
  }
}
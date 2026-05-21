public class Coach {

  private final String surname;
  private final String name;
  private final String middleName;

  public Coach(String surname, String name, String middleName) {
    this.surname = surname;
    this.name = name;
    this.middleName = middleName;
  }

  public String getSurname() {
    return surname;
  }

  public String getName() {
    return name;
  }

  public String getMiddleName() {
    return middleName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Coach coach = (Coach) obj;
    return surname.equals(coach.surname) && name.equals(coach.name) && middleName.equals(
        coach.middleName);
  }

  @Override
  public int hashCode() {
    int result = surname.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + middleName.hashCode();
    return result;
  }
}

public class Student {
    private final String name;
    private final double firstTest;
    private final double secondTest;

    public Student(String name, double firstTest, double secondTest){
        this.name = name;
        this.firstTest = firstTest;
        this.secondTest = secondTest;
    }

    public String getName() {
        return name;
    }

    public double getFirstTest() {
        return firstTest;
    }

    public double getSecondTest() {
        return secondTest;
    }

    public double getAverage(){
        return (firstTest + secondTest)/2;
    }

    public boolean isApproved(){
        return getAverage() >= 5;
    }

    public String getText(){
        if (isApproved())
            return name + " passou com " + getAverage();

        return name + " não passou com " + getAverage();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", firstTest=" + firstTest +
                ", secondTest=" + secondTest +
                '}';
    }
}

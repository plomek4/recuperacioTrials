package business.Trials;

public class Trial {
    private String name;

    public Trial(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String trialName) {this.name = trialName;}



    public void showAll() {
        System.out.print("\nTrial: " + this.getName() + "\n");
    }
}
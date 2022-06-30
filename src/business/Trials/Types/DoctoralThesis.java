package business.Trials.Types;

import business.Trials.Trial;

public class DoctoralThesis extends Trial {
    private String studyField;
    private int defenseDifficulty;

    public DoctoralThesis(String name, String studyField, int defenseDifficulty) {
        super(name);
        this.studyField = studyField;
        this.defenseDifficulty = defenseDifficulty;
    }

    /**
     * Getter of the study field
     * @return the study field
     */
    public String getStudyField() {
        return studyField;
    }

    /**
     * getter of the defense difficulty
     * @return the defense difficulty
     */
    public int getDefenseDifficulty() {
        return defenseDifficulty;
    }

    /**
     * Function that displays the study field
     */
    public void showThesisFieldOfStudy() {
        System.out.print("Field: " + this.getStudyField() + "\n");
    }

    /**
     * Function that displays the defense difficulty
     */
    public void showDefenseDifficulty() {
        System.out.print("Difficulty: " + this.getDefenseDifficulty() + "\n");
    }

    /**
     * Function used when the budget request trial starts
     * @return the total sum casted to int
     */
    public int startTrial(){
        int sum = 0;

        for (int i = 0; i <= defenseDifficulty; i++) {
            sum += 2*i-1;
        }

        sum = (int) Math.sqrt(sum);

        return sum;
    }
}

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

    public String getStudyField() {
        return studyField;
    }

    public int getDefenseDifficulty() {
        return defenseDifficulty;
    }

    public void showThesisFieldOfStudy() {
        System.out.print("Field: " + this.getStudyField() + "\n");
    }

    public void showDefenseDifficulty() {
        System.out.print("Difficulty: " + this.getDefenseDifficulty() + "\n");
    }
}

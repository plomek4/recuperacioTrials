package business.Trials.Types;

import business.Trials.Trial;

public class DoctoralThesisDefense extends Trial {
    private String thesisFieldOfStudy;
    private int defenseDifficulty;

    public DoctoralThesisDefense(String name, String thesisFieldOfStudy, int defenseDifficulty) {
        super(name);
        this.thesisFieldOfStudy = thesisFieldOfStudy;
        this.defenseDifficulty = defenseDifficulty;
    }
}

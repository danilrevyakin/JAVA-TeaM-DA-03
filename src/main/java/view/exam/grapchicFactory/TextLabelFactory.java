package view.exam.grapchicFactory;

public class TextLabelFactory {

    private final int maxCharactersInLabelLine;
    private String input;
    private int counterLinesInLabel = 1;

    private String result;

    public TextLabelFactory(int maxCharactersInLabelLine, String input) {
        this.maxCharactersInLabelLine = maxCharactersInLabelLine;
        this.input = input;
        formatString();
    }

    private void formatString() {
        if (input.length() < maxCharactersInLabelLine) {
            result = input;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        input = input.replaceAll("\n", ". ");
        String[] strings = input.split(" ");
        int lineLength = 0;
        for (String word : strings) {
            if (lineLength + word.length() < maxCharactersInLabelLine) {
                stringBuilder.append(" ");
                lineLength++;
            } else {
                stringBuilder.append("\n");
                counterLinesInLabel++;
                lineLength = 0;
            }
            stringBuilder.append(word);
            lineLength += word.length();
        }
        result = stringBuilder.toString();
    }

    public int getCounterLinesInLabel() {
        return counterLinesInLabel;
    }

    public String getResult() {
        return result;
    }
}

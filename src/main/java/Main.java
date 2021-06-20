import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.awt.event.KeyEvent;
import java.util.List;

public class Main{

    private static final Screen screen = new Screen();

    private static final float similar = 0.80f;

    private static final Pattern FOLDER = new Pattern(Main.class.getResource("folder.jpg"));
    private static final Pattern DOCUMENTS = new Pattern(Main.class.getResource("documents.jpg"));
    private static final Pattern INNER_FOLDER = new Pattern(Main.class.getResource("inner-folder.jpg"));
    private static final Pattern FILE = new Pattern(Main.class.getResource("file.jpg"));

    private static final Pattern SEARCH = new Pattern(Main.class.getResource("search.jpg"));
    private static final Pattern SEARCH_FIELD = new Pattern(Main.class.getResource("search-field.jpg"));
    private static final Pattern NOTEPAD = new Pattern(Main.class.getResource("notepad.jpg"));
    private static final Pattern NOTEPAD_BAR = new Pattern(Main.class.getResource("notepad-bar.jpg"));
    private static final Pattern NOTEPAD_FIELD = new Pattern(Main.class.getResource("notepad-field.jpg"));

    public static void main(String[] args){
        List<Student> students = new Excel().getListStudent();
        StringBuilder text = new StringBuilder();

        for (Student student : students){
            text.append(student.getText()).append("\n");
        }

        writeNotepad(String.format("%s",text));
    }

    public static void writeNotepad(String text){
        try {
            screen.wait(SEARCH.similar(similar), 2).click();
            screen.wait(SEARCH_FIELD.similar(similar), 2).click();
            screen.write("notepad");

            screen.wait(NOTEPAD.similar(similar),2).click();
            screen.wait(NOTEPAD_BAR.similar(similar),4);
            screen.wait(NOTEPAD_FIELD.similar(similar),2).click();

            screen.paste(text);
        }catch (FindFailed e){
            e.getStackTrace();
        }
    }

    public static String openFolder(){
        try {
            screen.wait(FOLDER.similar(similar), 2).click();
            screen.wait(DOCUMENTS.similar(similar), 2).click();

            screen.wait(INNER_FOLDER.similar(similar), 2).click();
            screen.click();
            screen.click();
            return screen.getName();

//            while( !( screen.has( FILE.similar(similar) ) ) ){
//                screen.wait(FILE.similar(similar), 2).find(FILE.similar(similar)).click();
//                screen.keyDown(KeyEvent.VK_DOWN);
//            }

//            return screen.wait(NOTEPAD_BAR.similar(similar), 2).getText();
        }catch (FindFailed e){
            e.getStackTrace();
        }

        return null;
    }
}

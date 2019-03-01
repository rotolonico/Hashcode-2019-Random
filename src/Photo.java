import java.util.ArrayList;

public class Photo {
    public int id;
    public String orientation;
    public ArrayList<String> tags;

    public Photo(int idInt, String orientationString, ArrayList<String> tagsStringArray){
        id = idInt;
        orientation = orientationString;
        tags = tagsStringArray;
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    static String inputPath = "d_pet_pictures.txt";
    static String outputPath = "output_d.txt";

    public static void main(String[] args) throws IOException {

        ArrayList<Photo> photos = RetrieveData(inputPath);
        ArrayList<Slide> slideshow = MakeSlides(photos);
        MakeOutput(slideshow);
    }

    static ArrayList<Photo> RetrieveData(String filePath) throws IOException {
        List<String> rawData = HappyParser.LoadInput(filePath);

        ArrayList<Photo> photos = new ArrayList<>();

        for (int i = 1; i < rawData.size(); i++){
            String[] splitData = rawData.get(i).split(" ");

            String orientation = splitData[0];
            ArrayList<String> tags = new ArrayList<>();

            for (int j = 2; j < splitData.length; j++){
                tags.add(splitData[j]);
            }

            Photo photo = new Photo(i, orientation, tags);
            photos.add(photo);

        }
        return photos;
    }

    static ArrayList<Slide> MakeSlides(ArrayList<Photo> photos){

        ArrayList<Slide> slideshow = new ArrayList<>();

        photos.sort((p1, p2) -> {
            int p1OrientationId;
            int p2OrientationId;

            if (p1.orientation.equals("H")) {
                p1OrientationId = 0;
            } else {
                p1OrientationId = 1;
            }

            if (p2.orientation.equals("H")) {
                p2OrientationId = 0;
            } else {
                p2OrientationId = 1;
            }

            return Double.compare(p1OrientationId, p2OrientationId);
        });


        for (int i = 0; i < photos.size(); i++){
            if (photos.get(i).orientation.equals("H")){
                Slide slide = new Slide(photos.get(i), null);
                slideshow.add(slide);
            } else if (i != photos.size()-1) {
                Slide slide = new Slide(photos.get(i), photos.get(i+1));
                slideshow.add(slide);
                i++;
            }
        }
        return slideshow;
    }

    static void MakeOutput(ArrayList<Slide> slideshow) throws IOException {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < slideshow.size(); i++){
            if (slideshow.get(i).photo2 == null){
                output.add(String.valueOf(slideshow.get(i).photo1.id-1));
            } else {
                output.add((slideshow.get(i).photo1.id-1) + " " + (slideshow.get(i).photo2.id-1));
            }
        }

        Collections.shuffle(output);

        output.add(0, String.valueOf(slideshow.size()));

        HappyParser.CreateOutput(outputPath, output);
    }
}

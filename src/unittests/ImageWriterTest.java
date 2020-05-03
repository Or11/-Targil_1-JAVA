package unittests;

import org.junit.Test;
import renderer.*;

import java.awt.*;

import static org.junit.Assert.*;

public class ImageWriterTest {

    @Test
    public void firstImageTest() {
        ImageWriter newImage = new ImageWriter("first_image_test", 1600, 1000, 800, 500);
        for (int i = 0; i < newImage.getNx(); ++i) {
            for (int j = 0; j < newImage.getNy(); ++j){
                if( i % 50 ==0 || j % 50 == 0 ) {
                    newImage.writePixel(i, j, Color.black);
                } else {
                    newImage.writePixel(i, j, Color.white);
                }
            }
        }
    }
}

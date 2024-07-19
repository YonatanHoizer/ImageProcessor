import java.awt.image.BufferedImage;
import java.awt.Color;

public class GrayscaleManipulation {

    public BufferedImage GrayscaleFilter (BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageGS = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int sum = (red + green + blue) / 3;
                int rgb = new Color(sum, sum, sum).getRGB();
                imageGS.setRGB(x, y, rgb);
            }
        }

        return imageGS;
    }
}

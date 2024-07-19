import java.awt.image.BufferedImage;
import java.awt.Color;

public class NegativeManipulation {

    public BufferedImage NegativeFilter (BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageN = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int rgb = new Color(255 - red,255 - green,255 - blue).getRGB();
                imageN.setRGB(x, y, rgb);
            }
        }

        return imageN;
    }

}

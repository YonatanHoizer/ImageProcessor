import java.awt.image.BufferedImage;
import java.awt.Color;

public class PosterizeManipulation {

    public BufferedImage PosterizeFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageN = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int levels = 6; // Number of levels to reduce to
        int interval = 256 / levels;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = (color.getRed() / interval) * interval;
                int green = (color.getGreen() / interval) * interval;
                int blue = (color.getBlue() / interval) * interval;

                int rgb = new Color(red, green, blue).getRGB();
                imageN.setRGB(x, y, rgb);
            }
        }

        return imageN;
    }
}

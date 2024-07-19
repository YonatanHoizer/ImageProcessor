import java.awt.image.BufferedImage;
import java.awt.Color;

public class BlackWhiteManipulation {

    public BufferedImage BlackWhiteFilter (BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageBW = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int sum = (red + green + blue) / 3;
                int bwColor = (sum > 128) ? 255 : 0;

                int rgb = new Color(bwColor, bwColor, bwColor).getRGB();
                imageBW.setRGB(x, y, rgb);
            }
        }

        return imageBW;
    }
}






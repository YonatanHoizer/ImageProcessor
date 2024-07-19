import java.awt.image.BufferedImage;
import java.awt.Color;

public class PixelateManipulation {

    public BufferedImage PixelateFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelSize = 10; // Size of the "pixels"

        BufferedImage imageN = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int avgRed = 0, avgGreen = 0, avgBlue = 0;
                int count = 0;

                for (int dy = 0; dy < pixelSize && y + dy < height; dy++) {
                    for (int dx = 0; dx < pixelSize && x + dx < width; dx++) {
                        Color color = new Color(image.getRGB(x + dx, y + dy));
                        avgRed += color.getRed();
                        avgGreen += color.getGreen();
                        avgBlue += color.getBlue();
                        count++;
                    }
                }

                avgRed /= count;
                avgGreen /= count;
                avgBlue /= count;

                for (int dy = 0; dy < pixelSize && y + dy < height; dy++) {
                    for (int dx = 0; dx < pixelSize && x + dx < width; dx++) {
                        int rgb = new Color(avgRed, avgGreen, avgBlue).getRGB();
                        imageN.setRGB(x + dx, y + dy, rgb);
                    }
                }
            }
        }

        return imageN;
    }
}

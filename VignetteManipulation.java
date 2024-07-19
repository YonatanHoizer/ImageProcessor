import java.awt.image.BufferedImage;
import java.awt.Color;

public class VignetteManipulation {

    public BufferedImage VignetteFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        double maxDistance = Math.sqrt(centerX * centerX + centerY * centerY);

        BufferedImage imageN = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double distance = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
                double ratio = distance / maxDistance;

                Color color = new Color(image.getRGB(x, y));
                int red = (int) (color.getRed() * (1 - ratio));
                int green = (int) (color.getGreen() * (1 - ratio));
                int blue = (int) (color.getBlue() * (1 - ratio));

                int rgb = new Color(red, green, blue).getRGB();
                imageN.setRGB(x, y, rgb);
            }
        }

        return imageN;
    }
}

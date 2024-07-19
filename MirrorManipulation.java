import java.awt.image.BufferedImage;

public class MirrorManipulation {

    public BufferedImage MirrorFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageN = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imageN.setRGB(width - 1 - x, y, image.getRGB(x, y));
            }
        }

        return imageN;
    }
}

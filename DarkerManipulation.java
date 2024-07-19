import java.awt.image.BufferedImage;
import java.awt.Color;

public class  DarkerManipulation{

    public BufferedImage DarkerFilter (BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageD = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if (red - 50 < 0){
                    red = 0;
                } else red = red - 50;

                if (green - 50 < 0){
                    green = 0;
                } else green = green - 50;

                if (blue - 50 < 0){
                    blue = 0;
                } else blue = blue - 50;

                int rgb = new Color(red ,green ,blue).getRGB();
                imageD.setRGB(x, y, rgb);
            }
        }

        return imageD;
    }

}

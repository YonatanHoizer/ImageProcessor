import java.awt.image.BufferedImage;
import java.awt.Color;

public class  LigherManipulation{

    public BufferedImage LigherFilter (BufferedImage image){

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage imageL = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (red + 50 > 225){
                    red = 225;
                } else red = red + 50;

                if (green + 50 > 225){
                    green = 225;
                } else green = green + 50;

                if (blue + 50 > 225){
                    blue = 225;
                } else blue = blue + 50;

                int rgb = new Color(red ,green ,blue ).getRGB();
                imageL.setRGB(x, y, rgb);
            }
        }

        return imageL;
    }

}

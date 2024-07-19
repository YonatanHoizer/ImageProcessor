import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class VerticalLineDrawer extends JLabel {
    private int mouseX = -1;
    private BufferedImage image;

    public VerticalLineDrawer() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
        if (mouseX != -1) {
            g.setColor(Color.BLACK);
            g.drawLine(mouseX, 0, mouseX, getHeight());
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    public BufferedImage applyFiltersToRightSide(BufferedImage originalImage, BufferedImage filteredImage) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int scaledMouseX = (int) ((double) mouseX / getWidth() * imageWidth);

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                if (x < scaledMouseX) {
                    result.setRGB(x, y, originalImage.getRGB(x, y));
                } else {
                    result.setRGB(x, y, filteredImage.getRGB(x, y));
                }
            }
        }
        return result;
    }
}

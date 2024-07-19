import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class ImageProcessor extends JFrame {
    private BufferedImage originalImage, processedImage;
    private VerticalLineDrawer imageLabel;
    private BlackWhiteManipulation bwFilter = new BlackWhiteManipulation();
    private GrayscaleManipulation gsFilter = new GrayscaleManipulation();
    private NegativeManipulation negativeFilter = new NegativeManipulation();
    private LigherManipulation ligherFilter = new LigherManipulation();
    private DarkerManipulation darkerFilter = new DarkerManipulation();
    private EliminateRedManipulation erFilter = new EliminateRedManipulation();
    private MirrorManipulation mirrorFilter = new MirrorManipulation();
    private PixelateManipulation pixelateFilter = new PixelateManipulation();
    private PosterizeManipulation posterFilter = new PosterizeManipulation();
    private VignetteManipulation vignetteFilter = new VignetteManipulation();
    private Timer currentTimer;

    public ImageProcessor() {
        setTitle("Image Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JButton openButton = new JButton("פתח תמונה");
        openButton.addActionListener(e -> openImage());

        JButton setBlackWhite = new JButton("אפקט שחור לבן");
        setBlackWhite.addActionListener(e -> applyFilter("BlackWhite"));

        JButton setGrayscale = new JButton("אפקט גווני אפור");
        setGrayscale.addActionListener(e -> applyFilter("Grayscale"));

        JButton setNegative = new JButton("אפקט תמונה שלילית");
        setNegative.addActionListener(e -> applyFilter("Negative"));

        JButton setLigher = new JButton("אפקט הבהרת תמונה");
        setLigher.addActionListener(e -> applyFilter("Ligher"));

        JButton setDarker = new JButton("אפקט החשכת תמונה");
        setDarker.addActionListener(e -> applyFilter("Darker"));

        JButton setEliminateRed = new JButton("אפקט בלי אדום");
        setEliminateRed.addActionListener(e -> applyFilter("EliminateRed"));

        JButton setMirror = new JButton("אפקט תמונת מראה");
        setMirror.addActionListener(e -> applyFilter("Mirror"));

        JButton setPixelate = new JButton("אפקט תמונה מפוקסלת");
        setPixelate.addActionListener(e -> applyFilter("Pixelate"));

        JButton setPoster = new JButton("אפקט תמונת פוסטר");
        setPoster.addActionListener(e -> applyFilter("Poster"));

        JButton setVignette = new JButton("אפקט תמונת וינטג'");
        setVignette.addActionListener(e -> applyFilter("Vignette"));


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton[] buttons = {setBlackWhite, setGrayscale, setNegative, setLigher, setDarker, setEliminateRed, setMirror, setPixelate, setPoster, setVignette};

        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            rightPanel.add(button);
        }

        imageLabel = new VerticalLineDrawer();
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String filename = file.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".bmp") || filename.endsWith(".gif");
                }
            }

            @Override
            public String getDescription() {
                return "Image files";
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                if (originalImage == null) {
                    throw new Exception("Not an image file");
                }
                processedImage = resizeImageToFitWindow(originalImage);
                imageLabel.setImage(processedImage);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private BufferedImage resizeImageToFitWindow(BufferedImage image) {
        int frameWidth = getWidth();
        int frameHeight = getHeight() - getInsets().top - getInsets().bottom;

        Image resizedImage = image.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedResizedImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();
        return bufferedResizedImage;
    }

    private void applyFilter(String str) {
        if (originalImage != null) {
            stopCurrentTimer();
            switch (str){
                case "BlackWhite" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = bwFilter.BlackWhiteFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Grayscale" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = gsFilter.GrayscaleFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Negative" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = negativeFilter.NegativeFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Ligher" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = ligherFilter.LigherFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Darker" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = darkerFilter.DarkerFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "EliminateRed" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = erFilter.EliminateRedFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Mirror" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = mirrorFilter.MirrorFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Pixelate" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = pixelateFilter.PixelateFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Poster" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = posterFilter.PosterizeFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
                case "Vignette" :
                    currentTimer = new Timer(100, e -> {
                        BufferedImage filteredImage = vignetteFilter.VignetteFilter(resizeImageToFitWindow(originalImage));
                        processedImage = imageLabel.applyFiltersToRightSide(resizeImageToFitWindow(originalImage), filteredImage);
                        imageLabel.setImage(processedImage);
                    });
                    break;
            }
            currentTimer.start();
        }
    }

    private void stopCurrentTimer() {
        if (currentTimer != null && currentTimer.isRunning()) {
            currentTimer.stop();
        }
    }
}

//Talan Hutto
//6/4/2025
//Updated Slideshow

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

public class SlideShow extends JFrame {
    // CardLayouts for image and text
    private CardLayout cardImages, cardTexts;
    private JPanel imagePanel;     // holds JLabels with icons
    private JPanel textPanel;      // holds JLabels with descriptions
    private JPanel bottomContainer;// holds textPanel ↑ and buttons ↓
    private JButton btnPrev, btnNext;
    private final int TOTAL_SLIDES = 5;

    public SlideShow() {
        initComponents();
    }

    private void initComponents() {
        // 1) FRAME setup
        setTitle("Top 5 Relaxation and Detox Locations SlideShow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        //use BorderLayout in the frame’s content pane:
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(10,10));

        // 2) IMAGE PANEL with CardLayout
        cardImages = new CardLayout();
        imagePanel = new JPanel(cardImages);
        imagePanel.setBackground(Color.BLACK); // just so you see the area
        cp.add(imagePanel, BorderLayout.CENTER);

        // 3) TEXT PANEL with CardLayout + black border
        cardTexts = new CardLayout();
        textPanel = new JPanel(cardTexts);
        textPanel.setBackground(Color.ORANGE);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // 4) BUTTON PANEL
        btnPrev = new JButton("Previous");
        btnNext = new JButton("Next");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);

        // 5) COMBINE textPanel and buttonPanel into bottomContainer
        bottomContainer = new JPanel();
        bottomContainer.setLayout(new BorderLayout());
        bottomContainer.add(textPanel, BorderLayout.CENTER);
        bottomContainer.add(buttonPanel, BorderLayout.SOUTH);
        cp.add(bottomContainer, BorderLayout.SOUTH);

        // 6) Fill both card containers with slide components:
        for (int i = 1; i <= TOTAL_SLIDES; i++) {
            // 6a) Create a JLabel that will eventually hold a scaled image
            JLabel slideLabel = new JLabel();
            slideLabel.setHorizontalAlignment(SwingConstants.CENTER);
            slideLabel.setVerticalAlignment(SwingConstants.CENTER);
            // We'll "name" each card "image1", "image2", ...
            imagePanel.add(slideLabel, "image" + i);

            // 6b) Create a JLabel for the descriptive text
            JLabel descLabel = new JLabel(getTextDescription(i));
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            descLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textPanel.add(descLabel, "text" + i);
        }

        // 7) Load all original images into memory once
        // keep them in an array so we can re-scale on resize.
        BufferedImage[] originals = new BufferedImage[TOTAL_SLIDES];
        for (int i = 1; i <= TOTAL_SLIDES; i++) {
            try {
                // e.g. "/resources/Thailand.jpg"
                originals[i-1] = ImageIO.read(
                    getClass().getResource("/resources/" + getFilenameFor(i))
                );
            } catch (IOException e) {
                originals[i-1] = null;
                e.printStackTrace();
            }
        }

        // 8) Add a ComponentListener to imagePanel so that when resized,
        //    it re-scales the currently visible slide for ALL cards.
        imagePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get panel’s new size:
                int w = imagePanel.getWidth();
                int h = imagePanel.getHeight();
                // Loop through each card’s JLabel and update its icon:
                for (int i = 1; i <= TOTAL_SLIDES; i++) {
                    JLabel lbl = (JLabel) imagePanel.getComponent(i-1);
                    BufferedImage orig = originals[i-1];
                    if (orig != null) {
                        Image scaled = orig.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
                        lbl.setIcon(new ImageIcon(scaled));
                    }
                }
            }
        });

        // 9) Button actions to flip cards:
        btnPrev.addActionListener(e -> {
            cardImages.previous(imagePanel);
            cardTexts.previous(textPanel);
        });
        btnNext.addActionListener(e -> {
            cardImages.next(imagePanel);
            cardTexts.next(textPanel);
        });
    }

    // Helper: map 1→"Thailand.jpg", 2→"Turkey.jpg", etc.
    private String getFilenameFor(int i) {
        switch (i) {
            case 1: return "Thailand.jpg";
            case 2: return "Turkey.jpg";
            case 3: return "India.jpg";
            case 4: return "Italy.jpg";
            case 5: return "Greece.jpg";
            default: return "";
        }
    }

    // Helper: produce your descriptive HTML (no <img> here anymore)
    private String getTextDescription(int i) {
        switch(i) {
            case 1:
                return "<html><center><b>#1 Thailand</b><br>"
                     + "Vital Body Cleanse at The BARAI Spa.<br>"
                     + "<i>Rejuvenate your body inside and out on this intensive and effective detox retreat on the coast of Thailand...</center></html>";
            case 2:
                return "<html><center><b>#2 Turkey</b><br>"
                     + "Master Detox at Sianji Well-Being Resort.<br>"
                     + "<i>For an affordable healthy holiday which promises results, escape to this specialist intensive detox retreat, situated in a secluded coastal area of Turkey...</center></html>";
            case 3:
                return "<html><center><b>#3 Italy</b><br>"
                     + "Detox at Lefay Resort & Spa Lago di Garda.<br>"
                     + "<i>Transform your body on a wellness holiday in Lake Garda with healthy nutrition, holistic therapies and toxin cleansing exercise..</center></html>";
            case 4:
                return "<html><center><b>#4 India</b><br>"
                     + "Detox at Ananda in the Himalayas.<br>"
                     + "vLocated in the breath-taking Himalayas, this award-winning detox holiday in India offers the perfect opportunity to improve your well-being...</center></html>";
            case 5:
                return "<html><center><b>#5 Greece</b><br>"
                     + "Wellbeing Detox at Euphoria Retreat.<br>"
                     + "<i>Dive into your wellbeing aspirations by detoxing and rebalancing your mind, body and soul for the ultimate wellness holiday....</center></html>";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SlideShow ss = new SlideShow();
            ss.setVisible(true);
        });
    }
}

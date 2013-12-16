package us.zolp.barcode;

// Imports from java.util for Lists and Random generator
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// Import the QR Code generator classes
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

// Import the AWT event and image classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

// Import some nice Swing controls
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

// Import some nice AWT layouts (kidding, they're evil)
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * 
 * @author Francis Zolp
 *
 */
public class BarcodeGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5849795342901389842L;
	private int qrLen = 512;
	private final String ALLCHARS 
			= "QWERTYUIOPASDFGHJKLZXCVBNM"
			+ "qwertyuiopasdfghjklzxcvbnm"
			+ "`1234567890-=[];',./~!@#$%^&*()_+{}|:<>?";
	
	private static BufferedImage codeImg;
	
	private JButton btnGenerate;
	private ImageIcon imageIcon;
	private JLabel imageLabel;
	
	// Some radio buttons for choosing the length of entropy
	private JRadioButton radio8 = new JRadioButton("8");
	private JRadioButton radio16 = new JRadioButton("16");
	private JRadioButton radio32 = new JRadioButton("32");
	private JRadioButton radio64 = new JRadioButton("64");
	private JRadioButton radio128 = new JRadioButton("128");
	private JRadioButton radio256 = new JRadioButton("256");
	private JRadioButton radio512 = new JRadioButton("512");
	
	// The last radio button we had clicked
	private Object lastBtn;
	
	// A list of radio buttons
	private List<JRadioButton> radioList = new ArrayList<JRadioButton>();
	
	// The size of the QR code. This'll be changed upon resize events
	private int curX = 400;
	private int curY = 400;
	
	// I forgot what I did with this.
	private String lastString;
	
	/**
	 * The default constructor
	 */
	public BarcodeGUI() {
		// Set the title
		super("Random QR Code Generator");

		// GridBagLayouts and me: this sort of thing is my bag, baby.
		GridBagLayout gridBagLayout = new GridBagLayout();

		// I don't know why it insists on having two columns, when
		// one of them has a width of *0*
		gridBagLayout.columnWidths = new int[] { 400, 0 };
		// Four rows!
		gridBagLayout.rowHeights = new int[] { 400, 48, 0, 0 };
		// Weights. More layout things I don't understand
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		// Set the content pane's layout to the configured GridBagLayout
		getContentPane().setLayout(gridBagLayout);

		// Create the Generate button
		btnGenerate = new JButton("Generate");
		// When it's clicked, do "actionPerformed"
		btnGenerate.addActionListener(this);
		// Generate an image
		btnGenerate.doClick();
		// Oh great, more layout stuff.
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenerate.ipady = 32;
		gbc_btnGenerate.anchor = GridBagConstraints.SOUTH;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerate.gridx = 0;
		gbc_btnGenerate.gridy = 1;
		
		// ImageIcon to hold the actual image of the QR code
		imageIcon = new ImageIcon(codeImg);
		
		// ...and the Label to *display* the image
		imageLabel = new JLabel();
		// Center that puppy
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setIcon(imageIcon);
		// LAYOUT!!!!!!!
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.fill = GridBagConstraints.BOTH;
		gbc_imageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_imageLabel.gridx = 0;
		gbc_imageLabel.gridy = 0;
		// Add the label to the pane
		getContentPane().add(imageLabel, gbc_imageLabel);
		// Add the button to the pane
		getContentPane().add(btnGenerate, gbc_btnGenerate);
		
		// panel: a nice place to throw some radio buttons
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Length (characters)",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);

		radio8.addActionListener(this);
		panel.add(radio8);
		radioList.add(radio8);
		
		radio16.addActionListener(this);
		panel.add(radio16);
		radioList.add(radio16);

		radio32.addActionListener(this);
		panel.add(radio32);
		radioList.add(radio32);

		radio64.addActionListener(this);
		panel.add(radio64);
		radioList.add(radio64);

		radio128.addActionListener(this);
		panel.add(radio128);
		radioList.add(radio128);

		radio256.addActionListener(this);
		panel.add(radio256);
		radioList.add(radio256);

		radio512.setSelected(true);
		radio512.addActionListener(this);
		panel.add(radio512);
		radioList.add(radio512);
		
		// Now, when we resize the window, we scale the barcode appropriately
		getRootPane().addComponentListener(new ComponentAdapter() {
			/**
			 * componentResized: I do things to make barcodes as big
			 * as they can possibly be (while staying proportional)
			 */
			public void componentResized(ComponentEvent e) {
				// If the width is greater than the height, use the
				// height as the scaling source. Otherwise, just use
				// the width as the scaling source.
				if (imageLabel.getWidth() > imageLabel.getHeight())
					curX = curY = imageLabel.getHeight();
				else
					curX = curY = imageLabel.getWidth();
				
				// Now let's pull together a QR code.
				try {
					// BitMatrix is the binary representation of a QR code
					// and we generate one using the random string and image size
					BitMatrix bitMatrix = new QRCodeWriter().encode(lastString,
							BarcodeFormat.QR_CODE, curX, curY);

					codeImg = MatrixToImageWriter.toBufferedImage(bitMatrix);

					if (imageIcon != null) {
						imageIcon.setImage(codeImg);
						imageLabel.setIcon(imageIcon);
						imageLabel.repaint();
					}
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGenerate.requestFocus();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(418, 542);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		char[] mytxt = new char[qrLen];
		String text;
		Random rand = new Random();

		if (obj == btnGenerate) {
			for (int x = 0; x < qrLen; x++) {
				mytxt[x] = ALLCHARS.charAt(rand.nextInt(ALLCHARS.length()));
				System.out.print(mytxt[x]);
			}
			System.out.println("");

			text = new String(mytxt);
			lastString = text;
			
			try {
				BitMatrix bitMatrix = new QRCodeWriter().encode(text,
						BarcodeFormat.QR_CODE, curX, curY);

				codeImg = MatrixToImageWriter.toBufferedImage(bitMatrix);

				if (imageIcon != null) {
					imageIcon.setImage(codeImg);
					imageLabel.setIcon(imageIcon);
					imageLabel.repaint();
				}
			} catch (WriterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			if (obj == radio8)
				qrLen = 8;
			else if (obj == radio16)
				qrLen = 16;
			else if (obj == radio32)
				qrLen = 32;
			else if (obj == radio64)
				qrLen = 64;
			else if (obj == radio128)
				qrLen = 128;
			else if (obj == radio256)
				qrLen = 256;
			else if (obj == radio512)
				qrLen = 512;
			
			for (JRadioButton btn : radioList) {
				if (btn != obj)
					btn.setSelected(false);
				if (btn == obj && !btn.isSelected())
					btn.setSelected(true);
			}
			if (lastBtn != obj) {
				lastBtn = obj;
				btnGenerate.doClick();
			}

			btnGenerate.requestFocus();
		}
	}
}

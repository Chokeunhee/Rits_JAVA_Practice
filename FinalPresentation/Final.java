
/*
 * Final Presentation
 * 
 * This is a program which first take picture using built-in camera.
 * Then detect Face using Haar Cascade and displaying the detected face.
 * Following mosaic the detected Face and display next to the original.
 * Than display the Face which is mosaic on to the original picture.
 * Next, make a picture of emerged two picture(original picture and face mosaic) and save it as a jpg file
 * Load the picture which is saved Next to the original picture
 * Use Affine Transformation to left and right rotate the loaded image
 * 
 * Used item (in total of 7)
 * 
 * (a) Swing: the seventh day
 * - used buttons and a panel
 * (c) Image load or save (ImageIO of Imgcodecs)
 * - used save image and load saved image
 * (d) BufferedImage: the eleventh day
 * - BufferedImage used for dealing with images
 * (e) Affine Transformation: the twelfth day
 * - Affine Transformation (rotate) has been used
 * (f) OpenCV: Chapter 5 and 6 of the thirteenth day
 * - Mosaic has been used
 * (g) OpenCV: using built-in camera
 * -built in camera used to get picture at the beginning
 * (h) OpenCV: Haar Cascade
 * -Used to detect face
 * 
 * On Jan 20th 2020
 * By Cho keun hee 
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier; 
import org.opencv.videoio.VideoCapture;

public class Final extends JFrame implements ActionListener{
	BufferedImage bi1, bi2, bi3, bi4, bi5;
	BufferedImage pi[];
	Mat imageArray, part, mosaic, binary, pic1;
	int width, height;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	boolean flag = true;
	String path = "/usr/local/Cellar/opencv/4.2.0_1/share/opencv4/haarcascades/"; //this path might be different with professor 
	String cascade = "haarcascade_frontalface_alt2.xml";
	String fn1 = "save.jpg";
	CascadeClassifier faceDetector;
	MatOfRect faceDetections;
	Rect[] faceArea = null;
	int count = 0;
	int xCenter, yCenter;
	double rotate = 0.0;
	
	
	public Final() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		setTitle("Final Presentation");
		setSize(1310,660);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btn1 = new JButton ("TakePic");
		btn1.addActionListener(this);
		btn3 = new JButton ("Mosaic");
		btn3.addActionListener(this);
		btn4 = new JButton ("SaveImage");
		btn4.addActionListener(this);
		btn5 = new JButton ("ChangeImage");
		btn5.addActionListener(this);
		btn2 = new JButton ("LoadImage");
		btn2.addActionListener(this);
		btn6 = new JButton ("Left Rotation");
		btn6.addActionListener(this);
		btn7 = new JButton ("Right Rotation");
		btn7.addActionListener(this);
		
		JPanel p = new JPanel();
		p.add(btn1);
		p.add(btn3);
		p.add(btn5);
		p.add(btn4);
		p.add(btn2);
		p.add(btn6);
		p.add(btn7);
		getContentPane().add(p,BorderLayout.NORTH);
		setVisible(true);
		faceDetector = new CascadeClassifier(path + cascade);
		cameraImage();
		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(bi1 != null) {
			g.drawImage(bi1, 10, 60, width, height, this);
		}
		if(faceArea != null) {
			g.setColor(Color.yellow);
			for(Rect rect : faceArea) {
				g.drawRect(rect.x/2 + 10,rect.y/2 + 60,rect.width/2,rect.height/2);
			}
			for(int i=0; i<count; i++) {
				g.drawImage(pi[i], 10+130*i, 450, 150, 150, this);
			}
		}
		if(bi2 != null) {
			g.drawImage(bi2, 660, 60, width, height, this);
		}
		if(bi5 != null) {
			g.drawImage(bi5, 660, 60, width, height, this);
		}
	}
	
	public void cameraImage() {
		imageArray = new Mat();
		VideoCapture videoDevice = new VideoCapture(0);
		if (videoDevice.isOpened()) {
			videoDevice.read(imageArray);
			while(imageArray.cols()==0) {
				System.out.println("imageArray is null.");
				videoDevice.read(imageArray);
			}
			bi1 = matToBufferedImage(imageArray);
			width = bi1.getWidth()/2;
			height = bi1.getHeight()/2;
			while(flag) {
				videoDevice.read(imageArray);
				bi1 = matToBufferedImage(imageArray);
				faceDetections = new MatOfRect();
				faceDetector.detectMultiScale(imageArray, faceDetections);
				faceArea = faceDetections.toArray();
				repaint();
			}
			videoDevice.release();
		}else {
			System.out.println("Error.");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) { //takepicture(camera)
			flag = false;
			getFace();
		}
		if(e.getSource() == btn3) { //mosaic
			mosaic = new Mat(imageArray, faceArea[0]);
			Imgproc.resize(mosaic,  mosaic,  new Size(), 0.05,0.05,Imgproc.INTER_NEAREST);
			Imgproc.resize(mosaic,  mosaic,  new Size(), 10.0,10.0,Imgproc.INTER_NEAREST);
			bi3 = matToBufferedImage(mosaic);		
			getFace();
		}
		if(e.getSource() == btn5) { //change image
			for (Rect rect : faceArea) {
				Graphics g = getGraphics();
				g.drawImage(bi3, rect.x/2 + 10,  rect.y /2 + 60, rect.width/2, rect.height/2,this);
				}
		}
		if(e.getSource() == btn4) { //save image
			
			BufferedImage mergedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = (Graphics2D) mergedImage.getGraphics();
			for (Rect rect : faceArea) {
				graphics.setBackground(Color.white);
				graphics.drawImage(bi1, 0, 0, bi1.getWidth()/2, bi1.getHeight()/2, null);
				graphics.drawImage(bi3, rect.x/2 , rect.y /2 , rect.width/2  , rect.height/2 ,  null);
			}
			File output = new File("save.jpg");
			try {
				ImageIO.write(mergedImage, "jpg", output);
			} catch(IOException e1) {
				System.out.println(e1);
			}
		}
		if(e.getSource() == btn2) { //load image
			Pic1();
			repaint();	
		}
		if(e.getSource() == btn6) { //left rotation
			rotate -= Math.toRadians(1.0);
			Rotate();
			repaint();
		}
		if(e.getSource() == btn7) { //right rotation
			rotate += Math.toRadians(1.0);
			Rotate();
			repaint();
		}
	}
	
	void Rotate() {
		xCenter = bi2.getWidth() / 2;
		yCenter = bi2.getHeight() / 2;
		bi5 = new BufferedImage(bi2.getWidth(), bi2.getHeight(), bi2.getType());
		AffineTransform affine = new AffineTransform();
		affine.translate(xCenter, yCenter);
		affine.rotate(rotate);
		affine.translate(-xCenter, -yCenter);
		AffineTransformOp operator = new AffineTransformOp(affine, AffineTransformOp.TYPE_BICUBIC);
		operator.filter(bi2, bi5);
	}
		
	void Pic1() {
		pic1 = new Mat();
		pic1 =Imgcodecs.imread(fn1);
		bi2 = matToBufferedImage(pic1);
	}

	void getFace() {
		Graphics g = getGraphics();	
		if(faceArea != null) {
			count = faceArea.length;
			if(count>1) {
				count=1;
			}
			pi = new BufferedImage[count];
			for(int i=0; i<count; i++) {
				part = new Mat(imageArray, faceArea[i]);
				pi[i] = matToBufferedImage(part);
			}
		}
		if(bi3 != null) {
			g.drawImage(bi3, 170, 450, 150, 150, this);
			}
	}
	
	public static void main(String[] args) {
		new Final();
		
	}
	
	public static BufferedImage matToBufferedImage(Mat matrix) {
		int cols = matrix.cols();
		int rows = matrix.rows();
		int elemSize = (int)matrix.elemSize();
		byte[] data = new byte[cols*rows*elemSize];
		int type;
		
		matrix.get(0,0,data);
		
		switch (matrix.channels()) {
		case 1:
			type = BufferedImage.TYPE_BYTE_GRAY;
			break;
		case 3:
			type = BufferedImage.TYPE_3BYTE_BGR;
			byte b;
			for(int i = 0; i < data.length; i = i + 3) {
				b = data[i];
				data[i] = data[i+2];
				data[i+2] = b;
			}
			break;
		default:
			return null;
		}
		BufferedImage image2 = new BufferedImage(cols,rows,type);
		image2.getRaster().setDataElements(0, 0, cols, rows, data);
		return image2;
	}

}
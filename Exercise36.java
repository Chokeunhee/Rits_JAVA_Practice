/*
 * Exercise36 using camera to detect face using classifier haarcascade_frontalface_alt2
 * 			  display the face detected with 120*120 size with 10pix interval
 * By cho keun hee
 * on Jan 14th 2020
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class Exercise36 extends JFrame implements ActionListener{
	BufferedImage bimage1;
	BufferedImage bimage[];
	Mat imageArray, part;
	int width, height;
	JButton button;
	boolean flag = true;
	String path = "/usr/local/Cellar/opencv/4.2.0_1/share/opencv4/haarcascades/";
	String cascade = "haarcascade_frontalface_alt2.xml";
	Rect[] area = null;
	CascadeClassifier faceDetector;
	MatOfRect faceDetections;
	Rect[] faceArea = null;
	int count = 0;
	
	public Exercise36() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		setTitle("FaceCamera");
		setSize(660,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button = new JButton("stop");
		button.setActionCommand("stop");
		button.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(button);
		getContentPane().add(panel,BorderLayout.NORTH);
		setVisible(true);
		faceDetector = new CascadeClassifier(path + cascade);
		cameraImage();
	}
	
	public void paint(Graphics g) {
		if(bimage1 != null) {
			g.drawImage(bimage1, 10, 60, width, height, this);
		}
		if(faceArea != null) {
			g.setColor(Color.yellow);
			for(Rect rect : faceArea) {
				g.drawRect(rect.x/2 + 10,rect.y/2 + 60,rect.width/2,rect.height/2);
			}
			for(int i=0; i<count; i++) {
				g.drawImage(bimage[i], 10+130*i, 450, 120, 120, this);
			}
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
			bimage1 = matToBufferedImage(imageArray);
			System.out.println("width = "+bimage1.getWidth()+"height = "+bimage1.getHeight());
			width = bimage1.getWidth()/2;
			height = bimage1.getHeight()/2;
			while(flag) {
				videoDevice.read(imageArray);
				bimage1 = matToBufferedImage(imageArray);
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
		if(e.getActionCommand().equals("stop")) {
			flag = false;
			getFace();
		}
	}
	
	void getFace() {
		if(faceArea != null) {
			count = faceArea.length;
			if(count>5) {
				count=5;
			}
			bimage = new BufferedImage[count];
			for(int i=0; i<count; i++) {
				part = new Mat(imageArray, faceArea[i]);
				bimage[i] = matToBufferedImage(part);
			}
		}
	}
	

	public static void main(String[] args) {
		new Exercise36();
		// TODO Auto-generated method stub
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

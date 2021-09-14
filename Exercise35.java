/*
 * Exercise35 Display picture "imageC.jpg"
 * 			  Detect face using classifier haarcascade_frontalface_default color yellow
 * 			  Display the face image detect by 100*100 size
 * by cho keun hee
 * on Jan 14th 2020
 */




import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class Exercise35 extends JFrame{
	BufferedImage bimage1;
	BufferedImage bimage[];
	Mat src, part;
	String filename = "imageC.jpg";
	String path = "/usr/local/Cellar/opencv/4.2.0_1/share/opencv4/haarcascades/";
	String cascade = "haarcascade_frontalface_default.xml";
	Rect[] faceArea = null;
	int count = 0;
	
	public Exercise35() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		setSize(660,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		detect();
		repaint();
	}
	
	public void paint(Graphics g) {
		if(bimage1 != null) {
			g.drawImage(bimage1, 10, 60, this);
		}
		if(faceArea != null) {
			g.setColor(Color.yellow);
			for(Rect rect : faceArea) {
				g.drawRect(rect.x+10,rect.y+60,rect.width,rect.height);
			}
			for(int i=0; i<count; i++) {
				g.drawImage(bimage[i], 10+110*i, 550, 100,100, this);
			}
		}
	}
	
	void detect() {
		src = new Mat();
		src = Imgcodecs.imread(filename);
		bimage1=matToBufferedImage(src);
		faceArea = getArea(cascade);
		if(faceArea != null) {
			count = faceArea.length;
			if(count>5) {
				count = 5;
			}
			bimage = new BufferedImage[count];
			for(int i = 0; i<count; i++) {
				part = new Mat(src,faceArea[i]);
				bimage[i] = matToBufferedImage(part);
			}
		}
	}
	
	Rect[] getArea(String str) {
		Rect[] area = null;
		CascadeClassifier faceDetector = new CascadeClassifier(path + str);
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(src, faceDetections);
		System.out.println("Detected " + faceDetections.toArray().length + " faces using " + str);
		area = faceDetections.toArray();
		return area;
	}

	public static void main(String[] args) {
		new Exercise35();
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

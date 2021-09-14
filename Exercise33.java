/* Exercise 33 taking picture using camera and selcting the part and modify
 * by cho keun hee
 * on jan  7th 2020
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Exercise33 extends JFrame implements ActionListener {
	BufferedImage bimage1, bimage2,bimage3,bimage4;
	Mat imageArray, gray,mosaic,part;
	int width, height;
	int partwidth, partheight;
	JButton button;
	int[][] rect = {{0, 0}, {0, 0}};
	boolean flag = true;

 public Exercise33() {
	 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	 setTitle("Exercise 33");
	 setSize(660, 700);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 button = new JButton("stop");
	 button.setActionCommand("stop");
	 button.addActionListener(this);
	 JPanel panel = new JPanel();
	 panel.add(button);
	 getContentPane().add(panel, BorderLayout.NORTH);
	 setVisible(true);
	 MyListener myListener = new MyListener();
	 addMouseListener(myListener);
	 addMouseMotionListener(myListener);
	 cameraImage();
 }

 public void paint(Graphics g){
	 if(bimage1 != null){
		 g.drawImage(bimage1, 10, 60, width, height, this);
	 }
	 if(bimage2 != null){
		 g.drawImage(bimage2, 10, 430, partwidth, partheight, this);
	 }
	 if(bimage3 != null){
		 g.drawImage(bimage3, 220, 430, partwidth, partheight, this);
	 }
	 if(bimage4 != null){
		 g.drawImage(bimage4, 430, 430, partwidth, partheight, this);
	 }
	 g.setColor(Color.red);
	 g.drawRect(rect[0][0], rect[0][1], rect[1][0] - rect[0][0],rect[1][1] - rect[0][1]);
 	}

 public void cameraImage(){
	 imageArray = new Mat();
	 VideoCapture videoDevice = new VideoCapture(0);
	 if (videoDevice.isOpened()) {
		 videoDevice.read(imageArray);
		 while(imageArray.cols()==0) {
			 videoDevice.read(imageArray);
		 }
		 bimage1 = matToBufferedImage(imageArray);
		 System.out.println("width = " + bimage1.getWidth() + " height = " + bimage1.getHeight());
		 width = bimage1.getWidth() / 2;
		 height = bimage1.getHeight() / 2;
		 while(flag) {
			 videoDevice.read(imageArray);
			 bimage1 = matToBufferedImage(imageArray);
			 repaint();
		 }
		 videoDevice.release();
	 } else {
		 System.out.println("Error.");
	 }
 	}
 
 
 public void partImage() {
	 double rat;
	 Rect roi = new Rect((rect[0][0] - 10)*2, (rect[0][1] - 60)*2, (rect[1][0] - rect[0][0])*2, (rect[1][1] - rect[0][1])*2);
	 part = new Mat(imageArray, roi);
	 if(part.cols() > part.rows()) {
		 if(part.cols() > 200) {
			 rat = 200.0 / part.cols();} else {
				 rat = 1.0;
			 }
	 } else {if(part.rows() > 200) {
		 rat = 200.0/ part.rows();
	 }
	 else
		 rat = 1.0;
	 }
	 partwidth = (int)(rat * part.cols());
	 partheight = (int)(rat * part.rows());
	 bimage2 = matToBufferedImage(part);
	 mosaic = new Mat(imageArray, roi);
	 gray = new Mat();
	 Imgproc.cvtColor(part, gray, Imgproc.COLOR_BGR2GRAY);
	 Imgproc.resize(mosaic, mosaic, new Size(), 0.1, 0.1, Imgproc.INTER_NEAREST);
	 Imgproc.resize(mosaic, mosaic, new Size(), 10.0, 10.0, Imgproc.INTER_NEAREST);
	 bimage3 = matToBufferedImage(gray);
	 bimage4 = matToBufferedImage(mosaic);
	 repaint();
 	}

 public void actionPerformed(ActionEvent e){
	 if(e.getActionCommand().equals("stop")) {
		 flag = false;

	 }
 }

 public static void main(String[] args) {
	 new Exercise33();
 }

 public static BufferedImage matToBufferedImage(Mat matrix) {
	 int cols = matrix.cols();  
	 int rows = matrix.rows();  
	 int elemSize = (int)matrix.elemSize();  
	 byte[] data = new byte[cols * rows * elemSize];  
	 int type;
	 matrix.get(0, 0, data);  
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
	 BufferedImage image2 = new BufferedImage(cols, rows, type);  
	 image2.getRaster().setDataElements(0, 0, cols, rows, data);  
	 return image2;  
 	}
 class MyListener extends MouseAdapter {
	 public void mousePressed(MouseEvent e) {
		 rect[0][0] = e.getX();
		 rect[0][1] = e.getY();
	 }
	 public void mouseReleased(MouseEvent e){
		 partImage();
		 repaint(); 
	 }
	 public void mouseDragged(MouseEvent e) { 
		 rect[1][0] = e.getX();
		 rect[1][1] = e.getY();
		 repaint();
	 }
 	}
}
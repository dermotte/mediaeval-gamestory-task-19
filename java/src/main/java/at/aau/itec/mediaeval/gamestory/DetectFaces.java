package at.aau.itec.mediaeval.gamestory;



import java.io.File;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

public class DetectFaces {
//    static String videoFile = "/home/mlux/dakotaz.mp4";
    static String videoFile = "/home/mlux/tmp/gamestory-2018/gamestory18-train/train_set/2018-03-02_P11.mp4";

    public static void main(String[] args) throws Exception {
        long currentFrame = 0;
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

//        if (args.length < 2) {
//            System.out.println("Two parameters are required to run this program, first parameter is the analized video and second parameter is the trained result for fisher faces.");
//        }

        String videoFileName = videoFile;
//        String trainedResult = args[1];


        CascadeClassifier face_cascade = new CascadeClassifier("../resources/haarcascade_frontalface_alt.xml");
//        FaceRecognizer lbphFaceRecognizer = LBPHFaceRecognizer.create();
//        lbphFaceRecognizer.read(trainedResult);

        File f = new File(videoFileName);

        FFmpegFrameGrabber grabber = null;
        try {
            grabber = new FFmpegFrameGrabber(f);
            grabber.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed start the grabber.");
        }

        Frame videoFrame = null;
        Mat videoMat = new Mat();
        while (true) {
            videoFrame = grabber.grabImage();
            videoMat = converterToMat.convert(videoFrame);
            Mat videoMatGray = new Mat();
            // Convert the current frame to grayscale:
            cvtColor(videoMat, videoMatGray, COLOR_BGRA2GRAY);
            equalizeHist(videoMatGray, videoMatGray);

//            Point p = new Point();
            RectVector faces = new RectVector();
            // Find the faces in the frame:
            face_cascade.detectMultiScale(videoMatGray, faces);
            System.out.println(++currentFrame + "\t" + faces.size());

            // At this point you have the position of the faces in
            // faces. Now we'll get the faces, make a prediction and
            // annotate it in the video. Cool or what?
            for (int i = 0; i < faces.size(); i++) {
                Rect face_i = faces.get(i);

//                Mat face = new Mat(videoMatGray, face_i);
                // If fisher face recognizer is used, the face need to be
                // resized.
                // resize(face, face_resized, new Size(im_width, im_height),
                // 1.0, 1.0, INTER_CUBIC);

                // Now perform the prediction, see how easy that is:
//                IntPointer label = new IntPointer(1);
//                DoublePointer confidence = new DoublePointer(1);
//                lbphFaceRecognizer.predict(face, label, confidence);
//                int prediction = label.get(0);

                // And finally write all we've found out to the original image!
                // First of all draw a green rectangle around the detected face:
                rectangle(videoMat, face_i, new Scalar(0, 255, 0, 1));

//                // Create the text we will annotate the box with:
//                String box_text = "Prediction = ";// + prediction;
//                // Calculate the position for annotated text (make sure we don't
//                // put illegal values in there):
//                int pos_x = Math.max(face_i.tl().x() - 10, 0);
//                int pos_y = Math.max(face_i.tl().y() - 10, 0);
//                // And now put it into the image:
//                putText(videoMat, box_text, new Point(pos_x, pos_y),
//                        FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));
            }
            // Show the result:
            imshow("face_detection", videoMat);
            waitKey(1);
//            char key = (char) waitKey(1);
//            // Exit this loop on escape:
//            if (key == 27) {
//                destroyAllWindows();
//                break;
//            }
        }
    }
}

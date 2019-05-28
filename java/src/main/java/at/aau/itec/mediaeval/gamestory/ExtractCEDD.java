package at.aau.itec.mediaeval.gamestory;

import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Extracts CEDD per frame from a video file using LIRE. Basically works with all possible features,
 * but the pretty print method might have to be re-written.
 */
public class ExtractCEDD {
    static String videoFile = "~/tmp/gamestory-2018/gamestory18-train/train_set/2018-03-02_P1.mp4";

    public static void main(String[] args) throws IOException, Exception {
        // init FFMPEG & helper vars
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile);
        frameGrabber.start();
        Frame frame;
        long frameCount = 0;
        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
        // init LIRE extraction
        CEDD cedd = new CEDD();
        try {
            while ((frame = frameGrabber.grabImage()) != null) {
                BufferedImage bi = java2DFrameConverter.getBufferedImage(frame);
                cedd.extract(bi);

                System.out.println(++frameCount + "\t" + prettyPrint(cedd.getByteHistogram()));
                // ImageIO.write(bi, "png", new File("img.png"));
            }
            frameGrabber.stop();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * Takes a byte array and makes a String for CSV files out of it (tab separated).
     * @param hist a byte array
     * @return a String for CSV files (tab separated)
     */
    static String prettyPrint(byte[] hist) {
        StringWriter sw = new StringWriter(144 * 3);
        for (int i = 0; i < hist.length; i++) {
            sw.append(Byte.toString(hist[i]));
            if (i < hist.length - 1) sw.append('\t');
        }
        return sw.toString();
    }
}

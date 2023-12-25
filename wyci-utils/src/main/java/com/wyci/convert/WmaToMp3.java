package com.wyci.convert;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;

/**
 * @Description 音频转换
 * @Author wangshuo
 * @Date 2023-11-23 14:49
 * @Version V1.0
 */
public class WmaToMp3 {

//    public static void main(String[] args) throws IOException, NoProcessorException, CannotRealizeException {
//        File inputFile = new File("input.wma");
//        File outputFile = new File("output.mp3");
//
//        Processor processor = Manager.createProcessor(new MediaLocator(inputFile.toURI().toURL()));
//        processor.configure();
//        while (processor.getState() != Processor.Configured) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        TrackControl[] tracks = processor.getTrackControls();
//        for (int i = 0; i < tracks.length; i++) {
//            Format format = tracks[i].getFormat();
//            if (format instanceof AudioFormat) {
//                AudioFormat af = (AudioFormat) format;
//                AudioFormat mp3Format = new AudioFormat(AudioFormat.MPEGLAYER3);
//                tracks[i].setFormat(mp3Format);
//            } else {
//                tracks[i].setEnabled(false);
//            }
//        }
//
//        processor.realize();
//        while (processor.getState() != Processor.Realized) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        DataSource ds = processor.getDataOutput();
//        DataSink dsink = Manager.createDataSink(ds, new MediaLocator(outputFile.toURI().toURL()));
//        dsink.open();
//        dsink.start();
//        processor.start();
//        while (processor.getState() != Processor.Started) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        while (processor.getState() != Processor.Prefetched) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        processor.stop();
//        processor.close();
//        dsink.stop();
//        dsink.close();
//    }


}

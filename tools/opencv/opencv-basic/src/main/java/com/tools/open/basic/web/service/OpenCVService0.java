package com.tools.open.basic.web.service;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.Arrays;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/22 17:25
 */
public class OpenCVService0 {
    static CascadeClassifier faceDetector;

    static {
        try {
            // 加载 OpenCV 本地库
            String dllPath = "D:\\home\\opencv\\opencv_java455.dll";
            System.load(dllPath);
            // 初始化人脸检测分类器
            String xmlPath = "D:\\home\\opencv\\haarcascade_frontalface_alt.xml";
            faceDetector = new CascadeClassifier(xmlPath);
            if (faceDetector.empty()) {
                throw new RuntimeException("分类器初始化失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("OpenCV 初始化失败", e);
        }
    }


    public static void faceIdentify() {
        String path = "D:\\home\\face\\1.png";
        String outPath = "D:\\home\\face\\1_1.png";
        Mat image = Imgcodecs.imread(path);
        // 执行人脸检测
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        Rect[] rects = faceDetections.toArray();
        // 遍历每张人脸，画框 + 标注
        Arrays.stream(rects).forEach(rect -> {
            Imgproc.rectangle(image
                    , new Point(rect.x - 2, rect.y - 2)
                    , new Point(rect.x + rect.width, rect.y + rect.height)
                    , new Scalar(0, 0, 255)
                    , 2);   // 红色框，线宽2px
            Imgproc.putText(image, "face", new Point(rect.x - 4, rect.y - 4), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, new Scalar(0, 0, 255), 1, Imgproc.LINE_AA, false);
        });
        Imgcodecs.imwrite(outPath, image);
        image.release();
    }

    public static void faceComparison() {
        String path = "D:\\home\\face\\1.png";
        String pathCompare = "D:\\home\\face\\2.png";
        Mat mat = Imgcodecs.imread(path);
        Mat matCompare = Imgcodecs.imread(pathCompare);
        // 灰度化 + 截取人脸
        Mat matGray = matGray(mat);
        Mat matCompareGray = matGray(matCompare);
        // 计算直方图
        Mat temp = new Mat();
        Mat tempCompare = new Mat();
        Imgproc.calcHist(List.of(matGray), new MatOfInt(0),
                new Mat(), temp, new MatOfInt(1000), new MatOfFloat(0f, 256f));
        Imgproc.calcHist(List.of(matCompareGray), new MatOfInt(0),
                new Mat(), tempCompare, new MatOfInt(1000), new MatOfFloat(0f, 256f));
        // 计算相似度（相关系数法）
        double res = Imgproc.compareHist(temp, tempCompare, Imgproc.CV_COMP_CORREL);
        System.out.println("匹配值：" + res);
        System.out.println("结论：" + (res > 0.8 ? "同一个人" : "不是同一个人"));
    }

    public static Mat matGray(Mat mat) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(mat, faceDetections);
        Rect[] rects = faceDetections.toArray();
        Mat faceMat = mat.submat(rects[0]);
        // 截取第一张人脸
        Mat matGray = new Mat();
        Imgproc.cvtColor(faceMat, matGray, Imgproc.COLOR_BGR2GRAY);  // 灰度化
        return matGray;
    }

    public static void main(String[] args) {
        faceComparison();   // 人脸比对
        faceIdentify();     // 人脸画框
    }
}
  



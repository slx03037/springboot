package com.tools.open.basic.web.service;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.Arrays;

/**
 * @author shenlx
 * @description OpenCV 人脸检测与比对工具类（基于 org.openpnp:opencv）
 * @date 2026/6/23 10:05
 */
public class OpenCVService {
    private static CascadeClassifier faceDetector;

    static {
        try {
            // 自动加载 OpenCV 本地库（由 org.openpnp 提供）
            nu.pattern.OpenCV.loadLocally();
            // 或者使用 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            // 加载人脸检测模型（建议从 classpath 读取）
            String xmlPath = getClassifierPath();
            faceDetector = new CascadeClassifier(xmlPath);
            if (faceDetector.empty()) {
                throw new RuntimeException("人脸检测分类器加载失败，请检查 XML 文件路径");
            }
        } catch (Exception e) {
            throw new RuntimeException("OpenCV 初始化失败", e);
        }
    }

    /**
     * 获取分类器 XML 文件路径（可改为从配置文件读取）
     */
    private static String getClassifierPath() {
        // 方式1：从 resources 目录加载
        // URL url = OpenCVService.class.getResource("/haarcascade_frontalface_alt.xml");
        // return url.getPath();
        // 方式2：使用绝对路径（建议使用外部配置）
        return "D:\\home\\opencv\\haarcascade_frontalface_alt.xml";
    }

    /**
     * 人脸检测并标注，保存结果图片
     * @param inputPath  输入图片路径
     * @param outputPath 输出图片路径
     * @throws IllegalArgumentException 如果图片不存在或无人脸
     */
    public static void detectAndMark(String inputPath, String outputPath) {
        Mat image = Imgcodecs.imread(inputPath);
        if (image.empty()) {
            throw new IllegalArgumentException("无法读取图片: " + inputPath);
        }

        MatOfRect faceDetections = new MatOfRect();
        try {
            faceDetector.detectMultiScale(image, faceDetections);
            Rect[] rects = faceDetections.toArray();
            if (rects.length == 0) {
                System.out.println("未检测到人脸，原图直接输出");
            } else {
                for (Rect rect : rects) {
                    // 直接使用 rect.tl() 和 rect.br()，避免坐标越界
                    Imgproc.rectangle(image, rect.tl(), rect.br(),
                            new Scalar(0, 0, 255), 2);
                    Imgproc.putText(image, "face",
                            new Point(rect.x, rect.y - 5), // 放在框上方
                            Imgproc.FONT_HERSHEY_SIMPLEX, 0.8,
                            new Scalar(0, 0, 255), 1, Imgproc.LINE_AA, false);
                }
            }
            Imgcodecs.imwrite(outputPath, image);
            System.out.println("结果已保存至: " + outputPath);
        } finally {
            image.release();
            faceDetections.release();
        }
    }

    /**
     * 比较两张图片中人脸的相似度（基于直方图相关系数）
     * @param imgPath1 第一张图片
     * @param imgPath2 第二张图片
     * @return 相似度值（-1 ~ 1），值越大越相似
     */
    public static double compareFaces(String imgPath1, String imgPath2) {
        Mat mat1 = Imgcodecs.imread(imgPath1);
        Mat mat2 = Imgcodecs.imread(imgPath2);
        if (mat1.empty() || mat2.empty()) {
            throw new IllegalArgumentException("图片读取失败");
        }

        // 提取人脸灰度区域
        Mat gray1 = extractFaceGray(mat1);
        Mat gray2 = extractFaceGray(mat2);
        if (gray1 == null || gray2 == null) {
            throw new RuntimeException("其中一张图片未检测到人脸，无法比较");
        }

        try {
            // 计算直方图（灰度图，bins=256）
            Mat hist1 = new Mat();
            Mat hist2 = new Mat();
            MatOfInt channels = new MatOfInt(0);
            MatOfInt histSize = new MatOfInt(256);
            MatOfFloat ranges = new MatOfFloat(0f, 256f);

            Imgproc.calcHist(Arrays.asList(gray1), channels, new Mat(),
                    hist1, histSize, ranges);
            Imgproc.calcHist(Arrays.asList(gray2), channels, new Mat(),
                    hist2, histSize, ranges);

            // 使用相关系数比较（OpenCV 4.x 推荐 HISTCMP_CORREL）
            double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);
            return similarity;
        } finally {
            // 释放中间 Mat
            if (gray1 != null) {
                gray1.release();
            }
            if (gray2 != null) {
                gray2.release();
            }
            mat1.release();
            mat2.release();
        }
    }

    /**
     * 从原始 Mat 中提取第一张人脸并转为灰度图
     * @param src 原始 BGR 图片
     * @return 人脸灰度 Mat，若无人脸则返回 null
     */
    private static Mat extractFaceGray(Mat src) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(src, faceDetections);
        Rect[] rects = faceDetections.toArray();
        if (rects.length == 0) {
            faceDetections.release();
            return null;
        }
        // 截取第一张人脸
        Mat face = new Mat(src, rects[0]);
        Mat gray = new Mat();
        Imgproc.cvtColor(face, gray, Imgproc.COLOR_BGR2GRAY);
        face.release();
        faceDetections.release();
        return gray;
    }

    // ---------- 测试入口 ----------
    public static void main(String[] args) {
        // 测试人脸检测
        //detectAndMark("D:\\home\\face\\1.png", "D:\\home\\face\\1_3.png");

        // 测试人脸比对
        double sim = compareFaces("D:\\home\\face\\1_1.png", "D:\\home\\face\\1_2.png");
        System.out.println("相似度: " + sim);
        System.out.println("结论: " + (sim > 0.8 ? "同一个人" : "不是同一个人"));
    }
}

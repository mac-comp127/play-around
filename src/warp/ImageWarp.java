package warp;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import transform.PointTransform;
import transform.Squinch;
import transform.Warble;

public class ImageWarp {
    public static void main(String[] args) {
        PixelArray input = new PixelArray(new Image("images/coo.jpeg"));

        int outSize = 1024;
        PixelArray output = new PixelArray(outSize, outSize, input.getChannelCount());

        CanvasWindow canvas = new CanvasWindow("Warp", outSize, outSize);
        canvas.setBackground(Color.BLACK);

        for (double x = -5; x < 5; x += 0.02) {
            PointTransform transform = new Squinch(x, -x / 3);
            Image image = createWarp(input, output, transform);
            canvas.removeAll();
            canvas.add(image);
            canvas.draw();
        }
    }

    private static Image createWarp(PixelArray input, PixelArray output, PointTransform transform) {
        int midX = input.getWidth() / 2;
        int midY = input.getHeight() / 2;
        double inScale = 1.0 / Math.min(midX, midY);

        int outSize = output.getWidth();
        double outScale = 3.0 / outSize;

        for (int outY = 0; outY < output.getHeight(); outY++) {
            for (int outX = 0; outX < output.getWidth(); outX++) {
                Point outPoint = new Point(
                    (outX - outSize / 2) * outScale,
                    (outY - outSize / 2) * outScale
                );
                Point inPoint = transform.apply(outPoint);
                int inX = (int) Math.round(inPoint.getX() / inScale + midX);
                int inY = (int) Math.round(inPoint.getY() / inScale + midY);

                for (int c = 0; c < input.getChannelCount(); c++) {
                    byte pix = input.getPixel(inX, inY, c);
                    output.setPixel(outX, outY, c, pix);
                }
            }
        }

        Image image = output.toImage();
        return image;
    }
}

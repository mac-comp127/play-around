package warp;

import edu.macalester.graphics.Image;

public class PixelArray {
    private final int width, height;
    private final int channelCount;
    private final byte[] pixels;
    
    public PixelArray(Image image) {
        this(
            image.getImageWidth(),
            image.getImageHeight(),
            4,
            image.toByteArray(Image.PixelFormat.ARGB)
        );
    }

    public PixelArray(int width, int height, int channelCount) {
        this(width, height, channelCount, new byte[width * height * channelCount]);
    }

    private PixelArray(int width, int height, int channelCount, byte[] pixels) {
        this.width = width;
        this.height = height;
        this.channelCount = channelCount;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public byte getPixel(int x, int y, int chan) {
        if (outOfBounds(x, y)) {
            return 0;
        }
        return pixels[pixelIndex(x, y, chan)];
    }

    public void setPixel(int x, int y, int chan, byte value) {
        if (!outOfBounds(x, y)) {
            pixels[pixelIndex(x, y, chan)] = value;
        }
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    private int pixelIndex(int x, int y, int chan) {
        return (x + y * width) * channelCount + chan;
    }

    public Image toImage() {
        return new Image(width, height, pixels, Image.PixelFormat.ARGB);
    }
}

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprite {
    private Image image;
    private boolean isPartOfSpritesheet = false;
    // Position relative to spritesheet image top left
    private short sheetOffsetX;
    private short sheetOffsetY;
    public short width;
    public short height;

    public Sprite(Sprite spriteSheetImage, int colIndex, int rowIndex, int tileWidth, int tileHeight) {
        this.image = spriteSheetImage.image;
        this.width = (short)tileWidth;
        this.height = (short)tileHeight;
        // Adding the offset of spriteSheetImage can be useful
        // if the spritesheet provided is also part of a spritesheet
        // (e.g. an image where every row is an animation sheet for a unit)
        this.sheetOffsetX = (short)(colIndex * tileWidth + spriteSheetImage.sheetOffsetX);
        this.sheetOffsetY = (short)(rowIndex * tileHeight + spriteSheetImage.sheetOffsetY);
        this.isPartOfSpritesheet = true;
    }

    public Sprite(String filename) throws Exception {
        byte[] imageData = AppCanvas.getFileBytes(filename);
        this.image = Image.createImage(imageData, 0, imageData.length);
        this.width = (short)this.image.getWidth();
        this.height = (short)this.image.getHeight();
        this.isPartOfSpritesheet = false;
    }

    public void draw(Graphics graphics, int x, int y) {
        if (this.isPartOfSpritesheet) {   
            // TODO motorola versions are much simpler, they use drawRegion
            int clipX = graphics.getClipX();
            int clipY = graphics.getClipY();
            int clipWidth = graphics.getClipWidth();
            int clipHeight = graphics.getClipHeight();
            // Without the clipRect() the game will draw the whole spritesheet
            // "Rendering operations have no effect outside of the clipping area."
            // See "Clipping" section:
            // https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Graphics.html
            graphics.clipRect(x, y, (int)this.width, (int)this.height);
            graphics.drawImage(this.image, x - this.sheetOffsetX, y - this.sheetOffsetY, 20);
            // Resets the clip area to its previous state
            graphics.setClip(clipX, clipY, clipWidth, clipHeight);
        } else {
            // TODO dehardcode anchor argument
            graphics.drawImage(this.image, x, y, 20);
        }
    }

    // TODO this is called only once in the method below, could be removed
    private Sprite() {
    }

    public static Sprite fromByteArray(byte[] imageData, byte playerIndex) {
        if (playerIndex != Class_I.PLAYER_BLUE) {
            byte[] bytesCopy = new byte[imageData.length];
            System.arraycopy(imageData, 0, bytesCopy, 0, imageData.length);
            Sprite.void_a(bytesCopy, playerIndex);
            imageData = bytesCopy;
        }
        Sprite newSprite = new Sprite();
        Image image = Image.createImage(imageData, 0, imageData.length);
        newSprite.width = (short)image.getWidth();
        newSprite.height = (short)image.getHeight();
        newSprite.image = image;
        return newSprite;
    }

    // TODO since there are images only for blu units, I suspect this creates the red versino
    private static void void_a(byte[] imageBytes, byte playerIndex) {
        try {
            int n5 = 33;
            int n6 = imageBytes.length - 3;
            // This loop looks for a specific pattern and stores
            // the index 4 bytes before the start of the sequence
            // rgb(80, 76, 84) is a dark gray
            for (int i = 0; i < n6; ++i) {
                if (imageBytes[i] != 80 || imageBytes[i + 1] != 76 || imageBytes[i + 2] != 84)
                    continue;
                n5 = i - 4;
                break;
            }
            int n4 = n5;
            n6 = ((imageBytes[n4] & 0xFF) << 24 | (imageBytes[n4 + 1] & 0xFF) << 16 | (imageBytes[n4 + 2] & 0xFF) << 8 | imageBytes[n4 + 3] & 0xFF) & 0xFFFFFFFF;
            n4 += 4;
            int n7 = -1;
            for (int i = 0; i < 4; ++i) {
                n7 = Sprite.a(imageBytes[n4 + i], n7);
            }
            for (int i = n4 += 4; i < n4 + n6; i += 3) {
                int R = imageBytes[i] & 0xFF;
                int G = imageBytes[i + 1] & 0xFF;
                int B = imageBytes[i + 2] & 0xFF;
                if (B > R && B > G) {
                    if (playerIndex == Class_I.PLAYER_RED) {
                        int n11 = R;
                        R = B;
                        B = n11;
                        G /= 2;
                    } else if (playerIndex == Class_I.PLAYER_NEUTRAL) {
                        R = B;
                        G = B;
                    }
                    imageBytes[i] = (byte)R;
                    imageBytes[i + 1] = (byte)G;
                    imageBytes[i + 2] = (byte)B;
                }
                n7 = Sprite.a(imageBytes[i], n7);
                n7 = Sprite.a(imageBytes[i + 1], n7);
                n7 = Sprite.a(imageBytes[i + 2], n7);
            }

            /*
             * The first version I decompiled, using a website, does this intead of the two lines below:
             * var5 = ~var5; // one's complement, inverts bits
             * var9 = var2 + 8 + var4;
             * var0[var9] = (byte)(var5 >> 24);
             */
            int index = n5 + 8 + n6;
            imageBytes[index] = (byte)((n7 ^= 0xFFFFFFFF) >> 24);
            imageBytes[index + 1] = (byte)(n7 >> 16);
            imageBytes[index + 2] = (byte)(n7 >> 8);
            imageBytes[index + 3] = (byte)n7;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // TODO after searching "0xEDB88320" I'm pretty seure this has to do with CRC checking
    private static int a(byte by, int n) {
        int n2 = by & 0xFF;
        n ^= n2;
        for (int j = 0; j < 8; ++j) {
            if ((n & 1) != 0) {
                n = n >>> 1 ^ 0xEDB88320;
            } else {
                n = n >>> 1;
            }
        }
        return n;
        
        // More compact loop: https://stackoverflow.com/a/18639999
        /*for (int j = 0; j < 8; ++j) {
            n = (n & 1) != 0 ? (n >>> 1) ^ 0xEDB88320 : (n >>> 1);
        }*/
    }
}


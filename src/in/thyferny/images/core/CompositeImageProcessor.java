
package in.thyferny.images.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import in.thyferny.images.core.io.ByteArrayOutputStream;
import in.thyferny.images.core.render.DrawTextParameter;
import in.thyferny.images.core.render.DrawTextRender;
import in.thyferny.images.core.render.ReadRender;
import in.thyferny.images.core.render.ScaleParameter;
import in.thyferny.images.core.render.ScaleRender;
import in.thyferny.images.core.render.WriteRender;
import in.thyferny.images.core.render.ScaleParameter.Algorithm;
import in.thyferny.images.core.util.ImageUtils;


@Deprecated
public final class CompositeImageProcessor {

    public static final int    FILE_CONTENT_LOG_MAX_FILE_NUM  = 50;
    public static final String FILE_CONTENT_LOG_BASE_DIR_NAME = "ibankErrorLog4FileContent";
    public static final long   EXPIRED_PERIOD                 = 7 * 24 * 60 * 60 * 1000L;

    private String             errorDir                       = null;

    static {
        ImageIO.setUseCache(false);
    }

    public CompositeImageProcessor(String errorDir){
        setErrorDir(errorDir);
    }

    public CompositeImageProcessor(){
        super();
    }

    
    public OutputStream process(InputStream inputStream, DrawTextParameter drawParam, int maxWidth, int maxHeight)
                                                                                                                  throws SimpleImageException {
        ImageRender wr = null;
        ByteArrayOutputStream output = null;

        try {
            inputStream = ImageUtils.createMemoryStream(inputStream);
            output = new ByteArrayOutputStream();
            ImageFormat outputFormat = ImageUtils.isGIF(inputStream) ? ImageFormat.GIF : ImageFormat.JPEG;
            ScaleParameter scaleParam = new ScaleParameter(maxWidth, maxHeight, Algorithm.AUTO);

            ImageRender rr = new ReadRender(inputStream, true);
            ImageRender dtr = new DrawTextRender(rr, drawParam);
            ImageRender sr = new ScaleRender(dtr, scaleParam);
            wr = new WriteRender(sr, output, outputFormat);

            wr.render();
        } catch (Exception e) {
            errorLog(inputStream);

            IOUtils.closeQuietly(output);

            if (e instanceof SimpleImageException) {
                throw (SimpleImageException) e;
            } else {
                throw new SimpleImageException(e);
            }
        } finally {
            try {
                if (wr != null) {
                    wr.dispose();
                }
            } catch (SimpleImageException ignore) {
            }
        }

        return output;
    }

    private void errorLog(InputStream is) {
        if (StringUtils.isBlank(errorDir)) {
            errorDir = System.getProperty("java.io.tmpdir") + File.separator + FILE_CONTENT_LOG_BASE_DIR_NAME;
        }

        if (StringUtils.isNotBlank(errorDir)) {
            File errorPath = new File(errorDir);
            if (!errorPath.exists()) {
                errorPath.mkdirs();
            }

            if (!canWriteErrorDir(errorPath)) {
                return;
            }

            // 错误文件夹定义存在， 及流支持重置(有些流不支持重置)
            if (errorPath.exists() && is.markSupported()) {
                OutputStream os = null;
                try {
                    is.reset();
                    File temp = new File(errorPath, "errimg-" + UUID.randomUUID().toString().replace("-", "_") + ".jpg");
                    os = new FileOutputStream(temp);
                    // write error image stream to a temp file
                    byte buffer[] = new byte[1024];
                    int count = -1;
                    while ((count = is.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                    os.flush();
                } catch (IOException igonre) {

                } finally {
                    IOUtils.closeQuietly(os);
                }
            }
        }
    }// end errorLog

    
    private boolean canWriteErrorDir(File errorPath) {
        String[] files = errorPath.list();
        if (files != null && files.length > FILE_CONTENT_LOG_MAX_FILE_NUM) {
            long now = System.currentTimeMillis();
            int delNum = 0;
            for (String filename : files) {
                File errImg = new File(errorPath, filename);
                if (now - errImg.lastModified() > EXPIRED_PERIOD) {
                    errImg.delete();
                    delNum++;
                }
            }

            if (files.length - delNum <= FILE_CONTENT_LOG_MAX_FILE_NUM) {
                return true;
            }

            return false;
        }

        return true;
    }

    
    public String getErrorDir() {
        return errorDir;
    }

    
    public void setErrorDir(String errorDir) {
        this.errorDir = errorDir;
        File errorPath = new File(errorDir);
        if (!errorPath.exists()) {
            errorPath.mkdirs();
        }
    }
}

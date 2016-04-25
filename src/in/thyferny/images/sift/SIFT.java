
package in.thyferny.images.sift;

import java.util.ArrayList;
import java.util.List;

import in.thyferny.images.sift.scale.FeaturePoint;
import in.thyferny.images.sift.scale.GaussianArray;
import in.thyferny.images.sift.scale.KDFeaturePoint;
import in.thyferny.images.sift.scale.OctaveSpace;
import in.thyferny.images.sift.scale.Pyramid;
import in.thyferny.images.sift.scale.ScalePeak;


public class SIFT {

    // 以下常数均是论文中推荐的参数值
    float preprocSigma             = 1.5f;   // 用于处理被double后的图像预处理的模糊因子
    float octaveSigma              = 1.6f;   // 用于处理每个8度空间图像的模糊因子
    int    minimumRequiredPixelsize = 32;    // 高斯金字塔中缩小时最小的尺寸
    int    scaleSpaceLevels         = 3;     // 每个8度空间需要获取极值点的差分图层，加上用于比较的上下层至少要有5个差分图像，所以至少要有6个高斯模糊图象。
    float dogThresh                = 0.0075f; // 在差分图象上的极致点值最小值，防止大片的模糊后的点被选中，这个值越小选中的点越多。
    float dValueLowThresh          = 0.008f; // 和周围点比较的差值，这个差值是经过导数运算的差值，不是直接比较的。论文中建议为0.03（page
                                              // 11），但获取的点数太少，这里修改为0.008
    float maximumEdgeRatio         = 20.0f;  // 非角点的过虑比
    float scaleAdjustThresh        = 0.50f;  // 尺度空间的精确点和真实图象上的离散点在投谢时需要调整，这个是最大调整范围，超这个值就可能是下一个点。
    float peakRelThresh            = 0.8f;   //
    int    relocationMaximum        = 4;

    public int detectFeatures(ImagePixelArray img) {
        return (detectFeaturesDownscaled(img, -1, 1.0f));
    }

    
    public int detectFeaturesDownscaled(ImagePixelArray img, int preProcessMark, float startScale) {

        if (preProcessMark < 0) {
            img = img.doubled();
            startScale *= 0.5;
        } else if (preProcessMark > 0) {
            while (img.width > preProcessMark || img.height > preProcessMark) {
                img = img.halved();
                startScale *= 2.0;
            }
        }
        if (preprocSigma > 0.0) {
            GaussianArray gaussianPre = new GaussianArray(preprocSigma);
            img = gaussianPre.convolve(img);
        }

        Pyramid pyr = new Pyramid();
        pyr.buildOctaves(img, startScale, scaleSpaceLevels, octaveSigma, minimumRequiredPixelsize);
        
        globalFeaturePoints = new ArrayList<FeaturePoint>();
        // Generate featurePoints from each scalespace.
        for (int on = 0; on < pyr.octaves.size(); ++on) {
            OctaveSpace osp = pyr.octaves.get(on);

            ArrayList<ScalePeak> peaks = osp.findPeaks(dogThresh);// 寻找图片中的极值点
            ArrayList<ScalePeak> peaksFilted = osp.filterAndLocalizePeaks(peaks, maximumEdgeRatio, dValueLowThresh,
                                                                          scaleAdjustThresh, relocationMaximum);

            // 先将要处理的图层上所有象素的梯度大小和方向计算出来
            osp.pretreatMagnitudeAndDirectionImgs();
            ArrayList<FeaturePoint> faturePoints = osp.makeFeaturePoints(peaksFilted, peakRelThresh, scaleSpaceLevels,
                                                                         octaveSigma);
            osp.clear();
            globalFeaturePoints.addAll(faturePoints);
        }
        return (globalFeaturePoints.size());

    }
    
    private List<FeaturePoint> globalFeaturePoints;
    private List<KDFeaturePoint> globalKDFeaturePoints;
    
    public List<KDFeaturePoint> getGlobalKDFeaturePoints() {

        if (globalKDFeaturePoints != null) return (globalKDFeaturePoints);
        if (globalFeaturePoints == null) throw (new IllegalArgumentException("No featurePoints generated yet."));
        globalKDFeaturePoints = new ArrayList<KDFeaturePoint>();
        for (FeaturePoint fp : globalFeaturePoints) {
            globalKDFeaturePoints.add(new KDFeaturePoint(fp));
        }
        return globalKDFeaturePoints;
    }
}


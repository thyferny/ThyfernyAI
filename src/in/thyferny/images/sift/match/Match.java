
package in.thyferny.images.sift.match;

import java.util.Comparator;

import in.thyferny.images.sift.scale.KDFeaturePoint;


public class Match {

    @Override
	public String toString() {
		return "Match [slopeArc=" + slopeArc + ", fp1=" + fp1 + ", fp2=" + fp2 + ", dist1=" + dist1 + ", dist2=" + dist2 + "]";
	}

	int slopeArc;
    public KDFeaturePoint fp1;
    public KDFeaturePoint fp2;

    public float dist1;
    public float dist2;


    // dist1: distance between fp1/kp2,
    // dist2: distance between fp1 and kp3, where kp3 is the next closest
    // match
    public Match(KDFeaturePoint fp1, KDFeaturePoint fp2, float dist1, float dist2){
        this.fp1 = fp1;
        this.fp2 = fp2;
        this.dist1 = dist1;
        this.dist2 = dist2;
    }
    
    public static class MatchWeighter implements Comparator<Match> {

        private float distExp;
        private float quotExp;

        public MatchWeighter(){
            this(1.0f, 1.0f);
        }

        // The formula goes like this, with lowest weight being best matches:
        // w(kp) = kp.dist1^{distExp} *
        // {\frac{1}{kp.dist2 - kp.dist1}}^{quotExp}
        //
        // This means, as both dist1 and dist2 are in [0.0 ; 1.0], that a high
        // distExp exponent (and distExp > quotExp) will make the absolute
        // distance for the best match more important. A high value for
        // quotExp will make the difference between the best and second best
        // match more important (as in "how many other candidates are likely
        // matches?").
        public MatchWeighter(float distExp, float quotExp){
            this.distExp = distExp;
            this.quotExp = quotExp;
        }

        public float OverallFitness(Match m) {
            float fitness = (float)(Math.pow(m.dist1, distExp) * Math.pow(1.0 / (m.dist2 - m.dist1), quotExp));
            return (fitness);
        }

        public int compare(Match o1, Match o2) {

            float fit1 = OverallFitness(o1);
            float fit2 = OverallFitness(o2);
            if (fit1 < fit2) return (-1);
            else if (fit1 > fit2) return (1);
            return (0);
        }

    }

}


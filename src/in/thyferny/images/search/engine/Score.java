
package in.thyferny.images.search.engine;



public class Score implements Comparable<Score> {
    private int identity;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    private float score;

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public Score(int identity, float score) {
        this.identity = identity;
        this.score = score;
    }

    public int compareTo(Score s) {
        float tmp = s.score - this.score;
        if(tmp > 0.000000001f)
            return 1;
        else if(tmp < -0.000000001f)
            return -1;
        else 
            return 0;
    }
}
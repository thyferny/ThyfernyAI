
package in.thyferny.images.search.cluster.impl;

import java.util.List;

import in.thyferny.images.search.cluster.Clusterable;


public class Point implements Clusterable {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float[] getLocation() {
        return new float[] { x, y };
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }


    public List<Clusterable> getItems() {

        return null;
    }
    public float[] getClusterMean() {
        return null;
    }
}


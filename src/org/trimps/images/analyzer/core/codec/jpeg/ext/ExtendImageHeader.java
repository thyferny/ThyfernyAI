
package org.trimps.images.analyzer.core.codec.jpeg.ext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ExtendImageHeader {

    private boolean             sawJFIFMarker;
    private boolean             sawJFXXMarker;
    private boolean             sawAdobeMarker;
    private int                 JFIFMajorVersion;
    private int                 JFIFMinorVersion;
    private int                 densityUnit;
    private int                 XDensity;
    private int                 YDensity;
    private int                 AdobeTransform;
    private boolean             existProfile    = false;
    private byte[]              profileData;

    // use for extend property
    private Map<String, Object> extraProperties = new HashMap<String, Object>();

    public void addExtraProperty(String name, Object value) {
        extraProperties.put(name, value);
    }

    public Object getExtraProperty(String name) {
        return extraProperties.get(name);
    }

    public Set<String> getExtraPropertyNames() {
        return extraProperties.keySet();
    }

    public void removeProperty(String name) {
        extraProperties.remove(name);
    }

    
    public boolean isSawJFIFMarker() {
        return sawJFIFMarker;
    }

    
    public void setSawJFIFMarker(boolean sawJFIFMarker) {
        this.sawJFIFMarker = sawJFIFMarker;
    }

    
    public boolean isSawAdobeMarker() {
        return sawAdobeMarker;
    }

    
    public void setSawAdobeMarker(boolean sawAdobeMarker) {
        this.sawAdobeMarker = sawAdobeMarker;
    }

    
    public int getJFIFMajorVersion() {
        return JFIFMajorVersion;
    }

    
    public void setJFIFMajorVersion(int jFIFMajorVersion) {
        JFIFMajorVersion = jFIFMajorVersion;
    }

    
    public int getJFIFMinorVersion() {
        return JFIFMinorVersion;
    }

    
    public void setJFIFMinorVersion(int jFIFMinorVersion) {
        JFIFMinorVersion = jFIFMinorVersion;
    }

    
    public int getDensityUnit() {
        return densityUnit;
    }

    
    public void setDensityUnit(int densityUnit) {
        this.densityUnit = densityUnit;
    }

    
    public int getXDensity() {
        return XDensity;
    }

    
    public void setXDensity(int xDensity) {
        XDensity = xDensity;
    }

    
    public int getYDensity() {
        return YDensity;
    }

    
    public void setYDensity(int yDensity) {
        YDensity = yDensity;
    }

    
    public int getAdobeTransform() {
        return AdobeTransform;
    }

    
    public void setAdobeTransform(int adobeTransform) {
        AdobeTransform = adobeTransform;
    }

    
    public boolean isSawJFXXMarker() {
        return sawJFXXMarker;
    }

    
    public void setSawJFXXMarker(boolean sawJFXXMarker) {
        this.sawJFXXMarker = sawJFXXMarker;
    }

    
    public boolean isExistProfile() {
        return existProfile;
    }

    
    public void setExistProfile(boolean existProfile) {
        this.existProfile = existProfile;
    }

    
    public void setProfileData(byte[] profileData) {
        this.profileData = profileData;
    }

    public byte[] getProfileData() {
        return this.profileData;
    }
}

package com.group.attract.pooshechka;

/**
 * Created by paul on 22.04.17.
 */

public class Status {
    // Date of request
    private String mStatus;

    // Resource name
    private String mResourceName;

    // Status code
    private int mStatusCode;

    /*
    * Create a new AndroidFlavor object.
    *
    * @param vStatus is the name of the Android version (e.g. Gingerbread)
    * @param vNumber is the corresponding Android version number (e.g. 2.3-2.7)
    * @param image is drawable reference ID that corresponds to the Android version
    * */
    public Status(String vStatus, String vNumber, int imageResourceId)
    {
        mStatus = vStatus;
        mResourceName = vNumber;
        mStatusCode = imageResourceId;
    }

    /**
     * Get the name of the Android version
     */
    public String getDate() {
        return mStatus;
    }

    /**
     * Get the Android version number
     */
    public String getResourceName() {
        return mResourceName;
    }

    /**
     * Get the image resource ID
     */
    public int getStatus() {
        return mStatusCode;
    }
}

package com.hnkjxy.bectbox;

public class Sound {
    private String mAseetPath;
    private String mName;
    private Integer mSoundId;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public  Sound(String aseetPath){
        mAseetPath = aseetPath;
        String[] componentis = aseetPath.split("/");
        String filename = componentis[componentis.length-1];
        mName = filename.replace(".wav","");
    }

    public String getAseetPath() {
        return mAseetPath;
    }

    public String getName() {
        return mName;
    }
}

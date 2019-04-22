package com.hnkjxy.bectbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BectBox {
    private static final String Tag ="BectBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public List<Sound> getSounds() {
        return mSounds;
    }

    public  void BectBox(Context context){
        //获取assets中的资源
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        londsSounds();
    }
    //播放音频
    public void Play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId == null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
    //释放SoundPool
    public void release(){
        mSoundPool.release();
    }

    private void londsSounds() {
        String[] soundsNames;
        try {
            soundsNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(Tag,"Found"+soundsNames.length+"sounds");
        } catch (IOException e) {
            Log.e(Tag,"Coun not aseets",e);
           return;
        }
        for (String filename:soundsNames){
            try {
                String assetPath = SOUNDS_FOLDER+"/"+filename;
                Sound sound = new Sound(assetPath);
               //加载全部音频
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(Tag,"Could not load sound "+filename,e);
            }
        }
    }

    /**
     * 加载音频
     * @param sound
     * @throws IOException
     */
    public void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAseetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

}

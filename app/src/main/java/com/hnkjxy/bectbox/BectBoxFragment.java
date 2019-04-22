package com.hnkjxy.bectbox;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

public class BectBoxFragment extends Fragment {
    private BectBox mBectBox;
    // TODO: Rename and change types and number of parameters
    public static BectBoxFragment newInstance() {
        return new BectBoxFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//确保音频不受设备配置影响
        mBectBox = new BectBox();
        mBectBox.BectBox(getContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 实例化布局文件
        View view = inflater.inflate(R.layout.activity_main,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.main_activity_recyvler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        //使用SoundAdapter
        recyclerView.setAdapter(new SoundAdapter(mBectBox.getSounds()));
        return view;
    }
    //释放BectBox
    @Override
    public  void onDestroy() {
        super.onDestroy();
        mBectBox.release();
    }
    private  class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button mButton;
        private Sound mSound;
        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound,container,false));
            mButton = itemView.findViewById(R.id.list_item_sound_button);
            //点击按钮播放音频
            mButton.setOnClickListener(this);
        }
        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            mBectBox.Play(mSound);
        }
    }
    /**
     * 创建一个Adepter,并绑定SoundHolder
     */
    private  class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }
        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder soundHolder, int i) {
            Sound sound = mSounds.get(i);
            soundHolder.bindSound(sound);

        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}

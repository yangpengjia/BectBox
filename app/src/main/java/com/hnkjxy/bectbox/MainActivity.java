package com.hnkjxy.bectbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BectBoxFragment.newInstance();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

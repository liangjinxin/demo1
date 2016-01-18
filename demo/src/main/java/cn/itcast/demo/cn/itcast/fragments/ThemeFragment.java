package cn.itcast.demo.cn.itcast.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.itcast.demo.R;

/**
 * Created by liangjinxin on 2015/12/22.
 */
public class ThemeFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.theme_fragment,null);
	}
}

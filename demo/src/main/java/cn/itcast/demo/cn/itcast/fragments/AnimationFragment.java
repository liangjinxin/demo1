package cn.itcast.demo.cn.itcast.fragments;

import android.animation.Animator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.itcast.demo.R;
import cn.itcast.demo.TransitionStartActivity;

/**
 * Created by liangjinxin on 2015/12/24.
 */
public class AnimationFragment extends Fragment {
	@InjectView(R.id.bt_reveal)
	Button btReveal;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.animation_fragment, null);
		ButterKnife.inject(this, view);
		return view;
	}

	/**
	 * 揭示动画
	 * @param v
	 */
	@OnClick(R.id.bt_reveal)
	public void reveal(View v){
		float radius = (float) Math.hypot(btReveal.getWidth(), btReveal.getHeight());
		Animator animator = ViewAnimationUtils.createCircularReveal(btReveal, 0, btReveal.getHeight(), 0, radius);
		animator.setDuration(300);
		animator.start();
	}

	@OnClick(R.id.bt_transition)
	public void transition(View v){
		Intent intent = new Intent(getActivity(), TransitionStartActivity.class);
		startActivity(intent);
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}

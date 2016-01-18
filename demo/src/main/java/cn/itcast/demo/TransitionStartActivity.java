package cn.itcast.demo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by liangjinxin on 2015/12/24.
 */
public class TransitionStartActivity extends Activity {
	@InjectView(R.id.iv1)
	View iv1;
	@InjectView(R.id.iv2)
	View iv2;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//标记这个类 起始动画
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		Log.e("TransitionStartActivity", "TransitionStartActivity");
		setContentView(R.layout.activity_transition_start);
		ButterKnife.inject(this);
	}

	@OnClick({R.id.bt_explode, R.id.bt_slide, R.id.bt_fade, R.id.bt_changebounds})
	public void test(View v) {
		Transition transition = null;
		Bundle options = null;
		switch (v.getId()) {
			case R.id.bt_explode:
				//爆炸动画
				transition = new Explode();
				//设置开启transition动画
				options = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
				//设置进出的动画
				getWindow().setEnterTransition(transition);
				getWindow().setExitTransition(transition);
				break;
			//拉出动画
			case R.id.bt_slide:
				transition = new Slide();
				options = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
				//设置进出的动画
				getWindow().setEnterTransition(transition);
				getWindow().setExitTransition(transition);
				break;
			//谈出动画
			case R.id.bt_fade:
				transition = new Fade();
				options = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
				//设置进出的动画
				getWindow().setEnterTransition(transition);
				getWindow().setExitTransition(transition);
				break;
			case R.id.bt_changebounds:
				transition = new ChangeBounds();
				options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(iv1,"s1"),Pair.create(iv2,"s2")).toBundle();
				break;
		}


		intent = new Intent(this, TransitionTragetActivity.class);
		intent.putExtra("transition", transition.getClass().getName());
		startActivity(intent, options);
	}
}

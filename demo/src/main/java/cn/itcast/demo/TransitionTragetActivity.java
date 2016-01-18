package cn.itcast.demo;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.Window;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by liangjinxin on 2015/12/24.
 */
public class TransitionTragetActivity extends Activity {
	@InjectView(R.id.iv1)
	ImageView iv1;
	@InjectView(R.id.iv2)
	ImageView iv2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//标记这个类 是动画进入的目标界面
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		//允许进入动画
		getWindow().setAllowEnterTransitionOverlap(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transition_traget);
		ButterKnife.inject(this);

		String name = getIntent().getStringExtra("transition");
		try {
			//设置进出的动画
			Transition transition = (Transition) Class.forName(name).newInstance();
			getWindow().setEnterTransition(transition);
			getWindow().setExitTransition(transition);
			iv1.setTransitionName("s1");
			iv2.setTransitionName("s2");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}

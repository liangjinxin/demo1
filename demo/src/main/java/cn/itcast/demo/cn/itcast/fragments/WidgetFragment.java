package cn.itcast.demo.cn.itcast.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.itcast.demo.R;
import cn.itcast.demo.WidgetActivity;

/**
 * Created by liangjinxin on 2015/12/22.
 */
public class WidgetFragment extends Fragment implements View.OnClickListener {


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.widget_fragment, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Button bt_linear_horizontal = (Button) view.findViewById(R.id.bt_linear_horizontal);
		Button bt_linear_vertical = (Button) view.findViewById(R.id.bt_linear_vertical);
		Button bt_grid_horizontal = (Button) view.findViewById(R.id.bt_grid_horizontal);
		Button bt_grid_vertical = (Button) view.findViewById(R.id.bt_grid_vertical);
		Button bt_stagged_horizontal = (Button) view.findViewById(R.id.bt_stagged_horizontal);
		Button bt_stagged_vertical = (Button) view.findViewById(R.id.bt_stagged_vertical);

		bt_linear_horizontal.setOnClickListener(this);
		bt_linear_vertical.setOnClickListener(this);
		bt_grid_horizontal.setOnClickListener(this);
		bt_grid_vertical.setOnClickListener(this);
		bt_stagged_horizontal.setOnClickListener(this);
		bt_stagged_vertical.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int orientation=0;
		int type=0;
		Intent intent=null;
		switch (v.getId()) {
			case R.id.bt_linear_vertical:
				orientation=WidgetActivity.ORIENTATION_VERTICAL;
				 intent= new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("orientation", orientation);
				intent.putExtra("type", WidgetActivity.TYPE_LINEAR);
				startActivity(intent);
				break;
			case R.id.bt_linear_horizontal:
				orientation=WidgetActivity.ORIENTATION_HORIZONTAL;
				intent = new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("type",WidgetActivity.TYPE_LINEAR);
				intent.putExtra("orientation",orientation);
				startActivity(intent);
				break;
			case R.id.bt_grid_horizontal:
				orientation=WidgetActivity.ORIENTATION_HORIZONTAL;
				type=WidgetActivity.TYPE_GRID;
				intent = new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("orientation",orientation);
				intent.putExtra("type",type);
				startActivity(intent);
				break;
			case R.id.bt_grid_vertical:
				orientation=WidgetActivity.ORIENTATION_VERTICAL;
				type=WidgetActivity.TYPE_GRID;
				intent = new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("orientation",orientation);
				intent.putExtra("type",type);
				startActivity(intent);
				break;
			case R.id.bt_stagged_horizontal:
				orientation=WidgetActivity.ORIENTATION_HORIZONTAL;
				type=WidgetActivity.TYPE_STAGGERED;
				intent = new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("orientation",orientation);
				intent.putExtra("type",type);
				startActivity(intent);
				break;
			case R.id.bt_stagged_vertical:
				orientation=WidgetActivity.ORIENTATION_VERTICAL;
				type=WidgetActivity.TYPE_STAGGERED;
				intent = new Intent(getActivity(), WidgetActivity.class);
				intent.putExtra("orientation",orientation);
				intent.putExtra("type",type);
				startActivity(intent);
				break;
		}
	}
}

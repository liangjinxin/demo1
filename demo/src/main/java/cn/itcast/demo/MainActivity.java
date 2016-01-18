package cn.itcast.demo;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.demo.cn.itcast.fragments.AnimationFragment;
import cn.itcast.demo.cn.itcast.fragments.ThemeFragment;
import cn.itcast.demo.cn.itcast.fragments.WidgetFragment;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


	private ActionBarDrawerToggle toggle;
	private DrawerLayout drawerlayout;
	private TextView tv;
	private List<Pair<String, ? extends Fragment>> datas;
	private View ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFrame();
		initLeftContent();
		Looper looper;
	}

	private void initLeftContent() {
		ll = findViewById(R.id.ll);

		ListView lv = (ListView) findViewById(R.id.lv);
		datas = new ArrayList<>();
		Pair<String,? extends  Fragment> pair = Pair.create("新控件",new WidgetFragment());
		datas.add(pair);
		pair = Pair.create("新主题样式",new ThemeFragment());
		datas.add(pair);
		pair = Pair.create("动画",new AnimationFragment());
		datas.add(pair);
		lv.setAdapter(new MyAdapter());
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Pair<String, ? extends Fragment> pair = datas.get(position);
		Fragment fragment = pair.second;
		getFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();
		//关闭抽屉b布局
//		drawerlayout.closeDrawer(Gravity.LEFT);
		drawerlayout.closeDrawer(ll);
	}

	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(parent.getContext(),android.R.layout.simple_list_item_1,null);
			TextView tv = (TextView) view.findViewById(android.R.id.text1);
			Pair<String, ? extends Fragment> pair = datas.get(position);
			tv.setText(pair.first);
			return view;
		}
	}


	private void initFrame() {
		drawerlayout = (DrawerLayout) findViewById(R.id.dl);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		toggle = new ActionBarDrawerToggle(this, drawerlayout, 0, 0);
		//设置抽屉监听
		drawerlayout.setDrawerListener(toggle);
		getFragmentManager().beginTransaction().replace(R.id.fl,new WidgetFragment()).commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		//显示toggle
		toggle.syncState();

	}

	//选项菜单同步
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		toggle.onOptionsItemSelected(item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		toggle.onConfigurationChanged(newConfig);
		super.onConfigurationChanged(newConfig);
	}
}

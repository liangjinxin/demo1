package cn.itcast.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by liangjinxin on 2015/12/22.
 */
public class WidgetActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

	private RecyclerView rlv;
	public static final int ORIENTATION_VERTICAL=LinearLayoutManager.VERTICAL;
	public static final int ORIENTATION_HORIZONTAL=LinearLayoutManager.HORIZONTAL;
	public static final int TYPE_LINEAR=3;
	public static final int TYPE_GRID=4;
	public static final int TYPE_STAGGERED=5;
	private List<Pair<String, Integer>> datas;
	private SwipeRefreshLayout swipeRefreshLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_activity);
		rlv = (RecyclerView) findViewById(R.id.rlv);
		//设置自带的下拉刷新
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
		//设置刷新颜色
		swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
		swipeRefreshLayout.setProgressBackgroundColorSchemeColor(0x0094FF);
		swipeRefreshLayout.setOnRefreshListener(this);
		int orientation = getIntent().getIntExtra("orientation",0);
		int type =getIntent().getIntExtra("type",0);
		datas = MockDataGeneration.getModkData(type, orientation);
		Log.e("WidgetActivity",datas.toString());
		//RecyclerView 可以实现水平 和垂直 , listview 和 gridView、瀑布流
		RecyclerView.LayoutManager mManager =null;
		switch (type){
			case TYPE_LINEAR:
				mManager = new LinearLayoutManager(this,orientation,false);
				break;
			case TYPE_GRID:
				mManager= new GridLayoutManager(this,4,orientation,false);
				break;
			case TYPE_STAGGERED:
				mManager = new StaggeredGridLayoutManager(2,orientation);
				break;
		}
		rlv.setAdapter(new MyAdapter());
		rlv.setLayoutManager(mManager);
	}

	@Override
	public void onRefresh() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						swipeRefreshLayout.setRefreshing(false);
					}
				});
			}
		}).start();
	}

	private class MyAdapter extends  RecyclerView.Adapter {
		//将getVIew 变成两个方法 一个是 创建 viewHolder 一个是 绑定视图
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = View.inflate(WidgetActivity.this, R.layout.widget_item, null);
			MyViewHolder myViewHolder = new MyViewHolder(view);
			return myViewHolder;
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			MyViewHolder myViewHolder = (MyViewHolder) holder;
			Pair<String, Integer> pair = datas.get(position);
			String name = pair.first;
			int id = pair.second;
			Log.e("WidgetActivity", id + "");
			myViewHolder.iv.setImageResource(id);
			myViewHolder.tv.setText(name);
			myViewHolder.iv.setTag(myViewHolder);
			myViewHolder.iv.setOnClickListener(listener);
		}

		//此方法和 getItem一样
		@Override
		public int getItemCount() {
			return datas == null ? 0 : datas.size();
		}

		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MyViewHolder myViewHolder = (MyViewHolder) v.getTag();
				//这个位置就相当于条目所在的位置position
				int position = myViewHolder.getAdapterPosition();
				Toast.makeText(WidgetActivity.this, "点击了这个条目" + position, Toast.LENGTH_SHORT).show();
				//点击条目删除
				datas.remove(position);
				//条目点击并刷新
				notifyItemRemoved(position);
			}
		};
	}
	private class MyViewHolder extends  RecyclerView.ViewHolder{

		private final ImageView iv;
		private final TextView tv;

		public MyViewHolder (View itemView){
			super(itemView);
			iv = (ImageView) itemView.findViewById(R.id .iv);
			tv = (TextView) itemView.findViewById(R.id.tv);
		}
	}
}

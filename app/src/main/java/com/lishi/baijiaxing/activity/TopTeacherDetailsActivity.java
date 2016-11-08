package com.lishi.baijiaxing.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.PhotosBean;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 名师详情
 */
public class TopTeacherDetailsActivity extends BaseActivity {
    private ListView mListView;
    private RecyclerView mRecyclerView;
    private String json = "{\"photos\":[\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/6ad4e78e400a561380357f693b484a77.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/a14a590f40382fda80dd7c9affce7c40.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/e600b2c34036cb7880c99e05e2611f72.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/5168cdf640f1e0fb8055d47e0e073c16.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/aa1e9a4840e5215380f31114289cb57c.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/468d998c40cbb2888011aebe52501446.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/09e1bde540ab5e8080b522ad746f8d74.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/a0e4987540c7a49380b0d0ae8266507b.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/069f45794020c33b80a6d253fa528d03.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/60b7cf94409cd644804ea377fa986825.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/babd98fc4039664980f5b20a850d66bc.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/9df9ac80402692658044189415096d55.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/7771827d406f260c807e01295a8d883c.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/217a5157402122f380ca7dbc91db4fa8.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/850cc51540e046b180bbb69dd1155b2d.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/1dfc417c4086c53980cc3c1eb8b0c957.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/ac2d8d4e40e83f5e80c1d6f18e96a536.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/cb66ffd14053e08080d23ba2df7c5745.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/c8c9ba5e4047d926800d88c57f468e3d.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/bf1d64d940993cb880b38f5093cbf4ee.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/0ed50d68402cfb4b804d02e5333670df.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/029db3d54003978a800f32ab0c27fc4f.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/cf275f23404faec780abdfd0d108378d.jpg\",\"http://bmob-cdn-4382.b0.upaiyun.com/2016/07/08/06e1afa540a9b605809b8902c73debf8.jpg\"]}";
    private PhotosBean p;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_teacher_details);

        findId();
        initView();

    }

    private void findId() {
        mListView = (ListView) findViewById(R.id.listview_topteacherdetails);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_topteacherdetails);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_topteacherdetails);
    }

    private void initView() {
        initData();
        initTitle();
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return p.getPhotos().size();
            }

            @Override
            public Object getItem(int position) {
                return p.getPhotos().get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TopTacherDetailsViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(TopTeacherDetailsActivity.this).inflate(R.layout.item_topteacherdetails, null, false);
                    holder = new TopTacherDetailsViewHolder();
                    holder.iv = (ImageView) convertView.findViewById(R.id.iv_topteacherdetails);
                    convertView.setTag(holder);
                } else {
                    holder = (TopTacherDetailsViewHolder) convertView.getTag();
                }
                Glide.with(TopTeacherDetailsActivity.this).load(p.getPhotos().get(position)).into(holder.iv).onStart();
//                ImageLoader.getInstance().displayImage(p.getPhotos().get(position),holder.iv);
                return convertView  ;
            }

            class TopTacherDetailsViewHolder {
                ImageView iv;
            }
        };
        mListView.setAdapter(adapter);

        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL); 
//        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
//
//                                     @Override
//                                     public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                                         topViewHolder h = new topViewHolder(LayoutInflater.from(TopTeacherDetailsActivity.this).inflate(R.layout.item_topteacherdetails, null, false));
//                                         return h;
//                                     }
//
//                                     @Override
//                                     public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                                         topViewHolder v = (topViewHolder) holder;
//                                         Glide.with(TopTeacherDetailsActivity.this).load(p.getPhotos().get(position)).into(v.iv).onStart();
//                                     }
//
//                                     @Override
//                                     public int getItemCount() {
//                                         return p.getPhotos().size();
//                                     }
//
//                                     class topViewHolder extends RecyclerView.ViewHolder {
//                                         ImageView iv;
//
//                                         public topViewHolder(View itemView) {
//                                             super(itemView);
//                                             iv = (ImageView) itemView.findViewById(R.id.iv_topteacherdetails);
//                                         }
//                                     }
//                                 }
//        );


    }

    private void initTitle() {
        String name = getIntent().getStringExtra("name");
        if (name != null && !name.equals("")) {
            mTopNavigationBar.setText_title(name);
        }
    }

    private void initData() {
        Gson gson = new Gson();
        p = gson.fromJson(json, PhotosBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListView = null;
    }
}

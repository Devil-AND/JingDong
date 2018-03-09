package com.project.jingdong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.project.jingdong.R;
import com.project.jingdong.adapters.HistoryNodeAdapter;
import com.project.jingdong.adapters.HotSearchAdapters;
import com.project.jingdong.bean.HistorySearchBean;
import com.project.jingdong.greendao.DaoMaster;
import com.project.jingdong.greendao.DaoSession;
import com.project.jingdong.greendao.HistorySearchBeanDao;
import com.project.jingdong.model.SearchGoodsModel;
import com.project.jingdong.presenter.SearchGoodsPresenter;
import com.project.jingdong.utils.DaoManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class SearchGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "搜索";
    private TextView mHomeBack;
    private EditText mEditSearch;
    private TextView mBtnSearch;
    private LinearLayout mLine2;
    private RecyclerView mSearchHot;
    private ListView mSearchHistory;
    private Button mHistryoSearchClear;
    private SearchGoodsPresenter searchGoodsPresenter;
    private SearchGoodsModel searchGoodsModel;
    private HistoryNodeAdapter historyNodeAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<>();
        list.add("锤子");
        list.add("手机");
        list.add("电脑");
        list.add("笔记本");
        list.add("可乐");
        list.add("充电器");
        list.add("烤箱");
        list.add("手机膜");
        list.add("面膜");
        list.add("上衣");
        HotSearchAdapters hotSearchAdapters = new HotSearchAdapters(this, list);
        mSearchHot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSearchHot.setAdapter(hotSearchAdapters);
        hotSearchAdapters.setOnItemClickListener(new HotSearchAdapters.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String s = list.get(position);
                //输入内容正确,跳转搜索页面
                Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                intent.putExtra("searchcontent", s);
                startActivity(intent);
                finish();
                //存储到本地数据库
                HistorySearchBean historySearchBean = new HistorySearchBean(s);
                insertecode(historySearchBean);
            }

        });
        showHistroy();
        mSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<HistorySearchBean> dataBaseData = getDataBaseData();
                String searchName = dataBaseData.get(position).getSearchName();
                //输入内容正确,跳转搜索页面
                Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                intent.putExtra("searchcontent", searchName);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        mHomeBack = (TextView) findViewById(R.id.back_home);
        mHomeBack.setOnClickListener(this);
        mEditSearch = (EditText) findViewById(R.id.search_edit);
        mBtnSearch = (TextView) findViewById(R.id.search_btn);
        mBtnSearch.setOnClickListener(this);
        mLine2 = (LinearLayout) findViewById(R.id.line2);
        mSearchHot = (RecyclerView) findViewById(R.id.hot_search);
        mSearchHistory = (ListView) findViewById(R.id.history_search);
        mHistryoSearchClear = (Button) findViewById(R.id.clear_histryo_search);
        mHistryoSearchClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:
                finish();
                break;
            case R.id.search_btn:

                //搜索按钮
                String searchcontent = mEditSearch.getText().toString();
                if (searchcontent == null || searchcontent.equals("")) {
                } else {
                    //输入内容正确,跳转搜索页面
                    Intent intent = new Intent(this, SearchDetailActivity.class);
                    intent.putExtra("searchcontent", searchcontent);
                    startActivity(intent);
                    finish();
                    //存储到本地数据库
                    HistorySearchBean historySearchBean = new HistorySearchBean(searchcontent);
                    insertecode(historySearchBean);
                }
                break;
            case R.id.clear_histryo_search:// TODO 18/02/11
                //清空数据库
                clearDataBase();
                //显示
                showHistroy();
                break;
            default:
                break;
        }
    }

    /**
     * 清空数据库
     */
    private void clearDataBase() {
        DaoMaster daoMaster = new DaoMaster(DaoManager.getInstance(this).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        HistorySearchBeanDao historySearchBeanDao = daoSession.getHistorySearchBeanDao();
        historySearchBeanDao.deleteAll();

    }

    //插入数据
    private void insertecode(HistorySearchBean historySearchBean) {
        List<HistorySearchBean> dataBaseData = getDataBaseData();
        DaoMaster daoMaster = new DaoMaster(DaoManager.getInstance(this).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        HistorySearchBeanDao historySearchBeanDao = daoSession.getHistorySearchBeanDao();
        historySearchBeanDao.insert(historySearchBean);


    }

    /**
     * 获取数据库信息
     */
    private List<HistorySearchBean> getDataBaseData() {
        DaoMaster daoMaster = new DaoMaster(DaoManager.getInstance(this).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        HistorySearchBeanDao historySearchBeanDao = daoSession.getHistorySearchBeanDao();
        QueryBuilder<HistorySearchBean> historySearchBeanQueryBuilder = historySearchBeanDao.queryBuilder();
        List<HistorySearchBean> list = historySearchBeanQueryBuilder.list();
        return list;
    }

    private void showHistroy() {
        //获取数据库的数据,显示到页面
        List<HistorySearchBean> dataBaseData = getDataBaseData();
        if (dataBaseData.size() == 0) {
            historyNodeAdapter = new HistoryNodeAdapter(dataBaseData, SearchGoodsActivity.this);
            mSearchHistory.setAdapter(historyNodeAdapter);
        } else {
            historyNodeAdapter = new HistoryNodeAdapter(dataBaseData, SearchGoodsActivity.this);
            mSearchHistory.setAdapter(historyNodeAdapter);
        }
    }
}

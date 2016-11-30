package com.wwh.appframe.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wwh.appframe.R;
import com.wwh.appframe.model.UserData;
import com.wwh.appframe.model.UserDetailData;
import com.wwh.appframe.network.NetApi;
import com.wwh.appframe.network.NetApiFactory;
import com.wwh.appframe.tools.SnackbarUtil;
import com.wwh.appframe.tools.StringUtil;
import com.wwh.appframe.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main)
    RelativeLayout mainRl;

    @BindView(R.id.main_et)
    EditText searchEt;

//    @BindView(R.id.main_tv)
//    TextView serchTv;

    @BindView(R.id.main_user_rv)
    RecyclerView userRv;

    List<UserData> uDataList = new ArrayList<>();
    SearchUserRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        userRv.setLayoutManager(layoutManager);
        mAdapter = new SearchUserRecyclerViewAdapter(getMContext(), uDataList);
        userRv.setAdapter(mAdapter);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        SnackbarUtil.ShortSnackbar(mainRl, getString(R.string.snap_load_fail), SnackbarUtil.Warning);
    }

    private UserData createUserDataWithDetail(UserData uData, UserDetailData uDData) {
        uData.setDetail(uDData);
        return uData;
    }

    private void showUserInfo(UserData uData) {
        if (uData != null) {
            return;
        }
        SnackbarUtil.ShortSnackbar(mainRl, uData.getName() + "," + uData.getDetail().getPhone(), SnackbarUtil.Info);
    }

    private void saveUserInfo(UserData uData) {

    }

    @OnClick(R.id.main_tv)
    void searchEvent() {
        String key = searchEt.getText().toString();
        if (StringUtil.verify(key)) {
            cleanList();
            searchUserList(key);
        } else {
            SnackbarUtil.ShortSnackbar(mainRl, getString(R.string.search_content_null), SnackbarUtil.Warning);
        }
    }

    //添加查询结果
    private List<UserData> handlerSearchResults(List<UserData> list) {
        uDataList.clear();
        uDataList.addAll(list);
        return uDataList;
    }

    //清空查询结果，并显示在列表中
    private void cleanList() {
        uDataList.clear();
        mAdapter.notifyDataSetChanged();
    }

    //显示查询结果
    private void showSearchResults() {
        if (uDataList != null && uDataList.size() > 0) {
            mAdapter.notifyDataSetChanged();
        } else {
            cleanList();
            SnackbarUtil.ShortSnackbar(mainRl, getString(R.string.search_result_null), SnackbarUtil.Warning);
        }
    }

    /**
     * 搜索用户
     *
     * @param key 搜索关键字
     */
    private void searchUserList(String key) {
        NetApi netApi = NetApiFactory.getNetApiSingleton();
        // @formatter:off
        netApi
                .searchUser(key)
                .map(this::handlerSearchResults)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    showSearchResults();
                }, throwable -> loadError(throwable));
    }

    /**
     * 获取用户数据
     *
     * @param uId 用户ID
     */
    private void loadUserData(String uId) {
        NetApi netApi = NetApiFactory.getNetApiSingleton();
        // @formatter:off
        Subscription s = Observable
                .zip(netApi.getUserData(uId),
                        netApi.getUserDetailData(uId),
                        this::createUserDataWithDetail)
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userData -> {
                    showUserInfo(userData);
                }, throwable -> loadError(throwable));
        // @formatter:on
        addSubscription(s);
    }

    class SearchUserRecyclerViewAdapter extends RecyclerView.Adapter {
        LayoutInflater inflater;
        Context mCtx;
        List<UserData> mList;

        public SearchUserRecyclerViewAdapter(Context context, List<UserData> list) {
            mCtx = context;
            mList = list;
            inflater = LayoutInflater.from(mCtx);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SearchUserViewHolder(inflater.inflate(R.layout.item_search_user, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SearchUserViewHolder viewHolder = (SearchUserViewHolder) holder;
            UserData user = mList.get(position);
            viewHolder.idTv.setText(user.getId());
            viewHolder.nameTv.setText(user.getName());
            viewHolder.ageTv.setText(user.getAge());
            viewHolder.phoneTv.setText(user.getDetail().getPhone());
            viewHolder.mailTv.setText(user.getDetail().getEmail());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class SearchUserViewHolder extends RecyclerView.ViewHolder {
            TextView idTv;
            TextView nameTv;
            TextView ageTv;
            TextView phoneTv;
            TextView mailTv;

            public SearchUserViewHolder(View itemView) {
                super(itemView);
                idTv = (TextView) itemView.findViewById(R.id.item_search_user_id);
                nameTv = (TextView) itemView.findViewById(R.id.item_search_user_name);
                ageTv = (TextView) itemView.findViewById(R.id.item_search_user_age);
                phoneTv = (TextView) itemView.findViewById(R.id.item_search_user_phone);
                mailTv = (TextView) itemView.findViewById(R.id.item_search_user_mail);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showUserInfo(mList.get(getPosition()));
                    }
                });
            }
        }
    }

}

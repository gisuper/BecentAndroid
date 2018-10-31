package com.yangxiong.gisuper.myapplication;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangxiong on 2018/10/31.
 */
public interface ServiceInterface {
    /**
     * 往期记录
     *
     * @param countTime 查询时间（例：20180901）
     * @param searchKey 查询期号（有搜索关键词时，时间条件无效）
     * @param page      分页 默认1
     * @param pageSize  分页 默认1
     */
    @GET("api/goods/history")
    Observable<RecordedListBean> loadRecordListData(@Query("countTime") String countTime,
                                                    @Query("searchKey") String searchKey,
                                                    @Query("page") int page,
                                                    @Query("pageSize") int pageSize);
}

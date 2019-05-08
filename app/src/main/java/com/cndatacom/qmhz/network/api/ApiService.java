package com.cndatacom.qmhz.network.api;

import com.cndatacom.qmhz.bean.GoodsTypeBean;
import com.cndatacom.qmhz.bean.LoginBean;
import com.cndatacom.qmhz.network.rxjava.BaseListResponse;
import com.cndatacom.qmhz.network.rxjava.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 获取token
     * @param map
     * @return
     */
    @POST("shops/erp/basis/CF946E2B/android/getTokenByLoginUser")
    Observable<BaseResponse<LoginBean>> getLoginToken(@Body Map<String, Object> map);

    /**
     *  商品类别搜索
     * @return
     */
    @GET("/shops/other/magicBox/pro/getTypes")
    Observable<BaseListResponse<GoodsTypeBean>> Yd2getTypes();
/*
    *//**
     *   验证token是否失效
     * @return
     *//*
    @GET("/shops/erp/basis/CF946E2B/checkToken")
    Observable<BaseResponse> checkToken();

    *//**
     * 获取门店列表
     * @param map
     * @return
     *//*
    @POST("/shops/other/magicBox/selectAllShop")
    Observable<BaseResponse<ResponseListBean<ShopBean>>> getShopList(@Body Map<String, Object> map);

    *//**
     * 设置门店
      * @param shopId
     * @return
     *//*
    @GET("/shops/other/magicBox/setDefaultShop")
    Observable<BaseResponse> setShop(@Query("shopId") int shopId);

    *//**
     * 检查shopWorking
     * @return
     *//*
    @GET("/shops/other/magicBox/checkWorking")
    Observable<BaseResponse<SalesclerkInfoBean>> checkWorking();

    *//**
     * 机器查询
     * @return
     *//*
    @GET("/shops/other/magicBox/all/normal")
    Observable<BaseResponse<List<MachineBean>>> getMachineList();


    *//**
     * 添加机器号
     * @return
     *//*
    @POST("/shops/other/magicBox/chooseMachine")
    Observable<BaseResponse> addMachine(@Body Map<String, Object> map);


    *//**
     *  商品搜索
     * @param map
     * @return
     *//*
    @POST("/shops/other/magicBox/select/proSearchByPad")
    Observable<BaseResponse<ResponseListBean<GoodsNewBean>>> Yd2Search(@Body Map<String, Object> map);

    *//**
     *  商品类别搜索
     * @return
     *//*
    @GET("/shops/other/magicBox/pro/getTypes")
    Observable<BaseListResponse<GoodsTypeBean>> Yd2getTypes();


    *//**
     * 计算活动商品(增加商品时调用)
     * @param token
     * @return
     *//*
//    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("/shops/other/magicBox/compute")
    Observable<BaseResponse<CalculateBean>> calculateOrder(@Header("Authorization") String token, @Body ComputeParameters parameters);

    *//**
     * 结算销售单
     * @param token
     * @param parameters
     * @return
     *//*
//    @Headers({"Content-Type:application/json;charset=utf-8", "Accept:application/json;"})
    @POST("/shops/other/magicBox/tallyOrder")
    Observable<BaseResponse<ClearOrderBean>> clearOrder(@Header("Authorization") String token, @Body ComputeParameters parameters) ;
    *//**
     *  会员信息查询
     * @return
     *//*
    @GET("/shops/other/magicBox/query")
    Observable<BaseResponse<MemberInfoBean<MemberTicketBean>>> getMemberInfo(@Query("no") String phone);

    *//**
     *  会员信息查询（优惠券查询）
     * @return
     *//*
    @GET("/shops/other/magicBox/query")
    Observable<BaseResponse<MemberTicketBean>> getMemberTicketInfo(@Query("no") String phone);

    *//**
     * 获取未支付订单列表
     * @param map
     * @return
     *//*
    @POST("/shops/erp/order/getNotPayOrderPage")
    Observable<BaseResponse<GetOrderResponseListBean<CommonOrderBean>>> getUnpayOrder(@Body Map<String, Object> map);

    *//**
     * 获取已支付订单列表
     * @param map
     * @return
     *//*
    @POST("/shops/erp/order/getPaidOrderPage")
    Observable<BaseResponse<GetOrderResponseListBean<CommonOrderBean>>> getIspayOrder(@Body Map<String, Object> map);

    *//**
     * 获取已退货订单列表
     * @param map
     * @return
     *//*
    @POST("/shops/erp/order/getReturnOrderPage")
    Observable<BaseResponse<GetOrderResponseListBean<CommonOrderBean>>> getIsBackOrder(@Body Map<String, Object> map);

    *//**
     * 获取订单内商品详情
     * @param orderId,pageCount
     * @return
     *//*
    @GET("/shops/erp/order/magicBox/getOrderCommodityList")
    Observable<BaseResponse<CommonOrderChildBean>> getOrderCommodityList(@Query("orderId") String orderId, @Query("pageCount") int pageCount);

    *//**
     * 订单-搜索订单
     * @param map
     * @return
     *//*
    @POST("/shops/erp/order/searchOrderPage")
    Observable<BaseResponse<GetOrderResponseListBean<CommonOrderBean>>> searchOrder(@Body Map<String, Object> map);

    *//**
     * 订单-退货入库-货物报损
     * @param map
     * @return
     *//*
    @POST("/shops/erp/order/salesReturn")
    Observable<BaseResponse> salesReturn(@Body Map<String, Object> map);

    *//**
     *退货-打印退货单
     * @param map
     * @return
     *//*
    @GET("/shops/erp/order/printReturnOrder")
    Observable<BaseResponse> printReturnOrder(@Body Map<String, Object> map);


    *//**
     * 交班界面-交班
     * @return
     *//*
    @POST("/shops/other/magicBox/changeWorkingNow")
    Observable<BaseResponse> changeWorkingNow();


    *//**
     * 订单-取单功能
     * @return
     *//*
    @GET("/shops/erp/order/getSalesOrder")
    Observable<BaseListResponse<GetSalesOrderBean>> getSalesOrder(@Query("orderId") String orderId);


    *//**
     * 订单-取单功能
     * @return
     *//*
    @POST("/shops/other/magicBox/getGuideManList")
    Observable<BaseResponse<ResponseListBean<ShopperBean>>> getGuideManList(@Body Map<String, Object> map);

    *//**
     * 扫码枪付款（刷卡支付）
     * @param map
     * @return
     *//*
    @POST("/shops/erp/wxmp/CF946E2B/codePay")
    Observable<BaseResponse> codePay(@Body Map<String, Object> map);*/


}



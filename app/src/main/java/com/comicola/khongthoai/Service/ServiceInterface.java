package com.comicola.khongthoai.Service;

import com.comicola.khongthoai.Models.finish.FinishResponse;
import com.comicola.khongthoai.Models.lichSuChoi.LichSuChoiResponse;
import com.comicola.khongthoai.Models.rewards.RewardResponse;
import com.comicola.khongthoai.Models.start.StartResponse;
import com.comicola.khongthoai.Models.userInfo.UserInfoResponse;
import com.comicola.khongthoai.Models.userReward.UserRewardResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceInterface {

    @FormUrlEncoded
    @POST("/api/v1/user/getInfo")
    Call<UserInfoResponse> getUserInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/v1/user/reward")
    Call<UserInfoResponse> reward(@Field("token") String token,@Field("reward_id")String rewardId);

    @FormUrlEncoded
    @POST("/api/v1/user/start")
    Call<StartResponse> start(@Field("token") String token, @Field("package") String packageStr);

    @FormUrlEncoded
    @POST("/api/v1/user/finish")
    Call<FinishResponse> finish(@Field("token") String token, @Field("package") String packageStr,@Field("session") String session);


    @GET("/api/v1/rewards")
    Call<RewardResponse> getRewardList();

    @GET("/api/v1/user/log/sessions")
    Call<LichSuChoiResponse> getLichSuChoi(@Query("token") String token);

    @GET("/api/v1/user/log/rewards")
    Call<UserRewardResponse> getLichSuDoiQua(@Query("token") String token);
}

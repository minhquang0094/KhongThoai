package com.comicola.khongthoai.Service;

public class ServiceGenerator {
    public static final String BASE_URL = "https://khongthoai.comicola.com";

    public static ServiceInterface getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(ServiceInterface.class);
    }
}

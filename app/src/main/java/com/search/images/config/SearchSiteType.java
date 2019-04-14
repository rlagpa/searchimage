package com.search.images.config;

public enum SearchSiteType {
    KAKAO_IMAGE("https://dapi.kakao.com/", "KakaoAK be23384c898aa429f544abdf4e3bcfb9"),
    KAKAO_VISION("https://kapi.kakao.com/", "KakaoAK be23384c898aa429f544abdf4e3bcfb9");

    String baseUrl;
    String authorization;

    SearchSiteType(String baseUrl, String authorization) {
        this.baseUrl = baseUrl;
        this.authorization = authorization;
    }

    public String getBaseUrl() { return baseUrl; }
    public String getAuthorization() { return authorization; }
}

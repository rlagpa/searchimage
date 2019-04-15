package com.search.images.config;

public interface APIProvider {

    int ITEM_PER_PGAE = 20;

    static APIProvider current() {
        return Kakao.INSTANCE;
    }

    String searchHost();
    String detailHost();
    String autoToken();
}

class Kakao implements APIProvider {
    static final Kakao INSTANCE = new Kakao();
    private Kakao() {}

    @Override
    public String searchHost() {
        return "https://dapi.kakao.com";
    }

    @Override
    public String detailHost() {
        return "https://kapi.kakao.com";
    }

    @Override
    public String autoToken() {
        return "KakaoAK be23384c898aa429f544abdf4e3bcfb9";
    }
}

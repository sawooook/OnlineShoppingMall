package util;

import com.shop.online.shopingMall.dto.KakaoPayReadyResponseDto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class KakakoPayUtil {

    private static final String KakaoPayHost = "https://kapi.kakao.com";
    public static String getApiServerUrl(String url, int port) {
        return "http://" + url + ":" + port;
    }

    public static ResponseEntity<KakaoPayReadyResponseDto> readyToKakaoPay() {
        RestTemplate restTemplate = new RestTemplate();

        String localhost = getApiServerUrl("localhost", 8080);

        UriComponentsBuilder kakaoUrl = UriComponentsBuilder.fromHttpUrl(KakaoPayHost + "/v1/payment/ready")
                .queryParam("cid", "TC0ONETIME")
                .queryParam("partner_order_id", "1")
                .queryParam("partner_user_id", "1")
                .queryParam("item_name", "test")
                .queryParam("quantity", 1)
                .queryParam("total_amount", 1)
                .queryParam("tax_free_amount", 1)
                .queryParam("approval_url", localhost + "/billingInfo/kakao/approve")
                .queryParam("cancel_url", localhost + "/billingInfo/kakao/cancel")
                .queryParam("fail_url", localhost + "/billingInfo/kakao/fail");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","KakaoAK" + " 29a74f0f85117e18e3e2a883dba59210");
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<KakaoPayReadyResponseDto> responseKakaoPay = restTemplate.exchange(kakaoUrl.toUriString(), HttpMethod.POST, httpEntity, KakaoPayReadyResponseDto.class);
        return responseKakaoPay;
    }
}

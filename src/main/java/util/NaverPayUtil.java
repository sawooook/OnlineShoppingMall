package util;

import com.shop.online.shopingMall.dto.billingInfo.NaverPayResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class NaverPayUtil {

    private static final String NaverPayHost = "https://dev.apis.naver.com";
    private static final String NaverPayClientId = "Ne5Uv_UuXI1GdTMpiuE7";
    private static final String NaverPayClientSecret = "qCW8W4lD02";

    public static String getApiServerUrl(String url, int port) {
        return "http://" + url + ":" + port;
    }

    public static RestTemplate restTemplate = new RestTemplate();


    public static ResponseEntity<NaverPayResponseDto> readyToNaverPay(Long userId) {
        String localhost = getApiServerUrl("localhost", 8080);

        UriComponentsBuilder kakaoUrl = UriComponentsBuilder.fromHttpUrl(NaverPayHost + "/naverpay-partner/naverpay/payments/recurrent/regist/v1/reserve")
                .queryParam("productCode", "TCSUBSCRIP")
                .queryParam("productName", "test")
                .queryParam("totalPayAmount", 123)
                .queryParam("returnUrl", localhost + "/billingInfo/naverpay/success")
                .queryParam("actionType", "new")
                .queryParam("targetRecurrentId", 1000);

        HttpEntity httpEntity = NaverHttpHeader();
        ResponseEntity<NaverPayResponseDto> responseNaverPay =
                restTemplate.exchange(kakaoUrl.toUriString(), HttpMethod.POST, httpEntity, NaverPayResponseDto.class);

        return responseNaverPay;
    }

    private static HttpEntity NaverHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", NaverPayClientId);
        httpHeaders.add("X-Naver-Client-Secret", NaverPayClientSecret);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8");
        return new HttpEntity<>(httpHeaders);
    }


}

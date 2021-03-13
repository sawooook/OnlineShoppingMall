package util;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayChargeResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class KakakoPayUtil {

    private static final String KakaoPayHost = "https://kapi.kakao.com";
    public static String getApiServerUrl(String url, int port) {
        return "http://" + url + ":" + port;
    }

    public static RestTemplate restTemplate = new RestTemplate();


    public static ResponseEntity<KakaoPayReadyResponseDto> readyToKakaoPay(Long userId) {
        String localhost = getApiServerUrl("localhost", 8080);

        UriComponentsBuilder kakaoUrl = UriComponentsBuilder.fromHttpUrl(KakaoPayHost + "/v1/payment/ready")
                .queryParam("cid", "TCSUBSCRIP")
                .queryParam("partner_order_id", "1")
                .queryParam("partner_user_id", "1")
                .queryParam("item_name", "test")
                .queryParam("quantity", 1)
                .queryParam("total_amount", 1000)
                .queryParam("tax_free_amount", 0)
                .queryParam("approval_url", localhost + "/billingInfo/kakao/approve/"+ userId)
                .queryParam("cancel_url", localhost + "/billingInfo/kakao/cancel")
                .queryParam("fail_url", localhost + "/billingInfo/kakao/fail");

        HttpEntity httpEntity = kakaoHttpHeader();
        ResponseEntity<KakaoPayReadyResponseDto> responseKakaoPay =
                restTemplate.exchange(kakaoUrl.toUriString(), HttpMethod.POST, httpEntity, KakaoPayReadyResponseDto.class);

        System.out.println(responseKakaoPay.getBody().getNext_redirect_app_url());

        return responseKakaoPay;
    }

    public static KakaoPayApproveResponseDto approveToKakaoPay(User user, String pgToken) {
        UriComponentsBuilder kakaoUrl = UriComponentsBuilder.fromHttpUrl(KakaoPayHost + "/v1/payment/approve")
                .queryParam("cid", "TCSUBSCRIP")
                .queryParam("partner_order_id", "1")
                .queryParam("partner_user_id", "1")
                .queryParam("tid", user.activeBillingInfo().orElseThrow(NotFoundBillingInfoException::new).getUniqueNumber())
                .queryParam("total_amount", 1000)
                .queryParam("pg_token", pgToken);

        HttpEntity httpEntity = kakaoHttpHeader();

        ResponseEntity<KakaoPayApproveResponseDto> responseKakaoPay =
                restTemplate.exchange(kakaoUrl.toUriString(), HttpMethod.POST, httpEntity, KakaoPayApproveResponseDto.class);

        System.out.println(responseKakaoPay.getBody());

        return responseKakaoPay.getBody();
    }

    public static KakaoPayChargeResponseDto charge(Order order) {
        UriComponentsBuilder kakaoUrl = UriComponentsBuilder.fromHttpUrl(KakaoPayHost + "/v1/payment/subscription")
                .queryParam("cid", "TCSUBSCRIP")
                .queryParam("sid", order.getBillingInfo().getPaymentKey())
                .queryParam("partner_order_id", order.getId())
                .queryParam("partner_user_id", order.getUser().getId())
                .queryParam("quantity", 1)
                .queryParam("total_amount", order.getTotalAmount())
                .queryParam("tax_free_amount", 0);

        HttpEntity httpEntity = kakaoHttpHeader();

        ResponseEntity<KakaoPayChargeResponseDto> responseKakaoPay =
                restTemplate.exchange(kakaoUrl.toUriString(), HttpMethod.POST, httpEntity, KakaoPayChargeResponseDto.class);

        return responseKakaoPay.getBody();
    }

    private static HttpEntity kakaoHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","KakaoAK" + " 29a74f0f85117e18e3e2a883dba59210");
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return new HttpEntity<>(httpHeaders);
    }

}

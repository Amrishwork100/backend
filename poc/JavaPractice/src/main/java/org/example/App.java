package org.example;

import com.phonepe.sdk.pg.Env;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.PhonePePaymentClient;
import com.phonepe.sdk.pg.payments.v1.models.request.PgPayRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PayPageInstrumentResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgPayResponse;

import java.util.UUID;

/**
 * Hello world!
 */
public class App {
    String merchantId = "<merchantId>";
    String saltKey = "<saltKey>";
    Integer saltIndex = 1;
    Env env = Env.UAT;
    boolean shouldPublishEvents = true;
    PhonePePaymentClient phonepeClient = new PhonePePaymentClient(merchantId, saltKey, saltIndex, env, shouldPublishEvents);

    String merchantTransactionId = UUID.randomUUID().toString().substring(0, 34);
    long amount = 100;
    String callbackurl = "https://www.merchant.com/callback";
    String merchantUserId = "merchantUserId";
    String redirecturl = "https://www.merchant.com/redirect";
    String redirectMode = "REDIRECT";

    PgPayRequest pgPayRequest = PgPayRequest.PayPagePayRequestBuilder()
            .amount(amount)
            .merchantId(merchantId)
            .merchantTransactionId(merchantTransactionId)
            .callbackUrl(callbackurl)
            .merchantUserId(merchantUserId)
            .redirectUrl(callbackurl)
            .redirectMode(callbackurl)
            .build();

    PhonePeResponse<PgPayResponse> payResponse = phonepeClient.pay(pgPayRequest);
    PayPageInstrumentResponse payPageInstrumentResponse = (PayPageInstrumentResponse) payResponse.getData().getInstrumentResponse();
    String url = payPageInstrumentResponse.getRedirectInfo().getUrl();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


}

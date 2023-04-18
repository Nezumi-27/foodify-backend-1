package fpt.sep490.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import fpt.sep490.payload.ItemDto;
import fpt.sep490.payload.MerchantDto;
import fpt.sep490.payload.MerchantResponse;
import fpt.sep490.payload.PaymentStatusResponse;
import fpt.sep490.service.CheckoutService;
import fpt.sep490.utils.HMACUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private static Map<String, String> config = new HashMap<>() {{
        put("appid", "2553");
        put("key1", "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL");
        put("key2", "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
    }};

    // 554
    // 8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn


    // 2553
    // PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL
    private static final Map<String, String> queryConfig = new HashMap<String, String>() {{
        put("appid", "2553");
        put("key1", "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL");
        put("key2", "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/query");
    }};

    public static String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    private String appTransId = "";

    @Override
    @Transactional
    public MerchantResponse createMerchant(List<ItemDto> itemsDto, MerchantDto merchantDto) throws IOException {
        Random rand = new Random();
        int random_id = rand.nextInt(1000000);
        String merchantInfo = merchantDto.getMerchantInfo();


        // put JSON item into the map
        List<Map> mapItems = new ArrayList<>();

        for (int i = 0; i < itemsDto.size(); i++) {
            ItemDto itemDto = itemsDto.get(i);
            Map mapItem = new HashMap<>();

            mapItem.put("itemname", "zaloItem");
            mapItem.put("itemprice", Integer.parseInt(itemDto.getPrice()));
            mapItem.put("itemquantity", Integer.parseInt(itemDto.getQuantity()));

            mapItems.add(mapItem);
        }

        appTransId = getCurrentTimeString("yyMMdd") + "_" + random_id;
        Map embed_data = new HashMap() {{
            put("redirecturl", "http://localhost:4200/shop/checkout/success/TOK_" + merchantDto.getOrderId());
        }};


        final Map test_embed_data = new HashMap(){{}};

        Map<String, Object> order = new HashMap<String, Object>() {{
            put("app_id", config.get("appid").toString());
            put("app_trans_id", appTransId); // translation missing: vi.docs.shared.sample_code.comments.app_trans_id
            put("app_time", System.currentTimeMillis()); // miliseconds
            put("app_user", "abc123");
            put("amount", Long.parseLong(merchantDto.getAmount()));
            put("description", "Foodify - Payment for the order #" + random_id);
            put("bank_code", "zalopayapp");
            put("item", new JSONArray(mapItems).toString());
            put("embed_data", new JSONObject(embed_data).toString());
            put("callback_url", "http://localhost:4200/shop/checkout/success");
        }};


        // app_id +”|”+ app_trans_id +”|”+ appuser +”|”+ amount +"|" + app_time +”|”+ embed_data +"|" +item
        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|" + order.get("amount") + "|"
                + order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.get("endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        // Content-Type: application/x-www-form-urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }
        return new MerchantResponse(result.get("order_url").toString(), "TOK_" + appTransId);
    }

    @Override
    public PaymentStatusResponse statusPayment() throws URISyntaxException, IOException {
        String app_trans_id = appTransId;  // Input your app_trans_id
        String data = queryConfig.get("appid") + "|" + app_trans_id + "|" + queryConfig.get("key1"); // appid|app_trans_id|key1
        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, queryConfig.get("key1"), data);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("app_id", queryConfig.get("appid")));
        params.add(new BasicNameValuePair("app_trans_id", app_trans_id));
        params.add(new BasicNameValuePair("mac", mac));

        URIBuilder uri = new URIBuilder(queryConfig.get("endpoint"));
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri.build());
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }

        return new PaymentStatusResponse(
                result.getString("return_message"),
                result.getString("sub_return_message"),
                result.getBoolean("is_processing"),
                result.getInt("return_code"));
    }
}

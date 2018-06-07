package com.bill.billdemo.entity;

import android.util.Log;

import com.bill.billdemo.App;
import com.bill.billdemo.DesEncrypt;
import com.bill.billdemo.R;
import com.bill.billdemo.activity.RequestBaseJson;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class CustomConverterFactory extends Converter.Factory {
    public static CustomConverterFactory create() {
        return create(new Gson());
    }

    public static CustomConverterFactory create(Gson gson) {
        return new CustomConverterFactory(gson);
    }

    private final Gson gson;

    private CustomConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CustomResponseBodyConverter(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new CustomRequestBodyConverter<>(gson, adapter);
    }
}


/**
 * 加密
 *
 * @param <T>
 */
class CustomRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String valueStr = "";
        if (value instanceof RequestBaseJson) {
            RequestBaseJson requestBaseJson = (RequestBaseJson) value;
            HashMap map = requestBaseJson.getData();
            try {
                String encrypt = DesEncrypt.getEncString(map.toString());
                requestBaseJson.setMessage(encrypt);
                Log.d("encrypt", encrypt);
                valueStr = "message=" + encrypt + "&isEncryption=" + App.Companion.getContext().getString(R.string.IS_ENCRYPTION_VALUE);
//                valueStr = "message=%2FtFHDaYuvcwk%2FSCL5hIyx0CSHkM07O%2FY7A%2FMx0iPfKCsWpodFL3I0NcTvF9HXcvbVBj2qFaEnBxWuVSibt9KW33kqtfHyKdWNgMM144DYJaes07F2kO5UZERyvP7BcHy9GwaEmC3RZaW8fisUe8AVv7FOl%2FMDoaLoOhF6Qn5KsAvv1%2BvqAaY1tX8AvEZ%2BitkDAXKoS05klhv%2F%2BNKtQZY8INRFY1gu%2BOhZJB7jxwpEymd7xZZLvUQSFLOv5tozXjS5kwJ3tZsrFDY7RwanO2LsnzL0M3gMRdA2owHGvKGn58FyhUHl6EsryzKZ%2FTlpNjLKA4%2Bpb2gWkTe%2BCep1YpPWqaLx5MhMJHTa9%2F20YQ9n7lStCfmH74aG6s1G%2BI6fJx9&isEncryption=EN02&";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RequestBody.create(MEDIA_TYPE, valueStr);
    }
}

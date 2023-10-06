package com.everyonegarden.weather.infra;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiFieldChecker {

    public boolean isNowT1HOrPTY(JsonElement item) {
        String category = extractCategoryField(item);
        return category.equals("T1H") || category.equals("PTY");
    }

    public boolean isFirstAndSecondDaySky(JsonElement item, String time, String oneday, String twoday) {
        String category = extractCategoryField(item);
        String fcstDate = extractFieldAsString(item, "fcstDate");
        String fcstTime = extractFieldAsString(item, "fcstTime");

        return (category.equals("SKY") && fcstDate.equals(oneday) && fcstTime.equals(time)) ||
                (category.equals("SKY") && fcstDate.equals(twoday) && fcstTime.equals(time));
    }

    public boolean isPopOrSkyOrTmp(JsonElement item) {
        String category = extractCategoryField(item);

        return category.equals("POP") || category.equals("SKY") || category.equals("TMP");
    }

    private String extractCategoryField(JsonElement item) {

        return extractFieldAsString(item, "category");
    }

    private String extractFieldAsString(JsonElement item, String fieldName) {
        JsonObject jsonObject = item.getAsJsonObject();
        JsonElement fieldElement = jsonObject.get(fieldName);

        return fieldElement != null ? fieldElement.getAsString() : "";
    }

}

package com.everyonegarden.batch.mafra;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// 텃밭 분양 상세정보
// 농림수산식품교육문화정보원

@RequiredArgsConstructor
@Service
public class MafraFetchService {

    private final RestTemplate restTemplate;

    @Value("${api.mafra.url}") private String REQUEST_URI;
    @Value("${api.mafra.secret}") private String API_KEY;

    // start, end -> 1부터 API에 있는 모든 정보 개수까지 (0부터 시작하지 않음에 주의)
    // 예) start = 5, end = 20 -> 등록 날짜 DESC 순서대로 5번째 부터 20번째까지 15개
    public List<ApiMafraRow> getMafraRows(int start, int end) {
        String url = REQUEST_URI + "?" +
                "API_KEY=" + API_KEY +
                "&TYPE=" + "JSON" +
                "&API_URL=" + "Grid_20171122000000000552_1" +
                "&START_INDEX=" + start +
                "&END_INDEX=" + end;

        ApiMafraResponse response = restTemplate.getForObject(url, ApiMafraResponse.class);

        if (response == null || response.getRow() == null) return List.of();

        return response.getRow();
    }

    // start, end -> 1부터 API에 있는 모든 정보 개수까지 (0부터 시작하지 않음에 주의)
    // 예) start = 5, end = 20 -> 등록 날짜 DESC 순서대로 5번째 부터 20번째까지 15개
    public ApiMafraResponse getMafraApiResponse(int start, int end) {
        String url = REQUEST_URI + "?" +
                "API_KEY=" + API_KEY +
                "&TYPE=" + "JSON" +
                "&API_URL=" + "Grid_20171122000000000552_1" +
                "&START_INDEX=" + start +
                "&END_INDEX=" + end;

        return restTemplate.getForObject(url, ApiMafraResponse.class);
    }

}

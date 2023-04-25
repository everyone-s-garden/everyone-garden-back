package com.everyonegarden.garden.api.mafra.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 텃밭 분양 상세정보
// 농림수산식품교육문화정보원

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApiMafraResponse {

    private Integer totalCnt;
    private Integer startRow;
    private Integer endRow;
    private ApiMafraResult result;

    private List<ApiMafraRow> row;

}

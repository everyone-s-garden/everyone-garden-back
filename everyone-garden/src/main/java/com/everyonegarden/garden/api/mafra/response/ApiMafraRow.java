package com.everyonegarden.garden.api.mafra.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 텃밭 분양 상세정보
// 농림수산식품교육문화정보원

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApiMafraRow {

    private Integer ROW_NUM;           // 1
    private Integer FARM_ID;           // 961
    private String FARM_TYPE;          // 민간단체
    private String FARM_NM;            // 경기도 도시농업농장
    private Integer AREA_LCD;          // 9
    private String AREA_LNM;           // 경기도
    private Integer AREA_MCD;          // 122
    private String AREA_MNM;           // 용인시 기흥구
    private String ADDRESS1;           // 경기도 용인시 기흥구 영덕동 1099번지
    private String FARM_AREA_INFO;     // 14,975.6m²
    private String SELL_AREA_INFO;     // 12,540m²
    private String HOMEPAGE;           // http://cafe.daum.net/ggpuafarm
    private String OFF_SITE;           // 교육장, 농기구창고, 퇴비장, 생태뒷간, 정자, 물탱크
    private Integer UPDT_DT;           // 20190418
    private Integer REGIST_DT;         // 20190418

    private String APPLY_MTHD;
    private String COLLEC_PROD;
    private String PRICE;
    private String POSLNG;
    private String POSLAT;

}

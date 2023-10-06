package com.everyonegarden.batch.mafra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// 텃밭 분양 상세정보
// 농림수산식품교육문화정보원

@XmlRootElement(name = "Grid_20171122000000000552_1")
@XmlAccessorType(XmlAccessType.FIELD)

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ApiMafraResponse {

    private Integer totalCnt;
    private Integer startRow;
    private Integer endRow;
    private ApiMafraResult result;

    private List<ApiMafraRow> row;

}

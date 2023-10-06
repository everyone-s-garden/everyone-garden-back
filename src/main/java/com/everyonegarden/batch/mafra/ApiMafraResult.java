package com.everyonegarden.batch.mafra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;


// 텃밭 분양 상세정보
// 농림수산식품교육문화정보원

@XmlRootElement(name = "result")

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ApiMafraResult {
    private String message;
    private String code;
}

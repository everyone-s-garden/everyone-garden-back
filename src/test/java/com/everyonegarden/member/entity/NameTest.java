package com.everyonegarden.member.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("이름으로 null 혹은 빈캆이 들어오는 경우 예외를 던진다.")
    void makeName_nullOrBlank_throwException() {
        //then
        assertThrows(IllegalArgumentException.class,()->new Name(""));
        assertThrows(IllegalArgumentException.class,()->new Name(null));
        assertThrows(IllegalArgumentException.class,()->new Name(" "));
    }
}

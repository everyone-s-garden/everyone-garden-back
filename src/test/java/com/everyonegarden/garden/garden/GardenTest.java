package com.everyonegarden.garden.garden;

import com.everyonegarden.Fixtures;
import com.everyonegarden.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GardenTest {

    @Test
    @DisplayName("주소가 null이거나 빈값인 경우 예외를 던진다.")
    public void createGarden_nullOrBlankAddress_ThrowIllegalArgumentException() {
        //given
        Member member = Fixtures.savedMember();
        Garden garden = Fixtures.gangwonGarden(member);

        //when_then
        assertThrows(IllegalArgumentException.class,
                () -> new Garden(
                        null,
                        garden.getLatitude(),
                        garden.getLongitude(),
                        garden.getName(),
                        garden.getType(),
                        garden.getStatus(),
                        garden.getLink(),
                        garden.getPrice(),
                        garden.getContact(),
                        garden.getSize(),
                        garden.getContent(),
                        garden.getToilet(),
                        garden.getWaterway(),
                        garden.getEquipment(),
                        member,
                        false,
                        0));
        assertThrows(IllegalArgumentException.class,
                () -> new Garden(
                        "",
                        garden.getLatitude(),
                        garden.getLongitude(),
                        garden.getName(),
                        garden.getType(),
                        garden.getStatus(),
                        garden.getLink(),
                        garden.getPrice(),
                        garden.getContact(),
                        garden.getSize(),
                        garden.getContent(),
                        garden.getToilet(),
                        garden.getWaterway(),
                        garden.getEquipment(),
                        member,
                        false,
                        0));
    }

    @Test
    @DisplayName("분양 텃밭의 이름이 null이거나 빈값인 경우 예외를 던진다.")
    public void createGarden_nullOrBlankName_ThrowIllegalArgumentException() {
        //given
        Member member = Fixtures.savedMember();
        Garden garden = Fixtures.gangwonGarden(member);

        //when_then
        assertThrows(IllegalArgumentException.class,
                () -> new Garden(
                        garden.getAddress(),
                        garden.getLatitude(),
                        garden.getLongitude(),
                        null,
                        garden.getType(),
                        garden.getStatus(),
                        garden.getLink(),
                        garden.getPrice(),
                        garden.getContact(),
                        garden.getSize(),
                        garden.getContent(),
                        garden.getToilet(),
                        garden.getWaterway(),
                        garden.getEquipment(),
                        member,
                        false,
                        0));
        assertThrows(IllegalArgumentException.class,
                () -> new Garden(
                        garden.getAddress(),
                        garden.getLatitude(),
                        garden.getLongitude(),
                        "",
                        garden.getType(),
                        garden.getStatus(),
                        garden.getLink(),
                        garden.getPrice(),
                        garden.getContact(),
                        garden.getSize(),
                        garden.getContent(),
                        garden.getToilet(),
                        garden.getWaterway(),
                        garden.getEquipment(),
                        member,
                        false,
                        0));
    }

    @Test
    @DisplayName("신고가 들어온 경우 게시글의 신고 점수가 올바르게 누적되는지 확인한다.")
    void registerReport() {
        //given
        Member member = Fixtures.savedMember();
        Garden garden = Fixtures.gangwonGarden(member);

        //when
        garden.registerReport(5);

        //then
        assertThat(garden.getReportedScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("신고 점수가 최대 누적 점수를 넘어가면 게시글은 삭제된다.")
    void registerReport_sumMaxDeletedScoreLimited_deleted() {
        //given
        Member member = Fixtures.savedMember();
        Garden garden = Fixtures.gangwonGarden(member);
        int maxDeletedScoreLimited = garden.getDELETED_MAX_SCORE();

        //when
        while(garden.getReportedScore()<= maxDeletedScoreLimited) {
            garden.registerReport(5);
        }

        //then
        assertThat(garden.isDeleted()).isEqualTo(true);
    }

}

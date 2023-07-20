package com.everyonegarden.garden.gardenUsing;

import com.everyonegarden.common.exception.NotFoundException;
import com.everyonegarden.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GardenUsingService {

    private final GardenUsingRepository gardenUsingRepository;

    public List<GardenUsing> getGardenUsingByMemberId(Long memberId, Pageable pageable) {
        return gardenUsingRepository.findAllByMemberId(memberId);
    }

    public GardenUsing add(GardenUsing gardenUsing) {
        return gardenUsingRepository.save(gardenUsing);
    }

    @Transactional
    public GardenUsing edit(GardenUsing gardenUsing) {
        GardenUsing existingGardenUsing = gardenUsingRepository.findById(gardenUsing.getId())
                .orElseThrow(() -> new NotFoundException("수정하시려는 이용중인 텃밭이 없어요"));

        if (!existingGardenUsing.getMemberId().equals(gardenUsing.getMemberId())) {
            throw new UnauthorizedException("이용중인 텃밭은 본인만 수정할 수 있어요");
        }

        return existingGardenUsing.edit(gardenUsing);
    }

    public void delete(Long gardenUsingId, Long memberId) {
        GardenUsing gardenUsing = gardenUsingRepository.findById(gardenUsingId)
                .orElseThrow(() -> new NotFoundException("지우시려는 이용중인 텃밭이 없어요"));

        if (!gardenUsing.getMemberId().equals(memberId)) {
            throw new UnauthorizedException("이용중인 텃밭은 본인만 지울 수 있어요");
        }

        gardenUsingRepository.deleteById(gardenUsingId);
    }

}
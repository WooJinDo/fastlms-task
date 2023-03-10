package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        }

        return null;
    }


    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto b : list) {
                b.setTotalCount(totalCount);
                b.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .link(parameter.getLink())
                .target(parameter.getTarget())
                .usingYn(parameter.isUsingYn())
                .sortValue(parameter.getSortValue())
                .regDt(LocalDateTime.now())
                .filename(parameter.getFilename())
                .urlFilename((parameter.getUrlFilename()))
                .build();
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setLink(parameter.getLink());
        banner.setTarget(parameter.getTarget());
        banner.setUsingYn(parameter.isUsingYn());
        banner.setSortValue(parameter.getSortValue());
        banner.setUdtDt(LocalDateTime.now());
        banner.setFilename(parameter.getFilename());
        banner.setUrlFilename(parameter.getUrlFilename());
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String s : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(s);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> frontList(BannerParam parameter) {
        return bannerMapper.frontList(parameter);
    }

}

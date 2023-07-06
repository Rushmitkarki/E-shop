package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.ReportDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Report;
import com.ecommerce.ecommerce.repo.ReportRepo;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.ReportService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final UserService userService;
    private final ItemService itemService;
    private final ReportRepo reportRepo;
    @Override
    public void reportItem(ReportDto reportDto) {
        Report report = new Report();
        Item item = itemService.getByIdNoOpt(reportDto.getItemId()).get();
        report.setUser(userService.getActiveUser().get());
        report.setItem(item);
        report.setReportDescription(reportDto.getReportDescription());
        reportRepo.save(report);

    }
}

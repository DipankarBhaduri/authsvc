package com.mingleHub.authsvc.services.impl;

import com.mingleHub.authsvc.constants.ServiceCategory;
import com.mingleHub.authsvc.dto.category.CategoryResponseDTO;
import com.mingleHub.authsvc.services.ServiceOptionSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Slf4j
@Service
public class ServiceOptionSvcImpl implements ServiceOptionSvc {
    @Override
    public CategoryResponseDTO getCategoryList() {
        ServiceCategory [] serviceCategories = ServiceCategory.values();

        return new CategoryResponseDTO()
                .setServiceCategoryList(Arrays
                        .stream(serviceCategories)
                        .toList()
                );
    }
}

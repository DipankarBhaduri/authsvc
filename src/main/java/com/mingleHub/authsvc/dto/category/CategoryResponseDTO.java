package com.mingleHub.authsvc.dto.category;

import com.mingleHub.authsvc.constants.ServiceCategory;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class CategoryResponseDTO {
    private List<ServiceCategory> serviceCategoryList;
}

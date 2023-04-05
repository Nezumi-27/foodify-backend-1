package fpt.sep490.payload;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryResponsePageable {
    private Set<CategoryDto> categories;
    private PageableDto page;
}

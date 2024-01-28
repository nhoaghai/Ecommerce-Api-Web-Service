package server.project_module05.model.dto.response.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PageResponse<T> {
    private List<T> data;
    private Integer totalPages;
    private Integer pageNumber;
    private Integer size;
    private String sort;
}

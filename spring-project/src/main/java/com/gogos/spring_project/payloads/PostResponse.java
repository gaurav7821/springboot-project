package com.gogos.spring_project.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int  pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;

}

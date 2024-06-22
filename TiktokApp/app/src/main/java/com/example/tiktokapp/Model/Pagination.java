package com.example.tiktokapp.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Pagination {
    private String orderBy;
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private String orderDirection;
}

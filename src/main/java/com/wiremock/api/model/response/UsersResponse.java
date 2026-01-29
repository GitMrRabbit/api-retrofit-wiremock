package com.wiremock.api.model.response;

import com.wiremock.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
}

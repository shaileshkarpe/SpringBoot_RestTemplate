package com.crossasyst.resttemplate1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostResponse extends PostRequest {
    public Long id;


}

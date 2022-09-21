package com.crossasyst.resttemplate1.service;

import com.crossasyst.resttemplate1.model.PostRequest;
import com.crossasyst.resttemplate1.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class PostService {
    @Value("${test.fakeUrl}")
    private String fakeUrl;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    public PostResponse getPost(Long userId)
//    {return restTemplate.getForObject(fakeUrl+"/posts/"+userId,PostResponse.class);}

    //    public PostResponse deletePost(Long userId) {
    //return  restTemplate.getForObject(fakeUrl+"/posts/"+userId,PostResponse.class,userId);
//    }
    public ResponseEntity<PostRequest[]> getPosts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(fakeUrl + "/posts", HttpMethod.GET, httpEntity, PostRequest[].class);
    }

    public PostResponse getById(Long id) {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(httpHeader);
        ResponseEntity<PostResponse> postResponse = restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.GET, entity, PostResponse.class);
        return postResponse.getBody();
    }

    public PostResponse createPosts(PostRequest postRequest) {
        HttpEntity<PostRequest> httpEntity = new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> body = restTemplate.exchange(fakeUrl + "/posts", HttpMethod.POST, httpEntity, PostResponse.class);
        return body.getBody();
    }

    public PostResponse updatePosts(Long id, PostRequest postRequest) {
        HttpEntity httpEntity = new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> response = restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.PUT, httpEntity, PostResponse.class);
        return response.getBody();
    }

    public void deleteById(long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(headers);
        restTemplate.exchange(fakeUrl + "/posts/" + userId, HttpMethod.DELETE, entity, PostService.class).getBody();
    }
}



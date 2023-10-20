package com.projeto.interact.service;

import com.projeto.interact.domain.RequestModel;

import java.util.List;

public interface RequestService {

    RequestModel getRequest(Long requestId);

    RequestModel createRequest(RequestModel request);

    void deleteRequest(Long requestId);

    void acceptRequest(Long requestId);

    List<RequestModel> getAllRequests();


}

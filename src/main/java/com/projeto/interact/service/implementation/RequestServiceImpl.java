package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.RequestModel;
import com.projeto.interact.domain.user.UserModel;
import com.projeto.interact.domain.user.UserRole;
import com.projeto.interact.respository.RequestRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.RequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.projeto.interact.utils.FindEntityUtil.findEntityById;

@Service
public class RequestServiceImpl implements RequestService {

    RequestRepository requestRepository;
    UserRepository userRepository;

    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param requestId id do request
     * @return retorna o request pelo id
     * <p>
     *
     * @throws EntityNotFoundException se o request não for encontrado
     *
     * @see RequestModel
     * @see com.projeto.interact.utils.FindEntityUtil
     */
    @Override
    public RequestModel getRequest(Long requestId) {
        return findEntityById(requestRepository, requestId, "Request");
    }

    @Override
    public RequestModel createRequest(RequestModel request) {
        return requestRepository.save(request);
    }

    /**
     * @param requestId
     *<p>
     * Metodo e utilizado para deletar, ou seja, rejeitar o request.
     *
     */
    @Override
    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    /**
     * Aceita o request e muda o role do user para monitor
     *
     * @param requestId id do request
     * @throws EntityNotFoundException se o request ou o user não forem encontrados
     * <p>
     * Aceita o request e muda o role do user para 'MONITOR'.
     * Primeiro, obtem o request pelo id, depois obtem o user pelo id do request, e muda o role do user
     * Depois que o role do user é mudado, deleta o request.
     *
     * @see RequestModel
     * @see UserModel
     */
    @Override
    public void acceptRequest(Long requestId) {
        RequestModel requestModel = findEntityById(requestRepository, requestId , "Request");
        UserModel userModel = findEntityById(userRepository, requestModel.getUser().getId(), "User");
        userModel.setRole(UserRole.MONITOR);
        userRepository.save(userModel);
        requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestModel> getAllRequests() {
        return requestRepository.findAll();
    }
}

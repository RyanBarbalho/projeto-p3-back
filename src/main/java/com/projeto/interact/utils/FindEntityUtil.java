package com.projeto.interact.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FindEntityUtil {


    public static <T> T findEntityById(JpaRepository<T, Long> repository, long id, String entityName) throws ResponseStatusException{
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," "+ entityName + " not found" + " with ID: " + id));

    }
    /*funcao para encontrar qualquer entidade e enviar mensagem de erro em caso de exception ƪ(˘⌣˘)ʃ
    * parametro: repository, id, nome da entidade
    * */
}

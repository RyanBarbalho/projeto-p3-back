package com.projeto.interact.controller;

import com.projeto.interact.domain.DTO.CreateRequestDTO;
import com.projeto.interact.domain.RequestModel;
import com.projeto.interact.service.BoardService;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.RequestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/request")
public class RequestController {

    RequestServiceImpl requestService;
    UserService userService;

    BoardService boardService;

    public RequestController(RequestServiceImpl requestService, UserService userService, BoardService boardService) {
        this.requestService = requestService;
        this.userService = userService;
        this.boardService = boardService;
    }

    /**
     * Cria uma nova solicitação com uma imagem enviada.
     *
     * @param image O arquivo de imagem enviado como MultipartFile.
     * @param dto   O objeto de transferência de dados (DTO) contendo detalhes da solicitação.
     * @return Um ResponseEntity representando a resposta HTTP para a criação da solicitação.
     * <p>
     * Este método lida com a criação de uma nova solicitação, associando-a com uma imagem enviada.
     * Ele valida os dados de entrada, cria uma instância RequestModel e configura seus atributos.
     * Se bem-sucedido, retorna um ResponseEntity com um status 200 OK.
     * Se houver um erro no processamento da imagem, retorna um erro interno do servidor 500.
     * Para outras exceções, retorna um erro interno do servidor 500 com uma mensagem de erro geral.
     *
     * @see MultipartFile
     * @see CreateRequestDTO
     * @see RequestModel
     */
    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@RequestParam("pdfFile") MultipartFile pdfFile,@RequestParam("username") String username, @RequestParam("boardId") Long boardId){
        try {
            // Valida dados
            if (pdfFile.isEmpty()  || username == null || boardId == null) {
                return ResponseEntity.badRequest().body("Invalid request data.");
            }
            //cria e popula request
            RequestModel request = new RequestModel();
            request.setUser(userService.findByUsername(username));
            request.setBoard(boardService.getBoard(boardId));
            request.setCertificate(pdfFile.getBytes());
            requestService.createRequest(request);
            System.out.println("Teste");
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the PDF file.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }

    @GetMapping("/{id}")
    public RequestModel getRequest(@PathVariable Long id){
        return requestService.getRequest(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id){
        requestService.deleteRequest(id);
    }

    @PostMapping("/accept/{id}")
    public void acceptRequest(@PathVariable Long id){
        requestService.acceptRequest(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAllRequests(){
        return ResponseEntity.ok(requestService.getAllRequests());
    }


}

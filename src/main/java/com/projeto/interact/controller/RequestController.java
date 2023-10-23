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

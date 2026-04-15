//package br.com.fiap.numberone.estoque.api.controllers;
//
//import br.com.fiap.numberone.estoque.api.dto.requests.MovimentacaoRequest;
//import br.com.fiap.numberone.estoque.application.services.EstoqueService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/estoque")
//public class EstoqueController {
//
//    private final EstoqueService service;
//
//    public EstoqueController(EstoqueService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/{itemId}/entrada")
//    public void entrada(@PathVariable UUID itemId,
//                        @RequestBody MovimentacaoRequest request) {
//
//        //service.entrada(itemId, request.getQuantidade(), request.getMotivo());
//    }
//
//    @PostMapping("/{itemId}/baixa")
//    public void baixa(@PathVariable UUID itemId,
//                      @RequestBody MovimentacaoRequest request) {
//
//        //service.baixa(itemId, request.getQuantidade(), request.getMotivo());
//    }
//
//    @PostMapping("/{itemId}/ajuste")
//    public void ajuste(@PathVariable UUID itemId,
//                       @RequestBody MovimentacaoRequest request) {
//
//       // service.ajuste(itemId, request.getQuantidade(), request.getMotivo());
//    }
//
//    @GetMapping("/{itemId}/saldo")
//    public Integer saldo(@PathVariable UUID itemId) {
//        //return service.consultarSaldo(itemId);
//        return 0;
//    }
//}

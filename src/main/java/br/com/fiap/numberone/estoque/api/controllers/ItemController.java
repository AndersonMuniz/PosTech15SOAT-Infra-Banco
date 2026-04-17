//package br.com.fiap.numberone.estoque.api.controllers;
//
//import br.com.fiap.numberone.estoque.api.dto.requests.ItemRequest;
//import br.com.fiap.numberone.estoque.api.dto.responses.ItemResponse;
//import br.com.fiap.numberone.estoque.api.mappers.ItemApiMapper;
//import br.com.fiap.numberone.estoque.application.services.ItemService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/itens")
//public class ItemController {
//
//    private final ItemService service;
//
//    public ItemController(ItemService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ItemResponse criar(@RequestBody ItemRequest request) {
//        var item = service.criar(ItemApiMapper.toDomain(request));
//        return ItemApiMapper.toResponse(item);
//    }
//
//    @PutMapping("/{id}")
//    public ItemResponse atualizar(@PathVariable UUID id,
//                                  @RequestBody ItemRequest request) {
//
//        var atualizado = service.atualizar(id, ItemApiMapper.toDomain(request));
//        return ItemApiMapper.toResponse(atualizado);
//    }
//
//    @GetMapping
//    public List<ItemResponse> listar() {
//        return service.listar()
//                .stream()
//                .map(ItemApiMapper::toResponse)
//                .toList();
//    }
//
//    @GetMapping("/{id}")
//    public ItemResponse detalhar(@PathVariable UUID id) {
//        return ItemApiMapper.toResponse(service.detalhar(id));
//    }
//}

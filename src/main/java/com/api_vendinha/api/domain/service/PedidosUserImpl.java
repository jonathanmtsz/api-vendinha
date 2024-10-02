package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.PedidosUserRepository;
import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.PedidoRequestDTO;
import com.api_vendinha.api.domain.dtos.response.PedidoResponseDTO;
import com.api_vendinha.api.domain.entities.PedidoUser;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidosUserImpl implements PedidosUserServiceInterface {

    private final PedidosUserRepository pedidosUserRepository;
    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;

    @Autowired
    public PedidosUserImpl(
            PedidosUserRepository pedidosUserRepository,
            ProdutoRepository produtoRepository,
            UserRepository userRepository
    ) {
        this.pedidosUserRepository = pedidosUserRepository;
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository;
    }

    public PedidoResponseDTO save(PedidoRequestDTO pedidoRequestDTO) {
        PedidoUser pedido = new PedidoUser();

        Optional<User> user = userRepository.findById(pedidoRequestDTO.getUser_id());
        if (user.isPresent()) {
            pedido.setUser_id(user.orElseThrow());
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }

        Optional<Produto> produto = produtoRepository.findById(pedidoRequestDTO.getProduct_id());
        if (produto.isPresent()) {
            pedido.setProduto_id(produto.orElseThrow());
        } else {
            throw new RuntimeException("Produto não encontrado");
        }

        pedido.setQuantity(pedidoRequestDTO.getQuantity());


        if (produto.get().getQntd() >= pedido.getQuantity()) {
            produto.get().setQntd(produto.get().getQntd() - pedido.getQuantity());
            float price;
            price = (produto.orElseThrow().getPreco() * pedido.getQuantity());
            pedido.setPrice(price);
        } else {
            throw new RuntimeException("Quantidade disponivel do produto é menor que a quantidade requisitada no pedido");
        }


        PedidoUser savedPedido = pedidosUserRepository.save(pedido);
        return formatPedidoRequestDTO(savedPedido);
    }

    @Override
    public int checkQnt(Produto produto) {
        Optional<Produto> produtoId = produtoRepository.findById(produto.getProduto_id());
        if(produtoId.isPresent()){
            return produtoId.orElseThrow().getQntd();
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    @Override
    public PedidoResponseDTO formatPedidoRequestDTO(PedidoUser pedido) {
        PedidoResponseDTO responseDTO = new PedidoResponseDTO();
        responseDTO.setId(pedido.getId());
        responseDTO.setPrice(pedido.getPrice());
        responseDTO.setQuantity(pedido.getQuantity());
        responseDTO.setUser_id(pedido.getUser_id().getUser_id());
        responseDTO.setProduct_id(pedido.getProduto_id().getProduto_id());
        return responseDTO;


    }


}

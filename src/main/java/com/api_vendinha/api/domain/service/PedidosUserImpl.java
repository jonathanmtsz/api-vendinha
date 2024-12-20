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

import java.util.List;
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
            pedido.setStatus(Boolean.TRUE);
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
        responseDTO.setId(String.valueOf(pedido.getId()));
        responseDTO.setPrice(pedido.getPrice());
        responseDTO.setQuantity(pedido.getQuantity());
        responseDTO.setUser_id(pedido.getUser_id().getUser_id());
        responseDTO.setProduct_id(pedido.getProduto_id().getProduto_id());
        return responseDTO;


    }

    @Override
    public List<PedidoUser> listAll() {
        return pedidosUserRepository.findAll();
    }

    @Override
    public PedidoResponseDTO inativarPedido(long IdPedidoUser) {
        Optional<PedidoUser> pedido = pedidosUserRepository.findById(IdPedidoUser);
        if (pedido.isPresent()) {
            PedidoUser solvedPedido = pedido.get();
            if(solvedPedido.getStatus().equals(Boolean.FALSE)){
                throw new RuntimeException("Venda já foi inativada");
            } else {
                solvedPedido.setStatus(Boolean.FALSE);
            }

            Optional<Produto> produto = produtoRepository.findById(solvedPedido.getProduto_id().getProduto_id());
            if(produto.isPresent()){
                Produto solvedProduct = produto.get();
                solvedProduct.setQntd(solvedProduct.getQntd() + solvedPedido.getQuantity());
                pedidosUserRepository.save(solvedPedido);
                produtoRepository.save(solvedProduct);
            } else {
                throw new RuntimeException("Produto não encontrado no estoque");
            }
            return formatPedidoRequestDTO(solvedPedido);
        } else {
            throw new RuntimeException("Venda não encontrada no sistema");
        }
    }

    @Override
    public List<PedidoUser> listAllActive() {
        return pedidosUserRepository.findAllActive();
    }


}
